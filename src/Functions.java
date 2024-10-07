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

    public static void imprimirTablero(int[][] tablero, int opc) {

        switch (opc){
            case 0:
                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[i].length; j++) {
                        if (tablero[i][j] == -1) {
                            System.out.print("B\t");
                        }else if(tablero[i][j] == -2|| tablero[i][j] == -3){
                            System.out.print("*\t");
                        }else if(tablero[i][j] == -4){
                            System.out.print("S\t");
                        }
                        else {
                            System.out.print(tablero[i][j] + "\t");  // Imprimir el número de minas alrededor
                        }
                    }
                    System.out.println();  // Salto de línea
                }
                break;
            case 1:
                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[i].length; j++) {
                        if (tablero[i][j] == -2) {
                            System.out.print("B\t");
                        }else if(tablero[i][j] == -3){
                            System.out.print("*\t");
                        }else if(tablero[i][j] == -4){
                            System.out.print("S\t");
                        }
                        else {
                            System.out.print(tablero[i][j] + "\t");  // Imprimir el número de minas alrededor
                        }
                    }
                    System.out.println();  // Salto de línea
                }

        }

    }

    public static String tableroToString(int[][] tablero, int opc) {
        StringBuilder sb = new StringBuilder();

        switch (opc) {
            case 0:
                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[i].length; j++) {
                        if (tablero[i][j] == -1) {
                            sb.append("B\t");  // Mina oculta
                        } else if (tablero[i][j] == -2 || tablero[i][j] == -3) {
                            sb.append("*\t");  // Casilla no revelada
                        } else if (tablero[i][j] == -4) {
                            sb.append("S\t");  // Casilla marcada
                        } else {
                            sb.append(tablero[i][j] + "\t");  // Número de minas alrededor
                        }
                    }
                    sb.append("\n");  // Salto de línea después de cada fila
                }
                break;

            case 1:
                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[i].length; j++) {
                        if (tablero[i][j] == -2) {
                            sb.append("B\t");  // Casilla marcada como bomba
                        } else if (tablero[i][j] == -3) {
                            sb.append("*\t");  // Casilla no revelada
                        } else if (tablero[i][j] == -4) {
                            sb.append("S\t");  // Casilla marcada
                        } else {
                            sb.append(tablero[i][j] + "\t");  // Número de minas alrededor
                        }
                    }
                    sb.append("\n");  // Salto de línea después de cada fila
                }
                break;
        }

        return sb.toString();  // Devolver el String construido
    }


    public static int revelarArea(int fila, int columna,int [][]matriz, int[][]matrizC,int opcion){

        if(opcion == 0){

            if(fila<0 || fila>= matriz.length || columna<0 || columna>= matriz[fila].length||matrizC[fila][columna]==0||(matrizC[fila][columna]==-4 && matriz[fila][columna]==-1)){
                return 0;
            }

            if (matriz[fila][columna]==-1) {
                System.out.println("Bomba!");

                return -1;
            }
            matrizC[fila][columna]=matriz[fila][columna];
            if (matriz[fila][columna]==0){
                int[] dirFila = {-1, -1, -1, 0, 0, 1, 1, 1};
                int[] dirColumna = {-1, 0, 1, -1, 1, -1, 0, 1};
                for (int k = 0; k < 8; k++) {
                    int nuevaFila = fila + dirFila[k];
                    int nuevaColumna = columna + dirColumna[k];
                    // Llamar recursivamente para revelar el área
                    revelarArea(nuevaFila, nuevaColumna, matriz, matrizC, 0);
                }

            }
        }
        if(opcion == 1){
            matrizC[fila][columna]=-4;
            if(matriz[fila][columna]== -1){
                return 1;
            }
            return 0;
        }
        return  0;
    }



}

