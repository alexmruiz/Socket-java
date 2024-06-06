package evaluable;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {

	public static void main(String[] args) {
        
		String servidor = "localhost";
        int puerto = 5545;

        try {
            //Conectando con el servidor en la dirección y puerto especificados
            Socket sc = new Socket(servidor, puerto);
            System.out.println("ESTATUS: Conectado al servidor");

            //Escribimos hacía el servidor con PrintWriter
            PrintWriter salida = new PrintWriter(sc.getOutputStream(), true);

            //Con Scanner leo desde el servidor
            Scanner entrada = new Scanner(sc.getInputStream());

            
            try (Scanner scanner = new Scanner(System.in)) {
				while (true) {// Leer mensajes hasta que se escriba Fin
				    System.out.print("Introduzca un texto a enviar (FIN para acabar)");
				    String mensajeCliente = scanner.nextLine();
				    salida.println(mensajeCliente);
				    System.out.println("ESTATUS: Enviando "+mensajeCliente+'\n'+"ESTATUS: Esperado a eco");
				    
				    if (mensajeCliente.equals("Fin")) {
				        System.out.println("Cerrando conexión");
				        break;
				    }

				    // Leer la respuesta del servidor
				    String respuestaServidor = entrada.nextLine();
				    System.out.println(respuestaServidor);
				}
			}
            // Cerrar los recursos
            entrada.close();
            salida.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}



