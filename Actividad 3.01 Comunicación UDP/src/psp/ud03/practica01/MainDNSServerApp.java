package psp.ud03.practica01;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;

public class MainDNSServerApp {

	 // Puerto en el que el servidor escuchará las solicitudes.
    private static final int PORT = 2222;
    // Archivo que contiene las propiedades DNS.
    private static final String DNS_FILE = "dns.properties"; 

    public static void main(String[] args) {
    	// Objeto para cargar las propiedades DNS.
        Properties dnsProperties = new Properties(); 
        
        // Intentamos cargar el archivo de propiedades DNS.
        try (FileInputStream entrada = new FileInputStream(DNS_FILE)) {
        	// Cargar las propiedades del archivo.
            dnsProperties.load(entrada); 
        } catch (IOException e) {
            System.err.println("Error cargando el archivo DNS properties: " + e.getMessage());
            return;
        }

        // Iniciamos el socket del servidor UDP en el puerto especificado.
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("DNS Server se está ejecutando en el puerto " + PORT);

            // Ciclo infinito para procesar solicitudes entrantes.
            while (true) {
                // Crear un buffer para recibir datos del cliente.
                byte[] buffer = new byte[1024];
                DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);

                // Recibir un paquete del cliente.
                serverSocket.receive(requestPacket);

                // Extraer y procesar la solicitud recibida.
                String request = new String(requestPacket.getData(), 0, requestPacket.getLength()).trim();
                System.out.println("Solicitud recibida: " + request);

                // Buscar el nombre solicitado en las propiedades DNS.
                String response = dnsProperties.getProperty(request, "El nombre no se encuentra");

                // Preparar la respuesta para enviarla al cliente.
                byte[] responseData = response.getBytes();
                //Direccion IP del clietnte
                InetAddress clientAddress = requestPacket.getAddress();
                //Puerto del cliente
                int clientPort = requestPacket.getPort(); 

                // Crear un paquete de respuesta lo envia al cliente.
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                serverSocket.send(responsePacket);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor DNS: " + e.getMessage());
        }
    }
}
