package model;

public class Pila<T> extends ListaSimple{
    public Pila() {
    }

    public void insertar (T valorNodo){
        super.agregarInicio(valorNodo);
    }

    public T quitar(){

        if(PilaVacia()) {
            throw new RuntimeException("La Pila está vacía");
        }
        T dato = (T) super.getNodoPrimero().getDato();
        super.setNodoPrimero(super.getNodoPrimero().getSiguiente());
        super.setTamanio(super.getTamanio()-1);
        return dato;
    }

    public boolean PilaVacia(){
        return super.estaVacia();
    }

    public void limpiarPila(){
        super.setNodoPrimero(null);
        super.setTamanio(0);
    }

    public T cimaPila(){
        NodoLista<T> nodoAux = super.getNodoPrimero();
        return nodoAux.getDato();
    }

    public int tamanioPila(){
        return super.getTamanio();
    }

}
