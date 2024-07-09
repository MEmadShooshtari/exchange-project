package com.example.exchange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class HelloApplication extends Application {
//    Socket socket;
//    Server server;
//    InputStream inputStream;
//    OutputStream outputStream;
//
//    BufferedReader reader;
//    PrintWriter writer;


//    public void getsocket(Socket socket){
//        this.socket = socket;
//    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("exchange!");
        stage.setScene(scene);
        stage.show();
    }

    public static  void  main(String[] args){
        launch();
    }
//    public void run(){
//
//        System.out.println("aaaaa");
 //       launch();
//        try {
//            inputStream = socket.getInputStream();
//            outputStream = socket.getOutputStream();
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//            writer = new PrintWriter(outputStream, true);
//
//        } catch ( IOException e) {
//            System.out.println("Server Exception : " + e.getMessage());
//            e.printStackTrace();
//        }
   }
