package chatclient;

import chatclient.controller.Controller;
import chatclient.server.ServerHandler;
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
    private ServerHandler serverHandler;

    public void connect(String ip) {
        serverHandler = new ServerHandler();
        Thread thread = new Thread(serverHandler);

        serverHandler.setIpAddress(ip);
        serverHandler.connect();

        thread.start();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void updateChat(String message) {
        controller.updateChat(message);
    }

    public void sendMessage(String message) throws IOException {
        serverHandler.send(message);
    }
}
