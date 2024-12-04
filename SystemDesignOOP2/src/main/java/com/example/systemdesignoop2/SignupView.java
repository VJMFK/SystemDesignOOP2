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
 * Controller class responsible for handling user actions in the sign-up view.
 * It validates the input and writes the new user's credentials to a file.
 */
public class SignupView {

    /**
     * Button that triggers saving the new user's credentials.
     */
    @FXML
    private Button saveButton;

    /**
     * Text field where the user can input their new username.
     */
    @FXML
    private TextField newUsernameTextField;

    /**
     * Text field where the user can input their new password.
     */
    @FXML
    private TextField newPasswordTextField;

    /**
     * Label for displaying the new username field.
     */
    @FXML
    private Label newUsername;

    /**
     * Label for displaying the new password field.
     */
    @FXML
    private Label newPassword;

    /**
     * Handles the action when the save button is clicked.
     * It validates the input and writes the new user's credentials to the client list file.
     *
     * If either field is empty, an error alert is shown. If the data is valid,
     * the credentials are appended to the client list file, and a success alert is shown.
     *
     * @param event the action event triggered when the save button is clicked
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {
        // Get the username and password values from the text fields
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
        File clientListFile = new File("C:\\Users\\adm1\\OneDrive - Champlain Regional College\\Fall2024\\OOP2\\Project\\SystemDesignOOP2" +
                "\\src\\main\\resources\\TextFiles\\ClientList.txt");

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
                    "Error occurred when adding user. User not added.");
        }
    }
}
