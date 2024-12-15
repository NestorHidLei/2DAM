package hlc.ud03.examen.datos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BloqueDatosApp {

	/**
	 * Variables estaticas RUTA - Ruta del archivo correcto ERRORES_RUTA - Rutas con
	 * errores de algun tipo CLAVES_VERIFICAR - Campos que se tienen que verificar
	 */
	private static final String RUTA = "./datos_previos/datos.txt";
	private static final String ERRORES_RUTA = "./datos_previos/conErrores/datos0%02d.txt";
	private static final String[] CLAVES_VERIFICAR = { "referencia", "nombre", "precio", "marca", "puntuacion",
			"fecha_inicio_venta", "dominio", "url", "correo_pedidos", "puntos_fuertes", "puntos_debiles" };

	public static void main(String[] args) {
		// Probar con el archivo correcto
		System.out.println("---- Prueba con archivo correcto ----");
		try {
			BloqueDatos bloque = new BloqueDatosEnFichero(RUTA);
			verificarCampos(bloque, CLAVES_VERIFICAR);
		} catch (BloqueDatosException e) {
			System.err.println("Error en archivo correcto: " + e.getMessage());
		}

		// Probar con archivos erróneos
		System.out.println("\n---- Prueba con archivos erróneos ----");
		for (int i = 1; i <= 33; i++) {
			String rutaArchivoError = String.format(ERRORES_RUTA, i);
			System.out.println("\nProbando archivo: " + rutaArchivoError);
			System.out.println("-----------------------------------------------------------------");
			try {
				BloqueDatos bloqueError = new BloqueDatosEnFichero(rutaArchivoError);
				verificarCampos(bloqueError, CLAVES_VERIFICAR);
			} catch (BloqueDatosException e) {
				System.err.println(
						"Error capturado en archivo " + String.format("datos0%02d.txt", i) + ": " + e.getMessage());
			}
		}
	}

	/**
	 * Método con un switch que verifique cada uno de los campos
	 * 
	 * @param bloque - Bloque de datos a analizar
	 * @param claves - Distintos parametros que tiene que verificar
	 */
	private static void verificarCampos(BloqueDatos bloque, String[] claves) {
		for (String clave : claves) {
			if (bloque.contieneDato(clave)) {
				String valor = bloque.getDato(clave);
				System.out.println("Dato encontrado - " + clave + ": " + valor);
				switch (clave) {
				case "referencia":
					verificarReferencia(valor);
					break;
				case "nombre":
					verificarNombre(valor);
					break;
				case "precio":
					verificarPrecio(valor);
					break;
				case "marca":
					verificarMarca(valor);
					break;
				case "puntuacion":
					verificarPuntuacion(valor);
					break;
				case "fecha_inicio_venta":
					if (!esFechaValida(valor)) {
						System.err.println("Error: La fecha no es correcta");
					}
					break;
				case "dominio":
					verificarDominio(valor);
					break;
				case "url":
					verificarUrl(valor);
					break;
				case "correo_pedidos":
					verificarCorreo(valor);
					break;
				case "puntos_fuertes":
					verificarPuntosFuertesYDebiles(bloque);
					break;
				case "puntos_debiles":
					verificarPuntosFuertesYDebiles(bloque);
					break;
				default:
					System.err.println("Clave desconocida: " + clave);
					break;
				}
			} else {
				System.err.println("Error: El archivo no contiene el dato: " + clave);
			}
		}
	}

	/**
	 * Metodo que verifica la referencia
	 * 
	 * @param referencia - Tiene que contener 13 numeros
	 */
	private static void verificarReferencia(String referencia) {
		if (!referencia.matches("\\d{13}")) {
			System.err.println("Error: La referencia debe tener exactamente 13 caracteres numéricos.");
		}
	}

	/**
	 * Metodo que verifica el nombre (sin repeticiones de patrones)
	 * 
	 * @param nombre - no puede estar vacio, ni ser null, tampoco se puede repetir
	 */
	private static void verificarNombre(String nombre) {
		if (nombre == null || nombre.isEmpty()) {
			System.err.println("Error: El nombre no puede estar vacío.");
		}
		if (tieneRepeticiones(nombre)) {
			System.err.println("Error: El nombre contiene repeticiones de patrones.");
		}
	}

	/**
	 * Metodo que verifica el precop
	 * 
	 * @param precio - tiene que ser un numero seguido de 2 decimales
	 */
	private static void verificarPrecio(String precio) {
		if (!precio.matches("\\d+\\.\\d{2}")) {
			System.err.println("Error: El precio debe tener exactamente dos decimales.");
		}
	}

	/**
	 * Metodo que verifica la marca
	 * 
	 * @param marca - tiene que cumplir con los mismos requisitos que el nombre
	 */
	private static void verificarMarca(String marca) {
		if (marca == null || marca.isEmpty()) {
			System.err.println("Error: La marca no puede estar vacía.");
		}
		if (tieneRepeticiones(marca)) {
			System.err.println("Error: La marca contiene repeticiones de patrones.");
		}
	}

	/**
	 * Metodo que verifica la puntuacion
	 * 
	 * @param puntuacion - tiene que ser un numero seguido de un decimal
	 */
	private static void verificarPuntuacion(String puntuacion) {
		if (!puntuacion.matches("\\d+\\.\\d{1}")) {
			System.err.println("Error: La puntuación debe ser un número con un solo decimal.");
		}
	}

	/**
	 * Verifica la fecha
	 * 
	 * @param fecha - debe de tener el siguiente formato (dd/mm/yyyy)
	 * @return true - Si la fecha es correcta. false - Si la fecha no es correcta
	 */
	private static boolean esFechaValida(String fecha) {
		// Verificar que la fecha tenga el formato dd/mm/yyyy
		String regexFecha = "\\d{2}/\\d{2}/\\d{4}";
		if (!fecha.matches(regexFecha)) {
			return false;
		}

		try {
			// Dividir la fecha en día, mes y año
			String[] partes = fecha.split("/");
			int dia = Integer.parseInt(partes[0]);
			int mes = Integer.parseInt(partes[1]);
			int año = Integer.parseInt(partes[2]);

			// Validar el rango del mes
			if (mes < 1 || mes > 12) {
				return false;
			}

			// Validar el rango del día según el mes y si es un año bisiesto
			int[] diasPorMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

			// Verificar año bisiesto y ajustar febrero si es necesario
			if (mes == 2 && esBisiesto(año)) {
				diasPorMes[1] = 29;
			}

			return dia >= 1 && dia <= diasPorMes[mes - 1];
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Metodo que comprueba si el año es bisiesto
	 * 
	 * @param año - Año de la fecha
	 * @return true - Si el año es bisiesto. False - Si no lo es.
	 */
	private static boolean esBisiesto(int año) {
		return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
	}

	/**
	 * Método que verifica el dominio
	 * @param dominio - Mismos conceptos que el nombre pero tiene que ser www.(dominio).com
	 */
	private static void verificarDominio(String dominio) {
		if (!dominio.matches("^[a-zA-Z.]+$")) {
			System.err.println("Error: El dominio solo puede contener letras y puntos.");
		}
		if (tieneRepeticiones(dominio)) {
			System.err.println("Error: El dominio contiene repeticiones de patrones.");
		}
	}

	/**
	 * Método que verifica la URL
	 * @param url - Tiene que tener el siguiente formato: http://www.(dominio).com/
	 */
	private static void verificarUrl(String url) {
		if (!url.matches("http://www\\.[a-zA-Z0-9-]+\\.com/")) {
			System.err.println("Error: La URL debe tener el formato http://www.xxxxx.com/");
		}
	}

	/**
	 * Metodo que verifica el correo
	 * @param correo
	 */
	private static void verificarCorreo(String correo) {
		if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			System.err.println("Error: El correo debe tener el formato cosa@cosa.com.");
		}
	}

	/**
	 * Método para verificar puntos fuertes y débiles
	 * @param bloque - Bloque de datos
	 */
	private static void verificarPuntosFuertesYDebiles(BloqueDatos bloque) {
		String puntosFuertes = bloque.getDato("puntos_fuertes");
		String puntosDebiles = bloque.getDato("puntos_debiles");

		if (puntosFuertes == null || puntosFuertes.isEmpty()) {
			throw new BloqueDatosException("El campo 'puntos_fuertes' no puede estar vacío.");
		}
		if (puntosDebiles == null || puntosDebiles.isEmpty()) {
			throw new BloqueDatosException("El campo 'puntos_debiles' no puede estar vacío.");
		}

		// Lista de palabras válidas para puntos fuertes y débiles
		Set<String> palabrasValidas = new HashSet<>(Arrays.asList("Precio", "Tecnologia", "Durabilidad",
				"Disponibilidad", "Marca", "Sostenible", "Calidad", "TamaÃ±o"));

		// Convertir los puntos fuertes y débiles a listas de palabras
		List<String> listaFuertes = Arrays.asList(puntosFuertes.split(","));
		List<String> listaDebiles = Arrays.asList(puntosDebiles.split(","));

		// Verificar que todas las palabras estén correctamente escritas
		for (String punto : listaFuertes) {
			if (!palabrasValidas.contains(punto.trim())) {
				throw new BloqueDatosException("Error en 'puntos_fuertes': palabra no válida o mal escrita - " + punto);
			}
		}

		for (String punto : listaDebiles) {
			if (!palabrasValidas.contains(punto.trim())) {
				throw new BloqueDatosException("Error en 'puntos_debiles': palabra no válida o mal escrita - " + punto);
			}
		}

		// Comprobar que no haya duplicados entre puntos fuertes y débiles
		Set<String> setFuertes = new HashSet<>(listaFuertes);
		Set<String> setDebiles = new HashSet<>(listaDebiles);

		for (String punto : setFuertes) {
			if (setDebiles.contains(punto)) {
				throw new BloqueDatosException(
						"La palabra '" + punto + "' está repetida en 'puntos_fuertes' y 'puntos_debiles'.");
			}
		}
	}

	/**
	 * Método para detectar repeticiones de patrones
	 * @param valor - Palabra que queremos ver si tiene repeticiones
	 * @return true - Si contiene repeticiones y false si no las contiene
	 */
	private static boolean tieneRepeticiones(String valor) {
		// Verificar repeticiones de subcadenas dentro de cadenas sin espacios
		// Expresión regular para detectar repeticiones consecutivas de una mismasubcadena
		// Busca patrones de entre 3 y 15 caracteres que se repiten al menos dos veces consecutivamente
		String regex = "(\\w{3,15})\\1{2,}";
		if (valor.matches(".*" + regex + ".*")) {
			return true;
		}

		// Verificar repeticiones de fragmentos de palabras consecutivas
		String[] palabras = valor.split("\\s+");
		// Número de palabras consecutivas para detectar repeticiones
		int longitudFragmento = 3;

		if (palabras.length >= longitudFragmento * 2) {
			Set<String> fragmentos = new HashSet<>();
			for (int i = 0; i <= palabras.length - longitudFragmento; i++) {
				String fragmento = String.join(" ", Arrays.copyOfRange(palabras, i, i + longitudFragmento));
				if (fragmentos.contains(fragmento)) {
					return true;
				}
				fragmentos.add(fragmento);
			}
		}

		// Verificar repeticiones consecutivas de palabras
		for (int i = 0; i < palabras.length - 1; i++) {
			if (palabras[i].equals(palabras[i + 1])) {
				return true;
			}
		}

		return false;
	}

}
