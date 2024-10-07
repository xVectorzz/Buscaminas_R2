
import java.net.*;
import java.io.*;
import java.util.Random;

public class ServerBuscaminas {
    public static void main(String[] args){
        try{
            int filran, colran, minas, fila, columna, opcion, dificultad, suma, conteominas = 0, ganar = 0;
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Servicio iniciado en el puerto "+s.getLocalPort()+"  Esperando por clientes..");
            Random random = new Random();

            for(;;){  //while(1)
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde "+cl.getInetAddress()+":"+cl.getPort());

                BufferedReader input = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                PrintWriter output = new PrintWriter(cl.getOutputStream(), true);

                output.println("Selecciona la dificultad: 1 (Fácil 9x9 10 minas), 2 (Medio 16x16 40 minas), 3 (Difícil 16x30 99 minas):");
                dificultad = Integer.parseInt(input.readLine()); // Leer la dificultad desde el cliente
                System.out.println("Dificultad seleccionada: " + dificultad);

                int[][] matrizCompleta;
                int[][] matrizCliente;

                switch (dificultad){
                    case 1:
                        matrizCompleta = new int[9][9];
                        matrizCliente = new int[9][9];
                        minas = 10;
                        break;
                    case 2:
                        matrizCompleta = new int[16][16];
                        matrizCliente = new int[16][16];
                        minas = 40;
                        break;
                    case 3:
                        matrizCompleta = new int[16][30];
                        matrizCliente = new int[16][30];
                        minas = 99;
                        break;
                    default:
                        matrizCompleta = new int[9][9];
                        matrizCliente = new int[9][9];
                        minas = 10;
                        break;
                }

                for (int m = 0; m < minas; m++) {
                    do {
                        filran = random.nextInt(matrizCompleta.length);
                        colran = random.nextInt(matrizCompleta[0].length);
                    } while (matrizCompleta[filran][colran] == -1); // -1 indica que hay una mina

                    matrizCompleta[filran][colran] = -1;
                    matrizCliente[filran][colran] = -2;
                }
                for (int i = 0; i < matrizCompleta.length; i++) {
                    for (int j = 0; j < matrizCompleta[i].length; j++) {
                        if (matrizCompleta[i][j] != -1) {
                            matrizCompleta[i][j] = Functions.contarMinasAlrededor(matrizCompleta, i, j);
                            matrizCliente[i][j] = -3;
                        }
                    }
                }

                Functions.imprimirTablero(matrizCompleta,0);

                while (ganar == 0) {
                    // Enviar al cliente el tablero actual
                    output.println(Functions.tableroToString(matrizCliente,0));

                    // Leer las coordenadas y la opción del cliente
                    output.println("Introduce la fila (coordenada y):");
                    fila = Integer.parseInt(input.readLine());

                    output.println("Introduce la columna (coordenada x):");
                    columna = Integer.parseInt(input.readLine());

                    output.println("Introduce si deseas revelar (0) o marcar una bomba (1):");
                    opcion = Integer.parseInt(input.readLine());

                    // Procesar la jugada
                    suma = Functions.revelarArea(fila, columna, matrizCompleta, matrizCliente, opcion);

                    if (suma < 0){
                        output.println("Bomba");
                        output.println(Functions.tableroToString(matrizCliente,1));
                        break;
                    }
                    conteominas += suma;


                    // Verificar si ganó
                    if (conteominas == minas) {
                        output.println("¡Ganaste!");
                        System.out.println("Cliente conectado desde "+cl.getInetAddress()+":"+cl.getPort()+"Ha ganado");
                        ganar = 1;
                    }
                }




                cl.close();

            }//for

        }catch(Exception e){
            e.printStackTrace();
        }
    }//main
}
