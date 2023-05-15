package model;

import java.io.Serializable;

public class NodoArbol<T extends Comparable<T>> implements Serializable {

    private NodoArbol<T> izquierdo, derecho;
    private T elemento;

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase
     * @param elemento Dato del nodo
     */
    public NodoArbol(T elemento) {
        this.elemento = elemento;
    }


    public boolean agregar(T nuevo) {
        if( nuevo.compareTo( elemento ) < 0 ) {
            if(izquierdo==null) {
                izquierdo = new NodoArbol<>(nuevo);
                return true;
            }else {
                return izquierdo.agregar(nuevo);
            }
        }else if( nuevo.compareTo( elemento ) > 0 ) {
            if(derecho==null) {
                derecho = new NodoArbol<>(nuevo);
                return true;
            }else {
                return derecho.agregar(nuevo);
            }
        }
        return false;
    }
    /**
     * Dice cuando un nodo es una hoja o no
     * @return true si el nodo es hoja
     */
    public boolean esHoja() {

        return izquierdo==null && derecho==null;
    }


    public NodoArbol<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol<T> derecho) {
        this.derecho = derecho;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }
}
