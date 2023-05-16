package com.example.storifymusic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CrearUsuarioVistaController {

    HelloApplication aplicacion;

    @FXML
    private PasswordField passContra;

    @FXML
    private TextField txtApellCrear;

    @FXML
    private TextField txtNomCrear;

    @FXML
    private TextField txtEmailCrear;

    public void setAplicacion(HelloApplication aplicacion) {
        this.aplicacion = aplicacion;
    }


    @FXML
    public void CrearUsuario(ActionEvent actionEvent) {

    }

    @FXML
    public void Atras(ActionEvent actionEvent) {
    }
}
