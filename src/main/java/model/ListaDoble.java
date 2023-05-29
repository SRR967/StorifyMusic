package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * La clase ListaDoble representa una lista doblemente enlazada genérica en Java.
 *
 * @param <T> el tipo de elementos que se almacenarán en la lista.
 */
public class ListaDoble<T> implements Iterable<T>, Serializable {

    private NodoLista<T> nodoPrimero;
    private NodoLista<T> nodoUltimo;
    private int tamanio;

    /**
     * Crea una nueva instancia de ListaDoble.
     */
    public ListaDoble() {
        nodoPrimero = null;
        nodoUltimo = null;
        tamanio = 0;
    }

    /**
     * Agrega un nuevo elemento al inicio de la lista.
     *
     * @param valorNodo el valor del nuevo nodo a agregar.
     */
    public void agregarInicio(T valorNodo) {
        NodoLista<T> nuevoNodo = new NodoLista<>(valorNodo);

        if (estaVacia()) {
            nodoPrimero = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(nodoPrimero);
            nodoPrimero = nuevoNodo;
        }
        tamanio++;
    }

    /**
     * Agrega un nuevo elemento al final de la lista.
     *
     * @param valorNodo el valor del nuevo nodo a agregar.
     */
    public void agregarFinal(T valorNodo) {
        NodoLista<T> nodo = new NodoLista<>(valorNodo);

        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nodo;
        } else {
            nodoUltimo.setSiguiente(nodo);
            nodoUltimo = nodo;
        }

        tamanio++;
    }

    /**
     * Obtiene todos los elementos de la lista en un ArrayList.
     *
     * @return ArrayList con todos los elementos de la lista.
     */
    public ArrayList<T> getAll() {
        ArrayList<T> allItems = new ArrayList<>();
        NodoLista<T> current = nodoPrimero;
        while (current != null) {
            allItems.add(current.getDato());
            current = current.getSiguiente();
        }
        return allItems;
    }

    /**
     * Obtiene el valor de un nodo en un índice dado.
     *
     * @param indice el índice del nodo.
     * @return el valor del nodo en el índice especificado.
     */
    public T obtenerValorNodo(int indice) {
        NodoLista<T> nodoTemporal = null;
        int contador = 0;

        if (indiceValido(indice)) {
            nodoTemporal = nodoPrimero;

            while (contador < indice) {
                nodoTemporal = nodoTemporal.getSiguiente();
                contador++;
            }
        }

        if (nodoTemporal != null)
            return nodoTemporal.getDato();
        else
            return null;
    }

    /**
     * Verifica si un índice es válido.
     *
     * @param indice el índice a verificar.
     * @return true si el índice es válido, false de lo contrario.
     * @throws RuntimeException si el índice no es válido.
     */
    private boolean indiceValido(int indice) {
        if (indice >= 0 && indice < tamanio) {
            return true;
        }
        throw new RuntimeException("Índice no válido");
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista está vacía, false de lo contrario.
     */
    public boolean estaVacia() {
        return (nodoPrimero == null);
    }

    /**
     * Imprime en la consola la lista enlazada.
     */
    public void imprimirLista() {
        NodoLista<T> aux = nodoPrimero;

        while (aux != null) {
            System.out.print(aux.getDato() + "\t");
            aux = aux.getSiguiente();
        }

        System.out.println();
    }

    /**
     * Elimina un nodo dado su valor.
     *
     * @param dato el valor del nodo a eliminar.
     * @return el valor del nodo eliminado.
     * @throws RuntimeException si el elemento no existe en la lista.
     */
    public T eliminar(T dato) {
        NodoLista<T> nodo = nodoPrimero;
        NodoLista<T> previo = null;
        NodoLista<T> siguiente = null;
        boolean encontrado = false;

        // Buscar el nodo a eliminar y su nodo previo
        while (nodo != null) {
            if (nodo.getDato() == dato) {
                encontrado = true;
                break;
            }
            previo = nodo;
            nodo = nodo.getSiguiente();
        }

        if (encontrado) {
            siguiente = nodo.getSiguiente();
            if (previo == null) {
                nodoPrimero = siguiente;
            } else {
                previo.setSiguiente(siguiente);
            }

            if (siguiente == null) {
                nodoUltimo = previo;
            } else {
                nodo.setSiguiente(null);
            }

            nodo = null;
            tamanio--;
            return dato;
        }
        throw new RuntimeException("El elemento no existe");
    }

    /**
     * Elimina el primer nodo de la lista.
     *
     * @return el valor del primer nodo eliminado.
     * @throws RuntimeException si la lista está vacía.
     */
    public T eliminarPrimero() {
        if (!estaVacia()) {
            NodoLista<T> n = nodoPrimero;
            T valor = n.getDato();
            nodoPrimero = n.getSiguiente();

            tamanio--;
            return valor;
        }

        throw new RuntimeException("Lista vacía");
    }

    /**
     * Verifica si un elemento existe en la lista.
     *
     * @param string el elemento a buscar.
     * @return true si el elemento existe en la lista, false de lo contrario.
     */
    public boolean existe(String string) {
        return buscar(string) != null;
    }

    /**
     * Busca un elemento en la lista.
     *
     * @param string el elemento a buscar.
     * @return el nodo que contiene el elemento, o null si no se encuentra.
     */
    private NodoLista<T> buscar(String string) {
        NodoLista<T> aux = nodoPrimero;

        while (aux != null) {
            if (aux.getDato().equals(string)) {
                return aux;
            }
            aux = aux.getSiguiente();
        }

        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple(nodoPrimero);
    }

    /**
     * Clase interna que implementa el iterador para la lista doblemente enlazada.
     */
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

        /**
         * Obtiene la posición actual del iterador.
         *
         * @return la posición actual del iterador.
         */
        public int getPosicion() {
            return posicion;
        }
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

