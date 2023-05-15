package model;

import java.io.Serializable;

public class Administrador implements Serializable {

    private String userName;
    private String contrasenia;

    private static final long serialVersionUID = 1L;

    public Administrador(String userName, String contrasenia) {
        this.userName = userName;
        this.contrasenia = contrasenia;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
