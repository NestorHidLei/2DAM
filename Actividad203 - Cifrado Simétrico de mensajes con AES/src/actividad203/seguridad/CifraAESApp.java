package actividad203.seguridad;

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

public class CifraAESApp {

	private static String INSTANCIAFABRICACLAVE = "PBKDF2WithHmacSHA256";
	private static String MENSAJE_POR_DEFECTO = "Mensaje de prueba";
	private static char[] PASSWORD_POR_DEFECTO = "pass123".toCharArray();
	private static byte[] SALT = "prueba de cadena para salt".getBytes();
	private static int ITERATIONS = 100;
	private static int KEYSIZE = 256;
	private String mensaje;
	private char[] password;
	
	

	

	public CifraAESApp(String iNSTANCIAFABRICACLAVE, byte[] sALT, int iTERATIONS, int kEYSIZE, String mensaje,
			char[] password) {
		this.INSTANCIAFABRICACLAVE = iNSTANCIAFABRICACLAVE;
		this.SALT = sALT;
		this.ITERATIONS = iTERATIONS;
		this.KEYSIZE = kEYSIZE;
		this.mensaje = mensaje;
		this.password = password;
	}
	
	

	public String getINSTANCIAFABRICACLAVE() {
		return INSTANCIAFABRICACLAVE;
	}



	public static String getMENSAJE_POR_DEFECTO() {
		return MENSAJE_POR_DEFECTO;
	}



	public static char[] getPASSWORD_POR_DEFECTO() {
		return PASSWORD_POR_DEFECTO;
	}



	public byte[] getSALT() {
		return SALT;
	}



	public int getITERATIONS() {
		return ITERATIONS;
	}



	public int getKEYSIZE() {
		return KEYSIZE;
	}



	public String getMensaje() {
		return mensaje;
	}



	public char[] getPassword() {
		return password;
	}

	
	public Key getClaveCifrado() throws NoSuchAlgorithmException, InvalidKeySpecException {
	    // Preparamos la clave a utilizar HMAC
	    SecretKeyFactory fabricaClave = SecretKeyFactory.getInstance(this.getINSTANCIAFABRICACLAVE());
	    // Indico la PBE a utilizar con la clave, el salt, las iteraciones y el tamaño de clave
	    PBEKeySpec especificaClave = new PBEKeySpec(this.getPassword(), this.getSALT(),
	    this.getITERATIONS(), this.getKEYSIZE());
	    // Genero una clave secreta con el tipo de fábrica y las especificaciones de la clave
	    SecretKey claveSecreta = fabricaClave.generateSecret(especificaClave);
	    // Aquí usamos 'claveSecreta' para generar la clave AES
	    Key key = new SecretKeySpec(claveSecreta.getEncoded(), "AES");

	    return key;
	}
	
	public String getMensajeCifrado() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
			Cipher cifrador = Cipher.getInstance("AES");
			cifrador.init(Cipher.ENCRYPT_MODE, this.getClaveCifrado());
			byte[] textoAntesCifrar = cifrador.doFinal(mensaje.getBytes());
			String textoCifrado = Base64.getEncoder().encodeToString(textoAntesCifrar);
			return textoCifrado;
	}

	public String getMensajeDescifrado() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cifrador = Cipher.getInstance("AES");
		cifrador.init(Cipher.DECRYPT_MODE, this.getClaveCifrado());
		
		byte[] textoDescifrado = cifrador.doFinal(Base64.getDecoder().decode(this.getMensajeCifrado()));
		
		String textoDescifradoString = new String(textoDescifrado);
		
		return textoDescifradoString;
}



	public static void main(String[] args) {
		String mensaje = MENSAJE_POR_DEFECTO;
		char[] password = PASSWORD_POR_DEFECTO;
		if (args.length == 2) {
			mensaje = args[0];
			password = args[1].toCharArray();
		} else if (args.length == 1) {
			mensaje = args[0];
		}
		
		CifraAESApp cifraAes = new CifraAESApp(INSTANCIAFABRICACLAVE, SALT, ITERATIONS, KEYSIZE, mensaje, password);
		
		try {
			System.out.println(cifraAes.getMensajeCifrado());
			System.out.println(cifraAes.getMensajeDescifrado());
		} catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
