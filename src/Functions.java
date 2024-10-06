public class Functions {
    public static int contarMinasAlrededor(int[][] tablero, int fila, int columna) {
        int minas = 0;

        // Definir las 8 direcciones alrededor de la casilla
        int[] dirFila = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dirColumna = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Recorrer las 8 posiciones adyacentes
        for (int k = 0; k < 8; k++) {
            int nuevaFila = fila + dirFila[k];
            int nuevaColumna = columna + dirColumna[k];

            // Verificar si la nueva posición está dentro de los límites del tablero
            if (nuevaFila >= 0 && nuevaFila < tablero.length && nuevaColumna >= 0 && nuevaColumna < tablero[0].length) {
                if (tablero[nuevaFila][nuevaColumna] == -1) {  // Si es una mina
                    minas++;
                }
            }
        }

        return minas;  // Retornar el conteo de minas alrededor
    }

    public static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == -1) {
                    System.out.print("*\t");  // Imprimir un asterisco para las minas
                } else {
                    System.out.print(tablero[i][j] + "\t");  // Imprimir el número de minas alrededor
                }
            }
            System.out.println();  // Salto de línea
        }
    }
    public void revelarArea(int fila, int columna,int [][]MatrizCompleta,int [][]MatrizCliente) {
        if (fila < 0 || fila >= MatrizCompleta.length || columna < 0 || columna >= MatrizCompleta[0].length || (MatrizCliente[fila][columna]!=-2 & MatrizCliente[fila][columna]!=0)) {
            return; // Fuera de los límites o ya revelado
        }

        MatrizCliente[fila][columna] = MatrizCompleta[fila][columna]; // Marcar como revelado

        // Si la celda es 0, revelar todas las adyacentes
        if (MatrizCompleta[fila][columna] == 0) {
            // Definir las 8 direcciones alrededor de la casilla
            int[] dirFila = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dirColumna = {-1, 0, 1, -1, 1, -1, 0, 1};

            // Recorrer las 8 posiciones adyacentes
            for (int k = 0; k < 8; k++) {
                int nuevaFila = fila + dirFila[k];
                int nuevaColumna = columna + dirColumna[k];
                // Llamar recursivamente para revelar el área
                revelarArea(nuevaFila, nuevaColumna,MatrizCompleta,MatrizCliente);
            }
        }
    }
}

