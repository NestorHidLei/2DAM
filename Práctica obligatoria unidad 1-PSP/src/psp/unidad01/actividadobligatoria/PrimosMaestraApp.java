package psp.unidad01.actividadobligatoria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrimosMaestraApp {

	// Atributos estaticos
	private static final int VALOR_MINIMO = 2;
	private static final int VALOR_MAXIMO = 2147483647;
	private static final int PROCESOS = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		// Salta un error si se proporcionan menos de parametros de los necesitados como
		// mínimo.
		if (args.length < 2) {
			mensajeError("Insuficiente número de parámetros");
			return;
		}

		try {
			// Declaramos los atributos
			int parametro1 = Integer.parseInt(args[0]);
			int parametro2 = Integer.parseInt(args[1]);

			// Salta un error si alguno de los parametros esta fuera del rango.
			if (parametro1 < VALOR_MINIMO || parametro1 > VALOR_MAXIMO || parametro2 < VALOR_MINIMO
					|| parametro2 > VALOR_MAXIMO) {
				mensajeError("Enteros no válidos, valores válidos entre " + VALOR_MINIMO + " y " + VALOR_MAXIMO);
				return;
			}

			// Salta un error si el parámetro 1 es mayor que el parámetro 2.
			if (parametro1 > parametro2) {
				mensajeError("ERROR: El valor inicial debe ser menor o igual al valor final.");
				return;
			}

			// Declaramos el atributo numCores si hay 3 parámetros
			int numCores = args.length == 3 ? Integer.parseInt(args[2]) : 1;

			// Salta error si el numero de cores es menor o igual a cero.
			if (numCores <= 0) {
				mensajeError("El valor del número de procesos no es positivo o no es un entero");
				return;
			}

			// Salta error si el numero de cores es mayor al número de procesos.
			if (numCores > PROCESOS) {
				mensajeError("Se solicitan más procesos que núcleos disponibles (" + PROCESOS + ") tiene el sistema");
				return;
			}

			// Declaramos un rango entre el parametro 2 y el 1
			int rangoTotal = parametro2 - parametro1 + 1;

			// Salta error en el caso de que haya más cores que números a analizar, minimo
			// un número por proceso.
			if (numCores > rangoTotal) {
				mensajeError("Se solicitan más procesos que números se quieren analizar");
				return;
			}

			// Rango para saber cuantos números tiene que analizar cada core.
			int rango = rangoTotal / numCores;
			// Lista de números primos.
			List<Integer> primosTotales = new ArrayList<>();
			// Tiempo total de la ejecución.
			long tiempoInicioTotal = System.currentTimeMillis();
			// Numero Total de primos.
			int totalPrimos = 0;
			// Total de números analizados.
			int totalNumerosAnalizados = 0;

			for (int i = 0; i < numCores; i++) {
				// Sacamas el primer número a analizar
				int inicio = parametro1 + i * rango;
				// Sacamas el último número a analizar
				int fin = (i == numCores - 1) ? parametro2 : inicio + rango - 1;

				// Creamos el proceso Esclavo.
				ProcessBuilder proceso = new ProcessBuilder("java", "-jar", "PrimosEsclavoApp.jar",
						String.valueOf(inicio), String.valueOf(fin));
				// Sacamos el tiempo antes de la ejecución de los procesos
				long tiempoInicio = System.currentTimeMillis();
				Process procesoEsclavo = proceso.start();

				// Sacamos la informacion del proceso esclavo.
				try (BufferedReader reader = new BufferedReader(
						new InputStreamReader(procesoEsclavo.getInputStream()))) {
					// Declaramos las variables de la información a sacar
					List<Integer> primos = new ArrayList<>();
					int contadorPrimos = 0;
					int numerosAnalizados = fin - inicio + 1;

					// Bucle para sacar la informacion
					String line;
					while ((line = reader.readLine()) != null) {
						primos.add(Integer.parseInt(line));
						contadorPrimos++;
					}

					// Sacamos el tiempo en el que termino de analizar y con él sacamos el tiempo
					// empleado en el proceso actual.
					long tiempoFinal = System.currentTimeMillis();
					long tiempoEmpleado = tiempoFinal - tiempoInicio;

					// Añadimos a los diferentes atributos
					primosTotales.addAll(primos);
					totalPrimos += contadorPrimos;
					totalNumerosAnalizados += numerosAnalizados;

					// Imprimimos por consola tanto la cantidad de procesos como la cantidad de
					// números analizados.
					System.out.printf(
							"Proceso P%d tiempo empleado: %d ms se han encontrado: %d números primos entre los %d analizados%n",
							i, tiempoEmpleado, contadorPrimos, numerosAnalizados);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// sacamos el tiempo total empleado entre tosos los procesos y lo mostramos por
			// pantalla junto a la cantidad de números primos encontrados y analizados
			long tiempoTotalEmpleado = System.currentTimeMillis() - tiempoInicioTotal;
			System.out.printf(
					"Tiempo total empleado en el programa: %d ms se han encontrado %d números primos entre los %d analizados%n",
					tiempoTotalEmpleado, totalPrimos, totalNumerosAnalizados);
			// Imprimimos la lista de primos
			System.out.println(primosTotales);

		} catch (NumberFormatException e) {
			mensajeError("El valor del número de procesos no es positivo o no es un entero");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mensaje de error
	 * 
	 * @param mensajeError - Encabezado del error depende del error que salte.
	 */
	private static void mensajeError(String mensajeError) {
		System.err.println(mensajeError);
		System.out.println("USO:\njava -jar maestra.jar valor1 valor2 valor3");
		System.out.println("valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre "
				+ VALOR_MINIMO + " y " + VALOR_MAXIMO + ")");
		System.out.println("valor2 -> (obligatorio) segundo extremo del rango de valores a analizar (entre "
				+ VALOR_MINIMO + " y " + VALOR_MAXIMO + ")");
		System.out
				.println("valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene " + PROCESOS + " núcleos");
	}
}
