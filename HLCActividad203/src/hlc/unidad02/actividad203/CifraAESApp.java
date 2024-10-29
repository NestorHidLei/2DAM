package hlc.unidad02.actividad203;

public class CifraAESApp {
	  // Valor de salt para generar la clave de 256 bits a partir de la contraseña
	  // Este valor es necesario y se puede usar uno aleatorio o fijo. En este caso es fijo
	  static String SAL = "Sal y Pimienta";
	  // Iteraciones para generar la clave
	  static int ITERACIONES = 500;
	  // Longitud de la clave
	  static int LONGITUDCLAVE = 256;

	  public static void main(String[] args) throws Throwable{
	    
	    // El mensaje es el primer argumento o el valor por defecto Mensaje de prueba
	    String mensaje = (args.length > 0) ? args[0] : "Mensaje de prueba";
	    // La password es el segundo argumento o la cadena pass123
	    String password = (args.length > 1) ? args[1] : "pass123";
	    
	    // Creamos el algorimo de cifrado. Los parámetros deben ser identicos cuando se cree una instancia para descifrado
	    AlgoritmoCifrado algoritmo = new AlgoritmoCifradoAES(password, SAL, ITERACIONES, LONGITUDCLAVE);

	    // Ciframos, obteniendo el resultado en Base64
	    String resultado = algoritmo.encriptaABase64(mensaje);
	    
	    // Finalmente lo imprimimos
	    System.out.println("El mensaje cifrado es " + resultado);
	  }

	}