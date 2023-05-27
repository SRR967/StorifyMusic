package model;

import java.io.Serializable;

public enum Genero implements Serializable {
    ROCK("rock"),
    POP("pop"),
    PUNK("punk"),
    REGGAETON("reggaeton"),
    ELECTRONICA("electronica");

    private String name;

    Genero(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
