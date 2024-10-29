package hlc.unidad02.actividad203;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Arrays;

public class CifraAESApp2 {
    public static void main(String[] args) throws Exception {
        // Parámetros opcionales
	    // El mensaje es el primer argumento o el valor por defecto Mensaje de prueba
	    String mensaje = (args.length > 0) ? args[0] : "Mensaje de prueba";
	    // La password es el segundo argumento o la cadena pass123
	    String password = (args.length > 1) ? args[1] : "pass123";

        // Cifrar el mensaje
        String mensajeCifrado = cifrar(mensaje, password);
        System.out.println("Mensaje cifrado en Base64: " + mensajeCifrado);
    }

    public static String cifrar(String mensaje, String password) throws Exception {
        // Generar la clave desde la password
        SecretKeySpec clave = generarClave(password);

        // Crear una instancia del cifrador AES
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clave);

        // Cifrar el mensaje
        byte[] mensajeCifrado = cipher.doFinal(mensaje.getBytes("UTF-8"));

        // Convertir a Base64 para hacerlo legible
        return Base64.getEncoder().encodeToString(mensajeCifrado);
    }

    public static SecretKeySpec generarClave(String password) throws Exception {
        // Generar un hash SHA-256 de la password
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);

        // Asegurarse de que la clave tenga 16 bytes (AES usa claves de 128 bits)
        return new SecretKeySpec(Arrays.copyOf(key, 16), "AES");
        
    }
}

