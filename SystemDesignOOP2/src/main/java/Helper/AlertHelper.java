package Helper;

import javafx.scene.control.Alert;

public class AlertHelper {

    /**
     * Displays an information alert.
     *
     * @param title   The title of the alert window.
     * @param header  The header text of the alert.
     * @param content The content text of the alert.
     */
    public static void showInfoAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
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
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
