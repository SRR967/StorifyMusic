package com.example.storifymusic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cancion;
import model.ListaDobleCircular;
import model.NodoLista;
import model.Usuario;

import java.util.*;

public class AdminVistaUsuario {

    HelloApplication aplicacion;
    private ObservableList<Usuario> listaUsuarios= FXCollections.observableArrayList();
    private ObservableList<String> artistasPopulares = FXCollections.observableArrayList();

    @FXML
    private TableView<Usuario> tblUsuarios;

    @FXML
    private TableColumn<Usuario,String> colUsername;

    @FXML
    private TableColumn<Usuario,String> colCorreo;

    @FXML
    private TableColumn<Usuario,String> colContrasenia;

    public void setAplicacion(HelloApplication aplicacion) {
        this.aplicacion = aplicacion;

        HashMap<String,Usuario> mapaUsuarios = aplicacion.getTablaUsuarios();
        // Iterar sobre las entradas del HashMap
        for (Map.Entry<String, Usuario> entry : mapaUsuarios.entrySet()) {
            // Obtener el valor de la entrada
            Usuario valor = entry.getValue();

            // Agregar el valor al ObservableList
            listaUsuarios.add(valor);
        }

        tblUsuarios.setItems(listaUsuarios);
    }

    @FXML
    public void initialize() {
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContrasenia.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));

    }

    @FXML
    public void artistaPopular(ActionEvent event){
        // Crear un HashMap para almacenar el conteo de canciones por artista
        HashMap<String, Integer> conteoArtistas = new HashMap<>();

        // Iterar sobre la lista de usuarios
        for (Usuario usuario : listaUsuarios) {
            // Iterar sobre la lista de canciones del usuario
            ListaDobleCircular<Cancion> canciones = usuario.getListaCanciones();
            NodoLista<Cancion> nodo = canciones.getNodoPrimero();
            do {
                Cancion cancion = nodo.getDato();

                // Obtener el artista de la canción
                String artista = cancion.getArtista();

                // Incrementar el conteo de canciones para ese artista en el HashMap
                conteoArtistas.put(artista, conteoArtistas.getOrDefault(artista, 0) + 1);

                // Mover al siguiente nodo de la lista de canciones
                nodo = nodo.getSiguiente();
            } while (nodo != canciones.getNodoPrimero());
        }

        // Ordenar el HashMap por el conteo de canciones en orden descendente
        List<Map.Entry<String, Integer>> listaArtistas = new ArrayList<>(conteoArtistas.entrySet());
        listaArtistas.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Obtener el nombre de los artistas y su conteo de canciones en el ObservableList
        for (Map.Entry<String, Integer> entry : listaArtistas) {
            String artista = entry.getKey();
            int conteo = entry.getValue();
            artistasPopulares.add(artista + ": " + conteo + " canciones");
        }

        // Imprimir los artistas populares
        for (String artista : artistasPopulares) {
            System.out.println(artista);
        }

    }

}
