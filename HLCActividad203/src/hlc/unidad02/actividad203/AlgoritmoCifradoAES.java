package hlc.unidad02.actividad203;

import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AlgoritmoCifradoAES extends AlgoritmoCifrado {
	  // Valor de salt para generar la clave de 256 bits a partir de la contraseña
	  // Este valor es necesario y se puede usar uno aleatorio o fijo. En este caso es fijo
	  private String sal = "Sal y Pimienta";
	  // Iteraciones para generar la clave
	  private int iteraciones;
	  // Longitud de la clave
	  private int longitudClave;
	  // Clave de encriptacion para JCA
	  Key clave = null;
	  
	  public AlgoritmoCifradoAES(String password, String sal, int iteraciones, int longitudClave) {
	    super(password);
	    this.sal = sal;
	    this.iteraciones = iteraciones;
	    this.longitudClave = longitudClave;
	  }

	  @Override
	  public String encriptaABase64(String mensaje) throws Throwable {

	    // Se asegura de que la clave se ha inicializado
	    inicializaClave();
	    
	    // Iniciamos el algoritmo con la clave
	    Cipher algoritmo = Cipher.getInstance("AES");
	    algoritmo.init(Cipher.ENCRYPT_MODE, this.clave);
	    
	    // Ciframos
	    byte[] mensajeCifrado = algoritmo.doFinal(mensaje.getBytes());
	    
	    // El resultado es una secuencia de bytes. Para que tenga un formato legible
	    // Lo convertimos a Base 64 que es un formato que codifica secuencias binarias
	    // usando caracteres imprimibles.
	    return Base64.getEncoder().encodeToString(mensajeCifrado);
	  }

	  @Override
	  public String desencriptaDesdeBase64(String mensaje) throws Throwable {

	    // Se asegura de que se ha inicializado la clave
	    inicializaClave();
	    // Iniciamos el algoritmo con la clave
	    Cipher algoritmo = Cipher.getInstance("AES");
	    algoritmo.init(Cipher.DECRYPT_MODE, this.clave);
	    
	    // Ciframos convirtiendo el mensaje cifrado desde Base64 a array de bytes
	    byte[] mensajeCifrado = algoritmo.doFinal(Base64.getDecoder().decode(mensaje));
	    
	    // El resultado es una secuencia de bytes. Para que tenga un formato legible
	    // Lo convertimos a Base 64 que es un formato que codifica secuencias binarias
	    // usando caracteres imprimibles.
	    return new String(mensajeCifrado);
	  }

	  private void inicializaClave() throws Throwable {

	    if (clave == null) {
	      /* AES necesita una clave de 128, 224 o 256 bits. La contraseña tiene menos
	       * Se necesita por lo tanto generar una clave de la longitud deseada a partir
	       * de la contraseña. para ello se usa PBKDF2.... que genera el número de bits que
	       * le digas haciendo un hash de la password
	       */
	      // Creamos la factoria
	      SecretKeyFactory factoria =
	         SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	      // Generamos las especificaciones de la clave
	      KeySpec especificacionPBE = new PBEKeySpec(password.toCharArray(), sal.getBytes(), iteraciones, longitudClave);
	      // Generamos la clave en formato PBE
	      SecretKey especificacionAES = factoria.generateSecret(especificacionPBE);
	      // La convertimos a formato AES y la devolvemos
	      clave =  new SecretKeySpec(especificacionAES.getEncoded(), "AES");
	    }
	  }
	}