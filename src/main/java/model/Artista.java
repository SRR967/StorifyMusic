package model;

import java.io.Serializable;

/**
 * Clase Artista que representa un artista musical.
 */
public class Artista implements Comparable<Artista>, Serializable {

    private String nombre;
    private String codigo;
    private String nacionalidad;
    private boolean grupo;
    private ListaDoble<Cancion> cancionesArtista = new ListaDoble<>();

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase Artista.
     *
     * @param nombre       el nombre del artista.
     * @param codigo       el código del artista.
     * @param nacionalidad la nacionalidad del artista.
     * @param isGrupo      indica si el artista es un grupo musical.
     */
    public Artista(String nombre, String codigo, String nacionalidad, boolean isGrupo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nacionalidad = nacionalidad;
        this.grupo = isGrupo;
    }

    /**
     * Constructor vacío de la clase Artista.
     */
    public Artista() {
    }

    /**
     * Devuelve una representación en cadena del objeto Artista.
     *
     * @return una cadena que representa al artista.
     */
    @Override
    public String toString() {
        return " " + nombre + "\n";
    }

    /**
     * Compara el artista con otro artista para determinar su orden.
     *
     * @param o el artista con el que se compara.
     * @return un valor negativo si este artista es menor que el otro, cero si son iguales,
     *         o un valor positivo si este artista es mayor que el otro.
     */
    @Override
    public int compareTo(Artista o) {
        if (o.getNombre().hashCode() > this.getNombre().hashCode()) {
            return 1;
        }
        if (o.getNombre().hashCode() < this.getNombre().hashCode()) {
            return -1;
        }
        return 0;
    }

    /**
     * Obtiene el código del artista.
     *
     * @return el código del artista.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del artista.
     *
     * @param codigo el código del artista.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre del artista.
     *
     * @return el nombre del artista.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del artista.
     *
     * @param nombre el nombre del artista.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la nacionalidad del artista.
     *
     * @return la nacionalidad del artista.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del artista.
     *
     * @param nacionalidad la nacionalidad del artista.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Verifica si el artista es un grupo musical.
     *
     * @return true si el artista es un grupo, false de lo contrario.
     */
    public boolean isGrupo() {
        return grupo;
    }

    /**
     * Establece si el artista es un grupo musical.
     *
     * @param grupo true si el artista es un grupo, false de lo contrario.
     */
    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
    }

    /**
     * Obtiene la lista de canciones del artista.
     *
     * @return la lista de canciones del artista.
     */
    public ListaDoble<Cancion> getCancionesArtista() {
        return cancionesArtista;
    }

    /**
     * Establece la lista de canciones del artista.
     *
     * @param cancionesArtista la lista de canciones del artista.
     */
    public void setCancionesArtista(ListaDoble<Cancion> cancionesArtista) {
        this.cancionesArtista = cancionesArtista;
    }
}
