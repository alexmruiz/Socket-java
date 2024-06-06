package evaluable;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	
	public static void main(String[] args) {
		
        int puerto = 5545; 

        try {
            //Crear un socket de servidor en el puerto especificado
            ServerSocket ssc = new ServerSocket(puerto);
            System.out.println("Servidor escuchando");

            //Esperar a que un cliente se conecte
            Socket sc = ssc.accept();
            System.out.println("Cliente conectado");

            //Crear un Scanner para leer desde el cliente
            Scanner entrada = new Scanner(sc.getInputStream());

            //Crear un PrintWriter para escribir hacia el cliente
            PrintWriter salida = new PrintWriter(sc.getOutputStream(), true);

            //Leer mensajes del cliente hasta que reciba "Fin"
            while (entrada.hasNextLine()) {
                String mensajeCliente = entrada.nextLine();
                System.out.println("Mensaje del cliente: " + mensajeCliente);

                if (mensajeCliente.equals("Fin")) {
                    System.out.println("Cerrando servidor");
                    break;
                }

                //Enviar un mensaje de respuesta al cliente
                String mensajeRespuesta = "echo: " + mensajeCliente;
                salida.println(mensajeRespuesta);
            }

            //Cerrar los recursos
            entrada.close();
            salida.close();
            sc.close();
            ssc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
	

