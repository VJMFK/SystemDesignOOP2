package com.example.systemdesignoop2;

import Helper.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

/**
 * Controller class responsible for handling login functionality and client sign-up.
 * It validates the login credentials, including checking them against two client list files, and loads different views based on user roles.
 */
public class LoginView {

    /**
     * Text field to enter the username.
     */
    @FXML
    private TextField usernameTextField;

    /**
     * Text field to enter the password.
     */
    @FXML
    private TextField passwordTextField;

    /**
     * Button for submitting the login form.
     */
    @FXML
    private Button loginButton;

    /**
     * Button to navigate to the sign-up page.
     */
    @FXML
    private Button signUpButton;

    /**
     * Radio button for selecting client user type.
     */
    @FXML
    private RadioButton clientRadioButton;

    /**
     * Radio button for selecting manager user type.
     */
    @FXML
    private RadioButton managerRadioButton;

    /**
     * Label for the username field.
     */
    @FXML
    private Label usernameLabel;

    /**
     * Label for the password field.
     */
    @FXML
    private Label passwordLabel;

    /**
     * Label for the sign-up option.
     */
    @FXML
    private Label signUpLabel;

    /**
     * Initializes the view and its components, including the radio buttons group.
     */
    @FXML
    public void initialize() {
        // Initialize ToggleGroup for radio buttons (this could be done in the FXML file too)
    }

    /**
     * Clears the username text field when clicked.
     *
     * @param event the action event triggered by the click
     */
    @FXML
    protected void onUsernameTextFieldClick(ActionEvent event) {
        usernameTextField.setText("");
    }

    /**
     * Clears the password text field when clicked.
     *
     * @param event the action event triggered by the click
     */
    @FXML
    protected void onPasswordTextFieldClick(ActionEvent event) {
        passwordTextField.setText("");
    }

    /**
     * Opens the sign-up page when the sign-up button is clicked.
     *
     * @param event the action event triggered by the click
     * @throws IOException if the FXML file cannot be loaded
     */
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

    /**
     * Handles the login process, checks the entered credentials, and navigates based on the user type (manager or client).
     * If the credentials are invalid, an error alert is shown.
     *
     * @param event the action event triggered by the click
     */
    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        // Check if the username or password is empty
        if (username.isEmpty() || password.isEmpty()) {
            AlertHelper.showErrorAlert("Error", "Login requirements",
                    "Username and Password are required");
            return; // Exit the method if fields are empty
        }

        // Check if it's a manager login
        if (username.equals("manager") && password.equals("12345")) {
            // Load ManagerView.fxml for the manager
            loadView("ManagerView.fxml", "Manager View");

        } else {
            // Check if it's the hardcoded client login
            if (username.equals("client") && password.equals("67890")) {
                // If client credentials match, load ClientView.fxml for the client
                loadView("client-view.fxml", "Client View");
            } else {
                // Check client credentials from both old and new client list files
                if (checkClientCredentials(username, password)) {
                    // If client credentials match, load ClientView.fxml for the client
                    loadView("client-view.fxml", "Client View");
                } else {
                    // If neither the manager nor the client credentials match, show an error
                    AlertHelper.showErrorAlert("Error", "Misspelling",
                            "Username and password are incorrect");
                }
            }
        }
    }

    /**
     * Checks the provided username and password against the entries in both client list files.
     *
     * @param username the entered username
     * @param password the entered password
     * @return true if the credentials match any entry in the files, false otherwise
     */
    private boolean checkClientCredentials(String username, String password) {
        // Check the old client list file
        if (checkClientInFile("src/main/resources/TextFiles/Client List", username, password)) {
            return true; // If found in the old client list
        }

        // Check the new client list file
        return checkClientInFile("src/main/resources/TextFiles/ClientList.txt", username, password);
    }

    /**
     * Helper method to check credentials in a given file.
     *
     * @param filePath the path to the client list file
     * @param username the entered username
     * @param password the entered password
     * @return true if credentials match, false otherwise
     */
    private boolean checkClientInFile(String filePath, String username, String password) {
        // Read the file line by line using a Scanner
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Remove the period at the end of the line and split by space
                if (line.endsWith(".")) {
                    line = line.substring(0, line.length() - 1); // Remove the period
                }

                String[] credentials = line.split(" "); // Split into username and password
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true; // If the username and password match
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            AlertHelper.showErrorAlert("Error", "File not found",
                    "Error occurred while reading file: " + filePath);
        }
        return false;
    }

    /**
     * Loads a new view based on the provided FXML file and title.
     *
     * @param fxmlFile the FXML file to load
     * @param title    the title of the window
     */
    private void loadView(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Stage currentStage = (Stage) usernameTextField.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.setTitle(title);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            AlertHelper.showErrorAlert("Error", "Failed to load view", "Failed to load the view");
        }
    }
}
