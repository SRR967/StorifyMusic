package com.example.storifymusic;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Cancion;
import model.Usuario;






public class UsuarioVistaController {

    HelloApplication aplicacion;
    private Usuario userName;
    private Cancion cancionSeleccionada;
    private ObservableList<Cancion> listaCancionesUsuario;


    public void setUserName(Usuario userName) {
        this.userName = userName;
    }

    public void setAplicacion(HelloApplication aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    private TableView<Cancion> tblCancionesUsuario;

    @FXML
    private TableColumn<Boolean,Cancion> colFavorito;

    @FXML
    private TableColumn<ImageView,Cancion> colImage;

    @FXML
    private TableColumn<String,Cancion> colTitulo;

    @FXML
    private TableColumn<String,Cancion> colArtista;

    @FXML
    private TableColumn<String,Cancion> colAlbum;

    @FXML
    private TableColumn<String,Cancion> colDuracion;

    @FXML
    public void initialize(){

        this.colTitulo.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        this.colAlbum.setCellValueFactory(new PropertyValueFactory<>("nombreAlbum"));
    }

    @FXML
    public void reproducir(ActionEvent actionEvent){

    }





}
