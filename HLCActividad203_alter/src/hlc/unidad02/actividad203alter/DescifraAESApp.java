package hlc.unidad02.actividad203alter;

import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DescifraAESApp {
  // Valor de salt para generar la clave de 256 bits a partir de la contraseña
  // Este valor es necesario y se puede usar uno aleatorio o fijo. En este caso es fijo
  static String SAL = "Sal y Pimienta";
  // Iteraciones para generar la clave
  static int ITERACIONES = 500;
  // Longitud de la clave
  static int LONGITUDCLAVE = 256;

  public static void main(String[] args) throws Throwable{

    // El mensaje es el primer argumento o el valor por defecto Hola Caracola (cifrado con la contraseña por defecto)
    String mensajeCifrado = (args.length > 0) ? args[0] : "0AHF661MRpNDy1p8dKt64A==";
    // La password es el segundo argumento o la cadena 123456789
    String password = (args.length > 1) ? args[1] : "123456789";
    
    // Se asegura de que se ha inicializado la clave
    /* AES necesita una clave de 128, 224 o 256 bits. La contraseña tiene menos
     * Se necesita por lo tanto generar una clave de la longitud deseada a partir
     * de la contraseña. para ello se usa PBKDF2.... que genera el número de bits que
     * le digas haciendo un hash de la password
     */
    // Creamos la factoria
    SecretKeyFactory factoria =
       SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    // Generamos las especificaciones de la clave
    KeySpec especificacionPBE = new PBEKeySpec(password.toCharArray(), SAL.getBytes(), ITERACIONES, LONGITUDCLAVE);
    // Generamos la clave en formato PBE
    SecretKey especificacionAES = factoria.generateSecret(especificacionPBE);
    // La convertimos a formato AES y la devolvemos
    Key clave =  new SecretKeySpec(especificacionAES.getEncoded(), "AES");

    // Iniciamos el algoritmo con la clave
    Cipher algoritmo = Cipher.getInstance("AES");
    algoritmo.init(Cipher.DECRYPT_MODE, clave);
    
    // Ciframos convirtiendo el mensaje cifrado desde Base64 a array de bytes
    byte[] mensajeDescifrado = algoritmo.doFinal(Base64.getDecoder().decode(mensajeCifrado));
    
    // El resultado es una secuencia de bytes. Para que tenga un formato legible
    // Lo convertimos a Base 64 que es un formato que codifica secuencias binarias
    // usando caracteres imprimibles.
    System.out.println(new String(mensajeDescifrado));
    
  }
}
