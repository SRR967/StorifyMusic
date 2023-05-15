package com.example.storifymusic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void ingresar(ActionEvent event) {

        String usuario = txtUsuario.getText();
        String contrasena = txtPassw.getText();
        if (usuario.equals("admin") && contrasena.equals("admin")) {
           // aplicacion.ingresarAdmin(usuario, contrasena);
        } else {
           // aplicacion.ingresarUsuario(usuario, contrasena);
        }

    }

    public void crearUsu(ActionEvent actionEvent) {
    }
}
