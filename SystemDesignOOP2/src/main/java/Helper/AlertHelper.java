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
     * @param pTitle   The title of the alert window.
     * @param pHeader  The header text of the alert.
     * @param pContent The content text of the alert.
     */
    public static void showInfoAlert(String pTitle, String pHeader, String pContent) {
        // Creates an information alert with the provided content and an OK button.
        Alert aAlert = new Alert(Alert.AlertType.INFORMATION, pContent, ButtonType.OK);
        aAlert.setTitle(pTitle); // Set the title of the alert window.
        aAlert.setHeaderText(pHeader); // Set the header text of the alert.

        // Customize the alert size.
        DialogPane aDialogPane = aAlert.getDialogPane();
        aDialogPane.setPrefWidth(300); // Set preferred width.
        aDialogPane.setPrefHeight(250); // Set preferred height.

        // Shows the alert and waits for the user to close it.
        aAlert.showAndWait();
    }

    /**
     * Displays a warning alert.
     *
     * @param pTitle   The title of the alert window.
     * @param pHeader  The header text of the alert.
     * @param pContent The content text of the alert.
     */
    public static void showWarningAlert(String pTitle, String pHeader, String pContent) {
        // Creates a warning alert with the provided content and an OK button.
        Alert aAlert = new Alert(Alert.AlertType.WARNING, pContent, ButtonType.OK);
        aAlert.setTitle(pTitle); // Set the title of the alert window.
        aAlert.setHeaderText(pHeader); // Set the header text of the alert.

        // Customize the alert size.
        DialogPane aDialogPane = aAlert.getDialogPane();
        aDialogPane.setPrefWidth(300); // Set preferred width.
        aDialogPane.setPrefHeight(250); // Set preferred height.

        // Shows the alert and waits for the user to close it.
        aAlert.showAndWait();
    }

    /**
     * Displays an error alert.
     *
     * @param pTitle   The title of the alert window.
     * @param pHeader  The header text of the alert.
     * @param pContent The content text of the alert.
     */
    public static void showErrorAlert(String pTitle, String pHeader, String pContent) {
        // Creates an error alert with the provided content and an OK button.
        Alert aAlert = new Alert(Alert.AlertType.ERROR, pContent, ButtonType.OK);
        aAlert.setTitle(pTitle); // Set the title of the alert window.
        aAlert.setHeaderText(pHeader); // Set the header text of the alert.

        // Customize the alert size.
        DialogPane aDialogPane = aAlert.getDialogPane();
        aDialogPane.setPrefWidth(300); // Set preferred width.
        aDialogPane.setPrefHeight(250); // Set preferred height.

        // Shows the alert and waits for the user to close it.
        aAlert.showAndWait();
    }

    /**
     * Displays a confirmation alert.
     *
     * @param pTitle   The title of the alert window.
     * @param pHeader  The header text of the alert.
     * @param pContent The content text of the alert.
     */
    public static void showConfirmationAlert(String pTitle, String pHeader, String pContent) {
        // Creates a confirmation alert with the provided content.
        Alert aAlert = new Alert(Alert.AlertType.CONFIRMATION);
        aAlert.setTitle(pTitle); // Set the title of the alert window.
        aAlert.setHeaderText(pHeader); // Set the header text of the alert.
        aAlert.setContentText(pContent); // Set the content text of the alert.

        // Customize the alert size.
        DialogPane aDialogPane = aAlert.getDialogPane();
        aDialogPane.setPrefWidth(300); // Set preferred width.
        aDialogPane.setPrefHeight(250); // Set preferred height.

        // Shows the alert and waits for the user to close it.
        aAlert.showAndWait();
    }
}
