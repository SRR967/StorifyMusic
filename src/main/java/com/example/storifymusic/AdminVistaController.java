package com.example.storifymusic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdminVistaController {

    HelloApplication aplicacion;

    public void setAplicacion(HelloApplication aplicacion) {
        this.aplicacion = aplicacion;
    }



    @FXML
    void crearArtista(ActionEvent event) {
        aplicacion.showArtista();
    }



    @FXML
    void devolver (ActionEvent event) throws IOException {

        aplicacion.devolverLogin();

    }
}
