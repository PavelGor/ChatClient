package chatclient.controller;

import chatclient.Handler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.Optional;

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

    private Handler handler = Handler.getInstance();
    private String ipAddress;

    public void onRegisterClick() throws IOException {
        handler.sendMessage(nickNameText.getText());

        nickNameText.setDisable(true);
        registerButton.setDisable(true);
        sendBtn.setDisable(false);
        messageText.setDisable(false);
    }

    public void updateChat(String message) {
        String string = chatText.getText();
        chatText.setText(string + "\n" + message);
    }

    public void onSendClick() throws IOException {
        handler.sendMessage(messageText.getText());
        messageText.setText("");
    }

    public void onClose() {
        System.exit(0);
    }

    public void onSetIPServer() {
        TextInputDialog dialog = new TextInputDialog("127.0.0.1");
        dialog.setTitle("Settings");
        dialog.setHeaderText("Enter IP-address of your Server with dots");
        dialog.setContentText("Please enter server IP-address:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            setIpAddress(result.get());
            System.out.println("You set server IP: " + result.get());
        }
    }

    public void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("developed by Pavel Gordeev");

        alert.showAndWait();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    private void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        //handler.notifyAll();
    }
}
