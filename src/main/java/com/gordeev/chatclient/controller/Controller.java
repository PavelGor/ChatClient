package com.gordeev.chatclient.controller;

import com.gordeev.chatclient.Handler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.controlsfx.control.Notifications;

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
    private TextArea messageText;

    private Handler handler = Handler.getInstance();

    public void onRegisterClick() {
        handler.sendMessage(nickNameText.getText());

        nickNameText.setDisable(true);
        registerButton.setDisable(true);
        sendBtn.setDisable(false);
        messageText.setDisable(false);
    }

    public void updateChat(String message) {
        chatText.appendText(message + System.lineSeparator());
        showNotification(message);
    }

    private void showNotification(String message){
        Platform.runLater(
                () -> Notifications.create()
                        .title("My Chat app")
                        .text(message)
                        .darkStyle()
                        .position(Pos.TOP_RIGHT)
                        .show()
        );

    }

    public void onSendButtonClick() {
        String text = messageText.getText();
        onSendEvent(text);
    }

    private void onSendEvent(String text) {
        messageText.setText("");
        messageText.requestFocus();
        handler.sendMessage(text);
    }

    public void onClose() {
        System.exit(0);
    }

    public void onSetIPServer() {
        TextInputDialog dialog = new TextInputDialog("127.0.0.1");
        dialog.setTitle("Settings");
        dialog.setHeaderText("Enter IP-address of your ServerHandler with dots");
        dialog.setContentText("Please enter server IP-address:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> handler.connect(s));
        nickNameText.setDisable(false);
        registerButton.setDisable(false);
    }

    public void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("developed by Pavel Gordeev");

        alert.showAndWait();
    }

    public void onEnterClick() {
        messageText.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String text = messageText.getText();
                text = text.substring(0, text.length() - 1);
                onSendEvent(text);
            }
        });
    }
}
