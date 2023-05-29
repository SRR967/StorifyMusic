package model;

import java.io.*;
import java.util.HashMap;

/**
 * Clase que representa el Memento utilizado para almacenar el estado de la tabla de usuarios.
 */
public class Memento implements Serializable {
    private HashMap<String, Usuario> tablaUsuarios;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase Memento.
     *
     * @param tablaUsuarios la tabla de usuarios a almacenar en el Memento.
     * @throws IOException            si ocurre un error de entrada/salida durante la copia profunda.
     * @throws ClassNotFoundException si no se encuentra la clase durante la copia profunda.
     */
    public Memento(HashMap<String, Usuario> tablaUsuarios) throws IOException, ClassNotFoundException {
        this.tablaUsuarios = new HashMap<>(tablaUsuarios);
        this.tablaUsuarios = deepCopy().tablaUsuarios;
    }

    /**
     * Realiza una copia profunda del Memento.
     *
     * @return una copia profunda del Memento.
     * @throws IOException            si ocurre un error de entrada/salida durante la copia profunda.
     * @throws ClassNotFoundException si no se encuentra la clase durante la copia profunda.
     */
    public Memento deepCopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
        outputStrm.writeObject(this);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
        return (Memento) objInputStream.readObject();
    }

    /**
     * Obtiene la tabla de usuarios almacenada en el Memento.
     *
     * @return la tabla de usuarios almacenada en el Memento.
     */
    public HashMap<String, Usuario> getTablaUsuarios() {
        return tablaUsuarios;
    }
}
