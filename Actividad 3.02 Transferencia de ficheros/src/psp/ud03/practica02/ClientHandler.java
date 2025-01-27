package psp.ud03.practica02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private final Socket clientSocket;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				OutputStream output = clientSocket.getOutputStream()) {
			String filePath = reader.readLine();

			File file = new File(filePath);
			if (file.exists() && file.isFile()) {
				output.write("OK\n\r".getBytes());
				try (FileInputStream fileInputStream = new FileInputStream(file)) {
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = fileInputStream.read(buffer)) != -1) {
						output.write(buffer, 0, bytesRead);
					}
				}
			} else {
				output.write("OK\n\r".getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
