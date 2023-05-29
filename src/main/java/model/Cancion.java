package model;

import java.io.Serializable;

/**
 * Clase Cancion que representa una canción.
 */
public class Cancion implements Serializable {

    private String codigo;
    private String nombreCancion;
    private String nombreAlbum;
    private String Caratula;
    private String anio;
    private String duracion;
    private Genero genero;
    private String URL;
    private String artista;

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase Cancion.
     *
     * @param codigo         el código de la canción.
     * @param nombreCancion  el nombre de la canción.
     * @param nombreAlbum    el nombre del álbum al que pertenece la canción.
     * @param anio           el año de lanzamiento de la canción.
     * @param genero         el género de la canción.
     * @param URL            la URL de la canción.
     * @param artista        el artista intérprete de la canción.
     * @param duracion       la duración de la canción.
     */
    public Cancion(String codigo, String nombreCancion, String nombreAlbum, String anio, Genero genero, String URL,
                   String artista, String duracion) {
        this.codigo = codigo;
        this.nombreCancion = nombreCancion;
        this.nombreAlbum = nombreAlbum;
        this.anio = anio;
        this.genero = genero;
        this.URL = URL;
        this.artista = artista;
        this.duracion = duracion;
    }

    /**
     * Constructor de la clase Cancion con parámetros mínimos.
     *
     * @param codigo        el código de la canción.
     * @param nombreCancion el nombre de la canción.
     * @param artista       el artista intérprete de la canción.
     */
    public Cancion(String codigo, String nombreCancion, String artista) {
        this.codigo = codigo;
        this.nombreCancion = nombreCancion;
        this.artista = artista;
    }

    /**
     * Obtiene el código de la canción.
     *
     * @return el código de la canción.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la canción.
     *
     * @param codigo el código de la canción.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre de la canción.
     *
     * @return el nombre de la canción.
     */
    public String getNombreCancion() {
        return nombreCancion;
    }

    /**
     * Establece el nombre de la canción.
     *
     * @param nombreCancion el nombre de la canción.
     */
    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    /**
     * Obtiene el nombre del álbum al que pertenece la canción.
     *
     * @return el nombre del álbum.
     */
    public String getNombreAlbum() {
        return nombreAlbum;
    }

    /**
     * Establece el nombre del álbum al que pertenece la canción.
     *
     * @param nombreAlbum el nombre del álbum.
     */
    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    /**
     * Obtiene la carátula de la canción.
     *
     * @return la carátula de la canción.
     */
    public String getCaratula() {
        return Caratula;
    }

    /**
     * Establece la carátula de la canción.
     *
     * @param caratula la carátula de la canción.
     */
    public void setCaratula(String caratula) {
        Caratula = caratula;
    }

    /**
     * Obtiene el año de lanzamiento de la canción.
     *
     * @return el año de lanzamiento de la canción.
     */
    public String getAnio() {
        return anio;
    }

    /**
     * Establece el año de lanzamiento de la canción.
     *
     * @param anio el año de lanzamiento de la canción.
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * Obtiene el género de la canción.
     *
     * @return el género de la canción.
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Establece el género de la canción.
     *
     * @param genero el género de la canción.
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la URL de la canción.
     *
     * @return la URL de la canción.
     */
    public String getURL() {
        return URL;
    }

    /**
     * Establece la URL de la canción.
     *
     * @param URL la URL de la canción.
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * Obtiene la duración de la canción.
     *
     * @return la duración de la canción.
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración de la canción.
     *
     * @param duracion la duración de la canción.
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene el artista intérprete de la canción.
     *
     * @return el artista intérprete de la canción.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Establece el artista intérprete de la canción.
     *
     * @param artista el artista intérprete de la canción.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }
}

