package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaDoble <T> implements Iterable<T>, Serializable {

    private NodoLista<T> nodoPrimero;
    private NodoLista<T> nodoUltimo;
    private int tamanio;

    public ListaDoble() {
        nodoPrimero = null;
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

    public ArrayList<T> getAll() {
        ArrayList<T> allItems = new ArrayList<>();
        NodoLista<T> current = nodoPrimero;
        while (current != null) {
            allItems.add(current.getDato());
            current = current.getSiguiente();
        }
        return allItems;
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
        throw new RuntimeException("indice no valido");
    }

    //Verificar si la lista esta vacia
    public boolean estaVacia() {
        return(nodoPrimero == null)?true:false;
    }

    //Imprime en consola la lista enlazada
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
//					nodoUltimo = previo;
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

            tamanio--;
            return valor;
        }

        throw new RuntimeException("Lista vac�a");
    }

    public boolean existe(String string) {
        return buscar(string)!=null;
    }

    private NodoLista<T> buscar(String string){

        NodoLista<T> aux = nodoPrimero;

        while(aux!=null) {
            if(aux.getDato().equals(string)) {
                return aux;
            }
            aux = aux.getSiguiente();
        }

        return null;
    }

    @Override
    public Iterator<T> iterator() {

        return new IteradorListaSimple (nodoPrimero);
    }

    public class IteradorListaSimple implements Iterator<T> {

        private NodoLista<T> nodo;
        private int posicion;

        public IteradorListaSimple(NodoLista<T> nodo) {
            this.nodo = nodo;
            this.posicion = 0;
        }

        @Override
        public boolean hasNext() {
            return nodo != null;
        }

        @Override
        public T next() {
            T valor = nodo.getDato();
            nodo = nodo.getSiguiente();
            posicion++;
            return valor;
        }

        public int getPosicion() {
            return posicion;
        }
    }
}
