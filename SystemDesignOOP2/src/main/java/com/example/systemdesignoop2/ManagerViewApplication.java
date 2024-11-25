package com.example.systemdesignoop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerViewApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Manager View");
        stage.setScene(scene);
        stage.show();
    }
}
