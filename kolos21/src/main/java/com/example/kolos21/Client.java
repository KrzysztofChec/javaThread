////package com.example.kolos21;
////
////import java.io.BufferedReader;
////import java.io.InputStreamReader;
////import java.io.PrintWriter;
////import java.net.Socket;
////
////public class Client {
////    private static final String SERVER_IP = "localhost";
////    private static final int SERVER_PORT = 1234;
////    static int counter =0;
////    public void connectToServer() {
////        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
////            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
////            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////            System.out.println("licznik to :"+ ++counter);
////            output.println("Hello, server!");
////            String serverResponse = input.readLine();
////            System.out.println("Server responded: " + serverResponse);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////}
//package com.example.kolos21;
//
//import javafx.application.Platform;
//import javafx.scene.canvas.Canvas;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.Socket;
//
//public class Client implements Runnable {
//    private final Socket client;
//    private final Canvas canvas;
//    private final HelloController controller;
//    private String selectedColor;
//
//    public Client(Socket client, Canvas canvas, HelloController helloController) {
//        this.client = client;
//        this.canvas = canvas;
//        this.controller = helloController;
//    }
//    @Override
//    public void run() {
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            String message;
//            while ((message = in.readLine()) != null) {
//                if (message.matches("^#[0-9A-Fa-f]{6}$")) {
//                    // This is a color
//                    this.selectedColor = message;
//                } else if (message.matches("^([-+]?[0-9]*\\.?[0-9]+\\s){3}([-+]?[0-9]*\\.?[0-9]+)$")) {
//                    // These are coordinates
//                    String[] coordinates = message.split("\\s");
//                    double x1 = Double.parseDouble(coordinates[0]);
//                    double y1 = Double.parseDouble(coordinates[1]);
//                    double x2 = Double.parseDouble(coordinates[2]);
//                    double y2 = Double.parseDouble(coordinates[3]);
//
//                    // Create a new line with the selected color and add it to the server
//                    Line line = new Line(x1, y1, x2, y2, this.selectedColor);
//                    Server.addLine(line);
//
//                    // Draw all lines on the canvas
//                    Platform.runLater(() -> controller.drawLines());
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//package com.example.kolos21;
//
//import javafx.application.Platform;
//import javafx.scene.canvas.Canvas;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.Socket;
//
//public class Client implements Runnable {
//    private final Socket client;
//    private final Canvas canvas;
//    private String selectedColor;
//    HelloController controller;
//
//    public Client(Socket client, Canvas canvas,HelloController controller) {
//        this.client = client;
//        this.canvas = canvas;
//        this.controller = controller;
//    }
//
//    @Override
//    public void run() {
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            String message;
//            while ((message = in.readLine()) != null) {
//                if (message.matches("^[0-9A-Fa-f]{6}\\s([-+]?[0-9]*\\.?[0-9]+\\s){3}([-+]?[0-9]*\\.?[0-9]+)$")) {
//                    // Split the message into color and coordinates
//                    String[] parts = message.split("\\s", 2);
//                    this.selectedColor = "#" + parts[0];
//                    String[] coordinates = parts[1].split("\\s");
//
//                    // Process the coordinates
//                    double x1 = Double.parseDouble(coordinates[0]);
//                    double y1 = Double.parseDouble(coordinates[1]);
//                    double x2 = Double.parseDouble(coordinates[2]);
//                    double y2 = Double.parseDouble(coordinates[3]);
//
//                    // Create a new line with the selected color and add it to the server
//                    Line line = new Line(x1, y1, x2, y2, this.selectedColor);
//                    Server.addLine(line);
//
//                    // Draw all lines on the canvas
//                    Platform.runLater(() -> controller.drawLines());
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

package com.example.kolos21;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable {
    private final Socket client;
    private final Canvas canvas;
    private String selectedColor;
    HelloController controller;

    public Client(Socket client, Canvas canvas,HelloController controller) {
        this.client = client;
        this.canvas = canvas;
        this.controller = controller;
    }

//    @Override
//    public void run() {
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            String message;
//            System.out.println("wiadowmoscw kliencie ");
//
//            while ((message = in.readLine()) != null) {
//                System.out.println("wiadomosci to :"+message);
//                if (message.matches("^[0-9A-Fa-f]{6}\\s([-+]?[0-9]*\\.?[0-9]+\\s){3}([-+]?[0-9]*\\.?[0-9]+)$")) {
//                   System.out.println("odebranie wiadomosci ");
//                    // Split the message into color and coordinates
//                    String[] parts = message.split("\\s", 2);
//                    this.selectedColor = "#" + parts[0];
//                    String[] coordinates = parts[1].split("\\s");
//
//                    // Process the coordinates
//                    double x1 = Double.parseDouble(coordinates[0]);
//                    double y1 = Double.parseDouble(coordinates[1]);
//                    double x2 = Double.parseDouble(coordinates[2]);
//                    double y2 = Double.parseDouble(coordinates[3]);
//
//                    // Create a new line with the selected color and add it to the server
//                    Line line = new Line(x1, y1, x2, y2, this.selectedColor);
//                    Server.addLine(line);
//                    System.out.println("przed dewa");
//                    // Draw all lines on the canvas
//                    Platform.runLater(() -> controller.draw());
//                }
//                //Platform.runLater(() -> controller.draw());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
public void run() {
    try {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String message;
        System.out.println("Received messages from client");

        while ((message = in.readLine()) != null) {
            System.out.println("Received message: " + message);
            if (message.matches("^[0-9A-Fa-f]{6}\\|([-+]?[0-9]*\\.?[0-9]+\\s){3}([-+]?[0-9]*\\.?[0-9]+)$")) {
                System.out.println("Valid message format");

                // Split the message into color and coordinates
                String[] parts = message.split("\\|");
                this.selectedColor = "#" + parts[0];
                String[] coordinates = parts[1].split("\\s");

                // Process the coordinates
                double x1 = Double.parseDouble(coordinates[0]);
                double y1 = Double.parseDouble(coordinates[1]);
                double x2 = Double.parseDouble(coordinates[2]);
                double y2 = Double.parseDouble(coordinates[3]);

                // Create a new line with the selected color and add it to the server
                Line line = new Line(x1, y1, x2, y2, this.selectedColor);
                Server.addLine(line);

                // Draw all lines on the canvas
                Platform.runLater(() -> controller.draw());
            }
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
}