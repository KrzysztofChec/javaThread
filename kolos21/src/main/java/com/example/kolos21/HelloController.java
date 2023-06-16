//package com.example.kolos21;
//
//import javafx.fxml.FXML;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class HelloController {
//    @FXML
//    private TextField colorTextField;
//
//    @FXML
//    private TextField coordinatesTextField;
//
//    @FXML
//    private Canvas canvas;
//
//    private Socket clientsocket;
//    public void draw() {
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
//
//        for (Line line : Server.getLines()) { // Get lines from server
//            gc.setStroke(Color.web(line.getColor()));
//            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
//        }
//    }
//
//    @FXML
//    public void createServerAndConnect(ActionEvent actionEvent) throws IOException {
//        final Server server = new Server(5000);
//        server.startServer();
//    }
//
//    public void connect(ActionEvent actionEvent) throws IOException {
//        Socket socket = new Socket("localhost", 5000);
//        this.clientsocket = socket;
//        System.out.println("connect from client");
//        new Thread(new Client(socket, canvas,this)).start();
//    }
//
//    public void drawLines() {
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
//
//        for (Line line : Server.getLines()) { // Get lines from server
//            gc.setStroke(Color.web(line.getColor()));
//            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
//        }
//    }
//
//    public void sendMessage(ActionEvent actionEvent) {
//        String color = colorTextField.getText();
//        String coordinates = coordinatesTextField.getText();
//
//        // Consolidate color and coordinates into one message
//        String message = color + "|" + coordinates;
//
//        // Send the message
//        sendToServer(message);
//    }
//
//    private void sendToServer(String message) {
//        try {
//            if (clientsocket != null) {
//                PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
//                out.println(message);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
// HelloController.java
package com.example.kolos21;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HelloController {
    @FXML
    private TextField colorTextField;

    @FXML
    private TextField coordinatesTextField;

    @FXML
    private Canvas canvas;

    private Socket clientsocket;

    @FXML
    public void createServerAndConnect(ActionEvent actionEvent) throws IOException {
        final Server server = new Server(5000);
        server.startServer();
    }

    public void connect(ActionEvent actionEvent) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        this.clientsocket = socket;
        System.out.println("connect from client");
        new Thread(new Client(socket, canvas,this)).start();
    }
    public void initialize() {

        canvas.setWidth(500);
        canvas.setHeight(500);
    }
    public void drawLine(ActionEvent actionEvent){
        draw();
    }

//    public void draw() {
//       // System.out.println("tu jest kurwa dorwa");
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
//
//        for (Line line : Server.getLines()) { // Get lines from server
//            gc.setStroke(Color.web(line.getColor()));
//            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
//        }
//    }
public void draw() {
    Platform.runLater(() -> {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas

        for (Line line : Server.getLines()) { // Get lines from server
            gc.setStroke(Color.web(line.getColor()));
            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
    });
}

    public void sendMessage(ActionEvent actionEvent) {
        String color = colorTextField.getText();
        String coordinates = coordinatesTextField.getText();

        // Consolidate color and coordinates into one message
        String message = color + "|" + coordinates;

        // Send the message
        sendToServer(message);
    }

    private void sendToServer(String message) {
        try {
            if (clientsocket != null) {
                PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
                out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
