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
	}
	
	private static boolean esPrimo(int numero) {
		// Si el número es divisible entre uno o entre si mismo es primo
		if (numero % 1 == 0 || numero % numero == 0) return true;
		//En cualquier otro caso es false
		return false;
	}
}
