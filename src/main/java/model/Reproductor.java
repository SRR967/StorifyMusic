package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import serializacion.Persistencia;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase representa el reproductor musical.
 */
public class Reproductor implements Serializable {
    private static volatile Reproductor instance;

    private HashMap<String, Usuario> tablaUsuarios = new HashMap<>();
    private HashMap<String, Administrador> tablaAdmin = new HashMap<>();
    private ArbolBinario<Artista> arbolArtista = new ArbolBinario<>();

    private static final long serialVersionUID = 1L;

    private Reproductor() {
        quemarDatosAdmin();
    }

    /**
     * Retorna la instancia única del reproductor utilizando el patrón Singleton.
     *
     * @return la instancia única del reproductor
     */
    public static Reproductor getInstance() {
        if (instance == null) {
            synchronized (Reproductor.class) {
                if (instance == null) {
                    instance = Persistencia.deserializar();
                    if (instance == null) {
                        instance = new Reproductor();
                    }
                }
            }
        }
        return instance;
    }

    private void quemarDatosAdmin() {
        Administrador admin1 = new Administrador("admin", "admin");
        tablaAdmin.put("admin", admin1);
    }

    /**
     * Crea un memento para guardar el estado actual de los usuarios.
     *
     * @return el memento creado
     * @throws IOException            si ocurre un error de E/S durante la serialización
     * @throws ClassNotFoundException si la clase del memento no se encuentra durante la deserialización
     */
    public Memento crearMemento() throws IOException, ClassNotFoundException {
        return new Memento(tablaUsuarios);
    }

    /**
     * Restaura el estado de los usuarios desde un memento.
     *
     * @param memento el memento desde el cual se restaurará el estado
     */
    public void restaurarDesdeMemento(Memento memento) {
        tablaUsuarios = new HashMap<>(memento.getTablaUsuarios());
    }

    /**
     * Crea un nuevo usuario y lo agrega a la tabla de usuarios.
     *
     * @param nombre el nombre del usuario
     * @param clave  la contraseña del usuario
     * @param correo el correo electrónico del usuario
     * @return true si se crea y agrega el usuario correctamente, false si el usuario ya existe
     */
    public boolean crearUser(String nombre, String clave, String correo) {
        Usuario newUser = new Usuario(nombre, clave, correo);

        if (!tablaUsuarios.containsKey(newUser.getUserName())) {
            tablaUsuarios.put(newUser.getUserName(), newUser);
            return true;
        } else {
            System.out.println("Usuario ya existe");
            return false;
        }
    }

    /**
     * Verifica si un usuario puede ingresar al sistema.
     *
     * @param userName    el nombre de usuario
     * @param contrasenia la contraseña
     * @return true si el usuario puede ingresar, false si no
     */
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

    /**
     * Verifica si un administrador puede ingresar al sistema.
     *
     * @param userName    el nombre de usuario
     * @param contrasenia la contraseña
     * @return true si el administrador puede ingresar, false si no
     */
    public boolean ingresarAdmin(String userName, String contrasenia) {
        Administrador admin = tablaAdmin.get(userName);
        if (admin != null && admin.getContrasenia().equals(contrasenia)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Crea un nuevo artista y lo agrega al árbol de artistas.
     *
     * @param nombre       el nombre del artista
     * @param nacionalidad la nacionalidad del artista
     * @param codigo       el código del artista
     * @param duo          true si el artista es un dúo, false si no
     * @return true si se crea y agrega el artista correctamente, false si el artista ya existe
     */
    public boolean crearArtista(String nombre, String nacionalidad, String codigo, boolean duo) {
        Artista art = new Artista(nombre, codigo, nacionalidad, duo);

        if (arbolArtista.estaVacio()) {
            arbolArtista.insertar(art);
            return true;
        } else if (!(arbolArtista.existe(arbolArtista.getRaiz(), art)) && !existeNombreArtista(arbolArtista, nombre)) {
            arbolArtista.insertar(art);
            return true;
        } else {
            //System.out.println("Artista ya existe");
            return false;
        }
    }

    /**
     * Crea una nueva canción y la agrega a la lista de canciones de un artista.
     *
     * @param artista       el artista al que se agregará la canción
     * @param codigo        el código de la canción
     * @param nombreCancion el nombre de la canción
     * @param nombreAlbum   el nombre del álbum de la canción
     * @param anio          el año de lanzamiento de la canción
     * @param genero        el género de la canción
     * @param URl           la URL de la canción
     * @param duracion      la duración de la canción
     * @return true si se crea y agrega la canción correctamente, false si el código está duplicado o la imagen no se puede obtener
     */
    public boolean crearCancion(Artista artista, String codigo, String nombreCancion, String nombreAlbum, String anio,
                                Genero genero, String URl, String duracion) {
        String idVideo = getVideoIdFromLink(URl);
        if (verificarCodigo(codigo)) {
            System.out.println("Codigo repetido");
        } else {
            Cancion cancion = new Cancion(codigo, nombreCancion, nombreAlbum, anio, genero, URl, artista.getNombre(), duracion);
            YoutubeImage youtubeImage = new YoutubeImage(idVideo, nombreCancion);
            if (youtubeImage.instancia()) {
                ListaDoble<Cancion> cancionesArtista = artista.getCancionesArtista();
                cancion.setCaratula("imagenes/" + cancion.getNombreCancion() + ".png");
                if (!cancionesArtista.existe(codigo)) {
                    cancionesArtista.agregarFinal(cancion);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Verifica si un código de canción está duplicado en el árbol de artistas.
     *
     * @param codigo el código a verificar
     * @return true si el código está duplicado, false si no
     */
    public boolean verificarCodigo(String codigo) {
        return verificarCodigo(arbolArtista.getRaiz(), codigo);
    }

    private boolean verificarCodigo(NodoArbol<Artista> nodo, String codigo) {
        boolean codigoDuplicado = false;

        if (nodo != null) {
            codigoDuplicado = verificarCodigo(nodo.getIzquierdo(), codigo);
            codigoDuplicado = verificarCodigoExistente(nodo.getElemento().getCancionesArtista(), codigo) || codigoDuplicado;
            codigoDuplicado = verificarCodigo(nodo.getDerecho(), codigo) || codigoDuplicado;
        }

        return codigoDuplicado;
    }

    private boolean verificarCodigoExistente(ListaDoble<Cancion> canciones, String codigo) {
        if (canciones.estaVacia()) {
            return false;
        } else {
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

    /**
     * Verifica si existe un artista con un determinado nombre en el árbol de artistas.
     *
     * @param arbol  el árbol de artistas
     * @param nombre el nombre del artista a buscar
     * @return true si el artista existe, false si no
     */
    public boolean existeNombreArtista(ArbolBinario<Artista> arbol, String nombre) {
        return existeNombreArtista(arbol.getRaiz(), nombre);
    }

    private boolean existeNombreArtista(NodoArbol<Artista> raiz, String nombre) {
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

    /**
     * Busca un artista por nombre en el árbol de artistas.
     *
     * @param nombre el nombre del artista a buscar
     * @param arbol  el árbol de artistas
     * @return el artista encontrado, o null si no se encuentra
     */
    public Artista buscarArtista(String nombre, ArbolBinario<Artista> arbol) {
        return buscarArtistaRecursivo(arbol.getRaiz(), nombre);
    }

    private Artista buscarArtistaRecursivo(NodoArbol<Artista> raiz, String nombre) {
        if (raiz == null) {
            return null; // El árbol está vacío o el artista no se encuentra
        }

        Artista artista = raiz.getElemento();
        if (artista.getNombre().equals(nombre)) {
            return artista; // Artista encontrado
        }

        Artista izquierda = buscarArtistaRecursivo(raiz.getIzquierdo(), nombre);
        Artista derecha = buscarArtistaRecursivo(raiz.getDerecho(), nombre);

        if (izquierda != null) {
            return izquierda; // Artista encontrado en el subárbol izquierdo
        }

        if (derecha != null) {
            return derecha; // Artista encontrado en el subárbol derecho
        }

        return null; // Artista no encontrado
    }

    /**
     * Obtiene el ID de video de YouTube a partir de una URL de YouTube.
     *
     * @param link la URL de YouTube
     * @return el ID de video de YouTube
     */
    public String getVideoIdFromLink(String link) {
        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(link); // El enlace de youtube debe terminar con
        // Expresión regular para obtener el ID de video
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }


    /**
     * Agrega una canción a la lista de reproducción de un usuario.
     *
     * @param usuario                  el usuario al que se le agregará la canción
     * @param cancionSeleccionadaTodas la canción que se agregará a la lista
     */
    public void agregarCancionListaUser(Usuario usuario, Cancion cancionSeleccionadaTodas) {
        usuario.agregarCancionLista(cancionSeleccionadaTodas);
    }


    /**
     * Elimina una canción de la lista de reproducción de un usuario.
     *
     * @param usuario                 el usuario del que se eliminará la canción
     * @param cancionSeleccionadaMias la canción que se eliminará de la lista
     */
    public void eliminarCancionListaUser(Usuario usuario, Cancion cancionSeleccionadaMias) {
        usuario.eliminarCancionLista(cancionSeleccionadaMias);
    }


    /**
     * Obtiene la tabla de usuarios.
     *
     * @return la tabla de usuarios
     */
    public HashMap<String, Usuario> getTablaUsuarios() {
        return tablaUsuarios;
    }


    /**
     * Obtiene la tabla de administradores.
     *
     * @return la tabla de administradores
     */
    public HashMap<String, Administrador> getTablaAdmin() {
        return tablaAdmin;
    }


    /**
     * Establece la tabla de administradores.
     *
     * @param tablaAdmin la tabla de administradores a establecer
     */
    public void setTablaAdmin(HashMap<String, Administrador> tablaAdmin) {
        this.tablaAdmin = tablaAdmin;
    }

    public ArbolBinario<Artista> getArbolArtista() {
        return arbolArtista;
    }


    /**
     * Muestra un mensaje de información en forma de cuadro de diálogo.
     *
     * @param mensaje el mensaje a mostrar
     * @return true si se hizo clic en el botón "Aceptar", false si se hizo clic en el botón "Cancelar"
     */
    private boolean mostrarMensajeInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true; // Se hizo clic en "Aceptar"
        } else {
            return false; // Se hizo clic en "Cancelar"
        }
    }
}