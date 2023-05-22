package com.example.storifymusic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.*;

import java.io.IOException;


public class CrearCancionVistaController {

    HelloApplication aplicacion;
    ObservableList<Artista> observableList;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombreAlbum;

    @FXML
    private TextField txtAnio;

    @FXML
    private TextField txtDuracion;

    @FXML
    private TextField txtUrl;

    @FXML
    private RadioButton opRock;

    @FXML
    private RadioButton opPop;

    @FXML
    private RadioButton opPunk;

    @FXML
    private RadioButton opReggaeton;

    @FXML
    private RadioButton opElectronica;

    @FXML
    private ToggleGroup tipoCancion;

    @FXML
    private ComboBox<Artista> cmbArtista;

    public void setAplicacion(HelloApplication aplicacion) {
        this.aplicacion = aplicacion;
        ArbolBinario<Artista> artistaArbolBinario = aplicacion.getArtistas();
        aplicacion.inOrderTraversal(artistaArbolBinario,artista -> observableList.addAll( artista));
        System.out.println("Datos en el observableList");

    }

    @FXML
    public void initialize() {
        observableList = FXCollections.observableArrayList();
        System.out.println("Datos en el comboBox");
        cmbArtista.setItems(observableList);

    }

    @FXML
    public void crearCancion(ActionEvent event) throws IOException {
        String nombre = txtNombre.getText();
        String album = txtNombreAlbum.getText();
        String URL = txtUrl.getText();
        Artista artista = cmbArtista.getValue();
        String codigo = txtCodigo.getText();
        String duracion = txtDuracion.getText();
        String anio = txtAnio.getText();

        Genero genero;

        if (opRock.isSelected()) {
            genero= Genero.ROCK;
        } else if (opPop.isSelected()) {
            genero = Genero.POP;
        } else if (opPunk.isSelected()) {
            genero = Genero.PUNK;
        } else if (opReggaeton.isSelected()) {
            genero = Genero.REGGAETON;
        } else {
            genero = Genero.ELECTRONICA;
        }

        if ((!opRock.isSelected() && !opPop.isSelected() && !opPunk.isSelected() && !opReggaeton.isSelected() &&
                !opElectronica.isSelected()) || nombre.equals("") || album.equals("") || URL.equals("") ||
                artista == null || codigo.equals("") || duracion.equals("") || anio.equals("")){
            System.out.println("llene todos los campos");
        }else {
            aplicacion.crearCancion(nombre,album,URL,artista,codigo,genero,duracion,anio);
        }

    }

    @FXML
    void devolver(ActionEvent event) throws IOException {
        aplicacion.devolverInicio();

    }
}
