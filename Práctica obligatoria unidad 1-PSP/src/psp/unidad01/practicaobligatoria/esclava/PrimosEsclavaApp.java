package psp.unidad01.practicaobligatoria.esclava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrimosEsclavaApp {
	public static void main(String[] args) {
		//Numero inicial y final de la lista
		int numeroInicial = Integer.parseInt(args[0]);
		int numeroFinal = Integer.parseInt(args[1]);
		//Lista de numeros
		List<Integer> listaNumeros = new ArrayList<Integer>();
		//Lista de numeros primos
		List<Integer> listaNumerosPrimos = new ArrayList<Integer>();
		
		//Cogemos la información necasario
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			//Añade todos los números a la lista
			for (int i = numeroInicial; i <= numeroFinal; i++) {
				listaNumeros.add(i);
			}
			//Recorremos la lista de numeros
			for (int i = 0; i < listaNumeros.size(); i++) {
				//vemos si el numero es primo
				if(esPrimo(listaNumeros.get(i))) {
					//Añadimos a la lista de primos
					listaNumerosPrimos.add(listaNumeros.get(i));
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer los datos");
		}
	    System.out.println(listaNumerosPrimos);

	}
	
	/**
	 * Metodo que dice si un numero es primo o no
	 * @param numero - numero a verificar
	 * @return
	 */
	private static boolean esPrimo(int numero) {
		//Si el numero es uno no es primo.
	    if (numero <= 1) return false;
		//Si el numero es dos es primo.
	    if (numero == 2) return true;
	    //Si el numero dividido dos es igual a cero, dicho numero no es primo.
	    if (numero % 2 == 0) return false;
	    //Va verificando cada dos numeros si es divisible.
	    for (int i = 3; i <= Math.sqrt(numero); i += 2) {
	        if (numero % i == 0) return false;
	    }
	    return true;
	}

}
