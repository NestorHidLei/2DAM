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
	public static final String RUTA = "./datos_previos/datos.txt";
	public static final String ERRORES_RUTA = "./datos_previos/conErrores/datos0%02d.txt";
	public static final String[] CLAVES_VERIFICAR = { "referencia", "nombre", "precio", "marca", "puntuacion",
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

		// Probar con archivos erroneos
		System.out.println("\n---- Prueba con archivos erroneos ----");
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
	 * Metodo con un switch que verifique cada uno de los campos
	 * 
	 * @param bloque - Bloque de datos a analizar
	 * @param claves - Distintos parametros que tiene que verificar
	 */
	public static void verificarCampos(BloqueDatos bloque, String[] claves) {
		for (String clave : claves) {
			if (bloque.contieneDato(clave)) {
				String valor = bloque.getDato(clave);
//				System.out.println("Dato encontrado - " + clave + ": " + valor);
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
						System.err.println("Error: La fecha no es correcta, tiene que tener el formato dd/mm/aaaa");
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
	public static void verificarReferencia(String referencia) {
		//Verifica que sean 13 caracteres numericos
		if (!referencia.matches("\\d{13}")) {
			System.err.println("Error: La referencia debe tener exactamente 13 caracteres numericos.");
		}
		
		//Verifica que los dos primeros digitos esten comprendidos entre 34 y 67
		int primerosDosDigitos = Integer.parseInt((String) referencia.subSequence(0, 2));
		
		if(primerosDosDigitos < 34 || primerosDosDigitos > 67) {
			System.err.println("Error: Los dos primeros digitos de la referencia deben estar comprendidos entre 34 y 67");
		}
	}

	/**
	 * Metodo que verifica el nombre (sin repeticiones de patrones)
	 * 
	 * @param nombre - no puede estar vacio, ni ser null, tampoco se puede repetir
	 */
	public static void verificarNombre(String nombre) {
		if (nombre == null || nombre.isEmpty()) {
			System.err.println("Error: El nombre no puede estar vacio.");
		}
		if(nombre.length()>200) {
			System.err.println("Error: El contenido del nombre excede la longitud maxima (200)");
		}
	}

	/**
	 * Metodo que verifica el precop
	 * 
	 * @param precio - tiene que ser un numero seguido de 2 decimales
	 */
	public static void verificarPrecio(String precio) {
		if (!precio.matches("\\d+\\.\\d{2}")) {
			System.err.println("Error: El precio debe tener exactamente dos decimales.");
		}
		
	}

	/**
	 * Metodo que verifica la marca
	 * 
	 * @param marca - tiene que cumplir con los mismos requisitos que el nombre
	 */
	public static void verificarMarca(String marca) {
		if(marca.length()>100) {
			System.err.println("Error: El contenido de la marca excede la longitud maxima (100)");
		}
	}

	/**
	 * Metodo que verifica la puntuacion
	 * 
	 * @param puntuacion - tiene que ser un numero seguido de un decimal
	 */
	public static void verificarPuntuacion(String puntuacion) {
		//Verifica que tenga el patron correcto
		if (!puntuacion.matches("\\d+\\.\\d{1}")) {
			System.err.println("Error: La puntuacion debe ser un numero con un solo decimal. (N.N)");
		}
		
		double puntuacionCorrecta = Double.parseDouble(puntuacion.replace(",", "."));
		
		//Verifica si esta entre los varemos correspondiente
		if(puntuacionCorrecta < 0.0 || puntuacionCorrecta > 5.0) {
			System.err.println("Error: El valor de la puntuacion debe estar dentro del rango indicado (0.0 - 5.0)");
		}

	}

	/**
	 * Verifica la fecha
	 * 
	 * @param fecha - debe de tener el siguiente formato (dd/mm/yyyy)
	 * @return true - Si la fecha es correcta. false - Si la fecha no es correcta
	 */
	public static boolean esFechaValida(String fecha) {
		// Verificar que la fecha tenga el formato dd/mm/yyyy
		String regexFecha = "\\d{2}/\\d{2}/\\d{4}";
		if (!fecha.matches(regexFecha)) {
			return false;
		}

		try {
			// Dividir la fecha en dia, mes y anio
			String[] partes = fecha.split("/");
			int dia = Integer.parseInt(partes[0]);
			int mes = Integer.parseInt(partes[1]);
			int anio = Integer.parseInt(partes[2]);

			// Validar el rango del mes
			if (mes < 1 || mes > 12) {
				return false;
			}

			// Validar el rango del dia segun el mes y si es un anio bisiesto
			int[] diasPorMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

			// Verificar anio bisiesto y ajustar febrero si es necesario
			if (mes == 2 && esBisiesto(anio)) {
				diasPorMes[1] = 29;
			}

			return dia >= 1 && dia <= diasPorMes[mes - 1];
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Metodo que comprueba si el anio es bisiesto
	 * 
	 * @param anio - Anio de la fecha
	 * @return true - Si el anio es bisiesto. False - Si no lo es.
	 */
	public static boolean esBisiesto(int anio) {
		return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
	}

	/**
	 * Metodo que verifica el dominio
	 * @param dominio - Mismos conceptos que el nombre pero tiene que ser www.(dominio).com
	 * 
	 */
	public static void verificarDominio(String dominio) {
		if (!dominio.matches("^[a-zA-Z0-9.-]+$")) {
			System.err.println("Error: Formato incorrecto");
		}
		if(dominio.startsWith("www.\\.[a-zA-Z]")) {
			System.err.println("Error: El dominio solo puede empezar por una letra");
		}
		if(dominio.endsWith("-.com")) {
			System.err.println("Error: El dominio no puede acabar en guion(-)");
		}
		if(dominio.length() <= 1 || dominio.length() >=63) {
			System.err.println("Error: El dominio debe estar comprendido entre 1 y 63 caracteres de longitud");
		}
	}

	/**
	 * Metodo que verifica la URL
	 * @param url - Tiene que tener el siguiente formato: http://www.(dominio).com/
	 */
	public static void verificarUrl(String url) {
		if (!url.matches("http://www\\.[a-zA-Z0-9-]+\\.com/")) {
			System.err.println("Error: La URL debe tener el formato http://www.xxxxx.com/");
		}
	}

	/**
	 * Metodo que verifica el correo
	 * @param correo
	 */
	public static void verificarCorreo(String correo) {
		if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			System.err.println("Error: El correo debe tener el formato cosa@cosa.com.");
		}
	}

	/**
	 * Metodo para verificar puntos fuertes y debiles
	 * @param bloque - Bloque de datos
	 */
	public static void verificarPuntosFuertesYDebiles(BloqueDatos bloque) {
		String puntosFuertes = bloque.getDato("puntos_fuertes");
		String puntosDebiles = bloque.getDato("puntos_debiles");


		// Lista de palabras validas para puntos fuertes y debiles
		Set<String> palabrasValidas = new HashSet<>(Arrays.asList("Precio", "Tecnologia", "Durabilidad",
				"Disponibilidad", "Marca", "Sostenible", "Calidad", "Tamaño"));

		// Convertir los puntos fuertes y debiles a listas de palabras
		List<String> listaFuertes = Arrays.asList(puntosFuertes.split(","));
		List<String> listaDebiles = Arrays.asList(puntosDebiles.split(","));

		// Verificar que todas las palabras esten correctamente escritas
		for (String punto : listaFuertes) {
			if (!palabrasValidas.contains(punto.trim())) {
				throw new BloqueDatosException("Error en 'puntos_fuertes': palabra no valida o mal escrita - " + punto);
			}
		}

		for (String punto : listaDebiles) {
			if (!palabrasValidas.contains(punto.trim())) {
				throw new BloqueDatosException("Error en 'puntos_debiles': palabra no valida o mal escrita - " + punto);
			}
		}
		
		int numeroDeComasDebiles = puntosDebiles.split(",", -1).length - 1;
		if(numeroDeComasDebiles == 5) {
			throw new BloqueDatosException("Error: Hay mas de cinco palabras");
		}
		
		int numeroDeComasFuertes = puntosFuertes.split(",", -1).length - 1;
		if(numeroDeComasFuertes == 5) {
			throw new BloqueDatosException("Error: Hay mas de cinco palabras");
		}
	}

}