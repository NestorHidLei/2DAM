package psp.ud03.act303.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private Socket clientSocket;
	
	public ClientHandler(Socket clienSocket) {
		this.clientSocket = clienSocket;
	}
	
	@Override
	public void run() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true)){
			
			String command;
			
			while((command = br.readLine()) != null) {
				if(command.equalsIgnoreCase("quit")) {
					pw.println("Conexión cerrada");
					break;
				}
				pw.println("Comando recibido: " + command);
			}
			
		} catch (IOException e) {
            System.err.println("Error con el cliente: " + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
			}
		}
	}

}
