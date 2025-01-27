package psp.ud03.practica02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainFileClientApp {

	private static final String HOST_DEFECTO = "localhost";
	private static final int PUERTO_DEFECTO = 4321;

	public static void main(String[] args) {
		String host = args.length > 0 ? args[0] : HOST_DEFECTO;
		int port = args.length > 1 ? Integer.parseInt(args[1]) : PUERTO_DEFECTO;

		// Solicitar la ruta del archivo al usuario
		try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.print("Introduce la ruta del archivo en el servidor: ");
			String filePath = consoleReader.readLine();

			// Conectar al servidor
			try (Socket socket = new Socket(host, port);
					OutputStream output = socket.getOutputStream();
					BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					InputStream serverInput = socket.getInputStream()) {

				// Enviar la solicitud al servidor
				output.write((filePath + "\n\r").getBytes());
				output.flush();

				// Leer la respuesta del servidor
				String response = serverReader.readLine();

				if ("OK".equals(response)) {
					System.out.println("El archivo existe en el servidor. Descargando...");

					// Nombre del archivo descargado (usar el nombre original del archivo)
					String localFileName = filePath.substring(filePath.lastIndexOf('/') + 1);

					try (OutputStream fileOutput = new FileOutputStream(localFileName)) {
						byte[] buffer = new byte[1024];
						int bytesRead;

						// Leer y guardar los datos del archivo
						while ((bytesRead = serverInput.read(buffer)) != -1) {
							fileOutput.write(buffer, 0, bytesRead);
						}
						System.out.println("Archivo descargado correctamente: " + localFileName);
					}
				} else if ("KO".equals(response)) {
					System.err.println("Error: El archivo solicitado no existe en el servidor.");
				} else {
					System.err.println("Respuesta desconocida del servidor: " + response);
				}
 
			} catch (IOException e) {
				System.err.println("Error al comunicarse con el servidor: " + e.getMessage());
			}

		} catch (IOException e) {
			System.err.println("Error al leer la entrada del usuario: " + e.getMessage());
		}
	}
}
