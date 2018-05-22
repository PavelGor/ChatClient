package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;

public class Controller {
    @FXML
    private TextField nickNameText;

    @FXML
    private TextArea chatText;

    @FXML
    private Button registerButton;

    @FXML
    private Button sendBtn;

    @FXML
    private TextField messageText;

    public void onRegisterClick() throws IOException {
        ServerControl serverControl = ServerControl.getInstance();

        serverControl.send(nickNameText.getText());

        nickNameText.setDisable(true);
        registerButton.setDisable(true);
        sendBtn.setDisable(false);
        messageText.setDisable(false);
    }

    public void updateChat() throws IOException {
        ServerControl serverControl = ServerControl.getInstance();

        while (true) {
            String response = serverControl.getMessage();
            String string = chatText.getText();
            chatText.setText(string + "\n" + response);
        }
    }

    public void onSendClick() throws IOException {
        ServerControl serverControl = ServerControl.getInstance();
        serverControl.send(messageText.getText());
        messageText.setText("");
    }
}
