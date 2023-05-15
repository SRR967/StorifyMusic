package model;

import java.io.Serializable;

public class ListaSimple<T> implements Serializable {

    private NodoLista<T> nodoPrimero;
    private NodoLista<T> nodoUltimo;
    private int tamanio;

    private static final long serialVersionUID = 1L;

    public ListaSimple() {
        nodoUltimo = null;
        nodoPrimero = null;
        tamanio = 0;
    }
    //Agregar al inicio de la lista
    public void agregarInicio(T valorNodo) {

        NodoLista<T> nuevoNodo = new NodoLista<>(valorNodo);

        if(estaVacia())
        {
            nodoPrimero = nuevoNodo;
        }
        else
        {
            nuevoNodo.setSiguiente(nodoPrimero);
            nodoPrimero = nuevoNodo;
        }
        tamanio++;
    }

    //Agregar al final de la lista
    public void agregarfinal(T valorNodo) {

        NodoLista<T> nodo = new NodoLista<>(valorNodo);

        if( estaVacia() ) {
            nodoPrimero = nodoUltimo = nodo;
        }else {
            nodoUltimo.setSiguiente(nodo);
            nodoUltimo = nodo;
        }

        tamanio++;
    }

    //Obtener Nodo el valor de un Nodo
    public T obtenerValorNodo(int indice) {

        NodoLista<T> nodoTemporal = null;
        int contador = 0;

        if(indiceValido(indice))
        {
            nodoTemporal = nodoPrimero;

            while (contador < indice) {

                nodoTemporal = nodoTemporal.getSiguiente();
                contador++;
            }
        }

        if(nodoTemporal != null)
            return nodoTemporal.getDato();
        else
            return null;
    }


    //Verificar si indice es valido
    private boolean indiceValido(int indice) {
        if( indice>=0 && indice<tamanio ) {
            return true;
        }
        throw new RuntimeException("Índice no válido");
    }


    //Verificar si la lista esta vacia
    public boolean estaVacia() {
        return nodoPrimero == null;
    }


    /**
     * Imprime en consola la lista enlazada
     */
    public void imprimirLista() {

        NodoLista<T> aux = nodoPrimero;

        while(aux!=null) {
            System.out.print( aux.getDato()+"\t" );
            aux = aux.getSiguiente();
        }

        System.out.println();
    }

    //Eliminar dado el valor de un nodo
    public T eliminar(T dato){
        NodoLista<T> nodo = nodoPrimero;
        NodoLista<T> previo = null;
        NodoLista<T> siguiente = null;
        boolean encontrado = false;

        //buscar el nodo previo
        while(nodo!=null) {
            if( nodo.getDato() == dato ) {
                encontrado = true;
                break;
            }
            previo = nodo;
            nodo = nodo.getSiguiente();
        }

        if(encontrado) {
            siguiente = nodo.getSiguiente();
            if( previo==null ) {
                nodoPrimero = siguiente;
            }else {
                previo.setSiguiente(siguiente);
            }

            if(siguiente==null) {
//				nodoUltimo = previo;
            }else {
                nodo.setSiguiente(null);
            }

            nodo = null;
            tamanio--;
            return dato;
        }
        throw new RuntimeException("El elemento no existe");
    }


    //Elimina el primer nodo de la lista
    public T eliminarPrimero() {

        if( !estaVacia() ) {
            NodoLista<T> n = nodoPrimero;
            T valor = n.getDato();
            nodoPrimero = n.getSiguiente();

            if(nodoPrimero==null) {
//				nodoUltimo = null;
            }

            tamanio--;
            return valor;
        }

        throw new RuntimeException("Lista vacía");
    }


    private NodoLista<T> obtenerNodo(int indice) {

        if(indice>=0 && indice<tamanio) {

            NodoLista<T> nodo = nodoPrimero;

            for (int i = 0; i < indice; i++) {
                nodo = nodo.getSiguiente();
            }

            return nodo;
        }

        return null;
    }


    /**
     * Cambia el valor de un nodo dada su posición en la lista
     * @param indice posición donde se va a cambiar el dato
     * @param nuevo nuevo valor por el que se actualizará el nodo
     */
    public void modificarNodo(int indice, T nuevo) {

        if( indiceValido(indice) ) {
            NodoLista<T> nodo = obtenerNodo(indice);
            nodo.setDato(nuevo);
        }

    }


    /**
     * Retorna la primera posición donde está guardado el dato
     * @param dato valor a buscar dentro de la lista
     * @return primera posición del dato
     */
    public int obtenerPosicionNodo(T dato) {

        int i = 0;

        for( NodoLista<T> aux = nodoPrimero ; aux!=null ; aux = aux.getSiguiente() ) {
            if( aux.getDato().equals(dato) ) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public NodoLista<T> getNodoPrimero() {
        return nodoPrimero;
    }

    public void setNodoPrimero(NodoLista<T> nodoPrimero) {
        this.nodoPrimero = nodoPrimero;
    }

    public NodoLista<T> getNodoUltimo() {
        return nodoUltimo;
    }

    public void setNodoUltimo(NodoLista<T> nodoUltimo) {
        this.nodoUltimo = nodoUltimo;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}
