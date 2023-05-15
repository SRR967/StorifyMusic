package model;

import java.io.Serializable;

public class ListaDobleCircular<T> implements Serializable {

    private NodoLista<T> nodoPrimero;
    private NodoLista<T> nodoUltimo;
    private int tamanio;

    private static final long serialVersionUID = 1L;

    public ListaDobleCircular() {
        nodoPrimero = null;
        nodoUltimo = null;
        tamanio = 0;
    }

    /**
     * Agrega un nuevo nodo en la posición dada
     * @param valor
     * @param posicion
     */
    public void insertar( T valor, int posicion ) {
        int cont = 0;

        for( NodoLista<T> aux = nodoPrimero; cont < tamanio; cont++, aux = aux.getSiguiente() ) {
            if( cont == posicion ) {
                NodoLista<T> aux2 = aux.getAnterior();
                NodoLista<T> nuevo = new NodoLista<>( valor );
                aux2.setSiguiente( nuevo );
                nuevo.setSiguiente( aux );
                nuevo.setSiguiente( aux2 );
                aux.setAnterior( nuevo );
                tamanio ++;
            }
        }
    }

    /**
     * Busca y retorna la posición de un nodo que tenga el valor ingresado por parámetro
     * @param valor a buscar
     * @return posición donde se encontró el nodo
     */
    public int buscar( T valor ) {
        int cont = 0;
        int pos = -1;

        for( NodoLista<T> aux = nodoPrimero; cont < tamanio; cont++, aux = aux.getSiguiente() ) {
            if( aux.getDato().equals( valor ) ) {
                pos = cont;
            }
        }
        return pos;
    }

    public void agregarFinal(T valorNodo) {

        NodoLista<T> nuevoNodo = new NodoLista<>( valorNodo );

        if( estaVacia() ) {
            nodoPrimero = nodoUltimo = nuevoNodo;
        }
        else {
            nuevoNodo.setSiguiente(nodoPrimero);
            nodoPrimero.setAnterior(nuevoNodo);
            nodoUltimo.setSiguiente( nuevoNodo );
            nuevoNodo.setAnterior( nodoUltimo );
            nodoPrimero = nuevoNodo;
        }
        tamanio++;
    }

    //Verificar si la lista esta vacia
    public boolean estaVacia() {
        return nodoPrimero == null && nodoUltimo == null;
    }

    public void eliminar(T valor) {
        NodoLista<T> actual = nodoPrimero;

        // Buscar el nodo con el valor dado
        while (actual != null && !actual.getDato().equals(valor)) {
            actual = actual.getSiguiente();
        }

        if (actual != null) {
            NodoLista<T> anterior = actual.getAnterior();
            NodoLista<T> siguiente = actual.getSiguiente();

            if (anterior != null) {
                anterior.setSiguiente(siguiente);
            } else {
                nodoPrimero = siguiente;
            }

            if (siguiente != null) {
                siguiente.setAnterior(anterior);
            } else {
                nodoUltimo = anterior;
            }

            tamanio--;
        }
    }



    /**
     * Imprime en consola la lista enlazada
     */
    public void imprimirLista() {

        NodoLista<T> aux = nodoPrimero;
        int cont = 0;

        while( aux!=null && cont != tamanio ) {
            System.out.print( aux.getDato()+"\t" );
            aux = aux.getSiguiente();
            cont ++;
        }

        System.out.println();
    }

}

