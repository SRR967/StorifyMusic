package model;

import java.io.Serializable;
import java.util.function.Consumer;

public class ArbolBinario <T extends Comparable<T>> implements Serializable {

    private NodoArbol<T> raiz;
    private int peso;
    private static final long serialVersionUID = 1L;

    /**
     * Metodo para verificar si el arbol esta vacio
     * @return true si el arbol esta vacio
     */
    public boolean estaVacio() {
        return raiz==null;
    }

    public void insertar(T valor) {
        raiz = insertarNodo(raiz, valor);
    }

    private NodoArbol<T> insertarNodo(NodoArbol<T> nodo, T valor) {
        if (nodo == null) {
            return new NodoArbol<>(valor);
        }

        if (valor.compareTo(nodo.getElemento()) < 0) {
            nodo.setIzquierdo(insertarNodo(nodo.getIzquierdo(), valor));
        } else if (valor.compareTo(nodo.getElemento()) > 0) {
            nodo.setDerecho(insertarNodo(nodo.getDerecho(), valor));
        }

        return nodo;
    }
    /**
     * Realiza el recorrido inorden en el arbol
     */
    public void inorden() {
        inorden(raiz);
    }

    /**
     * recorrido inorden del arbol binario
     * @param n Nodo
     */
    private void inorden(NodoArbol<T> n) {
        if(n!=null) {
            inorden(n.getIzquierdo());
            System.out.println(n.getElemento());
            inorden(n.getDerecho());
        }
    }

    /**
     * Realiza el recorrido preorden en el arbol
     * @param n Nodo
     */
    public void preorden(NodoArbol<T> n) {
        if(n!=null) {
            System.out.println(n.getElemento());
            preorden(n.getIzquierdo());
            preorden(n.getDerecho());
        }
    }

    /**
     * Realiza el recorrido postorden en el arbol
     * @param n Nodo
     */
    public void postorden(NodoArbol<T> n) {
        if(n!=null) {
            postorden(n.getIzquierdo());
            postorden(n.getDerecho());
            System.out.println(n.getElemento());
        }
    }

    /**
     * Vetifica si un elemento existe en el �arbol
     * @param n Nodo
     * @param elemento Elemento a buscar
     * @return true si lo encuentra
     */
    public boolean existe(NodoArbol<T> n, T elemento) {
        if(n!=null) {
            if( elemento.compareTo(n.getElemento()) == 0 ) {
                return true;
            }else if( elemento.compareTo(n.getElemento()) < 0 ) {
                return existe(n.getIzquierdo(), elemento);
            }else if( elemento.compareTo(n.getElemento()) > 0 ) {
                return existe(n.getDerecho(), elemento);
            }
        }
        return false;
    }

    public int obtenerPeso(NodoArbol<T> n) {

        if(n!=null) {
            return 1+obtenerPeso(n.getIzquierdo())+obtenerPeso(n.getDerecho());
        }

        return 0;
    }

    public int obtenerAltura(NodoArbol<T> n, int prof) {
        if(n!=null) {
            int profIzq= obtenerAltura(n.getIzquierdo(),prof +1 );
            int profDer= obtenerAltura(n.getDerecho(),prof +1 );
            if (profIzq>=profDer)	{

                return profIzq;
            }else {

                return profDer;
            }


        }

        return prof;
    }

    public int obtenerNivel(NodoArbol <T> nodo, T elemento, int nivel) {

        if (nodo!=null) {
            if(elemento.compareTo(nodo.getElemento())==0) {
                return nivel;
            } else if (elemento.compareTo(nodo.getIzquierdo().getElemento())<0) {
                return obtenerNivel(nodo.getIzquierdo(), elemento, nivel+1);
            }
            else if (elemento.compareTo(nodo.getDerecho().getElemento())>0) {
                return obtenerNivel(nodo.getDerecho(), elemento, nivel+1);

            }
        }
        return -1;
    }

    public int contarHojas(NodoArbol <T> n ) {
        if (n!=null) {
            int c=0;
            if (n.esHoja()) {
                c=1;
            }
            return c+ contarHojas(n.getIzquierdo())+contarHojas(n.getDerecho());

        }
        return 0;

    }


    public T obtenerMenor () {
        NodoArbol <T> aux = raiz;

        while (aux.getIzquierdo()!=null) {
            aux = aux.getIzquierdo();
        }
        return aux.getElemento();
    }

    public void imprimirHorizontal(NodoArbol <T> n, int nivel) {
        if (n!=null) {
            imprimirHorizontal(n.getDerecho(), nivel+1);

            for (int i = 0; i < nivel; i++) {
                System.out.println("/t");

            }

            System.out.println(n.getElemento());

            imprimirHorizontal(n.getIzquierdo(), nivel+1);

        }
    }

    public NodoArbol<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol<T> raiz) {
        this.raiz = raiz;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
