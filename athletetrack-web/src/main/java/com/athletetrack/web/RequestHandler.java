package com.athletetrack.web;

import com.athletetrack.router.Router;

import java.io.*;
import java.net.Socket;

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

            // Четене на HTTP заявката от клиента
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            StringBuilder requestLine = new StringBuilder();
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                requestLine.append(line).append("\n");
            }

            // Извлечете метода и пътя от първия ред на заявката
            String[] requestParts = requestLine.toString().split("\n")[0].split(" ");

            if (requestParts.length < 3) {
                // Невалидна заявка (трябва да има поне 3 части: метод, път и версия)
                String errorResponse = "HTTP/1.1 400 Bad Request\r\n\r\n";
                HttpUtils.sendResponse(output, errorResponse);
                return;
            }

            String method = requestParts[0];
            String path = requestParts[1];

            // Обработка на заявката чрез маршрутизатора
            String httpResponse = router.route(path, method);

            // Изпратете отговора обратно на клиента
            HttpUtils.sendResponse(output, httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            // При грешка, можете да изпратите съобщение за грешка на клиента
            try {
                String errorResponse = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
                HttpUtils.sendResponse(clientSocket.getOutputStream(), errorResponse);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            try {
                clientSocket.close(); // Уверете се, че сокетът е затворен
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
