package sample;

import java.io.*;
import java.net.Socket;

public class ServerControl {

    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    {
        try {
            Socket socket = new Socket("localhost", 3000);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private ServerControl() {
    }

    private final static ServerControl INSTANCE = new ServerControl();

    public static ServerControl getInstance(){
        return INSTANCE;
    }

    public void send(String text) throws IOException {
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public String getMessage() throws IOException {
        return bufferedReader.readLine();
    }
}
