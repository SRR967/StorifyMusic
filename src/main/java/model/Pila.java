package model;

/**
 * Esta clase representa una implementación de una pila utilizando una lista simple.
 *
 * @param <T> el tipo de elementos almacenados en la pila
 */
public class Pila<T> extends ListaSimple {

    /**
     * Crea una nueva instancia de la clase Pila.
     */
    public Pila() {
    }

    /**
     * Inserta un nuevo elemento en la parte superior de la pila.
     *
     * @param valorNodo el valor del elemento a insertar
     */
    public void insertar(T valorNodo) {
        super.agregarInicio(valorNodo);
    }

    /**
     * Quita y devuelve el elemento en la parte superior de la pila.
     *
     * @return el elemento quitado de la pila
     * @throws RuntimeException si la pila está vacía
     */
    public T quitar() {
        if (PilaVacia()) {
            throw new RuntimeException("La Pila está vacía");
        }
        T dato = (T) super.getNodoPrimero().getDato();
        super.setNodoPrimero(super.getNodoPrimero().getSiguiente());
        super.setTamanio(super.getTamanio() - 1);
        return dato;
    }

    /**
     * Verifica si la pila está vacía.
     *
     * @return true si la pila está vacía, false en caso contrario
     */
    public boolean PilaVacia() {
        return super.estaVacia();
    }

    /**
     * Limpia la pila, eliminando todos los elementos.
     */
    public void limpiarPila() {
        super.setNodoPrimero(null);
        super.setTamanio(0);
    }

    /**
     * Retorna el elemento en la parte superior de la pila sin quitarlo.
     *
     * @return el elemento en la cima de la pila
     */
    public T cimaPila() {
        NodoLista<T> nodoAux = super.getNodoPrimero();
        return nodoAux.getDato();
    }

    /**
     * Retorna el tamaño actual de la pila.
     *
     * @return el tamaño de la pila
     */
    public int tamanioPila() {
        return super.getTamanio();
    }
}
