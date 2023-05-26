package com.example.storifymusic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.function.Consumer;


public class UsuarioVistaController {

    HelloApplication aplicacion;
    private Usuario userName;
    private Cancion cancionSeleccionadaTodas;
    private Cancion cancionSeleccionadaMias;
    private ObservableList<Cancion> listaCancionesArtistas;
    private ObservableList<Cancion> listaCancionesUsuario;

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


    public void setUserName(Usuario userName) {
        this.userName = userName;
    }

    public void setAplicacion(HelloApplication aplicacion) {

        this.aplicacion = aplicacion;
        lblUsuario.setText(userName.getUserName());

        ArbolBinario<Artista> artistas= aplicacion.getArtistas();
        listaCancionesArtistas = FXCollections.observableArrayList();
        inOrderTraversal(artistas,artista -> listaCancionesArtistas.addAll(artista.getCancionesArtista().getAll()));
        tblCancionesTodas.setItems(listaCancionesArtistas);

        ListaDobleCircular<Cancion> cancionesUsuario = userName.getListaCanciones();
        listaCancionesUsuario = FXCollections.observableArrayList();
        agregarCancionesMias(cancionesUsuario);
        tblCancionesUsuario.setItems(listaCancionesUsuario);

    }

    @FXML
    public void initialize(){

        /*
        listaCancionesUsuario = FXCollections.observableArrayList(new Cancion("01","mor"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        tblCancionesUsuario.setItems(listaCancionesUsuario);


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


         */


        colTituloTodas.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        colArtistaTodas.setCellValueFactory(new PropertyValueFactory<>("artista"));
        tblCancionesTodas.setItems(listaCancionesArtistas);

        //Tabla Usuario
        colTituloUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
        colArtistaTodas.setCellValueFactory(new PropertyValueFactory<>("artista"));
        tblCancionesUsuario.setItems(listaCancionesUsuario);

        tblCancionesTodas.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    cancionSeleccionadaTodas = newSelection;
                });

        tblCancionesUsuario.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    cancionSeleccionadaMias = newSelection;
                });
    }


    @FXML
    public void reproducir(ActionEvent actionEvent){

        // Initialize Chromium.
        EngineOptions options = EngineOptions.newBuilder(HARDWARE_ACCELERATED)
                .licenseKey("1BNDHFSC1G6ACMC4FPYDA9JCGE2ON6O8O1TLU39NUKF2TT6JPNM2U3U13827LFGQ5LROE8")
                .build();
        Engine engine = Engine.newInstance(options);

        // Create a Browser instance.
        Browser browser = engine.newBrowser();

        // Load the required web page.
        browser.navigation().loadUrl(cancionSeleccion.getUrlYoutube());

        // Create and embed JavaFX BrowserView component to display web content.
        BrowserView view = BrowserView.newInstance(browser);

        Scene scene = new Scene(new BorderPane(view), 600, 406);
        Stage primaryStage = new Stage();
        primaryStage.setTitle(cancionSeleccion.getNombre());
        primaryStage.setScene(scene);
        primaryStage.show();

        // Shutd<own Chromium and release allocated resources.
        primaryStage.setOnCloseRequest(event1 -> engine.close());



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
        actualizarTablaMiLista();
    }

    @FXML
    public void rehacer(ActionEvent event) throws IOException, ClassNotFoundException {
        aplicacion.rehacer();
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
