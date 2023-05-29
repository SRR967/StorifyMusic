package model;

import java.io.IOException;
import java.io.Serializable;

/**
 * Clase Caretaker que gestiona el historial de estados de un reproductor y permite deshacer y rehacer acciones.
 */
public class Caretaker implements Serializable {

    private Pila<Memento> historialDeshacer = new Pila<>();
    private Pila<Memento> historialRehacer = new Pila<>();

    private static final long serialVersionUID = 1L;

    /**
     * Guarda el estado actual del reproductor en el historial de deshacer.
     *
     * @param reproductor el reproductor cuyo estado se va a guardar.
     * @throws IOException            si ocurre un error de entrada/salida durante la operación.
     * @throws ClassNotFoundException si no se encuentra la clase durante la operación de deserialización.
     */
    public void guardarEstado(Reproductor reproductor) throws IOException, ClassNotFoundException {
        Memento memento = reproductor.crearMemento();
        historialDeshacer.insertar(memento);
        historialRehacer.limpiarPila();
    }

    /**
     * Deshace la última acción realizada en el reproductor, restaurando el estado anterior.
     *
     * @param reproductor el reproductor en el que se va a deshacer la acción.
     * @throws IOException            si ocurre un error de entrada/salida durante la operación.
     * @throws ClassNotFoundException si no se encuentra la clase durante la operación de deserialización.
     */
    public void deshacer(Reproductor reproductor) throws IOException, ClassNotFoundException {
        if (!historialDeshacer.estaVacia()) {
            Memento memento = historialDeshacer.quitar();
            historialRehacer.insertar(reproductor.crearMemento());
            reproductor.restaurarDesdeMemento(memento.deepCopy());
            System.out.println("Deshaciendo la acción anterior.");
        } else {
            System.out.println("No hay más acciones para deshacer.");
        }
    }

    /**
     * Rehace la última acción deshecha en el reproductor, restaurando el estado anterior.
     *
     * @param reproductor el reproductor en el que se va a rehacer la acción.
     * @throws IOException            si ocurre un error de entrada/salida durante la operación.
     * @throws ClassNotFoundException si no se encuentra la clase durante la operación de deserialización.
     */
    public void rehacer(Reproductor reproductor) throws IOException, ClassNotFoundException {
        if (!historialRehacer.estaVacia()) {
            Memento memento = historialRehacer.quitar();
            historialDeshacer.insertar(reproductor.crearMemento());
            reproductor.restaurarDesdeMemento(memento.deepCopy());
            System.out.println("Rehaciendo la última acción.");
        } else {
            System.out.println("No hay más acciones para rehacer.");
        }
    }
}

