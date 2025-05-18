public class AVLVisual {
    private NodoAVL raiz;

    private int obtenerAltura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    private int obtenerBalance(NodoAVL nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }

    private int mayor(int a, int b) {
        return (a > b) ? a : b;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL temp = x.derecho;

        x.derecho = y;
        y.izquierdo = temp;

        y.altura = 1 + mayor(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho));
        x.altura = 1 + mayor(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho));

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL temp = y.izquierdo;

        y.izquierdo = x;
        x.derecho = temp;

        x.altura = 1 + mayor(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho));
        y.altura = 1 + mayor(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho));

        return y;
    }

    public void agregar(int valor) {
        raiz = insertarNodo(raiz, valor);
    }

    private NodoAVL insertarNodo(NodoAVL nodo, int valor) {
        if (nodo == null) return new NodoAVL(valor);

        if (valor < nodo.dato) {
            nodo.izquierdo = insertarNodo(nodo.izquierdo, valor);
        } else if (valor > nodo.dato) {
            nodo.derecho = insertarNodo(nodo.derecho, valor);
        } else {
            return nodo;
        }

        nodo.altura = 1 + mayor(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        int balance = obtenerBalance(nodo);

        if (balance > 1 && valor < nodo.izquierdo.dato) return rotacionDerecha(nodo);
        if (balance < -1 && valor > nodo.derecho.dato) return rotacionIzquierda(nodo);
        if (balance > 1 && valor > nodo.izquierdo.dato) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && valor < nodo.derecho.dato) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("(Árbol vacío)");
        } else {
            imprimirFormato(raiz, 0, "Raíz");
        }
        System.out.println("--------------------------------");
    }

    private void imprimirFormato(NodoAVL nodo, int nivel, String rama) {
        if (nodo == null) return;

        imprimirFormato(nodo.derecho, nivel + 1, "\\");

        for (int i = 0; i < nivel; i++) System.out.print("    ");
        System.out.println(rama + nodo.dato);

        imprimirFormato(nodo.izquierdo, nivel + 1, "/");
    }
}
