package com.example.systemdesignoop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
    protected void onSignUpButtonClick(ActionEvent event) throws IOException {
        // Load the new view FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup-view.fxml"));
        Parent newView = fxmlLoader.load();

        // Create a new stage (window) to display the new scene
        Stage stage = new Stage();
        Scene scene = new Scene(newView, 400, 300);  // You can adjust the size
        stage.setScene(scene);
        stage.setTitle("Sign Up");

        // Show the new stage
        stage.show();
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
        }
        if (managerRadioButton.isSelected()) {


        }
    }
}

