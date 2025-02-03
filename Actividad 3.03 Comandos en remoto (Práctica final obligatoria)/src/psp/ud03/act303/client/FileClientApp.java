package psp.ud03.act303.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FileClientApp {

    private static final String DEFAULT_HOST = "192.168.1.234";
    private static final int DEFAULT_PORT = 2121;
    private static final String OK = "OK";
    private static final String KO = "KO";

    public static void main(String[] args) {
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;

        // Verifica si se proporcionaron host y puerto en los argumentos
        if (args.length >= 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor en " + host + ":" + port);
            handleClientCommunication(out, in, scanner);

        } catch (IOException e) {
            System.err.println("Error al conectarse al servidor: " + e.getMessage());
        }
    }

    /**
     * Metodo que se ocupa de mantener la conexion entre el  cliente y el servidor
     * @param out - Salida del servidor
     * @param in - Entrada al servidor
     * @param scanner - El scanner para leer la respuesta del servidor
     * @throws IOException
     */
    private static void handleClientCommunication(PrintWriter out, BufferedReader in, Scanner scanner) throws IOException {
        String userInput;
        while (true) {
            System.out.print("Introduce un comando (list, delete, show, quit): ");
            userInput = scanner.nextLine();

            // Envía el comando al servidor
            out.println(userInput);

            // Lee la respuesta del servidor
            String serverResponse = in.readLine();
            if (serverResponse == null) {
                System.out.println("El servidor cerró la conexión.");
                break;
            }

            // Procesa la respuesta del servidor
            processServerResponse(serverResponse, userInput, in);

            // Si el comando es 'quit', cierra la conexión y sale del bucle
            if ("quit".equalsIgnoreCase(userInput)) {
                System.out.println("Cerrando la conexión...");
                break;
            }
        }
    }

    /**
     * Metodo que mira el estado del servidor
     * @param serverResponse - La respuesta que da el servidor, puede ser 
     * OK si es correcta o KO si no lo es.
     * @param userInput - Parametro que da el usuario puede ser list o show
     * @param in - Parametro de entrada
     * @throws IOException
     */
    private static void processServerResponse(String serverResponse, String userInput, BufferedReader in) throws IOException {
        if (serverResponse.equals(OK)) {
            if (userInput.startsWith("list")) {
                processListResponse(in);
            } else if (userInput.startsWith("show")) {
                processShowResponse(in);
            } else {
                // Respuesta OK para delete o quit
                System.out.println(OK);
            }
        } else if (serverResponse.equals(KO)) {
            System.out.println(KO + ": Error en la operación.");
        }
    }

    /**
     * Metodo que lista los archivos del servidor
     * @param in - Parametro de entrada
     * @throws IOException
     */
    private static void processListResponse(BufferedReader in) throws IOException {
        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            System.out.println(line);
        }
    }

    /**
     * Metodo para la opcion de show
     * @param in - Paramatro de entrada
     * @throws IOException
     */
    private static void processShowResponse(BufferedReader in) throws IOException {
        int numLines = Integer.parseInt(in.readLine());
        for (int i = 0; i < numLines; i++) {
            System.out.println(in.readLine());
        }
    }
}