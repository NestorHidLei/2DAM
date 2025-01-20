package psp.ud03.practica01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DNSClient {
	// Puerto donde escucha el servidor DNS.
    private static final int SERVER_PORT = 2222; 
    // Dirección IP del servidor DNS.
    private static final String SERVER_ADDRESS = "127.0.0.1"; 

    public static void main(String[] args) {
        // Crear el socket del cliente y un escáner para entrada de datos del usuario.
        try (DatagramSocket clientSocket = new DatagramSocket(); 
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("DNS Client esta ejecutandose.Proporciona un nombre o dejalo en blanco para salir.");

            // Ciclo para leer y procesar nombres ingresados por el usuario.
            while (true) {
            	 // Solicitar al usuario un nombre para consultar.
                System.out.print("Proporciona un nombre: ");
                String name = scanner.nextLine(); 

                // Si el usuario no ingresa nada, salir del cliente.
                if (name.isEmpty()) {
                    System.out.println("Saliendo DNS Client.");
                    break;
                }

                // Enviar solicitud al servidor DNS.
                // Convertir el nombre a bytes.
                byte[] requestData = name.getBytes();
                // Resolver la dirección del servidor.
                InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS); 
                DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, SERVER_PORT);
                // Enviar el paquete al servidor.
                clientSocket.send(requestPacket); 

                // Recibir respuesta del servidor DNS.
                // Buffer para almacenar la respuesta.
                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                // Recibir paquete desde el servidor.
                clientSocket.receive(responsePacket); 

                // Procesar y mostrar la respuesta del servidor.
                String response = new String(responsePacket.getData(), 0, responsePacket.getLength()).trim();
                System.out.println("Response: " + response); 
            }
        } catch (IOException e) {

            System.err.println("Error in DNS Client: " + e.getMessage());
        }
    }
}
