package socketiaMicrosoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 5545)) {
            System.out.println("Connected to server on port " + socket.getPort());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(socket.getInputStream());

            try (Scanner scanner = new Scanner(System.in)) {
				String inputLine;
				while ((inputLine = scanner.nextLine()) != null) {
				    out.println(inputLine);
				    System.out.println("Server says: " + in.nextLine());
				}
			}
        }
    }
}

