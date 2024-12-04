package com.example.systemdesignoop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the Client Showtimes application.
 * <p></p>
 * This class is responsible for launching the JavaFX application.
 */
public class ClientViewApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientViewApplication.class.getResource("client-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        stage.setTitle("Client Showtimes");
        stage.setScene(scene);
        stage.show();

        System.out.println("Current working directory: " + System.getProperty("user.dir"));

    }

    public static void main(String[] args) {
        launch();
    }
}