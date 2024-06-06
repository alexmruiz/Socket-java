package socketiaMicrosoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5545)) {
            System.out.println("Server is listening on port " + serverSocket.getLocalPort());
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());

                String inputLine;
                while ((inputLine = in.nextLine()) != null) {
                    System.out.println("Client says: " + inputLine);
                    out.println("Server received: " + inputLine);
                }
            }
        }
    }
}


