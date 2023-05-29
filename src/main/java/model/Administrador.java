package model;

import java.io.Serializable;

/**
 * Clase Administrador que representa un administrador del sistema.
 */
public class Administrador implements Serializable {

    private String userName;
    private String contrasenia;

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase Administrador.
     *
     * @param userName    el nombre de usuario del administrador.
     * @param contrasenia la contraseña del administrador.
     */
    public Administrador(String userName, String contrasenia) {
        this.userName = userName;
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene el nombre de usuario del administrador.
     *
     * @return el nombre de usuario del administrador.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Establece el nombre de usuario del administrador.
     *
     * @param userName el nombre de usuario del administrador.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Obtiene la contraseña del administrador.
     *
     * @return la contraseña del administrador.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del administrador.
     *
     * @param contrasenia la contraseña del administrador.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}

