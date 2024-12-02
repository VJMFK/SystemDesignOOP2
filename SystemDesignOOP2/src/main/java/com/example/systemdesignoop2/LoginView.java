package com.example.systemdesignoop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
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

        // Check if the username or password is empty
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username and Password are required!");
            alert.showAndWait();
            return; // Exit the method if fields are empty
        }

        // If both fields are non-empty, proceed to load the manager view
        try {
            // Load the manager view FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/com.example.systemdesignoop2/ManagerView.fxml"));
            StackPane root = loader.load();  // You can replace StackPane with the root element of your manager view
            Scene managerScene = new Scene(root);

            // Set the new scene on the current stage (window)
            Stage currentStage = (Stage) usernameTextField.getScene().getWindow();
            currentStage.setScene(managerScene);
            currentStage.setTitle("Manager View");
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load Manager View!");
            alert.showAndWait();
        }
    }
}


