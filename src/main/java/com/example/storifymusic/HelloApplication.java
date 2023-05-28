package com.example.storifymusic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import serializacion.Persistencia;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;

public class HelloApplication extends Application {

    private Reproductor reproductor = Reproductor.getInstance();
    private Stage primaryStage;

    private Caretaker caretaker = new Caretaker();

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

    public void showAdministrarUsuarios() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("/com/example/storifymusic/AdminVistaUsuario.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            AdminVistaUsuario administrarUsuarioVistaController = loader.getController();
            administrarUsuarioVistaController.setAplicacion(this);
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

    public void deshacer() throws IOException, ClassNotFoundException {
        caretaker.deshacer(reproductor);
        Persistencia.serializar(reproductor);
    }

    public void rehacer() throws IOException, ClassNotFoundException {
        caretaker.rehacer(reproductor);
        Persistencia.serializar(reproductor);
    }

    public void ingresarAdmin(String userName, String contrasenia) throws IOException {
        boolean verificar = reproductor.ingresarAdmin(userName,contrasenia);
        if (verificar){
            showAdminView();
        }else {
            mostrarMensajeInformacion("Username o contraseña incorrectos");
        }

    }

    public void ingresarUsuario(String userName, String contrasenia) throws IOException {
        boolean verificar = reproductor.ingresarUser(userName, contrasenia);
        if (verificar){
            showUsuario(userName);
        }else {
            mostrarMensajeInformacion("Username o contraseña incorrectos");
        }
    }

    public void crearUsuario(String nombre, String clave, String correo) throws IOException {
        boolean verify = reproductor.crearUser(nombre, clave, correo);
        if (verify) {
            Persistencia.serializar(reproductor);
            showLogin();
        } else {
            mostrarMensajeInformacion("El usuario ya existe");
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

    public void agregarCancionListaUser(Usuario usuario, Cancion cancionSeleccionada) throws IOException, ClassNotFoundException {
        caretaker.guardarEstado(reproductor);
        reproductor.agregarCancionListaUser(usuario,cancionSeleccionada);
        Persistencia.serializar(reproductor);

    }

    public void eliminarCancionUser(Usuario usuario, Cancion cancionSeleccionadaMias) throws IOException, ClassNotFoundException {
        caretaker.guardarEstado(reproductor);
        System.out.println("Almacenado estado reproductor");
        reproductor.eliminarCancionListaUser(usuario, cancionSeleccionadaMias);
        Persistencia.serializar(reproductor);
    }


    public ArbolBinario<Artista> getArtistas(){
        return reproductor.getArbolArtista();
    }

    public HashMap<String, Usuario> getTablaUsuarios() {
        return reproductor.getTablaUsuarios();
    }

    public Usuario reemplazarUsuario(Usuario usuario){
        return reemplazarUsuario(getTablaUsuarios(),usuario);
    }

    private Usuario reemplazarUsuario(HashMap<String,Usuario> tablaUsuarios,Usuario usuario){
        return tablaUsuarios.get(usuario.getUserName());
    }

    public boolean getExisteNombreArtista(ArbolBinario<Artista> artistas,String nombre){
        return reproductor.existeNombreArtista(artistas, nombre);
    }

    public Artista getArtistaNombre(ArbolBinario<Artista> arbol, String nombre){
        return reproductor.getArtista(arbol,nombre);
    }

    public boolean getExisteCodigo(String codigo){
        return reproductor.verificarCodigo(codigo);
    }

    private boolean mostrarMensajeInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}