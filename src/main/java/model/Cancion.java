package model;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Cancion implements Serializable {

    private String codigo;
    private String nombreCancion;
    private String nombreAlbum;
    private ImageView Caratula;
    private String anio;
    private Genero genero;
    private String URL;

    private static final long serialVersionUID = 1L;

    public Cancion(String codigo, String nombreCancion, String nombreAlbum, ImageView caratula, String anio, Genero genero, String URL) {
        this.codigo = codigo;
        this.nombreCancion = nombreCancion;
        this.nombreAlbum = nombreAlbum;
        Caratula = caratula;
        this.anio = anio;
        this.genero = genero;
        this.URL = URL;
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
}
