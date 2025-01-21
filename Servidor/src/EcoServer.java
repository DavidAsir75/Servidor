import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EcoServer {
    public static void main(String[] args) {
        int port = 6000; // Puerto del servidor

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port);
            System.out.println("Esperando conexiones...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                String receivedMessage;
                while ((receivedMessage = input.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + receivedMessage);
                    if ("exit".equalsIgnoreCase(receivedMessage)) {
                        System.out.println("Cliente ha cerrado la conexión.");
                        break;
                    }
                    output.println("ECHO: " + receivedMessage);
                }

                clientSocket.close();
                System.out.println("Cliente desconectado.");
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}