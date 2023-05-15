package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Optional;

public class Reproductor implements Serializable {

    private HashMap<String,Usuario> tablaUsuarios = new HashMap<>();
    private HashMap<String,Administrador> tablaAdmin = new HashMap<>();

    private ArbolBinario<Artista> arbolArtista = new ArbolBinario<>();

    private ListaSimple<Artista> artistaInterfaz= new ListaSimple<>();
    private ListaSimple<Cancion> cancionesArtista = new ListaSimple<>();


    private static final long serialVersionUID = 1L;

    public boolean crearUser(String nombre, String clave, String correo) {

        Usuario newUser = new Usuario(nombre,clave,correo);

        if (!tablaUsuarios.containsKey(newUser.getUserName())){

            tablaUsuarios.put(newUser.getUserName(),newUser);
            return true;
        }else {

            return false;
        }

    }

    public boolean ingresarUser(String userName, String contrasenia) {

        Usuario user = tablaUsuarios.get(userName);
        if (user != null && user.getContrasenia().equals(contrasenia)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ingresarAdmin(String userName, String contrasenia) {
        // TODO Auto-generated method stub
        Administrador admin = tablaAdmin.get(userName);
        if (admin != null && admin.getContrasenia().equals(contrasenia)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean crearArtista(String nombre, String nacionalidad, String codigo, boolean duo) {

        Artista art = new Artista(codigo,nombre,nacionalidad,duo);

        if (arbolArtista.estaVacio()) {
            arbolArtista.insertar(art);

            return true;
        } else if (!arbolArtista.existe(arbolArtista.getRaiz(), art)) {
            arbolArtista.insertar(art);

            return true;
        } else {

            return false;
        }

    }

    public boolean crearCancion(Artista artista,String codigo,String nombreCancion, String nombreAlbum,String anio, Genero genero, String URl) {

        Cancion cancion = new Cancion(codigo,nombreCancion,nombreAlbum,anio,genero,URl);
        ListaDoble<Cancion> cancionesArtista = artista.getCancionesArtista();

        if (!cancionesArtista.existe(codigo)){
            cancionesArtista.agregarfinal(cancion);
            return true;

        }else {
            return false;
        }

    }





    public HashMap<String, Usuario> getTablaUsuarios() {
        return tablaUsuarios;
    }

    public void setTablaUsuarios(HashMap<String, Usuario> tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }

    public HashMap<String, Administrador> getTablaAdmin() {
        return tablaAdmin;
    }

    public void setTablaAdmin(HashMap<String, Administrador> tablaAdmin) {
        this.tablaAdmin = tablaAdmin;
    }

    public ArbolBinario<Artista> getArbolArtista() {
        return arbolArtista;
    }

    public void setArbolArtista(ArbolBinario<Artista> arbolArtista) {
        this.arbolArtista = arbolArtista;
    }
}
