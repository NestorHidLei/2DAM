package actividad202.seguridad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash {
	
	public static void main(String[] args) {
		if (args.length < 1 || args.length > 2) {
			System.out.println("Error tienes que poner como minimo un parametro");
			return;
		}
		
		
		   String fileName = args[0]; // Primer argumento es el nombre del fichero
	        String algoritmo = (args.length > 1) ? args[1].toLowerCase() : "sha"; // Segundo argumento (opcional) es el algoritmo

	        try {
	            // Determinar el algoritmo de hash
	            MessageDigest digest;
	            if ("md5".equals(algoritmo)) {
	                digest = MessageDigest.getInstance("MD5");
	            } else {
	                digest = MessageDigest.getInstance("SHA-256"); // Por defecto usa SHA-256
	            }

	            byte[] contenido = Files.readAllBytes(Paths.get(fileName));

	            // Convertir el resumen en hexadecimal
	            byte[] hashBytes = digest.digest(contenido);
	            StringBuilder hexadecimal = new StringBuilder();
	            for (byte b : hashBytes) {
	            	hexadecimal.append(String.format("%02x", b)); // Convertir cada byte a hexadecimal
	            }

	            // Imprimir el resultado
	            System.out.println("Resumen (" + algoritmo.toUpperCase() + "): " + hexadecimal.toString());

	        } catch (NoSuchAlgorithmException e) {
	            System.out.println("Error: Algoritmo de hash no soportado.");
	        } catch (IOException e) {
	            System.out.println("Error: No se pudo leer el archivo.");
	        }
	    }
	}
