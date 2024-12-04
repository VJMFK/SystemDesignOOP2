package Helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

/**
 * A helper class to display 4 types of alerts
 * (Information, Warning, Error, Confirmation).
 */
public class AlertHelper {

    /**
     * Displays an information alert.
     *
     * @param title   The title of the alert window.
     * @param header  The header text of the alert.
     * @param content The content text of the alert.
     */
    public static void showInfoAlert(String title, String header, String content) {
        // Creates an information alert with the provided content and an OK button.
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        alert.setTitle(title); // Set the title of the alert window.
        alert.setHeaderText(header); // Set the header text of the alert.

        // Customize the alert size.
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefWidth(300); // Set preferred width.
        dialogPane.setPrefHeight(250); // Set preferred height.

        // Shows the alert and waits for the user to close it.
        alert.showAndWait();
    }

    /**
     * Displays a warning alert.
     *
     * @param title   The title of the alert window.
     * @param header  The header text of the alert.
     * @param content The content text of the alert.
     */
    public static void showWarningAlert(String title, String header, String content) {
        // Creates a warning alert with the provided content and an OK button.
        Alert alert = new Alert(Alert.AlertType.WARNING, content, ButtonType.OK);
        alert.setTitle(title); // Set the title of the alert window.
        alert.setHeaderText(header); // Set the header text of the alert.

        // Customize the alert size.
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefWidth(300); // Set preferred width.
        dialogPane.setPrefHeight(250); // Set preferred height.

        // Shows the alert and waits for the user to close it.
        alert.showAndWait();
    }

    /**
     * Displays an error alert.
     *
     * @param title   The title of the alert window.
     * @param header  The header text of the alert.
     * @param content The content text of the alert.
     */
    public static void showErrorAlert(String title, String header, String content) {
        // Creates an error alert with the provided content and an OK button.
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        alert.setTitle(title); // Set the title of the alert window.
        alert.setHeaderText(header); // Set the header text of the alert.

        // Customize the alert size.
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefWidth(300); // Set preferred width.
        dialogPane.setPrefHeight(250); // Set preferred height.

        // Shows the alert and waits for the user to close it.
        alert.showAndWait();
    }

    /**
     * Displays a confirmation alert.
     *
     * @param title   The title of the alert window.
     * @param header  The header text of the alert.
     * @param content The content text of the alert.
     */
    public static void showConfirmationAlert(String title, String header, String content) {
        // Creates a confirmation alert with the provided content.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title); // Set the title of the alert window.
        alert.setHeaderText(header); // Set the header text of the alert.
        alert.setContentText(content); // Set the content text of the alert.

        // Customize the alert size.
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefWidth(300); // Set preferred width.
        dialogPane.setPrefHeight(250); // Set preferred height.

        // Shows the alert and waits for the user to close it.
        alert.showAndWait();
    }
}
