package com.example.storifymusic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.io.IOException;
import java.util.function.Consumer;

public class AdminVistaController {

    HelloApplication aplicacion;

    @FXML
    private TableView<Artista> tblArtistas;

    @FXML
    private TableColumn<Artista,String> colCodigo;

    @FXML
    private TableColumn<Artista,String> colNombre;

    @FXML
    private TableColumn<Artista,String> colNacionalidad;

    @FXML
    private TableColumn<Artista,Boolean> colGrupo;

    private ObservableList<Artista> listaArtistas;

    public void setAplicacion(HelloApplication aplicacion) {

        this.aplicacion = aplicacion;
        //tblArtistas.getItems().clear();
        ArbolBinario<Artista> artistas= aplicacion.getArtistas();
        listaArtistas = FXCollections.observableArrayList();
        inOrderTraversal(artistas,artista -> listaArtistas.addAll(artista));
        tblArtistas.setItems(listaArtistas);

    }


    @FXML
    public void initialize(){

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));

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

    @FXML
    void crearArtista(ActionEvent event) {
        aplicacion.showArtista();
    }

    @FXML
    void crearCancion(ActionEvent event) {
        aplicacion.showCancion();

    }


    @FXML
    void devolver (ActionEvent event) throws IOException {

        aplicacion.devolverLogin();

    }
}
