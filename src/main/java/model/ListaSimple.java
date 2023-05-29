package model;

import java.io.Serializable;

/**
 * Clase que representa una lista enlazada simple.
 *
 * @param <T> el tipo de dato de los elementos de la lista.
 */
public class ListaSimple<T> implements Serializable {

    private NodoLista<T> nodoPrimero;
    private NodoLista<T> nodoUltimo;
    private int tamanio;

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase ListaSimple.
     * Inicializa la lista vacía.
     */
    public ListaSimple() {
        nodoUltimo = null;
        nodoPrimero = null;
        tamanio = 0;
    }

    /**
     * Agrega un nuevo elemento al inicio de la lista.
     *
     * @param valorNodo el valor del elemento a agregar.
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
     * @param valorNodo el valor del elemento a agregar.
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
     * Obtiene el valor de un nodo en la posición especificada.
     *
     * @param indice la posición del nodo.
     * @return el valor del nodo en la posición especificada, o null si el índice es inválido.
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
        return nodoPrimero == null;
    }

    /**
     * Imprime en consola la lista enlazada.
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
     * Elimina un nodo con el valor especificado de la lista.
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

        // buscar el nodo previo
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
                // nodoUltimo = previo;
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
     * @return el valor del nodo eliminado.
     * @throws RuntimeException si la lista está vacía.
     */
    public T eliminarPrimero() {
        if (!estaVacia()) {
            NodoLista<T> n = nodoPrimero;
            T valor = n.getDato();
            nodoPrimero = n.getSiguiente();

            if (nodoPrimero == null) {
                // nodoUltimo = null;
            }

            tamanio--;
            return valor;
        }

        throw new RuntimeException("Lista vacía");
    }

    private NodoLista<T> obtenerNodo(int indice) {
        if (indice >= 0 && indice < tamanio) {
            NodoLista<T> nodo = nodoPrimero;

            for (int i = 0; i < indice; i++) {
                nodo = nodo.getSiguiente();
            }

            return nodo;
        }

        return null;
    }

    /**
     * Modifica el valor de un nodo en la posición especificada.
     *
     * @param indice la posición del nodo.
     * @param nuevo  el nuevo valor del nodo.
     */
    public void modificarNodo(int indice, T nuevo) {
        if (indiceValido(indice)) {
            NodoLista<T> nodo = obtenerNodo(indice);
            nodo.setDato(nuevo);
        }
    }

    /**
     * Retorna la primera posición donde está guardado el dato.
     *
     * @param dato el valor a buscar dentro de la lista.
     * @return la primera posición del dato, o -1 si no se encuentra en la lista.
     */
    public int obtenerPosicionNodo(T dato) {
        int i = 0;

        for (NodoLista<T> aux = nodoPrimero; aux != null; aux = aux.getSiguiente()) {
            if (aux.getDato().equals(dato)) {
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
