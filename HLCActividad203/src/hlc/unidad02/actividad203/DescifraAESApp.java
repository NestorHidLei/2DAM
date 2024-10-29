package hlc.unidad02.actividad203;

public class DescifraAESApp {
	  // Valor de salt para generar la clave de 256 bits a partir de la contraseña
	  // Este valor es necesario y se puede usar uno aleatorio o fijo. En este caso es fijo
	  static String SAL = "Sal y Pimienta";
	  // Iteraciones para generar la clave
	  static int ITERACIONES = 500;
	  // Longitud de la clave
	  static int LONGITUDCLAVE = 256;

	  public static void main(String[] args) throws Throwable{
		  // Compruebo que tenga exactamente dos argumentos
		  if (args.length == 2) {
			  	String mensajeCifrado = args[0];
			  	String password = args[1];
			    // Creamos el algoritmo de desccifrado. Los parametros deben ser los mismos empleados en el cifrado
			    AlgoritmoCifrado algoritmo = new AlgoritmoCifradoAES(password, SAL, ITERACIONES, LONGITUDCLAVE);
			    
			    // Obtenemos el resultado
			    String resultado = algoritmo.desencriptaDesdeBase64(mensajeCifrado);
			    
			    // Y lo imprimimos
			    System.out.println("El mensaje descifrado es " + resultado);
		  } else {
			  System.err.println("Error se esperaban dos parámetros\n");
			  instrucciones();
		  }
	  }
		private static void instrucciones() {
			System.out.println("\nInstruciones de uso:");
			System.out.println("\tEste programa genera el texto en claro de uno cifrado");
			System.out.println("\tSe esperan dos parámetros");
			System.out.println("\t\tEl mensaje cifrado en base64");
			System.out.println("\t\tLa contraseña que se utilizó para generar el mensaje.");
		}

	}