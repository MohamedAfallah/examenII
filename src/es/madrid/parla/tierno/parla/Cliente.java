package es.madrid.parla.tierno.parla;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable{
    final String HOST = "localhost";
    final int PUERTO = 8080;
    final String ERROR_CLIENTE = "Error en el cliente";

    //Practica no correcta
    int operandoI;
    int operandoII;
    String operacion;

    public Cliente(int operandoI, int operandoII, String operacion) {
        this.operandoI = operandoI;
        this.operandoII = operandoII;
        this.operacion = operacion;
    }

    @Override
    public void run() {
        try(Socket socket = new Socket(HOST, PUERTO);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream())){

            out.writeInt(operandoI);
            out.writeUTF(operacion);
            out.writeInt(operandoII);

            in.readUTF();

            socket.close();
        }catch(IOException e){
            System.out.println(ERROR_CLIENTE + e.getMessage());
        }
    }


}
