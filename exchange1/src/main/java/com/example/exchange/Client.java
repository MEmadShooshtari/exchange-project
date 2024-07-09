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
//public class Client{
//
//    public static void main(String[] args) {
//        String hostport = "127.0.0.1" ;
//        int port = 9090 ;
//        try{
//            Socket socket = new Socket(hostport , port );
//            OutputStream output = socket.getOutputStream() ;
//            PrintWriter writer = new PrintWriter(output ,true) ;
//
//            InputStream input = socket.getInputStream() ;
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input) ) ;
//
//            BufferedReader consoleReader = new BufferedReader( new InputStreamReader(System.in));
//
//        } catch (IOException e) {
//            System.out.println("Client Exception :" + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
