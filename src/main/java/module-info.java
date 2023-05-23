module com.example.storifymusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.web;
    requires jdk.jsobject;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.json.jackson2;
    requires com.google.api.client.json.gson;
    requires com.google.api.services.youtube;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    exports model;



    opens com.example.storifymusic to javafx.fxml;
    exports com.example.storifymusic;
    opens model to javafx.fxml;
}