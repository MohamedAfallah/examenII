import es.madrid.parla.tierno.parla.Cliente;
import es.madrid.parla.tierno.parla.Servidor;

public class App {
    public static void main(String[] args) throws Exception {
        Servidor server = new Servidor();
        Thread hiloServidor = new Thread(server);
        hiloServidor.start();

        Cliente clienteI = new Cliente(7, 5, "+");
        Thread hiloCliente = new Thread(clienteI);
        hiloCliente.start();

        Cliente clienteII = new Cliente(7, 5, "-");
        Thread hiloClienteI = new Thread(clienteII);
        hiloClienteI.start();

        Cliente clienteIII = new Cliente(7, 5, "/");
        Thread hiloClienteII = new Thread(clienteIII);
        hiloClienteII.start();

        Cliente clienteIV = new Cliente(7, 5, "*");
        Thread hiloClienteIII = new Thread(clienteIV);
        hiloClienteIII.start();
    }
}
