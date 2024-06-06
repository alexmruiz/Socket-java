package socketIAChatGPT;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChat {

    public static void main(String[] args) {
        String servidorIP = "localhost"; // Puedes cambiar la dirección IP según donde esté el servidor
        int puerto = 12345; // Puedes cambiar el puerto según tus necesidades

        try {
            // Conectar con el servidor en la dirección y puerto especificados
            Socket socket = new Socket(servidorIP, puerto);

            // Crear un PrintWriter para escribir hacia el servidor
            PrintWriter salidaServidor = new PrintWriter(socket.getOutputStream(), true);

            // Crear un Scanner para leer desde el servidor
            Scanner entradaServidor = new Scanner(socket.getInputStream());

            // Leer mensajes desde el usuario hasta que escriba "FIN"
            try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
				    System.out.print("Escribe un mensaje (o FIN para finalizar la conexión): ");
				    String mensajeUsuario = scanner.nextLine();
				    salidaServidor.println(mensajeUsuario);

				    // Finalizar la conexión si el usuario escribe "FIN"
				    if (mensajeUsuario.equals("FIN")) {
				        System.out.println("Cliente solicitó finalizar la conexión. Cerrando cliente...");
				        break;
				    }

				    // Leer la respuesta del servidor
				    String respuestaServidor = entradaServidor.nextLine();
				    System.out.println("Respuesta del servidor: " + respuestaServidor);
				}
			}
            // Cerrar los recursos
            entradaServidor.close();
            salidaServidor.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
