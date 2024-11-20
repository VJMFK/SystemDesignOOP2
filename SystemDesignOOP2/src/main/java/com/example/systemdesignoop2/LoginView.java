package com.example.systemdesignoop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginView {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private RadioButton clientRadioButton;
    @FXML
    private RadioButton managerRadioButton;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label signUpLabel;

    @FXML
    public void initialize() {
        // Initialize ToggleGroup for radio buttons
        // Declare ToggleGroup
        ToggleGroup userToggleGroup = new ToggleGroup();
        clientRadioButton.setToggleGroup(userToggleGroup);
        managerRadioButton.setToggleGroup(userToggleGroup);
    }

    @FXML
    public void onClientRadioButtonClick(ActionEvent event) {
        // No need to manually select; ToggleGroup will handle it
    }

    @FXML
    public void onManagerRadioButtonClick(ActionEvent event) {
        // No need to manually select; ToggleGroup will handle it
    }

    @FXML
    protected void onUsernameTextFieldClick(ActionEvent event) {
        usernameTextField.setText("");
    }

    @FXML
    protected void onPasswordTextFieldClick(ActionEvent event) {
        passwordTextField.setText("");
    }

    @FXML
    protected void onSignUpButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sign Up");
        alert.setHeaderText("Do you want to create a new account?");
        alert.showAndWait();
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username and Password are required!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Welcome, " + username + "!");
            alert.showAndWait();
        }
    }
}
