package model;


import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;


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

        Cancion cancion = new Cancion(codigo,nombreCancion,nombreAlbum,anio,genero,URl,artista,duracion);
        ListaDoble<Cancion> cancionesArtista = artista.getCancionesArtista();

        if (!cancionesArtista.existe(codigo)){
            cancionesArtista.agregarfinal(cancion);
            return true;

        }else {
            return false;
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
