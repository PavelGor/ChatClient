package com.gordeev.chatclient.server;

import com.gordeev.chatclient.Handler;

import java.io.*;
import java.net.Socket;

public class ServerHandler implements Runnable {

    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String ipAddress;

    private Handler handler = Handler.getInstance();

    public void connect() {
        try {
            Socket socket = new Socket(ipAddress, 3000);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            handler.updateChat("Connected to server: " + ipAddress + ". Now register your NickName");
        } catch (IOException e) {
            handler.updateChat("Cannot connect to server: " + ipAddress);
            e.printStackTrace();
        }
    }

    public void send(String text) throws IOException {
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void run() {
        try {
            String message = bufferedReader.readLine();
            while ((message != null) && (!Thread.interrupted())) {
                handler.updateChat(message);
                message = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
