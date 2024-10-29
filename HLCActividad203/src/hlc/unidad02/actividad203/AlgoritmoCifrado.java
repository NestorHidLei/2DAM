package hlc.unidad02.actividad203;

public abstract class AlgoritmoCifrado {
	  protected String password;
	  
	  public AlgoritmoCifrado(String password) {
	    this.password = password;
	  }
	  
	  // Cifra usando el algoritmo y devuelve el resultado en base 64
	  public abstract String encriptaABase64(String mensaje) throws Throwable;
	  
	  // Descrifra un mensaje en formato Base64 y devuelve el mensaje descifrado en texto plano 
	  public abstract String desencriptaDesdeBase64(String mensaje) throws Throwable;
	  
	}