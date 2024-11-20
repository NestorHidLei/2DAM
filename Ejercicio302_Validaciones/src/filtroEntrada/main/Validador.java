package filtroEntrada.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validador {

	/**
	 * Valida que el nombre y apellidos est�n correctamente formateados (primera
	 * letra may�scula)
	 * 
	 * @param nombreApellidos - El nombre y los apellidos del usuario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
	public static boolean validarNombreApellidos(String nombreApellidos) {
		if (nombreApellidos == null || nombreApellidos.isEmpty()) {
			return false;
		}
		String[] palabras = nombreApellidos.split(" ");
		for (String palabra : palabras) {
			if (!Character.isUpperCase(palabra.charAt(0))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Valida que la fecha de nacimiento sea v�lida y anterior a la fecha actual
	 * 
	 * @param fechaNacimiento - fecha de nacimiento del usuario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
	public static boolean validarFechaNacimiento(String fechaNacimiento) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date fecha = sdf.parse(fechaNacimiento);
			return fecha.before(new Date());
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Valida que el tel�fono fijo est� en formato espa�ol (9 d�gitos y comienza con
	 * 9)
	 * 
	 * @param telefonoFijo
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
	public static boolean validarTelefonoFijo(String telefonoFijo) {
		if (telefonoFijo == null || telefonoFijo.isEmpty()) {
			return true; // El tel�fono fijo es opcional
		}
		return Pattern.matches("^9\\d{8}$", telefonoFijo);
	}

	/**
	 * Valida que el tel�fono m�vil est� en formato nacional o internacional
	 * 
	 * @param telefonoMovil
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
	public static boolean validarTelefonoMovil(String telefonoMovil) {
		return Pattern.matches("^(\\+34|0034|34)?[6-9]\\d{8}$", telefonoMovil);
	}

	/**
	 * Valida que el n�mero de hijos sea un n�mero entero mayor o igual a cero
	 * 
	 * @param numeroHijos
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
	public static boolean validarNumeroHijos(String numeroHijos) {
		try {
			int hijos = Integer.parseInt(numeroHijos);
			return hijos >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Valida que la comunidad aut�noma sea una de las tres opciones:
	 * "Andaluc�a","Extremadura", "Otra"
	 * 
	 * @param comunidad
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
	public static boolean validarComunidadAutonoma(String comunidad) {
		return comunidad.equals("Andalucia") || comunidad.equals("Extremadura") || comunidad.equals("Otra");
	}

	/**
	 * Valida que la localidad tenga entre 1 y 50 caracteres y que la primera letra
	 * est� en may�scula
	 * 
	 * @param localidad
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
	public static boolean validarLocalidad(String localidad) {
		if (localidad == null || localidad.length() < 1 || localidad.length() > 50) {
			return false;
		}
		return Character.isUpperCase(localidad.charAt(0));
	}

	/**
	 * Valida que los intereses sean entre 1 y 5 elementos separados por comas y no
	 * repetidos
	 * 
	 * @param intereses
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
	public static boolean validarIntereses(String intereses) {
		String[] listaIntereses = intereses.split(",");
		if (listaIntereses.length < 1 || listaIntereses.length > 5) {
			return false;
		}
		// Lista de intereses v�lidos
		String[] interesesValidos = { "Lectura", "Videojuegos", "Series", "Pel�culas", "Actividades al aire libre",
				"deportes", "Tecnolog�a", "Manualidades" };
		for (String interes : listaIntereses) {
			boolean valido = false;
			for (String validoInteres : interesesValidos) {
				if (interes.equals(validoInteres)) {
					valido = true;
					break;
				}
			}
			if (!valido) {
				return false;
			}
		}
		// Verificar que no haya intereses repetidos
		for (int i = 0; i < listaIntereses.length; i++) {
			for (int j = i + 1; j < listaIntereses.length; j++) {
				if (listaIntereses[i].equals(listaIntereses[j])) {
					return false;
				}
			}
		}
		return true;
	}
}
