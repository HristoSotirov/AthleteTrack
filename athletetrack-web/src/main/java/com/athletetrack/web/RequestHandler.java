package com.athletetrack.web;

import com.athletetrack.router.HttpRequest;
import com.athletetrack.router.Router;
import org.json.JSONException;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements Runnable {

    private final Socket clientSocket;
    private final Router router;

    public RequestHandler(Socket clientSocket, Router router) {
        this.clientSocket = clientSocket;
        this.router = router;
    }

    @Override
    public void run() {
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder requestLine = new StringBuilder();
            String line;
            Map<String, String> headers = new HashMap<>();
            String contentLength = "0";

            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                if (line.contains(":")) {
                    String[] headerParts = line.split(":\\s*", 2);
                    if (headerParts.length == 2) {
                        headers.put(headerParts[0], headerParts[1]);
                    }
                } else {
                    requestLine.append(line).append("\n");
                }
            }

            String[] requestParts = requestLine.toString().split("\n")[0].split(" ");
            if (requestParts.length < 3) {
                String errorResponse = "HTTP/1.1 400 Bad Request\r\n\r\n";
                sendResponse(output, errorResponse);
                return;
            }

            String method = requestParts[0];
            String path = requestParts[1];

            StringBuilder requestBody = new StringBuilder();
            if (method.equals("POST") || method.equals("PUT")) {
                int length = 0;
                if (headers.containsKey("Content-Length")) {
                    length = Integer.parseInt(headers.get("Content-Length"));
                }
                char[] bodyBuffer = new char[length];
                reader.read(bodyBuffer, 0, length);
                requestBody.append(bodyBuffer);
            }

            HttpRequest httpRequest = new HttpRequest(method, path, requestBody.toString(), new HashMap<>(), headers);
            String httpResponse = router.route(path, method, requestBody.toString(), headers);

            sendResponse(output, httpResponse);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            try {
                String errorResponse = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
                sendResponse(clientSocket.getOutputStream(), errorResponse);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void sendResponse(OutputStream output, String response) throws IOException {
        output.write(response.getBytes());
        output.flush();
    }
}
