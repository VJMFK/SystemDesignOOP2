package com.example.systemdesignoop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * Controller class for handling user actions and saving form data.
 */
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

    /**
     *for when a new user is added
     *
     */
    @FXML
    protected void onNewUsernameTextFieldClick() {

    }

    /**
     *
     *
     */
    @FXML
    protected void onNewPasswordTextFieldClick() {

    }

    /**
     *
     * @param event
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {
        // Get the username and password values
        String username = newUsernameTextField.getText();
        String password = newPasswordTextField.getText();

        // Check if either of the fields is empty
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No username or password");
            alert.showAndWait();

        }
    }

}
