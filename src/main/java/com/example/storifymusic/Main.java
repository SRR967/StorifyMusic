package com.example.storifymusic;

import model.Reproductor;

import java.io.*;

public class Main implements Serializable {
    public static void main(String[] args) {
        Reproductor reproductor = new Reproductor();
        reproductor.crearUser("San","123","san");

        // Serializar objeto
        String filename = "reproductor.ser";

        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

            objOut.writeObject(reproductor);
            System.out.println("Objeto serializado correctamente");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializar objeto
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

            Reproductor deserializedObj = (Reproductor) objIn.readObject();
            System.out.println("Objeto deserializado correctamente");


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

