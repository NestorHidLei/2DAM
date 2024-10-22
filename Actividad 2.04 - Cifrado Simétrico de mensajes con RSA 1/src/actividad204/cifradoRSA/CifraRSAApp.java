package actividad204.cifradoRSA;

import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class CifraRSAApp {

    public static void main(String[] args) {
        // Validar los argumentos de entrada
        if (args.length < 2) {
            System.out.println("Uso: java CifraRSAApp <mensaje> <alias> <archivoSalida>");
            return;
        }

        String mensaje = args[0];
        String alias = args[1];
        String archivoSalida = "texto_cifrado.txt";
        String rutaKeystore = "almacen";
        String passwordKeystore = "12345678";

        try {
            // Cargar el keystore desde el archivo proporcionado
            KeyStore keystore = KeyStore.getInstance("JKS");
            FileInputStream keystoreFile = new FileInputStream(rutaKeystore);
            keystore.load(keystoreFile, passwordKeystore.toCharArray());

            // Obtener el certificado asociado al alias de la clave pública
            Certificate cert = keystore.getCertificate(alias);
            if (cert == null) {
                throw new Exception("Alias no encontrado en el Keystore.");
            }

            // Obtener la clave pública del certificado
            PublicKey publicKey = cert.getPublicKey();

            // Cifrar el mensaje utilizando RSA
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // Cifrar el mensaje en claro
            byte[] mensajeCifrado = cipher.doFinal(mensaje.getBytes("UTF-8"));

            // Convertir el mensaje cifrado a Base64 para su legibilidad
            String mensajeCifradoBase64 = Base64.getEncoder().encodeToString(mensajeCifrado);

            // Guardar el mensaje cifrado en un archivo de texto
            guardarEnArchivo(archivoSalida, mensajeCifradoBase64);

            // Confirmación de que el mensaje se guardó
            System.out.println("Mensaje cifrado: " + mensajeCifradoBase64);

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para guardar el mensaje cifrado en un archivo
    private static void guardarEnArchivo(String archivoSalida, String contenido) throws IOException {
        try (FileWriter writer = new FileWriter(archivoSalida)) {
            writer.write(contenido);
        }
    }
}
