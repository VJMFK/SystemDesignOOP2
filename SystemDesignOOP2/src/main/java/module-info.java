module com.example.systemdesignoop2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.systemdesignoop2 to javafx.fxml;
    exports com.example.systemdesignoop2;
}