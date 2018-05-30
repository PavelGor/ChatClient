package com.gordeev.chatclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../chatclient.fxml"));
        Parent root = loader.load();

        Handler handler = Handler.getInstance();
        handler.setController(loader.getController());

        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 420, 570));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
