package serializacion;

import model.Reproductor;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Persistencia {

    public static final String URL_PERSIT = "src/main/java/serializacion/datos.xml";

    public static void serializar(Reproductor reproductor){
        try (FileOutputStream fileOut = new FileOutputStream(URL_PERSIT);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

            objOut.writeObject(reproductor);
            System.out.println("Objeto serializado correctamente");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Reproductor deserializar(){

        try (FileInputStream fileIn = new FileInputStream(URL_PERSIT);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

            Reproductor deserializedObj = (Reproductor) objIn.readObject();
            System.out.println("Objeto deserializado correctamente");
            return deserializedObj;


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;

    }

}
