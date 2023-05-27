package com.example.storifymusic;

import com.teamdev.jxbrowser.deps.org.checkerframework.checker.units.qual.A;
import com.teamdev.jxbrowser.deps.org.checkerframework.checker.units.qual.C;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AdminVistaController {

    HelloApplication aplicacion;
    private ObservableList<Cancion> listaCanciones= FXCollections.observableArrayList();
    private FilteredList<Cancion> filteredCancionData;
    private String cancionSeleccionada;

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

    @FXML
    private TableView<Cancion> tblCanciones;

    @FXML
    private TableColumn<Artista,String> colCodigoCancion;

    @FXML
    private TableColumn<Cancion,String> colNombreCancion;

    @FXML
    private TableColumn<Cancion,String> colAlbumCancion;

    @FXML
    private TableColumn<Cancion,String> colAnioCancion;

    @FXML
    private TableColumn<Cancion,String> colDuracionCancion;

    @FXML
    private TableColumn<Cancion,String> colGeneroCancion;

    @FXML
    private TableColumn<Cancion,String> colArtistaCancion;



    public void setAplicacion(HelloApplication aplicacion) {

        this.aplicacion = aplicacion;
        //tblArtistas.getItems().clear();
        ArbolBinario<Artista> artistas= aplicacion.getArtistas();
        listaArtistas = FXCollections.observableArrayList();
        inOrderTraversal(artistas,artista -> listaArtistas.addAll(artista));
        tblArtistas.setItems(listaArtistas);


        //llenar tabla canciones de la tienda
        tblCanciones.getItems().clear();
        inOrderTraversal(artistas,artista -> listaCanciones.addAll(artista.getCancionesArtista().getAll()));
        tblCanciones.setItems(listaCanciones);

        // Para filtrar las canciones segun el parametro
        SortedList<Cancion> sortedCancionData = new SortedList<>(filteredCancionData);
        sortedCancionData.comparatorProperty().bind(tblCanciones.comparatorProperty());
        tblCanciones.setItems(sortedCancionData);
    }


    @FXML
    public void initialize(){

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));

        colNombreCancion.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        colArtistaCancion.setCellValueFactory(new PropertyValueFactory<>("artista"));
        colAlbumCancion.setCellValueFactory(new PropertyValueFactory<>("nombreAlbum"));
        colCodigoCancion.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colAnioCancion.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colDuracionCancion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        colGeneroCancion.setCellValueFactory(new PropertyValueFactory<>("genero"));

        tblArtistas.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    cancionSeleccionada = newSelection.getNombre();
                    System.out.println(cancionSeleccionada);

                });

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        filteredCancionData = new FilteredList<>(listaCanciones, p -> true);

        filteredCancionData.setPredicate(cancion -> {
            // If filter text is empty, display all persons.
            if (cancionSeleccionada == null || cancionSeleccionada.isEmpty()) {
                return true;
            }

            boolean nombreMatch = cancion.getArtista().toLowerCase().contains(cancionSeleccionada);
            System.out.println(nombreMatch);
            return nombreMatch;

        });


    }

    @FXML
    public void generoPopular(ActionEvent event){
        // Crear un Map para almacenar el recuento de géneros
        HashMap<String, Integer> recuentoGeneros = new HashMap<>();

        // Recorrer la lista de canciones y realizar el recuento de géneros
        for (Cancion cancion : listaCanciones) {
            String genero = cancion.getGenero().getName();

            // Verificar si el género ya existe en el mapa
            if (recuentoGeneros.containsKey(genero)) {
                // Si existe, incrementar el contador en 1
                int contador = recuentoGeneros.get(genero);
                recuentoGeneros.put(genero, contador + 1);
            } else {
                // Si no existe, agregar el género al mapa con un contador inicial de 1
                recuentoGeneros.put(genero, 1);
            }
        }

        // Imprimir el recuento de géneros
        for (Map.Entry<String, Integer> entry : recuentoGeneros.entrySet()) {
            String genero = entry.getKey();
            int contador = entry.getValue();
            System.out.println("Género: " + genero + ", Cantidad: " + contador);
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

    @FXML
    void crearArtista(ActionEvent event) {
        aplicacion.showArtista();
    }

    @FXML
    void crearCancion(ActionEvent event) {
        aplicacion.showCancion();
    }

    @FXML
    public void administrarUsuarios(ActionEvent event){
        aplicacion.showAdministrarUsuarios();
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
                    String codigo = generarCodigo(nombreCancion,nombreArtista,genero.getName());

                    ArbolBinario<Artista> artistaArbolBinario = aplicacion.getArtistas();
                    if (aplicacion.getExisteNombreArtista(artistaArbolBinario, nombreArtista)) {
                        Artista artista = aplicacion.getArtistaNombre(artistaArbolBinario, nombreArtista);
                        aplicacion.crearCancion(nombreCancion, nombreAlbum, url, artista, codigo, genero, duracion, anio);
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

    private String generarCodigo(String cancion, String artista, String genero){
        StringBuilder codigo = new StringBuilder();

        // Concatenar información de las canciones al código
        codigo.append(cancion.substring(0, 3));
        codigo.append(artista.substring(0, 2));
        codigo.append(genero.substring(0, 2));

        // Verificar si el código ya existe y, en caso afirmativo, agregar un sufijo numérico
        String codigoUnico = codigo.toString();
        int sufijo = 1;
        while (existeCodigo(codigoUnico)) {
            codigoUnico = codigo.toString() + sufijo;
            sufijo++;
        }
        return codigoUnico;
    }

    public boolean existeCodigo(String codigo){
        return aplicacion.getExisteCodigo(codigo);
    }

    @FXML
    void devolver (ActionEvent event) throws IOException {
        aplicacion.devolverLogin();
    }
}
