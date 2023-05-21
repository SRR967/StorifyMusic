package com.example.storifymusic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CrearUsuarioVistaController {

    HelloApplication aplicacion;

    @FXML
    private PasswordField passContra;

    @FXML
    private TextField txtNomCrear;

    @FXML
    private TextField txtEmailCrear;

    public void setAplicacion(HelloApplication aplicacion) {
        this.aplicacion = aplicacion;
    }


    @FXML
    public void CrearUsuario(ActionEvent actionEvent) throws IOException {
        String nombre = txtNomCrear.getText();
        String clave = passContra.getText();
        String correo = txtEmailCrear.getText();

        if (!( nombre.equals("") && correo.equals("") && clave.equals("")  )){
            aplicacion.crearUsuario(nombre,clave,correo);
        }else {
            System.out.println("Hay campos sin completar");
        }

    }

    @FXML
    public void Atras(ActionEvent actionEvent) throws IOException {
        aplicacion.devolverLogin();
    }
}
