package psp.ud03.act303.server;

import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import psp.ud03.act303.server.handler.ClientHandler;

public class FileServerApp {

	private static final int DEFAULT_PORT = 2121;
	private int port;

	public FileServerApp() {
		this.port = loadPortFromConfig();
	}

	private int loadPortFromConfig() {
		Properties properties = new Properties();
		try (InputStream input = new FileInputStream("server.properties")) {
			properties.load(input);
			return Integer.parseInt(properties.getProperty("puerto", String.valueOf(DEFAULT_PORT)));
		} catch (IOException | NumberFormatException e) {
			System.out.println(
					"Error al leer el archivo server.properties. Usando el puerto por defecto: " + DEFAULT_PORT);
			return DEFAULT_PORT;
		}
	}
	
	public void start() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		try(ServerSocket serverSocket = new ServerSocket(port)){
			System.out.println("Servidor iniciado en el puerto: " + port);
			while(true) {
				Socket clientSocket = serverSocket.accept();
				threadPool.execute(new ClientHandler(clientSocket));
			}
		} catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
	}

	 public static void main(String[] args) {
	        new FileServerApp().start();
	}
}
