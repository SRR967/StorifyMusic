package model;

import java.io.Serializable;

/**
 * Esta clase representa un nodo en una lista enlazada.
 *
 * @param <T> el tipo de dato almacenado en el nodo.
 */
public class NodoLista<T> implements Serializable {

    private NodoLista<T> anterior;
    private NodoLista<T> siguiente;
    private T dato;

    private static final long serialVersionUID = 1L;

    /**
     * Crea un nuevo nodo con el dato especificado.
     *
     * @param dato el dato a almacenar en el nodo.
     */
    public NodoLista(T dato) {
        this.dato = dato;
    }

    /**
     * Obtiene el nodo anterior en la lista.
     *
     * @return el nodo anterior.
     */
    public NodoLista<T> getAnterior() {
        return anterior;
    }

    /**
     * Establece el nodo anterior en la lista.
     *
     * @param anterior el nodo anterior a establecer.
     */
    public void setAnterior(NodoLista<T> anterior) {
        this.anterior = anterior;
    }

    /**
     * Obtiene el nodo siguiente en la lista.
     *
     * @return el nodo siguiente.
     */
    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el nodo siguiente en la lista.
     *
     * @param siguiente el nodo siguiente a establecer.
     */
    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return el dato almacenado.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato a almacenar en el nodo.
     *
     * @param dato el dato a establecer.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }
}
