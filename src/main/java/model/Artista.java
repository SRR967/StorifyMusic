package model;

import java.io.Serializable;

public class Artista implements Comparable<Artista>,Serializable {

    private String nombre;
    private String codigo;
    private String nacionalidad;
    private boolean grupo;
    private ListaDoble<Cancion> cancionesArtista;

    private static final long serialVersionUID = 1L;

    public Artista(String nombre, String codigo,String nacionalidad, boolean isGrupo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nacionalidad = nacionalidad;
        this.grupo = isGrupo;
    }

    public Artista() {
    }

    @Override
    public String toString() {
        return " " + nombre + "\n";
    }
    @Override
    public int compareTo(Artista o) {
        if (o.getNombre().hashCode() > this.getNombre().hashCode()){
            return 1;
        }
        if (o.getNombre().hashCode() < this.getNombre().hashCode()){
            return -1;
        }
        return 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    public boolean isGrupo() {
        return grupo;
    }

    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
    }

    public ListaDoble<Cancion> getCancionesArtista() {
        return cancionesArtista;
    }

    public void setCancionesArtista(ListaDoble<Cancion> cancionesArtista) {
        this.cancionesArtista = cancionesArtista;
    }
}
