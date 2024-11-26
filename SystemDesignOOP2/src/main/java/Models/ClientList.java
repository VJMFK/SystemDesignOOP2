package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * The ClientList class is in charge of managing a list of clients by
 * loading them from a file (Client List) and providing methods to interact
 * with the list (Getters, Setters, ReadFiles/InputStreams).
 * <p></p>
 * Extends the AlertHelper class to help with error handling using
 * alerts.
 *
 */
public class ClientList extends AlertHelper {

    /**
     * Path to the client list file.
     */
    private static final String aCLIENT_FILE_NAME = "src/main/java/TextFiles/Client List";

    /**
     * An observable list that will hold the clients.
     */
    private ObservableList<String> aClientList;

    /**
     * Constructs a new ClientList and initializes it.
     */
    public ClientList() {
        aClientList = FXCollections.observableArrayList();
    }

    /**
     * Loads clients from a file (Client List) into the client list.
     * <p>
     * Reads the file line by line, adding each non-empty line to the
     * clientList. If the file is not found or cannot be read, an error
     * alert is displayed.
     * </p>
     *
     * @param pFilePath The path to the client list file.
     */
    public void loadClientsFromFile(String pFilePath) {
        aClientList.clear(); // Clear the list to avoid duplicates

        // Load the resource file from the classpath
        InputStream inputStream = getClass().getResourceAsStream(aCLIENT_FILE_NAME);
        if (inputStream == null) {
            showErrorAlert("File Not Found", null, "Unable to locate the file: " + aCLIENT_FILE_NAME);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) { // Read the file line by line until no lines are available.
                if (!line.trim().isEmpty()) { // Check if the line is not empty or whitespace only.
                    aClientList.add(line.trim()); // Add the trimmed line to the clientList.
                }
            }

            if (aClientList.isEmpty()) {
                showWarningAlert("No Clients Found", null, "The file is empty or contains no valid clients.");
            }

        } catch (Exception e) {
            showErrorAlert("File Read Error", null, "An error occurred while reading the file: " + e.getMessage());
        }
    }

    /**
     * Gets the client list.
     *
     * @return an ObservableList of clients.
     */
    public ObservableList<String> getClientList() {
        return aClientList;
    }

    /**
     * Binds the client list to the provided ListView.
     *
     * @param pListView The ListView to populate with clients.
     */
    public void setClientListToListView(ListView<String> pListView) {
        pListView.setItems(aClientList);
    }
}
