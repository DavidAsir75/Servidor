import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EcoCliente {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int port = 6000;           // Puerto del servidor

        try (Socket socket = new Socket(host, port);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor en " + host + ":" + port);
            System.out.println("Escribe un mensaje (o 'exit' para salir):");

            String userInput;
            while (true) {
                System.out.print("Tú eres: ");
                userInput = scanner.nextLine();
                output.println(userInput);

                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Saliendo del cliente...");
                    break;
                }

                String response = input.readLine();
                System.out.println("Servidor: " + response);
            }
        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}