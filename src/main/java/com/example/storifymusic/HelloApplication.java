package com.example.storifymusic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import serializacion.Persistencia;

import java.io.IOException;
import java.util.function.Consumer;

public class HelloApplication extends Application {

    private Reproductor reproductor = new Reproductor();
    //private Reproductor reproductor = Persistencia.deserializar();
    private Stage primaryStage;

    public static void main(String[] args) {
        launch();
    }

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

    public void showCrearCancion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("/com/example/storifymusic/CrearCancionVista.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            CrearCancionVistaController crearCancionVistaController = loader.getController();
            crearCancionVistaController.setAplicacion(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearCancion(String nombre, String album, String uRL, Artista artista, String codigo, Genero genero,
                             String duracion, String anio) throws IOException {
        boolean verify = reproductor.crearCancion(artista,codigo,nombre,album,anio,genero,uRL,duracion);

        if (verify) {
            Persistencia.serializar(reproductor);
            showAdminView();
        } else {
            System.out.println("Error con los datos");
        }

    }


    public void inOrderTraversal(ArbolBinario<Artista> arbol, Consumer<Artista> action) {
        inOrderTraversal(arbol.getRaiz(), action);
    }

    private void inOrderTraversal(NodoArbol<Artista> node, Consumer<Artista> action) {
        if (node != null) {
            inOrderTraversal(node.getIzquierdo(), action);
            action.accept(node.getElemento());
            inOrderTraversal(node.getDerecho(), action);
        }
    }

    public void showArtista(){
        showCrearArtistaView();

    }

    public void showCancion(){
        showCrearCancion();
    }

    public void devolverLogin() throws IOException {
        showLogin();
    }

    public void devolverInicio() throws IOException {
        showAdminView();
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

    public ArbolBinario<Artista> getArtistas(){
        return reproductor.getArbolArtista();
    }


}