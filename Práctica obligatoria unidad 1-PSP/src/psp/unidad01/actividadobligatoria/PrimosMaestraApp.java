package psp.unidad01.actividadobligatoria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PrimosMaestraApp {
	
	//Valores máximos y mínimos de la lista
	private static final int VALOR_MINIMO = 2;
	private static final int VALOR_MAXIMO = 2147483647;
	//Número de núcleos disponibles
	private static final int PROCESOS = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		// Adquirimos los atributos
		int parametro1 = Integer.parseInt(args[0]);
		int parametro2 = Integer.parseInt(args[1]);
		
		try {	
			//Verificación de los dos primeros parametros
			if (parametro1 < VALOR_MINIMO || parametro1 > VALOR_MAXIMO || parametro2 < VALOR_MINIMO || parametro2 > VALOR_MAXIMO) {
				System.err.println(
						"ERROR: Los parámetros deben ser enteros entre " + VALOR_MINIMO + " y " + VALOR_MAXIMO + ".");
				return;
			}
			
			if (parametro1 > parametro2) {
			    System.err.println("ERROR: El valor inicial debe ser menor o igual al valor final.");
			    return;
			}
			
			// si hay tres parametros
			if (args.length == 3) {
				// Adquirimos los atributos
				int numCores = Integer.parseInt(args[2]);
				
				//Verifica que no sea cero
				if (numCores <= 0) {
					System.err.println("ERROR: El número de núcleos debe ser un entero positivo.");
					return;
				}
				
				//Verifica que el número de processos no sea mayor al numero de cores
				if (numCores > PROCESOS) {
					System.err.println(
							"ERROR: Excesivo número de procesos. No se pueden asignar más procesos que núcleos disponibles ("
									+ PROCESOS + ").");
					return;
				}
				
				 // Calcular el rango de números para cada proceso
	            int rango = (parametro2 - parametro1 + 1) / numCores;
	            List<Process> procesos = new ArrayList<>();
			}
			
		} catch (NumberFormatException e) {
			System.err.println("Error: Los números tienen que ser enteros");
		}
		
		ProcessBuilder proceso;
		
		try {
			proceso = new ProcessBuilder("java", "-cp", "./bin",
			        "psp.unidad01.practicaobligatoria.esclava.PrimosEsclavaApp",
			        String.valueOf(parametro1), String.valueOf(parametro2));

			// Startea el proceso escclavo
			Process procesoEsclavo = proceso.start();

			// Lee desde el proceso esclavo
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(procesoEsclavo.getInputStream()))) {
			    String line;
			    System.out.println("Numeros Primos  ");
			    System.out.println("----------------");
			    //Recorro todas laslienas del proceso esclavo
			    while ((line = reader.readLine()) != null) {
			        System.out.print(line + " ");
			    }
			}
			//Esperamos a la respuesta del proceso
			procesoEsclavo.waitFor();
            
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
