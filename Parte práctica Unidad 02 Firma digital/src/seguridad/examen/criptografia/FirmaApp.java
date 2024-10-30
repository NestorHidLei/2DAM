package seguridad.examen.criptografia;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.Cipher;

public class FirmaApp {

    public static void main(String[] args) {
        // Validate input arguments
        if (args.length != 2) {
            System.out.println(
                    "Faltan parámetros \n USO: \n FirmaApp <archivo> <password> \n archivo - archivo del que se quiere obtener la firma \n password - Password para desbloquear la clave privada");
            return;
        }

        String archivo = args[0];
        String password = args[1];
        String rutaKeystore = "examenclaves.jks";

        try {
            // Lee el archivo
            byte[] contenido = Files.readAllBytes(Paths.get(archivo));

            //  resumen sobre los datos del archivo con SHA
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(contenido);
            System.out.println("Hash (SHA-256) generado del archivo: " + bytesToHex(hashBytes));

            // Cargamos el KeyStore
            KeyStore keystore = KeyStore.getInstance("JKS");
            try (FileInputStream keystoreFile = new FileInputStream(rutaKeystore)) {
                keystore.load(keystoreFile, password.toCharArray());
            }

            // Miramos que el alias existe
            String alias = "examen"; 
            if (!keystore.isKeyEntry(alias)) {
                throw new Exception("Alias '" + alias + "' no encontrado en el Keystore.");
            }
            
            // Adquirimos la clave privada
            PrivateKey privateKey =  (PrivateKey) keystore.getKey(alias, password.toCharArray());
            if (privateKey == null) {
                throw new Exception("Clave privada no encontrada en el keystore.");
            }

            // Cifrar el mensaje utilizando RSA
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            
            byte[] hashCifrado = cipher.doFinal(hashBytes);
            
            // Firmamos el hash usando la clave privada
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(hashCifrado);
            byte[] firma = signature.sign();

            // Convertimos la firma a base64
            String firmaBase64 = Base64.getEncoder().encodeToString(firma);
            System.out.println("Firma digital en Base64: " + firmaBase64);

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Metodo para convertir de bytes a HEX 
     * @param bytes - bytes
     * @return Texto en Hex
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
