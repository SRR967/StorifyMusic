package com.example.storifymusic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Genero;
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

    public void showCrearUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("/com/example/storifymusic/CrearUsuarioVista.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        CrearUsuarioVistaController crearUsuarioVistaController = loader.getController();
        crearUsuarioVistaController.setAplicacion(this);
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showUsuario(String userName) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("/com/example/storifymusic/UsuarioVista.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        UsuarioVistaController usuarioVistaController = loader.getController();
        usuarioVistaController.setUserName(reproductor.getTablaUsuarios().get(userName));
        usuarioVistaController.setAplicacion(this);
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showAdminView() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("/com/example/storifymusic/AdminVista.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        AdminVistaController adminVistaController = loader.getController();
        adminVistaController.setAplicacion(this);
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showCrearArtistaView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("/com/example/storifymusic/CrearArtistaVista.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            CrearArtistaVistaController crearArtistaVistaController = loader.getController();
            crearArtistaVistaController.setAplicacion(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showArtista(){
        showCrearArtistaView();

    }

    public void devolverLogin() throws IOException {
        showLogin();
    }





    public void ingresarAdmin(String userName, String contrasenia) throws IOException {
        boolean verificar = reproductor.ingresarAdmin(userName,contrasenia);
        if (verificar){
            showAdminView();
        }else {
            //Mostrar error
        }

    }

    public void ingresarUsuario(String userName, String contrasenia) throws IOException {
        boolean verificar = reproductor.ingresarUser(userName, contrasenia);
        if (verificar){
            showUsuario(userName);
        }else {
            //Mostrar mensaje
        }
    }

    public void crearUsuario(String nombre, String clave, String correo) throws IOException {
        boolean verify = reproductor.crearUser(nombre, clave, correo);
        if (verify) {
            Persistencia.serializar(reproductor);
            showLogin();
        } else {
            System.out.println("El usuario ya existe");
            //mostrarMensajeError("El usuario no puede ser creado");
        }

    }



    public static void main(String[] args) {
        launch();
    }

    public void crearArtista(String nombre, String nacionalidad, String codigo, Boolean isGrupo) throws IOException {
        boolean verify = reproductor.crearArtista(nombre,nacionalidad,codigo,isGrupo);
        if (verify){
            System.out.println("Se creo el artista");
            Persistencia.serializar(reproductor);
            showAdminView();
        }else {
            System.out.println("Error");
        }
    }
}