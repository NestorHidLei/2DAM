package hlc.auth.totp;

import hlc.auth.otp.GeneradorHOTP;

public class SistemaSeguridadTOTP {
	// Tiempo inicial
	private static final int TIEMPO_INICIAL = 0;
	//Intervalo de tiempo en segundos
	private static final int INTERVALO =30;
	
	private GeneradorHOTP generadorHOTP;
	
	/**
	 * Constructor del sistema de seguridad
	 */
	public  SistemaSeguridadTOTP() {
		//Por defecto usara el algoritmo SHA-1 por defecto
		this.generadorHOTP = new GeneradorHOTP();
	}
	
	/**
	 *  Genera un código TOTP basado en el secreto 
	 *  del usuario y el tiempo actual.
	 * @param secreto - Secreto del usuario (en hexadecimal).
	 * @return Codigo TOTP de 6 dígitos.
	 */
	public String generarTOTP(String secreto) {
		// Tiempo actual en segundos
		long tiempoActual = System.currentTimeMillis() / 1000;
		// Contador basado en el intervalo
		long contador = (tiempoActual - TIEMPO_INICIAL) / INTERVALO;
		// Genera el codigo HOTP
		return generadorHOTP.genera(secreto, contador);
	}
	/**
	 * Verifica si el código TOTP proporcionado es válido.
	 * @param secreto - Secreto del usuario (en hexadecimal).
	 * @param codigo - Código TOTP proporcionado por el usuario.
	 * @return true si el código es válido, false en caso contrario.
	 */
	public boolean verificarTOTP(String secreto, String codigo){ 
		String totpGenerado = generarTOTP(secreto);
		return totpGenerado.equals(codigo);
	}
		
}
