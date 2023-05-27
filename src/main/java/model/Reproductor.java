package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Reproductor implements Serializable {

    private HashMap<String,Usuario> tablaUsuarios = new HashMap<>();
    private HashMap<String,Administrador> tablaAdmin = new HashMap<>();
    private ArbolBinario<Artista> arbolArtista = new ArbolBinario<>();

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
        if ( verificarCodigo(codigo)){
            System.out.println("Codigo repetido");
        }else{
            Cancion cancion = new Cancion(codigo,nombreCancion,nombreAlbum,anio,genero,URl,artista.getNombre(),duracion);
            YoutubeImage youtubeImage= new YoutubeImage(idVideo,nombreCancion);
            if (youtubeImage.instancia()){

                ListaDoble<Cancion> cancionesArtista = artista.getCancionesArtista();
                cancion.setCaratula("imagenes/"+cancion.getNombreCancion()+".png");
                if (!cancionesArtista.existe(codigo)){
                    cancionesArtista.agregarfinal(cancion);
                    return true;

                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
        return false;
    }

    public boolean verificarCodigo(String codigo) {
        return verificarCodigo(arbolArtista.getRaiz(), codigo);
    }

    private boolean verificarCodigo(NodoArbol<Artista> nodo, String codigo) {
        boolean codigoDuplicado = false;

        if (nodo != null) {
            codigoDuplicado = verificarCodigo(nodo.getIzquierdo(), codigo);
            codigoDuplicado = verificarCodigoExistente(nodo.getElemento().getCancionesArtista(),codigo) || codigoDuplicado;
            codigoDuplicado = verificarCodigo(nodo.getDerecho(),codigo) || codigoDuplicado;
        }

        return codigoDuplicado;
    }

    private boolean verificarCodigoExistente(ListaDoble<Cancion> canciones, String codigo) {
        if (canciones.estaVacia()){
            return false;
        }else {
            HashSet<String> codigos = new HashSet<>();
            NodoLista<Cancion> actual = canciones.getNodoPrimero();
            codigos.add(actual.getDato().getCodigo());
            while (actual != null) {
                if (codigos.contains(codigo)) {
                    return true; // Código duplicado encontrado, retorna true
                } else {
                    codigos.add(actual.getDato().getCodigo());
                }
                actual = actual.getSiguiente();
            }

            return false; // No se encontraron códigos duplicados
        }

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

    // Método para buscar un artista por nombre
    public Artista buscarArtista(String nombre,ArbolBinario<Artista> arbol) {
        return buscarArtistaRecursivo(arbol.getRaiz(), nombre);
    }

    private Artista buscarArtistaRecursivo(NodoArbol<Artista> raiz, String nombre) {
        if (raiz == null) {
            return null; // El árbol está vacío o el artista no se encuentra
        }

        if (raiz.getElemento().getNombre().equals(nombre)) {
            return raiz.getElemento(); // Se encontró el artista
        }

        // Realizar la búsqueda en los subárboles izquierdo y derecho de forma recursiva
        Artista artistaEncontrado = buscarArtistaRecursivo(raiz.getIzquierdo(), nombre);
        if (artistaEncontrado == null) {
            artistaEncontrado = buscarArtistaRecursivo(raiz.getDerecho(), nombre);
        }

        return artistaEncontrado; // Retorna el artista encontrado o null si no se encuentra
    }

    public  Artista getArtista(ArbolBinario<Artista> arbol,String nombre) {
        return getArtista(arbol.getRaiz(),nombre);
    }

    private Artista getArtista(NodoArbol<Artista> nodo, String nombre){
        if (nodo == null) {
            return null; // El árbol está vacío o el artista no se encuentra
        }

        if (nodo.getElemento().getNombre().equals(nombre)) {
            return nodo.getElemento(); // Se encontró el artista
        }

        // Realizar la búsqueda en los subárboles izquierdo y derecho de forma recursiva
        Artista artistaEncontrado = buscarArtistaRecursivo(nodo.getIzquierdo(), nombre);
        if (artistaEncontrado == null) {
            artistaEncontrado = buscarArtistaRecursivo(nodo.getDerecho(), nombre);
        }

        return artistaEncontrado; // Retorna el artista encontrado o null si no se encuentra

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
