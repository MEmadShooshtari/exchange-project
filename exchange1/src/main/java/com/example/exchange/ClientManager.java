//package com.example.exchange;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.*;
//import java.net.Socket;
//
//public class ClientManager implements Runnable {
//    Socket socket;
//    Server server;
//    InputStream inputStream;
//    OutputStream outputStream;
//
//    BufferedReader reader;
//    PrintWriter writer;
//
//    public ClientManager(Socket client) {
//        socket = client;
//    }
//
//
//    public void run() {
////        try {
////            inputStream = socket.getInputStream();
////            outputStream = socket.getOutputStream();
////            reader = new BufferedReader(new InputStreamReader(inputStream));
////            writer = new PrintWriter(outputStream, true);
////
////        } catch ( IOException e) {
////            System.out.println("Server Exception : " + e.getMessage());
////            e.printStackTrace();
////        }
//    }
//}
//
//
