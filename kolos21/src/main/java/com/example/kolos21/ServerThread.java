package com.example.kolos21;////package com.example.kolos21;
////
////import java.io.BufferedReader;
////import java.io.InputStreamReader;
////import java.io.PrintWriter;
////import java.net.Socket;
////
////public class ServerThread extends Thread {
////    private Socket socket;
////
////    public ServerThread(Socket socket) {
////        this.socket = socket;
////    }
////
////    public void run() {
////        try {
////            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
////
////            String line;
////            while ((line = input.readLine()) != null) {
////                System.out.println("Server received: " + line);
////                output.println("Server response: " + line);
////            }
////
////            socket.close();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////}
//package com.example.kolos21;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class ServerThread extends Thread {
//
//    private final Socket clientSocket;
//    private final PrintWriter writer;
//
//    public ServerThread(Socket clientSocket) throws IOException {
//        this.clientSocket = clientSocket;
//        this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
//    }
//
//    @Override
//    public void run() {
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            String message;
//            while ((message = in.readLine()) != null) {
//                System.out.println("Client says: " + message);
//                Server.broadcast(message, this);
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void sendMessage(String message) {
//        writer.println(message);
//    }
//}
//
//
// ServerThread.java

//package com.example.kolos21;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class ServerThread extends Thread {
//
//    private final Socket clientSocket;
//    private final PrintWriter writer;
//    private String currentColor = "#000000"; // Default color is black
//
//    public ServerThread(Socket clientSocket) throws IOException {
//        this.clientSocket = clientSocket;
//        this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
//    }

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//    @Override
//    public void run() {
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            String message;
//            while ((message = in.readLine()) != null) {
//                if (isColor(message)) {
//                    currentColor = message;
//                } else {
//                    String[] coordinates = message.split(" ");
//                    double x1 = Double.parseDouble(coordinates[0]);
//                    double y1 = Double.parseDouble(coordinates[1]);
//                    double x2 = Double.parseDouble(coordinates[2]);
//                    double y2 = Double.parseDouble(coordinates[3]);
//                    Server.addLine(new Line(x1, y1, x2, y2, currentColor));
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void run() {
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            String message;
//            while ((message = in.readLine()) != null) {
//                parseMessage(message);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private boolean isColor(String message) {
//        return message.matches("^#?([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
//    }
//
//    private void parseMessage(String message) {
//        String[] parts = message.split("\\|");
//        if (parts.length != 2) {
//            System.err.println("Invalid message format: " + message);
//            return;
//        }
//
//        if (isColor(parts[0])) {
//            currentColor = parts[0];
//        }
//
//        String[] coordinates = parts[1].split(" ");
//        if (coordinates.length != 4) {
//            throw new IllegalArgumentException("Expected four coordinates");
//        }
//        double x1 = Double.parseDouble(coordinates[0]);
//        double y1 = Double.parseDouble(coordinates[1]);
//        double x2 = Double.parseDouble(coordinates[2]);
//        double y2 = Double.parseDouble(coordinates[3]);
//        Server.addLine(new Line(x1, y1, x2, y2, "#" + currentColor));
//    }
//    public void sendMessage(String message) {
//        writer.println(message);
//    }
//}
// ServerThread.java
public class ServerThread extends Thread {

    private final Socket clientSocket;
    Server server;
    private final PrintWriter writer;
    private String currentColor = "#000000"; // Default color is black

    public ServerThread(Socket clientSocket,Server server) throws IOException {
        this.clientSocket = clientSocket;
        this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
        this.server=server;
    }


    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                parseMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean isColor(String message) {
        return message.matches("^#?([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }

    private void parseMessage(String message) {
        String temp=message;
        String[] parts = message.split("\\|");
        if (parts.length != 2) {
            System.err.println("Invalid message format: " + message);
            return;
        }
        System.out.println("message recived");
        if (isColor(parts[0])) {
            currentColor = parts[0];
        }

        String[] coordinates = parts[1].split(" ");
        if (coordinates.length != 4) {
            throw new IllegalArgumentException("Expected four coordinates");
        }
        double x1 = Double.parseDouble(coordinates[0]);
        double y1 = Double.parseDouble(coordinates[1]);
        double x2 = Double.parseDouble(coordinates[2]);
        double y2 = Double.parseDouble(coordinates[3]);
        Server.addLine(new Line(x1, y1, x2, y2, "#" + currentColor));
      //  sendMessage(temp);
        server.broadcast(temp);
    }
    public void sendMessage(String message) {
        System.out.println("wysylam wiadomosc zwrotna");
        writer.println(message);
    }
}