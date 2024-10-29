package hlc.unidad02.actividad203clase;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DescifraAESApp {
  private static final byte[] SAL = {21,10,8,34,40};
  private static final int ITERACIONES = 10;
  private static final int KEY_SIZE = 128;

  public static void main(String[] args) {
    // Procesamos los parametros
    Parametros parametros = new Parametros(args);
    
    // Ciframos el mensaje
    String mensajeDescifrado = "";
    try {
      mensajeDescifrado = descifraMensaje(parametros.getMensaje(), parametros.getPassword());
    } catch (InvalidKeyException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (BadPaddingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    // Mostrar el mensaje cifrado por pantalla
    System.out.println("El mensaje descifrado es: " + mensajeDescifrado);
  }

  private static String descifraMensaje(String mensaje, String password) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException  {
    
    byte[] mensajeOriginal = Base64.getDecoder().decode(mensaje);
    
    // Prepara la clave
    Key clave = preparaClave(password);
    
    // Crear el objeto Cipher
    Cipher cifrador = Cipher.getInstance("AES");
    
    // Iniciarlo
    cifrador.init(Cipher.DECRYPT_MODE, clave);
    
    // Desciframos
    byte[] mensajeDescifrado = cifrador.doFinal(mensajeOriginal);
    
    return new String(mensajeDescifrado);
    
  }

  private static Key preparaClave(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    // Crea la factoria de claves
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    // Prepara la especificación de clave
    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), SAL, ITERACIONES, KEY_SIZE);
    // Genera la clave
    SecretKey secretKey =  factory.generateSecret(spec);
    // La convertimos a clave AES
    return new SecretKeySpec(secretKey.getEncoded(), "AES");
  }

}
