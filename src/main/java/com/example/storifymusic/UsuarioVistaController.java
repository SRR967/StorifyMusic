package com.example.storifymusic;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.function.Consumer;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;


public class UsuarioVistaController {

    HelloApplication aplicacion;
    private Usuario userName;
    private Cancion cancionSeleccionadaTodas;
    private Cancion cancionSeleccionadaMias;
    private ObservableList<Cancion> listaCancionesArtistas= FXCollections.observableArrayList();
    private ObservableList<Cancion> listaCancionesUsuario= FXCollections.observableArrayList();
    private FilteredList<Cancion> filteredCancionData;

    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<Cancion> tblCancionesTodas;

    @FXML
    private TableView<Cancion> tblCancionesUsuario;

    @FXML
    private TableColumn<Boolean,Cancion> colFavorito;


    @FXML
    private TableColumn<Cancion,String> colTituloTodas;

    @FXML
    private TableColumn<Cancion,String> colTituloUsuario;

    @FXML
    private TableColumn<String,Cancion> colArtistaTodas;

    @FXML
    private TableColumn<String,Cancion> colArtistaUsuario;

    @FXML
    private TableColumn<String,Cancion> colAlbumTodas;

    @FXML
    private TableColumn<String,Cancion> colAlbumUsuario;

    @FXML
    private TableColumn<String,Cancion> colDuracionTodas;

    @FXML
    private TableColumn<String,Cancion> colDuracionUsuario;

    @FXML
    private TextField txtBuscarCancion;


    public void setUserName(Usuario userName) {
        this.userName = userName;
    }

    public void setAplicacion(HelloApplication aplicacion) {

        this.aplicacion = aplicacion;
        lblUsuario.setText(userName.getUserName());

        //llenar tabla canciones de la tienda
        tblCancionesTodas.getItems().clear();
        ArbolBinario<Artista> artistas= aplicacion.getArtistas();
        inOrderTraversal(artistas,artista -> listaCancionesArtistas.addAll(artista.getCancionesArtista().getAll()));
        tblCancionesTodas.setItems(listaCancionesArtistas);

        // Para filtrar las canciones segun el parametro
        SortedList<Cancion> sortedCancionData = new SortedList<>(filteredCancionData);
        sortedCancionData.comparatorProperty().bind(tblCancionesTodas.comparatorProperty());
        tblCancionesTodas.setItems(sortedCancionData);

        //llenar tabla canciones usuario
        tblCancionesUsuario.getItems().clear();
        ListaDobleCircular<Cancion> cancionesUsuario = userName.getListaCanciones();
        agregarCancionesMias(cancionesUsuario);
        tblCancionesUsuario.setItems(listaCancionesUsuario);

    }

    @FXML
    public void initialize(){

        colTituloTodas.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        colArtistaTodas.setCellValueFactory(new PropertyValueFactory<>("artista"));
        colAlbumTodas.setCellValueFactory(new PropertyValueFactory<>("nombreAlbum"));
        tblCancionesTodas.setItems(listaCancionesArtistas);

        tblCancionesTodas.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    cancionSeleccionadaTodas = newSelection;
                });

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        filteredCancionData = new FilteredList<>(listaCancionesArtistas, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtBuscarCancion.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Texto ingresado: " + newValue);
            filteredCancionData.setPredicate(cancion -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (cancion.getNombreCancion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cancion.getArtista().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (cancion.getGenero().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (cancion.getNombreAlbum().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false; // Does not match.
            });
        });


        //Tabla Usuario
        colTituloUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        colArtistaTodas.setCellValueFactory(new PropertyValueFactory<>("artista"));
        colAlbumUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreAlbum"));
        tblCancionesUsuario.setItems(listaCancionesUsuario);

        tblCancionesUsuario.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    cancionSeleccionadaMias = newSelection;
                });
    }


    @FXML
    public void reproducir(ActionEvent actionEvent){

        if (cancionSeleccionadaTodas != null){
            reproducirVideo(cancionSeleccionadaTodas);
        }else if (cancionSeleccionadaMias != null){
            reproducirVideo(cancionSeleccionadaMias);
        }else {
            System.out.println("No se selecciono ningun video");
        }




        /*
        if (cancionSeleccionadaTodas!= null){
            YoutubePlayer youtubePlayer = new YoutubePlayer(cancionSeleccionadaTodas.getURL());
            Stage stage = new Stage();
            youtubePlayer.start(stage);
        }else if (cancionSeleccionadaMias!= null){
            YoutubePlayer youtubePlayer = new YoutubePlayer(cancionSeleccionadaMias.getURL());
            Stage stage = new Stage();
            youtubePlayer.start(stage);
        }else {
            System.out.println("No hay cancion seleccionada");
        }

         */

    }

    private void reproducirVideo(Cancion cancion){
        // Initialize Chromium.
        EngineOptions options = EngineOptions.newBuilder(HARDWARE_ACCELERATED)
                .licenseKey("1BNDHFSC1G6ACMC4FPYDA9JCGE2ON6O8O1TLU39NUKF2TT6JPNM2U3U13827LFGQ5LROE8")
                .build();
        Engine engine = Engine.newInstance(options);

        // Create a Browser instance.
        Browser browser = engine.newBrowser();

        // Load the required web page.
        browser.navigation().loadUrl(cancion.getURL());

        // Create and embed JavaFX BrowserView component to display web content.
        BrowserView view = BrowserView.newInstance(browser);

        Scene scene = new Scene(new BorderPane(view), 600, 406);
        Stage primaryStage = new Stage();
        primaryStage.setTitle(cancion.getNombreCancion());
        primaryStage.setScene(scene);
        primaryStage.show();

        // Shutd<own Chromium and release allocated resources.
        primaryStage.setOnCloseRequest(event1 -> engine.close());
    }

    @FXML
    public void agregarCancionUsuario(ActionEvent event) throws IOException, ClassNotFoundException {
        if (cancionSeleccionadaTodas != null) {
            aplicacion.agregarCancionListaUser(userName, cancionSeleccionadaTodas);
            actualizarTablaMiLista();
        } else {
            System.out.println("Ninguna cancion ha sido seleccionada");
        }
    }

    @FXML
    public void eliminarCancionUsuario(ActionEvent event) throws IOException, ClassNotFoundException {
        if (cancionSeleccionadaMias != null) {
            aplicacion.eliminarCancionUser(userName, cancionSeleccionadaMias);
            actualizarTablaMiLista();
        } else {
            System.out.println("Ninguna cancion ha sido seleccionada");
        }
    }

    @FXML
    public void deshacer(ActionEvent event) throws IOException, ClassNotFoundException {
        aplicacion.deshacer();
        userName= aplicacion.reemplazarUsuario(userName);
        actualizarTablaMiLista();
    }

    @FXML
    public void rehacer(ActionEvent event) throws IOException, ClassNotFoundException {
        aplicacion.rehacer();
        userName= aplicacion.reemplazarUsuario(userName);
        actualizarTablaMiLista();
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

    public void agregarCancionesMias(ListaDobleCircular<Cancion> listaCanciones){
        listaCancionesUsuario.clear();
        NodoLista<Cancion> currentNode = listaCanciones.getNodoPrimero();

        if (currentNode !=null){

            do {
                listaCancionesUsuario.add(currentNode.getDato());
                currentNode = currentNode.getSiguiente();
            }while (currentNode != listaCanciones.getNodoPrimero());
        }else {
            System.out.println("NO hay canciones del usuario");
        }
    }

    private void actualizarTablaMiLista() {
        tblCancionesUsuario.getItems().clear();
        agregarCancionesMias(userName.getListaCanciones());
        tblCancionesUsuario.setItems(listaCancionesUsuario);
        tblCancionesUsuario.refresh();
    }






}
