package model;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Reproductor implements Serializable {

    private HashMap<String,Usuario> tablaUsuarios = new HashMap<>();
    private HashMap<String,Administrador> tablaAdmin = new HashMap<>();
    private ArbolBinario<Artista> arbolArtista = new ArbolBinario<>();

    private ListaSimple<Artista> artistaInterfaz= new ListaSimple<>();
    private ListaSimple<Cancion> cancionesArtista = new ListaSimple<>();


    private static final long serialVersionUID = 1L;

    public Reproductor() {
        quemarDatosAdmin();
    }

    private void quemarDatosAdmin() {
        // TODO Auto-generated method stub
        Administrador admin1 = new Administrador("admin", "admin");
        tablaAdmin.put("admin", admin1);


    }

    public Memento crearMemento() throws IOException, ClassNotFoundException {
        return new Memento(tablaUsuarios);
    }

    public void restaurarDesdeMemento(Memento memento) {
        tablaUsuarios = new HashMap<>(memento.getTablaUsuarios());
    }

    public boolean crearUser(String nombre, String clave, String correo) {

        Usuario newUser = new Usuario(nombre,clave,correo);

        if (!tablaUsuarios.containsKey(newUser.getUserName())){

            tablaUsuarios.put(newUser.getUserName(),newUser);
            return true;
        }else {
            System.out.println("Usuario ya existe");
            return false;
        }

    }

    public boolean ingresarUser(String userName, String contrasenia) {

        Usuario user = tablaUsuarios.get(userName);
        if (user != null && user.getContrasenia().equals(contrasenia)) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("False");
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

        Artista art = new Artista(nombre,codigo,nacionalidad,duo);

        if (arbolArtista.estaVacio()) {
            arbolArtista.insertar(art);

            return true;
        } else if (!(arbolArtista.existe(arbolArtista.getRaiz(), art)) && !existeNombreArtista(arbolArtista,nombre) ) {
            arbolArtista.insertar(art);
            System.out.println("Se inserto el artista");

            return true;
        } else {
            System.out.println("Artista ya existe");
            return false;
        }

    }

    public boolean crearCancion(Artista artista,String codigo,String nombreCancion, String nombreAlbum,String anio,
                                Genero genero, String URl, String duracion) {


        String idVideo = getVideoIdFromLink(URl);
        Cancion cancion = new Cancion(codigo,nombreCancion,nombreAlbum,anio,genero,URl,artista.getNombre(),duracion);
        YoutubeImage youtubeImage= new YoutubeImage(idVideo,nombreCancion);
        if (youtubeImage.instancia()){

            ListaDoble<Cancion> cancionesArtista = artista.getCancionesArtista();

            if (!cancionesArtista.existe(codigo)){
                cancionesArtista.agregarfinal(cancion);
                return true;

            }else {
                return false;
            }
        }else {
            return false;
        }


        //asignarImagen(cancion);

    }
    public void asignarImagen(Cancion cancion){
        Image image = new Image("imagenes/"+cancion.getNombreCancion()+".png");
        cancion.setCaratula(new ImageView(image));
    }


    public  boolean existeNombreArtista(ArbolBinario<Artista> arbol,String nombre) {
        return existeNombreArtista(arbol.getRaiz(),nombre);
    }

    private boolean existeNombreArtista(NodoArbol<Artista> raiz, String nombre){
        if (raiz == null) {
            return false;
        }

        Artista artista = raiz.getElemento();
        if (artista.getNombre().equals(nombre)) {
            return true;
        }

        boolean izquierda = existeNombreArtista(raiz.getIzquierdo(), nombre);
        boolean derecha = existeNombreArtista(raiz.getDerecho(), nombre);

        return izquierda || derecha;
    }

    public String getVideoIdFromLink(String videoLink) {
        String videoId = "";

        if (videoLink != null && !videoLink.isEmpty()) {
            String regex = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=)" +
                    "([\\w-]+)(?=(&|\\?|$))";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(videoLink);

            if (matcher.find()) {
                videoId = matcher.group();
            }
        }
        return videoId;
    }

    public boolean verificarEnlaceYouTube(String enlace) {
        // Verificar si el enlace contiene la cadena "youtube.com" o "youtu.be"
        if (enlace.contains("youtube.com") || enlace.contains("youtu.be")) {
            // Verificar si el enlace tiene el formato correcto
            if (enlace.matches("(http(s)?:\\/\\/)?(www\\.)?((youtube\\.com\\/watch\\?v=\\S+)|(youtu\\.be\\/\\S+))")) {
                return true; // El enlace es válido
            }
        }
        return false; // El enlace no es válido
    }


    public void agregarCancionListaUser(Usuario usuario, Cancion cancionSeleccionadaTodas) {
        usuario.agregarCancionLista(cancionSeleccionadaTodas);
    }

    public void eliminarCancionListaUser(Usuario usuario, Cancion cancionSeleccionadaMias) {
        usuario.eliminarCancionLista(cancionSeleccionadaMias);
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
