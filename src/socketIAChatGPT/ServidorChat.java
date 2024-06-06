package socketIAChatGPT;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// Clase del servidor TCP
	public class ServidorChat {

    public static void main(String[] args) {
        int puerto = 12345; // Puedes cambiar el puerto según tus necesidades

        try {
            // Crear un socket de servidor en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Esperar a que un cliente se conecte
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

            // Crear un Scanner para leer desde el cliente
            Scanner entradaCliente = new Scanner(clientSocket.getInputStream());

            // Crear un PrintWriter para escribir hacia el cliente
            PrintWriter salidaCliente = new PrintWriter(clientSocket.getOutputStream(), true);

            // Leer mensajes del cliente hasta que reciba "FIN"
            while (entradaCliente.hasNextLine()) {
                String mensajeCliente = entradaCliente.nextLine();
                System.out.println("Mensaje del cliente: " + mensajeCliente);

                // Finalizar la conexión si se recibe "FIN"
                if (mensajeCliente.equals("FIN")) {
                    System.out.println("Cliente solicitó finalizar la conexión. Cerrando servidor...");
                    break;
                }

                // Enviar un mensaje de respuesta al cliente
                String mensajeRespuesta = "Respuesta desde el servidor: " + mensajeCliente;
                salidaCliente.println(mensajeRespuesta);
            }

            // Cerrar los recursos
            entradaCliente.close();
            salidaCliente.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
