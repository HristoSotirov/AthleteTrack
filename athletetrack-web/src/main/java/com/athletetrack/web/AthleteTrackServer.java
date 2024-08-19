package com.athletetrack.web;

import com.athletetrack.router.Router;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AthleteTrackServer {

    private static final int PORT = 8080;
    private static final Router router = new Router();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("AthleteTrack Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection accepted from " + clientSocket.getInetAddress());
                new Thread(new RequestHandler(clientSocket, router)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
