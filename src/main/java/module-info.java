module com.example.storifymusic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.storifymusic to javafx.fxml;
    exports com.example.storifymusic;
}