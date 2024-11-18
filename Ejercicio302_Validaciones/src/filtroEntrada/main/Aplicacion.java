package filtroEntrada.main;

import java.io.*;
import java.util.*;

public class Aplicacion {

	public static void main(String[] args) {
		//archivo
		String archivo = "datos.txt"; 

		// Leer los datos del archivo
		Map<String, String> datos = leerArchivo(archivo);
		if (datos.isEmpty()) {
			System.out.println("�ERROR! No se pudieron leer los datos.");
			return;
		}

		// Mostrar los datos le�dos
		System.out.println("\n********* DATOS LE�DOS DEL ARCHIVO *********");
		System.out.println("------------------------------------------------");
		for (Map.Entry<String, String> entry : datos.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		System.out.println("------------------------------------------------");

		// Validaci�n de los campos
		boolean valido = true;
		System.out.println("\n********* VALIDANDO LOS DATOS *********");

		if (!Validador.validarNombreApellidos(datos.get("nombreApellidos"))) {
			mostrarError("El campo 'Nombre y Apellidos' no es v�lido.");
			valido = false;
		}else if (!Validador.validarFechaNacimiento(datos.get("fechaNacimiento"))) {
			mostrarError("La 'Fecha de Nacimiento' no es v�lida.");
			valido = false;
		}else if (!Validador.validarTelefonoFijo(datos.get("telefonoFijo"))) {
			mostrarError("El 'Tel�fono Fijo' no es v�lido.");
			valido = false;
		}else if (!Validador.validarTelefonoMovil(datos.get("telefonoMovil"))) {
			mostrarError("El 'Tel�fono M�vil' no es v�lido.");
			valido = false;
		}else if (!Validador.validarNumeroHijos(datos.get("numeroHijos"))) {
			mostrarError("El 'N�mero de Hijos' no es v�lido.");
			valido = false;
		}else if (!Validador.validarComunidadAutonoma(datos.get("comunidad"))) {
			mostrarError("La 'Comunidad Aut�noma' no es v�lida.");
			valido = false;
		}else if (!Validador.validarLocalidad(datos.get("localidad"))) {
			mostrarError("La 'Localidad' no es v�lida.");
			valido = false;
		}else if (!Validador.validarIntereses(datos.get("intereses"))) {
			mostrarError("Los 'Intereses' no son v�lidos.");
			valido = false;
		}

		// Resultados de la validaci�n
		System.out.println("\n********* RESULTADO DE LA VALIDACI�N *********");
		if (valido) {
			System.out.println("�Todos los campos son v�lidos!");
		} else {
			System.out.println("Existen errores en el formulario. Por favor, revise los campos marcados.");
		}
	}

	/**
	 * M�todo para mostrar errores de validaci�n de forma organizada
	 * @param mensaje
	 */
	private static void mostrarError(String mensaje) {
		System.out.println("ERROR: " + mensaje);
	}

	/**
	 * M�todo para leer los datos del archivo
	 * @param archivo
	 * @return
	 */
	public static Map<String, String> leerArchivo(String archivo) {
		Map<String, String> datos = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("=");
				if (partes.length == 2) {
					datos.put(partes[0], partes[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return datos;
	}
}
