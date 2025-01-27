package psp.ud03.practica02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainFileServerApp {
	
	private static int PUERTO_DEFECTO = 4321;
	
	public static void main(String[] args) {
		//pueto por defecto
		int puerto = PUERTO_DEFECTO;
		
		//Si se pasan argumento, ese sera el nuevo puerto
		if(args.length > 0) {
			try {
				puerto = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("El puerto no es valido. Se usara el puerto por defecto: " + PUERTO_DEFECTO);
			}
		}
		
		//intenta iniciar el servidor TCP en el puerto seleccionado
		try (ServerSocket serverSocket = new ServerSocket(puerto)){
            System.out.println("Servidor iniciado en el puerto: " + puerto);
            
            //bucle infinito para aceptar conexiones de clientes
            while(true) {
            	Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress().getHostAddress());
               
                new ClientHandler(clientSocket).start();
            }
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
