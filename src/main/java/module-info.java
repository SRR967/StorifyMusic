module com.example.storifymusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.web;
    requires jdk.jsobject;
    exports model;



    opens com.example.storifymusic to javafx.fxml;
    exports com.example.storifymusic;
}