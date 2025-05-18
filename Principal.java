public class Principal {
    public static void main(String[] args) {
        AVLVisual arbol = new AVLVisual();

        int[] elementos = {30, 20, 40, 10, 25, 35, 50};

        for (int numero : elementos) {
            arbol.agregar(numero);
        }

        arbol.mostrarArbol();
    }
}
