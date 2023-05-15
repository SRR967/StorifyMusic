package model;

import java.io.Serializable;

public class NodoLista <T> implements Serializable {

    private NodoLista<T> anterior;
    private NodoLista<T> siguiente;
    private T dato;

    private static final long serialVersionUID = 1L;

    public NodoLista(T dato) {
        this.dato = dato;
    }

    public NodoLista<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoLista<T> anterior) {
        this.anterior = anterior;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }
}
