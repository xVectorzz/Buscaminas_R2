
import java.net.*;
import java.io.*;
import java.util.Random;

public class ServerBuscaminas {
    public static void main(String[] args){
        try{
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Servicio iniciado en el puerto "+s.getLocalPort()+"  Esperando por clientes..");
            Random random = new Random();
            int filran;
            int colran;
            int ganado = 0;
            for(;;){  //while(1)
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde "+cl.getInetAddress()+":"+cl.getPort());
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                dos.writeUTF("Selecciona la dificultad del juego" +
                                                "\n(1:Fácil 9x9 casillas y 10 minas)" +
                                                "\n(2:Intermedio 16x16 casillas y 40 minas)" +
                                        "\n(3:Dificil 16x30 y 99 minas)");
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                int dificultad = dis.readInt();
                System.out.println("Dificultad seleccionada por el cliente: "+ dificultad);
                switch (dificultad){
                    case 1:
                        int [][] matrizCompletaf = new int[9][9];
                        int [][] matrizClientef = new int[9][9];

                        for(int m=0; m<9;m++){
                            filran= random.nextInt(9);
                            colran= random.nextInt(9);
                            matrizCompletaf[filran][colran]= -1;
                            matrizClientef[filran][colran]= -2;
                        }
                        for (int i= 0; i<9;i++){
                            for (int j= 0; j<9;j++){
                                if(matrizCompletaf[i][j]!=-1){
                                    matrizCompletaf[i][j]= Functions.contarMinasAlrededor(matrizCompletaf,i,j);
                                    while(!(ganado==1)){

                                    }
                                }
                            }
                        }

                        break;
                    case 2:
                        int [][] matrizCompletam = new int[16][16];
                        int [][] matrizClientem = new int[16][16];
                        for(int m=0; m<16;m++){
                            filran= random.nextInt(16);
                            colran= random.nextInt(16);
                            matrizCompletam[filran][colran]= -1;
                            matrizClientem[filran][colran]= -2;
                        }
                        for (int i= 0; i<16;i++){
                            for (int j= 0; j<16;j++){
                                if(matrizCompletam[i][j]!=-1){
                                    matrizCompletam[i][j]= Functions.contarMinasAlrededor(matrizCompletam,i,j);
                                }
                            }
                        }
                        break;
                    case 3:
                        int [][] matrizCompletad = new int[16][16];
                        int [][] matrizCliented = new int[16][16];
                        for(int m=0; m<16;m++){
                            filran= random.nextInt(16);
                            colran= random.nextInt(16);
                            matrizCompletad[filran][colran]= -1;
                            matrizCliented[filran][colran]= -2;
                        }
                        for (int i= 0; i<16;i++){
                            for (int j= 0; j<16;j++){
                                if(matrizCompletad[i][j]!=-1){
                                    matrizCompletad[i][j]= Functions.contarMinasAlrededor(matrizCompletad,i,j);
                                }
                            }
                        }
                        break;
                    default:
                        dos.writeUTF("Invalido, intente de nuevo...");
                }
                dis.close();
                dos.close();
                cl.close();

            }//for

        }catch(Exception e){
            e.printStackTrace();
        }
    }//main
}
