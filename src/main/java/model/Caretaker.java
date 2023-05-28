package model;


import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

public class Caretaker implements Serializable {

    private Pila<Memento> historialDeshacer = new Pila<>();
    private Pila<Memento> historialRehacer = new Pila<>();

    private static final long serialVersionUID = 1L;


    public void guardarEstado(Reproductor reproductor) throws IOException, ClassNotFoundException {
        Memento memento = reproductor.crearMemento();
        historialDeshacer.insertar(memento);
        historialRehacer.limpiarPila();
    }

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
