package com.example.storifymusic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;


public class UsuarioVistaController {

    HelloApplication aplicacion;
    private Usuario userName;
    private Cancion cancionSeleccionada;
    private ObservableList<Cancion> listaCancionesUsuario;

    @FXML
    private TableView<Cancion> tblCancionesUsuario;

    @FXML
    private TableColumn<Boolean,Cancion> colFavorito;

    @FXML
    private TableColumn<ImageView,Cancion> colImage;

    @FXML
    private TableColumn<Cancion,String> colTitulo;

    @FXML
    private TableColumn<String,Cancion> colArtista;

    @FXML
    private TableColumn<String,Cancion> colAlbum;

    @FXML
    private TableColumn<String,Cancion> colDuracion;

    public void setUserName(Usuario userName) {
        this.userName = userName;
    }

    public void setAplicacion(HelloApplication aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    public void initialize(){

        /*
        listaCancionesUsuario = FXCollections.observableArrayList(new Cancion("01","mor"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        tblCancionesUsuario.setItems(listaCancionesUsuario);

         */
        Artista artista1 = new Artista("Feid","01","Colombiano",false);
        Cancion cancion = new Cancion("01","Ferxxo 100", artista1);
        ListaDoble<Cancion> listaDoble= new ListaDoble<>();
        listaDoble.agregarInicio(cancion);
        artista1.setCancionesArtista(listaDoble);

        ArbolBinario <Artista> artistas= new ArbolBinario<>();
        artistas.insertar(artista1);

        listaCancionesUsuario = FXCollections.observableArrayList();
        //ArbolBinario<Artista> artistas= aplicacion.getArtistas();
        inOrderTraversal(artistas,artista -> listaCancionesUsuario.addAll(artista.getCancionesArtista().getAll()));

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        colArtista.setCellValueFactory(new PropertyValueFactory<>("artista"));
        tblCancionesUsuario.setItems(listaCancionesUsuario);
    }


    @FXML
    public void reproducir(ActionEvent actionEvent){
        YoutubePlayer youtubePlayer = new YoutubePlayer();
        Stage stage = new Stage();
        youtubePlayer.start(stage);
    }

    public void inOrderTraversal(ArbolBinario<Artista> arbol,Consumer<Artista> action) {
        inOrderTraversal(arbol.getRaiz(), action);
    }

    private void inOrderTraversal(NodoArbol<Artista> node, Consumer<Artista> action) {
        if (node != null) {
            inOrderTraversal(node.getIzquierdo(), action);
            action.accept(node.getElemento());
            inOrderTraversal(node.getDerecho(), action);
        }
    }





}
