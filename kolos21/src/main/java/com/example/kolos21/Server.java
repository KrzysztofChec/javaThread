//package com.example.kolos21;
//
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class Server {
//    private static final int PORT = 1234;
//
//    public void start() {
//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            System.out.println("Server is listening on port " + PORT);
//
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("New client connected");
//                ServerThread serverThread = new ServerThread(clientSocket);
//                serverThread.start();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//package com.example.kolos21;
//
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Server {
//    private static final int PORT = 1234;
//    private List<ServerThread> clients = new ArrayList<>();
//
//    public void start() {
//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            System.out.println("Server is listening on port " + PORT);
//
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("New client connected");
//                ServerThread serverThread = new ServerThread(clientSocket, this);
//                serverThread.start();
//                clients.add(serverThread);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void broadcastMessage(String message) {
//        for (ServerThread client : clients) {
//            client.sendMessage(message);
//        }
//    }
//}
//package com.example.kolos21;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Server {
//
//    private static final List<ServerThread> clients = new ArrayList<>();
//    private static final List<Line> lines = new ArrayList<>();
//    final ServerSocket socket;
//    public static List<Line> getLines() {
//        return lines;
//    }
//    public static void addLine(Line line) {
//        lines.add(line);
//
//    }
//
//    public Server(int port) throws IOException {
//        this.socket = new ServerSocket(port);
//    }
//
//    public static void broadcast(String message, ServerThread sender) {
//        for (ServerThread client : clients) {
//            if (client != sender) {
//                client.sendMessage("BCAST;" + message);
//            }
//        }
//    }
//
//    public void listen() throws IOException {
//        System.out.println("Server is running");
//        while (true) {
//            final Socket joinedClient = socket.accept();
//            System.out.println("New client join: " + joinedClient);
//            for (ServerThread client : clients) {
//                System.out.println("z fora");
//                System.out.println(client.getName());
//
//            }
//            final ServerThread clientInstanceOnServer = new ServerThread(joinedClient);
//            clients.add(clientInstanceOnServer);
//            clientInstanceOnServer.start();
//        }
//    }
//
//    public void startServer() {
//        new Thread(() -> {
//            try {
//                listen();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();
//    }
//    }

package com.example.kolos21;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void broadcast(String message) {
        for (ServerThread client : clients) {
            //if (client != sender) {
                System.out.println("wysylam wiadomosc do clienta");
                client.sendMessage(message);
           // }
        }
    }

    private static final List<ServerThread> clients = new ArrayList<>();
    private static final List<Line> lines = new ArrayList<>();
    final ServerSocket socket;
    public static List<Line> getLines() {
        return lines;
    }
    public static void addLine(Line line) {
        lines.add(line);

    }

    public Server(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }


    public void listen() throws IOException {
        System.out.println("Server is running");
        while (true) {
            final Socket joinedClient = socket.accept();
            System.out.println("New client join: " + joinedClient);
            for (ServerThread client : clients) {
                System.out.println("z fora");
                System.out.println(client.getName());

            }
            final ServerThread clientInstanceOnServer = new ServerThread(joinedClient,this);
            clients.add(clientInstanceOnServer);
            clientInstanceOnServer.start();
        }
    }

    public void startServer() {
        new Thread(() -> {
            try {
                listen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}