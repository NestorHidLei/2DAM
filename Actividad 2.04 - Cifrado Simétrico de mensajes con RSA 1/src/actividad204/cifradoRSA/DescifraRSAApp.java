package actividad204.cifradoRSA;

import java.security.KeyStore;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class DescifraRSAApp {

    public static void main(String[] args) {
        // Validar los argumentos de entrada
        if (args.length < 1) {
            System.out.println("Uso: java DescifraRSAApp <archivoCifrado> <alias>");
            return;
        }
        
        // Archivo que contiene el mensaje cifrado en Base64
        String archivoCifrado = "texto_cifrado.txt"; 
        String alias = args[0];
        // Ruta fija del keystore
        String rutaKeystore = "almacen"; 
        // Contraseña del keystore
        String passwordKeystore = "12345678"; 

        try {
            // Leer el contenido cifrado desde el archivo
            String mensajeCifradoBase64 = new String(Files.readAllBytes(Paths.get(archivoCifrado)), "UTF-8");

            // Cargar el keystore desde el archivo proporcionado
            KeyStore keystore = KeyStore.getInstance("JKS");
            FileInputStream keystoreFile = new FileInputStream(rutaKeystore);
            keystore.load(keystoreFile, passwordKeystore.toCharArray());

            // Obtener la clave privada asociada al alias
            PrivateKey privateKey = (PrivateKey) keystore.getKey(alias, passwordKeystore.toCharArray());

            if (privateKey == null) {
                throw new Exception("Alias no encontrado o clave privada no disponible en el Keystore.");
            }

            // Decodificar el mensaje cifrado de Base64 a bytes
            byte[] mensajeCifrado = Base64.getDecoder().decode(mensajeCifradoBase64);

            // Inicializar el cifrador en modo DESCIFRADO con la clave privada
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            // Descifrar el mensaje
            byte[] mensajeDescifrado = cipher.doFinal(mensajeCifrado);

            // Convertir el mensaje descifrado a String
            String mensajeEnClaro = new String(mensajeDescifrado, "UTF-8");

            // Imprimir el mensaje en claro
            System.out.println("Mensaje descifrado: " + mensajeEnClaro);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
