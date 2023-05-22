package com.example.storifymusic;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Genero;
import model.ListaSimple;

import java.io.IOException;
import java.util.EnumSet;

public class CrearArtistaVistaController {

    private HelloApplication aplicacion;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextField txtNacionalidad;

    @FXML
    private TextField txtCodigo;

    @FXML
    private RadioButton opSolista;

    @FXML
    private RadioButton opGrupo;

    @FXML
    private ToggleGroup tipoArtista;

    public void setAplicacion(HelloApplication aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    public void crearArtista(ActionEvent event) throws IOException {
        String nombre = txtArtista.getText();
        String nacionalidad = txtNacionalidad.getText();
        String codigo = txtCodigo.getText();
        Boolean isGrupo;

        if (opSolista.isSelected()){
            isGrupo= false;
        }else {
            isGrupo = true;
        }

        if ( (!opGrupo.isSelected() && !opSolista.isSelected()) || nombre.equals("") || nacionalidad.equals("") || codigo.equals("") ){
            System.out.println("Llene todos los campos");
        }else {
            aplicacion.crearArtista(nombre, nacionalidad,codigo,isGrupo);
        }
    }

    @FXML
    public void devolver(ActionEvent event) throws IOException {
        aplicacion.devolverInicio();
    }




}
