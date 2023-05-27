package com.example.storifymusic;

import com.teamdev.jxbrowser.deps.org.checkerframework.checker.units.qual.A;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    void subirArchivo(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo de texto");

        // Filtro para mostrar solo archivos de texto
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el diálogo y obtener el archivo seleccionado
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            // Procesar el archivo seleccionado
            try {
                readFile(selectedFile);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }

    // Método para leer el contenido de un archivo de texto
    private void readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line=reader.readLine()) != null){
            if (line.startsWith("#Artistas")) {

                while ((line = reader.readLine())!= null && !line.startsWith("#")) {

                    if (line.isEmpty()){
                        continue;
                    }

                    String[] parts = line.split(";");
                    String codigo = parts[0];
                    String nombre = parts[1];
                    String nacionalidad = parts[2];
                    boolean isGrupo = Boolean.parseBoolean(parts[3]);
                    aplicacion.crearArtista(nombre, nacionalidad, codigo, isGrupo);
                }

            }
            if (line.startsWith("#Canciones")){
                while ((line = reader.readLine())!= null && !line.startsWith("#")) {

                    if (line.isEmpty()) {
                        continue; // Saltar línea vacía
                    }

                    String[] parts = line.split(";");
                    String nombreArtista = parts[0];
                    String nombreCancion = parts[1];
                    String nombreAlbum = parts[2];
                    String anio = parts[3];
                    String duracion = parts[4];
                    Genero genero = Genero.valueOf(parts[5]);
                    String url = parts[6];

                    ArbolBinario<Artista> artistaArbolBinario = aplicacion.getArtistas();
                    if (aplicacion.getExisteNombreArtista(artistaArbolBinario, nombreArtista)) {
                        Artista artista = aplicacion.getArtistaNombre(artistaArbolBinario, nombreArtista);
                        aplicacion.crearCancion(nombreCancion, nombreAlbum, url, artista, "0", genero, duracion, anio);
                    } else {
                        System.out.println("NO existe el artista");


                    }
                }
            }else{
                System.out.println("Archivo no legible");
            }
        }
        reader.close();
    }


    @FXML
    void devolver (ActionEvent event) throws IOException {
        aplicacion.devolverLogin();
    }



}
