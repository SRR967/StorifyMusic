package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.Serializable;
import java.util.Optional;

public class Usuario implements Serializable {

    private String userName;
    private String contrasenia;
    private String email;
    private ListaDobleCircular<Cancion> listaCanciones;

    private static final long serialVersionUID = 1L;

    public Usuario(String userName, String contrasenia, String email) {
        this.userName = userName;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public void agregarCancionLista(Cancion cancion){
        int posicion = listaCanciones.buscar(cancion);
        if (posicion !=-1){
            mostrarMensajeError("Está cancion ya se encuentra en la lista");
        }else {
            listaCanciones.agregarFinal(cancion);
            mostrarMensajeError("Se agregó la cancion");

        }

    }

    public void eliminarCancionLista(Cancion cancion){
        listaCanciones.eliminar(cancion);
        mostrarMensajeInformacion("La cancion fue eliminada");

    }

    private boolean mostrarMensajeInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private boolean mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Confirmacion");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ListaDobleCircular<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(ListaDobleCircular<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }
}
