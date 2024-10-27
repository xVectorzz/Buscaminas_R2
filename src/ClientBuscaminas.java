
import java.net.Socket;
import java.io.*;
import java.net.StandardSocketOptions;
import java.util.Scanner;

public class ClientBuscaminas {
    public static void main(String[] args){
        try{
            String dir = "127.0.0.1";
            int pto = 1234;
            Socket cl = new Socket(dir,pto);
            System.out.println("Cliente conectado al servidor de buscaminas...");
            System.out.println("SO_RCVBUF="+cl.getOption(StandardSocketOptions.SO_RCVBUF));
            System.out.println("SO_SNDBUF="+cl.getOption(StandardSocketOptions.SO_SNDBUF));
            System.out.println("TCP_NODELAY="+cl.getOption(StandardSocketOptions.TCP_NODELAY));

            BufferedReader input = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            PrintWriter output = new PrintWriter(cl.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            // ** Leer la solicitud de dificultad del servidor **
            System.out.println(input.readLine()); // Mostrar mensaje "Selecciona la dificultad..."
            int dificultad = scanner.nextInt();
            output.println(dificultad); // Enviar la dificultad seleccionada al servidor

            System.out.println(input.readLine()); // "Introduce la fila (coordenada y):"
            int fila = scanner.nextInt() -1;
            output.println(fila);

            System.out.println(input.readLine()); // "Introduce la columna (coordenada x):"
            int columna = scanner.nextInt() -1;
            output.println(columna);

            System.out.println(input.readLine()); // "Introduce si deseas revelar (0) o marcar una bomba (1):"
            int opcion = scanner.nextInt();
            output.println(opcion); // Enviar la opción al servidor

            StringBuilder tableroCompleto = new StringBuilder();
            String linea;

            while ((linea = input.readLine()) != null && !linea.isEmpty()) {
                tableroCompleto.append(linea).append("\n");
            }
            System.out.println("Juego iniciado");

            while (true) {

                // Leer el tablero actual enviado por el servidor
                StringBuilder tablero = new StringBuilder();


                // Mientras no haya una línea vacía, lee las líneas del tablero
                while ((linea = input.readLine()) != null && !linea.isEmpty()) {
                    tablero.append(linea).append("\n");
                }

                System.out.print(tablero.toString());


                // Pedir al usuario que ingrese las coordenadas y la opción
                System.out.println(input.readLine()); // "Introduce la fila (coordenada y):"
                fila = scanner.nextInt() -1;
                output.println(fila); // Enviar la fila al servidor

                System.out.println(input.readLine()); // "Introduce la columna (coordenada x):"
                columna = scanner.nextInt() -1;
                output.println(columna); // Enviar la columna al servidor

                System.out.println(input.readLine()); // "Introduce si deseas revelar (0) o marcar una bomba (1):"
                opcion = scanner.nextInt();
                output.println(opcion); // Enviar la opción al servidor



                // Leer el resultado del servidor
                String resultado = input.readLine();
                System.out.println(resultado); // Imprimir el resultado
                if (resultado.contains("¡Ganaste!")) {
                    System.out.print(tableroCompleto.toString());
                    break; // Salir del bucle si el cliente gana
                } else if (resultado.contains("Bomba")){
                    System.out.println("Tablero actual:");
                    System.out.print(tableroCompleto.toString());
                    break;
                }
                System.out.println("Tablero actual:");

            }
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
