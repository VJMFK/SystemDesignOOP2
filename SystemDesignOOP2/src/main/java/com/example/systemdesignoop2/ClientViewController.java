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
    private ListView<String> aShowtimeList;

    /**
     * Button for purchasing a ticket for the chosen showtime.
     */
    @FXML
    private Button aPurchaseTicketButton;

    /**
     * Label for the naming the section.
     */
    @FXML
    private Label aShowtimeLabel;

    /**
     * Observable List to store showtimes.
     * <p></p>
     * ObservableList is used to automatically update the ListView when
     * data changes.
     */
    private ObservableList<String> aShowtimes;

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
        aShowtimes = FXCollections.observableArrayList();

        // Load showtimes from the file.
        loadShowtimesFromFile();

        // Populate the ListView with the showtimes.
        aShowtimeList.setItems(aShowtimes);
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
        try (BufferedReader aReader = new BufferedReader(new InputStreamReader(
                // the getClass().getResourceAsStream(SHOWTIMES_FILE_PATH) method locates the file in the resources' folder.
                getClass().getResourceAsStream(SHOWTIMES_FILE_PATH)))) {

            String aLine; // Variable to hold each line read from the file.
            // Read the file line by line.
            while ((aLine = aReader.readLine()) != null) {
                // Check if the line is not empty or just whitespace, then add it to the showtimes list.
                if (!aLine.trim().isEmpty()) {
                    aShowtimes.add(aLine.trim());
                }
            }

            // If no showtimes were loaded, display a warning alert.
            if (aShowtimes.isEmpty()) {
                showWarningAlert("No Showtimes Found", null, "The showtimes file is empty or contains no valid data.");
            }
        } catch (Exception e) {
            // Display an error alert if there was an issue reading the file.
            showErrorAlert("File Read Error", null,
                    "An error occurred while reading the showtimes file:\n\n" + e.getMessage());
        }
    }

    /**
     * Handles the event when the "Purchase Ticket" button is clicked.
     * <p></p>
     * If a showtime is selected, a unique ticket ID is generated, and
     * displays an alert showing the ticket details.
     * If no showtime is selected, it displays an error alert.
     */
    @FXML
    protected void onPurchaseTicketButtonClick() {
        // Get the selected showtime from the ListView
        String pSelectedShowtime = aShowtimeList.getSelectionModel().getSelectedItem();

        // Check if a showtime is selected
        if (pSelectedShowtime == null) {
            showErrorAlert("No Showtime Selected", null, "Please select a showtime to purchase a ticket.");
        } else {
            // Generate a unique ticket ID
            int aTicketID = generateUniqueTicketID();

            // Get the current date and time for the ticket
            LocalDateTime aNow = LocalDateTime.now(); // Format will be like (2024-12-03T20:36:50)

            // Will have the desired format for displaying the date and time.
            DateTimeFormatter aFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a"); // Example: 03-Dec-2024 8:36 PM

            // Formats the current date and time into the specified pattern.
            String aPurchaseTime = aNow.format(aFormatter);


            // Display an alert with the purchased showtime
            showInfoAlert(
                    "Ticket Purchased",
                    null,
                    "You have purchased a ticket for: " + pSelectedShowtime + "\n" + "\n" +
                    "Ticket ID: " + aTicketID + "\n" + "\n"
                    + "Purchase Date/Time: " + "\n" + aPurchaseTime
            );
        }
    }

    /**
     * Generates a unique 4-digit ticket ID.
     * <p></p>
     * This method ensures that the generated ticket ID does not already exist
     * within the TicketIDs file.
     *
     * @return A unique 4-digit ticket ID (Int)
     */
    private int generateUniqueTicketID() {
        // Load all existing ticket IDs from the file.
        // Set<Integer> stores all the previously generated ticket IDs, ensuring that a new number cannot be any of the previously stored ones.
        Set<Integer> aExistingIDs = loadExistingTicketIDs();

        int aNewTicketID; // Variable to store the newly generated ticket ID.
        Random aRandom = new Random();

        // Continue generating new IDs until a unique one is found.
        do {
            // Generate a random 4-digit number between 1000-9999 (Because original range was 0-8999, 1000 shifts range up by 1000)
            aNewTicketID = 1000 + aRandom.nextInt(9000);
        } while (aExistingIDs.contains(aNewTicketID)); // Check if the generated ID already exists

        // After finding a unique ID, store it in the file for future reference
        storeTicketID(aNewTicketID);

        return aNewTicketID;
    }

    /**
     * Loads all existing ticket IDs from the TicketIDs file into a set.
     * <p>
     * Valid ticket IDs are added to a set to ensure they are unique.
     * Any invalid entries in the file (Ex. non-numeric values) are skipped.
     * </p>
     *
     * @return A set of unique ticket IDs already store in the file.
     */
    private Set<Integer> loadExistingTicketIDs() {
        // Create a HashSet to store unique ticket IDs.
        // HashSet ensures no duplicate IDs are added by adding unique hash code for the element.
        // HashSet will check if the hash code for an element already exists. If it exists it won't be added.
        // Won't be ordered, but an order is not needed in this situation.
        Set<Integer> aExistingIDs = new HashSet<>();

        // File path to the TicketIDs in the data folder
        File aFile = new File("SystemDesignOOP2/SystemDesignOOP2/data/TicketIDs");

        try (BufferedReader aReader = new BufferedReader(new FileReader(aFile))) {
            String aLine; // Variable to hold each line read from the file.

            // Reads the file line by line until end of file.
            while ((aLine = aReader.readLine()) != null) {
                try {
                    // Parse each line as an integer and add it to the set.
                    aExistingIDs.add(Integer.parseInt(aLine.trim()));
                } catch (NumberFormatException e) {
                    // Handle invalid IDs (skip them)
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading ticket IDs from file: " + e.getMessage());
        }

        return aExistingIDs;
    }

    /**
     * Stores a new ticket ID in the TicketIDs file.
     * <p></p>
     * Appends (Add new data to end of file without overwriting existing content)
     * the new ticket ID to the file for future use.
     *
     * @param pTicketID the ticket ID to be stored.
     */
    private void storeTicketID(int pTicketID) {
        // File path to the TicketIDs in the data folder
        File aFile = new File("SystemDesignOOP2/SystemDesignOOP2/data/TicketIDs");

        try (BufferedWriter aWriter = new BufferedWriter(new FileWriter(aFile, true))) {
            aWriter.write(pTicketID + "\n"); // Append the new ID to the file
        } catch (IOException e) {
            System.out.println("Error writing ticket ID to file: " + e.getMessage());
        }
    }
}