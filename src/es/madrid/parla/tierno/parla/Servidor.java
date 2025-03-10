package es.madrid.parla.tierno.parla;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class Servidor implements Runnable{
    final int PORT = 8080;
    final String MSG_ERROR_SERVER = "Error en el servidor";
    final String MSG_ERROR_SERVER_OPERACION = "Operacion denegada, Simbolo incorrecto";
    final String MSG_RECIBIDO = "El cliente ha enviado los datos: ";
    final String MSG_INICIO_SERVIDOR = "El servidor se ha iniciado en " + PORT;
    final String MSG_ENVIADO = "Enviado al cliente : ";
    final String MSG_COMPLEJAS = "Las operaciones complejas: ";
    final String MSG_SENCILLAS = "Las operaciones sencillas: ";
    final String ESPACIO = " ";

    int contadorSencillas = 0;
    int contadorComplejas = 0;

    LoggerAplicacion logger = new LoggerAplicacion("Servidor", false);

    @Override
    public void run() {
        int resultadoOperacion = 0;
        int operandoI = 0;
        int operandoII = 0;
        String operacion = "";
        try(ServerSocket ss = new ServerSocket(PORT)){
            System.out.println(MSG_INICIO_SERVIDOR);
            logger.log(Level.ALL, MSG_INICIO_SERVIDOR);
            while(true){
                Socket socket = ss.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                operandoI = in.readInt();
                operacion = in.readUTF();
                operandoII = in.readInt();

                logger.log(Level.INFO, MSG_RECIBIDO + operandoI + ESPACIO + operacion + ESPACIO + operandoII);
                System.out.println(MSG_RECIBIDO + operandoI + ESPACIO + operacion + ESPACIO + operandoII);


                if(comprobarSimbolo(operacion)){     

                    resultadoOperacion = operar(operandoI, operandoII, operacion);
                    out.writeInt(resultadoOperacion);
                    logger.log(Level.INFO, MSG_ENVIADO + resultadoOperacion);
                    System.out.println(MSG_ENVIADO + resultadoOperacion);

                }else{

                    out.writeUTF(MSG_ERROR_SERVER_OPERACION);
                    logger.log(Level.WARNING, MSG_ERROR_SERVER_OPERACION);
                    System.out.println(MSG_ENVIADO + MSG_ERROR_SERVER_OPERACION);
                }

                System.out.println(MSG_SENCILLAS + contadorSencillas + "\n" + MSG_COMPLEJAS + contadorComplejas);


                socket.close();
            }


        }catch(IOException e){
            System.out.println(MSG_ERROR_SERVER + e.getMessage());
            logger.log(Level.WARNING, MSG_ERROR_SERVER + e.getMessage());
        }
    }

    private int operar(int operandoI, int operandoII, String operacion){
        int resultadoOperacion = 0 ;

        if(operacion.equals("+")){
            resultadoOperacion =  operandoI + operandoII;
            contadorSencillas++;
        }else if(operacion.equals("-")){
            resultadoOperacion = operandoI - operandoII;
            contadorSencillas++;
        }else if(operacion.equals("*")){
            resultadoOperacion = operandoI + operandoII;
            contadorComplejas++;
        }else if(operacion.equals("/")){
            resultadoOperacion = operandoI / operandoII;
            contadorComplejas++;
        }

        return resultadoOperacion;
    }

    private boolean comprobarSimbolo(String operacion){
        if(operacion.equals("*") || operacion.equals("-") || operacion.equals("+")|| operacion.equals("/")){
            return true;
        }else{
            return false;
        }
    }

}
