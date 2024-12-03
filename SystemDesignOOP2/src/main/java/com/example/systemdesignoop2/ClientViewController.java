package com.example.systemdesignoop2;

import Helper.AlertHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class manages the loading and displaying of showtimes,
 * as well as user interactions.
 * <p></p>
 * Extends the AlertHelper to display the proper alerts.
 */
public class ClientViewController extends AlertHelper {

    /**
     * ListView for displaying the showtimes.
     */
    @FXML
    private ListView<String> showtimeList;

    /**
     * Button for purchasing a ticket for the chosen showtime.
     */
    @FXML
    private Button purchaseTicketButton;

    /**
     * Label for the naming the section.
     */
    @FXML
    private Label showtimeLabel;

    /**
     * Observable List to store showtimes.
     * <p></p>
     * ObservableList is used to automatically update the ListView when
     * data changes.
     */
    private ObservableList<String> showtimes;

    /**
     * Path to the Showtimes file, located in resources folder.
     */
    private static final String SHOWTIMES_FILE_PATH = "/TextFiles/Showtimes";


    /**
     * Initializes the controller class.
     * Loads showtimes from a file and populates the ListView.
     */
    @FXML
    public void initialize() {
        // Creates a list that will automatically update the ListView.
        showtimes = FXCollections.observableArrayList();

        // Load showtimes from the file.
        loadShowtimesFromFile();

        // Populate the ListView with the showtimes.
        showtimeList.setItems(showtimes);
    }

    /**
     * Loads showtimes from the file specified in SHOWTIMES_FILE_PATH.
     * <p></p>
     * The showtimes are added to the observable list.
     * <p></p>
     * If the file is empty or cannot be read,
     * the proper alert for the situation is displayed.
     */
    private void loadShowtimesFromFile() {
        // Used to read the specified file.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                // the getClass().getResourceAsStream(SHOWTIMES_FILE_PATH) method locates the file in the resources' folder.
                getClass().getResourceAsStream(SHOWTIMES_FILE_PATH)))) {

            String line;
            // Read the file line by line.
            while ((line = reader.readLine()) != null) {
                // Check if the line is not empty or just whitespace, then add it to the showtimes list.
                if (!line.trim().isEmpty()) {
                    showtimes.add(line.trim());
                }
            }

            if (showtimes.isEmpty()) {
                showWarningAlert("No Showtimes Found", null, "The showtimes file is empty or contains no valid data.");
            }
        } catch (Exception e) {
            showErrorAlert("File Read Error", null,
                    "An error occurred while reading the showtimes file:\n\n" + e.getMessage());
        }
    }

    /**
     * Handles the event when the "Purchase Ticket" button is clicked.
     * <p></p>
     * Displays an alert showing the selected showtime.
     * If no showtime is selected, an error alert is displayed.
     */
    @FXML
    protected void onPurchaseTicketButtonClick() {
        // Get the selected showtime from the ListView
        String selectedShowtime = showtimeList.getSelectionModel().getSelectedItem();

        // Check if a showtime is selected
        if (selectedShowtime == null) {
            showErrorAlert("No Showtime Selected", null, "Please select a showtime to purchase a ticket.");
        } else {
            // Display an alert with the purchased showtime
            showInfoAlert("Ticket Purchased", null, "You have purchased a ticket for: " + selectedShowtime);
        }

    }
}