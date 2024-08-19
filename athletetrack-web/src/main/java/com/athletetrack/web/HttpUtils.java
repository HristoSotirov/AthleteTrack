package com.athletetrack.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

public class HttpUtils {

    public static String readRequest(BufferedReader reader) throws IOException {
        StringBuilder request = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            request.append(line).append("\n");
        }
        return request.toString();
    }

    public static void sendResponse(OutputStream output, String response) throws IOException {
        synchronized (output) {
            output.write(response.getBytes("UTF-8"));
        }
    }
}
