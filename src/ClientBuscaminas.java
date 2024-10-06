
import java.net.Socket;
import java.io.*;
import java.net.StandardSocketOptions;

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
            DataInputStream dis = new DataInputStream(cl.getInputStream());
            String mensaje = dis.readUTF();
            System.out.println("Servidor: " + mensaje);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce el nivel de dificultad (1: Fácil, 2: Medio, 3: Difícil): ");
            int dificultad = Integer.parseInt(br.readLine());

            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            dos.writeInt(dificultad);  // Enviar la dificultad seleccionada al servidor

            String respuestaServidor = dis.readUTF();
            System.out.println("Servidor: " + respuestaServidor);



            dis.close();
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//main
}
