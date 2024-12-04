package com.example.systemdesignoop2;

import Helper.AlertHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
            // Generate a unique ticket ID
            int ticketID = generateUniqueTicketID();

            // Get the current date and time for the ticket
            LocalDateTime now = LocalDateTime.now(); // Format will be like (2024-12-03T20:36:50)

            // Will have the desired format for displaying the date and time.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a"); // Example: 03-Dec-2024 8:36 PM

            // Formats the current date and time into the specified pattern.
            String purchaseTime = now.format(formatter);


            // Display an alert with the purchased showtime
            showInfoAlert(
                    "Ticket Purchased",
                    null,
                    "You have purchased a ticket for: " + selectedShowtime + "\n" + "\n" +
                    "Ticket ID: " + ticketID + "\n" + "\n"
                    + "Purchase Date/Time: " + "\n" + purchaseTime
            );
        }
    }

    private int generateUniqueTicketID() {
        Set<Integer> existingIDs = loadExistingTicketIDs();
        int newTicketID;
        Random random = new Random();

        // Continue generating new IDs until a unique one is found
        do {
            newTicketID = 1000 + random.nextInt(9000); // Generate a random 4-digit number
        } while (existingIDs.contains(newTicketID)); // Check if the generated ID already exists

        // After finding a unique ID, store it in the file for future reference
        storeTicketID(newTicketID);

        return newTicketID;
    }

    /**
     * Load all existing ticket IDs from the TicketIDs.txt file.
     */
    private Set<Integer> loadExistingTicketIDs() {
        Set<Integer> existingIDs = new HashSet<>();

        // File path to the TicketIDs.txt in the data folder
        File file = new File("data/TicketIDs");
        System.out.println("File exists: " + file.exists());
        System.out.println("Absolute path: " + file.getAbsolutePath());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    existingIDs.add(Integer.parseInt(line.trim())); // Store IDs as integers
                } catch (NumberFormatException e) {
                    // Handle invalid IDs (skip them)
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading ticket IDs from file: " + e.getMessage());
        }

        return existingIDs;
    }

    /**
     * Store the generated ticket ID into a text file in the data folder.
     */
    private void storeTicketID(int ticketID) {
        // File path to the TicketIDs.txt in the data folder
        File file = new File("data/TicketIDs");
        System.out.println("File exists: " + file.exists());
        System.out.println("Absolute path: " + file.getAbsolutePath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(ticketID + "\n"); // Append the new ID to the file
        } catch (IOException e) {
            System.out.println("Error writing ticket ID to file: " + e.getMessage());
        }
    }

}