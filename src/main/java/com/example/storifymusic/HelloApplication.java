package com.example.storifymusic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Reproductor;
import serializacion.Persistencia;

import java.io.IOException;

public class HelloApplication extends Application {

    private Reproductor reproductor = Persistencia.deserializar();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Storify Music");
        this.primaryStage.setResizable(false);
        showLogin();
    }

    private void showLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("/com/example/storifymusic/LoginVista.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        LoginVistaController loginVistaController = loader.getController();
        loginVistaController.setAplicacion(this);
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}