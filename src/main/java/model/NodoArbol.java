package model;

import java.io.Serializable;

/**
 * Clase que representa un nodo de un árbol binario.
 *
 * @param <T> el tipo de dato almacenado en el nodo, que debe ser comparable.
 */
public class NodoArbol<T extends Comparable<T>> implements Serializable {

    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;
    private T elemento;

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase NodoArbol.
     *
     * @param elemento el dato del nodo.
     */
    public NodoArbol(T elemento) {
        this.elemento = elemento;
    }

    /**
     * Agrega un nuevo elemento al árbol.
     *
     * @param nuevo el nuevo elemento a agregar.
     * @return true si el elemento se agregó correctamente, false en caso contrario.
     */
    public boolean agregar(T nuevo) {
        if (nuevo.compareTo(elemento) < 0) {
            if (izquierdo == null) {
                izquierdo = new NodoArbol<>(nuevo);
                return true;
            } else {
                return izquierdo.agregar(nuevo);
            }
        } else if (nuevo.compareTo(elemento) > 0) {
            if (derecho == null) {
                derecho = new NodoArbol<>(nuevo);
                return true;
            } else {
                return derecho.agregar(nuevo);
            }
        }
        return false;
    }

    /**
     * Verifica si el nodo es una hoja.
     *
     * @return true si el nodo es una hoja, false en caso contrario.
     */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    /**
     * Obtiene el nodo izquierdo.
     *
     * @return el nodo izquierdo.
     */
    public NodoArbol<T> getIzquierdo() {
        return izquierdo;
    }

    /**
     * Establece el nodo izquierdo.
     *
     * @param izquierdo el nodo izquierdo a establecer.
     */
    public void setIzquierdo(NodoArbol<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * Obtiene el nodo derecho.
     *
     * @return el nodo derecho.
     */
    public NodoArbol<T> getDerecho() {
        return derecho;
    }

    /**
     * Establece el nodo derecho.
     *
     * @param derecho el nodo derecho a establecer.
     */
    public void setDerecho(NodoArbol<T> derecho) {
        this.derecho = derecho;
    }

    /**
     * Obtiene el elemento almacenado en el nodo.
     *
     * @return el elemento almacenado en el nodo.
     */
    public T getElemento() {
        return elemento;
    }

    /**
     * Establece el elemento del nodo.
     *
     * @param elemento el elemento a establecer.
     */
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }
}

