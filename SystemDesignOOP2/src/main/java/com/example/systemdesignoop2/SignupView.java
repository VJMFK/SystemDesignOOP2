package com.example.systemdesignoop2;

import Helper.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
     * Method to handle the save button click event.
     * It validates the input and writes the data to a file in the TextFiles directory.
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {
        // Get the username and password values from the TextFields
        String username = newUsernameTextField.getText();
        String password = newPasswordTextField.getText();

        // Check if either of the fields is empty
        if (username.isEmpty() || password.isEmpty()) {
            // Show an alert if the fields are empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No username or password");
            alert.setHeaderText("Both fields must be filled.");
            alert.showAndWait();
            return;
        }

        // Prepare the text to write to the file (username password.)
        String newUserEntry = username + " " + password + ".";

        // Get the path for the client list file inside the TextFiles folder
        File clientListFile = new File("TextFiles/Client List.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(clientListFile, true))) {
            // Append the new user entry to the file
            writer.write(newUserEntry);
            writer.newLine(); // Add a new line after the entry

            // Show a confirmation alert
            AlertHelper.showConfirmationAlert("Success", "New User added",
                    "New user was added successfully.");
        } catch (IOException e) {
            // Show an error alert if something goes wrong while writing to the file
            AlertHelper.showErrorAlert("Error", "File error",
                    "error occurred when adding user. User not added.");
        }
    }
}
