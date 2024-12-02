package com.example.systemdesignoop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupView {
    @FXML
    private Button saveButton;
    @FXML
    private TextField newUsernameTextField;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private Label newUsername;
    @FXML
    private Label newPassword;

    @FXML
    protected void onNewUsernameTextFieldClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Username");
        alert.showAndWait();
    }
    @FXML
    protected void onNewPasswordTextFieldClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Password");
        alert.showAndWait();
    }
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {

    }

}
