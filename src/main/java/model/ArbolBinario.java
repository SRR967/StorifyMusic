package model;

import java.io.Serializable;

/**
 * Clase ArbolBinario que representa un árbol binario.
 *
 * @param <T> el tipo de elementos que se almacenan en el árbol.
 */
public class ArbolBinario<T extends Comparable<T>> implements Serializable {

    private NodoArbol<T> raiz;
    private int peso;
    private static final long serialVersionUID = 1L;

    /**
     * Verifica si el árbol está vacío.
     *
     * @return true si el árbol está vacío, false en caso contrario.
     */
    public boolean estaVacio() {
        return raiz == null;
    }

    /**
     * Inserta un nuevo valor en el árbol.
     *
     * @param valor el valor a insertar.
     */
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
     * Realiza el recorrido inorden en el árbol.
     */
    public void inorden() {
        inorden(raiz);
    }

    private void inorden(NodoArbol<T> n) {
        if (n != null) {
            inorden(n.getIzquierdo());
            System.out.println(n.getElemento());
            inorden(n.getDerecho());
        }
    }

    /**
     * Realiza el recorrido preorden en el árbol.
     *
     * @param n el nodo actual.
     */
    public void preorden(NodoArbol<T> n) {
        if (n != null) {
            System.out.println(n.getElemento());
            preorden(n.getIzquierdo());
            preorden(n.getDerecho());
        }
    }

    /**
     * Realiza el recorrido postorden en el árbol.
     *
     * @param n el nodo actual.
     */
    public void postorden(NodoArbol<T> n) {
        if (n != null) {
            postorden(n.getIzquierdo());
            postorden(n.getDerecho());
            System.out.println(n.getElemento());
        }
    }

    /**
     * Verifica si un elemento existe en el árbol.
     *
     * @param n        el nodo actual.
     * @param elemento el elemento a buscar.
     * @return true si el elemento existe en el árbol, false en caso contrario.
     */
    public boolean existe(NodoArbol<T> n, T elemento) {
        if (n != null) {
            if (elemento.compareTo(n.getElemento()) == 0) {
                return true;
            } else if (elemento.compareTo(n.getElemento()) < 0) {
                return existe(n.getIzquierdo(), elemento);
            } else if (elemento.compareTo(n.getElemento()) > 0) {
                return existe(n.getDerecho(), elemento);
            }
        }
        return false;
    }

    /**
     * Obtiene el peso del árbol.
     *
     * @param n el nodo actual.
     * @return el peso del árbol.
     */
    public int obtenerPeso(NodoArbol<T> n) {
        if (n != null) {
            return 1 + obtenerPeso(n.getIzquierdo()) + obtenerPeso(n.getDerecho());
        }
        return 0;
    }

    /**
     * Obtiene la altura del árbol.
     *
     * @param n    el nodo actual.
     * @param prof la profundidad actual.
     * @return la altura del árbol.
     */
    public int obtenerAltura(NodoArbol<T> n, int prof) {
        if (n != null) {
            int profIzq = obtenerAltura(n.getIzquierdo(), prof + 1);
            int profDer = obtenerAltura(n.getDerecho(), prof + 1);
            if (profIzq >= profDer) {
                return profIzq;
            } else {
                return profDer;
            }
        }
        return prof;
    }

    /**
     * Obtiene el nivel de un elemento en el árbol.
     *
     * @param nodo     el nodo actual.
     * @param elemento el elemento a buscar.
     * @param nivel    el nivel actual.
     * @return el nivel del elemento en el árbol, -1 si no se encuentra.
     */
    public int obtenerNivel(NodoArbol<T> nodo, T elemento, int nivel) {
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                return nivel;
            } else if (elemento.compareTo(nodo.getIzquierdo().getElemento()) < 0) {
                return obtenerNivel(nodo.getIzquierdo(), elemento, nivel + 1);
            } else if (elemento.compareTo(nodo.getDerecho().getElemento()) > 0) {
                return obtenerNivel(nodo.getDerecho(), elemento, nivel + 1);
            }
        }
        return -1;
    }

    /**
     * Cuenta el número de hojas en el árbol.
     *
     * @param n el nodo actual.
     * @return el número de hojas en el árbol.
     */
    public int contarHojas(NodoArbol<T> n) {
        if (n != null) {
            int c = 0;
            if (n.esHoja()) {
                c = 1;
            }
            return c + contarHojas(n.getIzquierdo()) + contarHojas(n.getDerecho());
        }
        return 0;
    }

    /**
     * Obtiene el valor más pequeño del árbol.
     *
     * @return el valor más pequeño del árbol.
     */
    public T obtenerMenor() {
        NodoArbol<T> aux = raiz;

        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        return aux.getElemento();
    }

    /**
     * Imprime el árbol en formato horizontal.
     *
     * @param n     el nodo actual.
     * @param nivel el nivel actual.
     */
    public void imprimirHorizontal(NodoArbol<T> n, int nivel) {
        if (n != null) {
            imprimirHorizontal(n.getDerecho(), nivel + 1);

            for (int i = 0; i < nivel; i++) {
                System.out.println("\t");
            }

            System.out.println(n.getElemento());

            imprimirHorizontal(n.getIzquierdo(), nivel + 1);
        }
    }

    /**
     * Obtiene la raíz del árbol.
     *
     * @return la raíz del árbol.
     */
    public NodoArbol<T> getRaiz() {
        return raiz;
    }

    /**
     * Establece la raíz del árbol.
     *
     * @param raiz la raíz del árbol.
     */
    public void setRaiz(NodoArbol<T> raiz) {
        this.raiz = raiz;
    }

    /**
     * Obtiene el peso del árbol.
     *
     * @return el peso del árbol.
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Establece el peso del árbol.
     *
     * @param peso el peso del árbol.
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
}

