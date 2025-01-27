package psp.ud03.practica02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientHandler extends Thread {

	private final Socket clientSocket;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}


		
	@Override
	public void run() {
		//Recibe la solicitud del cliente.
		//Busca el archivo en el servidor.
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			OutputStream output = clientSocket.getOutputStream()) {
			String filePath = reader.readLine();
			Path rutaArchivo = Paths.get(filePath);
			
			//Si el file existe envía una respuesta al cliente: OK con los datos del archivo o KO si no se encuentra el archivo.
			if (Files.exists(rutaArchivo) && Files.isRegularFile(rutaArchivo) && Files.isReadable(rutaArchivo)) {
				output.write("OK\n\r".getBytes());
				//Limpiar cache
				output.flush();
				//Copiamos y enviamos el archivo
				Files.copy(rutaArchivo, output);
			} else {
				output.write("OK\n\r".getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//Cierra la conexión al final.
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
