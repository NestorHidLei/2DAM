package psp.ud03.practica02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainFileClientApp {

	private static final String HOST_DEFECTO = "localhost";
	private static final int PUERTO_DEFECTO = 4321;

	public static void main(String[] args) {
		String host = args.length > 0 ? args[0] : HOST_DEFECTO;
		int port = args.length > 1 ? Integer.parseInt(args[1]) : PUERTO_DEFECTO;

		try (Socket socket = new Socket(host, port);
				BufferedReader readerConsola = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader readerServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				OutputStream writerServer = socket.getOutputStream()) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
