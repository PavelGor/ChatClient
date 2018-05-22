package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 420, 570));
        primaryStage.show();


        //NewThread
        Thread thread = new Thread(() -> {
            Controller controller = loader.getController();
            try {
                controller.updateChat();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "Thread New");
        thread.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
