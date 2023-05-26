package model;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Cancion implements Serializable {

    private String codigo;
    private String nombreCancion;
    private String nombreAlbum;
    private transient ImageView Caratula;
    private String anio;
    private String duracion;
    private Genero genero;
    private String URL;
    private Artista artista;

    private static final long serialVersionUID = 1L;

    public Cancion(String codigo, String nombreCancion, String nombreAlbum, String anio, Genero genero, String URL,
                   Artista artista,String duracion) {
        this.codigo = codigo;
        this.nombreCancion = nombreCancion;
        this.nombreAlbum = nombreAlbum;
        this.anio = anio;
        this.genero = genero;
        this.URL = URL;
        this.artista = artista;
        this.duracion = duracion;
    }

    public Cancion(String codigo, String nombreCancion, Artista artista) {
        this.codigo = codigo;
        this.nombreCancion = nombreCancion;
        this.artista = artista;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public ImageView getCaratula() {
        return Caratula;
    }

    public void setCaratula(ImageView caratula) {
        Caratula = caratula;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
