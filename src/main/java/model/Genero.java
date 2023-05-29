package model;

import java.io.Serializable;

/**
 * Enumeración que representa los géneros musicales.
 */
public enum Genero implements Serializable {
    ROCK("rock"),
    POP("pop"),
    PUNK("punk"),
    REGGAETON("reggaeton"),
    ELECTRONICA("electronica");

    private String name;

    /**
     * Constructor de la enumeración Genero.
     *
     * @param name el nombre del género musical.
     */
    Genero(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre del género musical.
     *
     * @return el nombre del género musical.
     */
    public String getName() {
        return name;
    }
}

