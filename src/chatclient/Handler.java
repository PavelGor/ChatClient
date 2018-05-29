package chatclient;

import chatclient.controller.Controller;
import chatclient.server.Server;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Handler {
    private final static Handler INSTANCE = new Handler();

    private Handler() {
    }

    public static Handler getInstance() {
        return INSTANCE;
    }

    private Controller controller;
    private Server server;

    public void handle(FXMLLoader loader)  {
        controller = loader.getController();

        server = new Server();

        //this.wait();
        Thread thread = new Thread(server);

        //server.setIpAddress(controller.getIpAddress());
        server.connect();

        thread.start();
    }

    public void updateChat(String message) {
        controller.updateChat(message);
    }

    public void sendMessage(String message) throws IOException {
        server.send(message);
    }
}
