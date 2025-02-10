package psp.ud03.act303.server.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
	
	private static final String OK = "OK";
	private static final String KO = "KO";
	
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String command;
            while ((command = br.readLine()) != null) {
                if (command.equalsIgnoreCase("quit")) {
                    pw.println(OK);
                    pw.flush();
                    break;
                } else if (command.startsWith("list")) {
                    handleListCommand(command, pw);
                } else if (command.startsWith("delete")) {
                    handleDeleteCommand(command, pw);
                } else if (command.startsWith("show")) {
                    handleShowCommand(command, pw);
                } else {
                    pw.println(KO);
                    pw.flush();
                }
            }

        } catch (IOException e) {
            System.err.println("Error con el cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    private void handleListCommand(String command, PrintWriter pw) {
        String path = command.substring(5).trim();
        File directory = new File(path);

        if (directory.exists() && directory.isDirectory()) {
            pw.println(OK);
            pw.flush();
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        pw.println(file.getName() + " " + (file.length() / 1024.0));
                    } else if (file.isDirectory()) {
                        pw.println(file.getName() + " 0");
                    }
                }
            }
            pw.println();
            pw.flush();
        } else {
            pw.println(KO);
            pw.flush();
        }
    }

    private void handleDeleteCommand(String command, PrintWriter pw) {
        String path = command.substring(7).trim();
        File file = new File(path);

        if (file.exists() && file.delete()) {
            pw.println(OK);
            pw.flush();
        } else {
            pw.println(KO);
            pw.flush();
        }
    }

    private void handleShowCommand(String command, PrintWriter pw) {
        String path = command.substring(5).trim(); // Extrae la ruta del comando
        File file = new File(path);

        // Verifica si el archivo existe y es un archivo regular
        if (file.exists() && file.isFile()) {
        	// Envía "OK" para indicar éxito
        	pw.println("OK"); 
        	pw.flush();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                // Contar el número de líneas en el archivo
                int lineCount = 0;
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = fileReader.readLine()) != null) {
                	// Almacena cada línea
                	content.append(line).append("\n"); 
                    lineCount++;
                }

                // Enviar la respuesta al cliente
                // Envía el número de líneas
                pw.println(lineCount);
                pw.flush();

                // Envía el contenido del archivo
                pw.print(content.toString()); 
                pw.flush();
            } catch (IOException e) {
                // Si hay un error al leer el archivo, envía "KO"
                pw.println("KO");
                pw.flush();
            }
        } else {
            // Si el archivo no existe o no es un archivo regular, envía "KO"
            pw.println("KO");
            pw.flush();
        }
    }
}