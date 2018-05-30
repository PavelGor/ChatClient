package com.gordeev.chatclient;

import com.gordeev.chatclient.controller.Controller;
import com.gordeev.chatclient.server.ServerHandler;

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

    public void sendMessage(String message) {
        serverHandler.send(message);
    }
}
