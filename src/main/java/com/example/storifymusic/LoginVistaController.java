package com.example.storifymusic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginVistaController {
    HelloApplication aplicacion;

    @FXML
    private PasswordField txtPassw;

    @FXML
    private TextField txtUsuario;

    public void setAplicacion(HelloApplication helloApplication){
        this.aplicacion= helloApplication;
    }

    @FXML
    void ingresar(ActionEvent event) throws IOException {

        String usuario = txtUsuario.getText();
        String contrasena = txtPassw.getText();
        if (usuario.equals("admin") && contrasena.equals("admin")) {
           aplicacion.ingresarAdmin(usuario, contrasena);
        } else {
            aplicacion.ingresarUsuario(usuario, contrasena);
        }

    }

    @FXML
    public void crearUsuario(ActionEvent actionEvent) throws IOException {
        aplicacion.showCrearUsuario();

    }
}
