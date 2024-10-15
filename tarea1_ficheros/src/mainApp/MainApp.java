package mainApp;

import java.util.ArrayList;

import models.Empleados;
import utils.UtilsFicheroArray;

public class MainApp extends UtilsFicheroArray {

	public static void main(String args[]) {

		// Crear algunos empleados para probar
		ArrayList<Empleados> empleados = new ArrayList<>();
		empleados.add(new Empleados("Ayuntamiento de Gijón/Xixón", 17, 1));
		empleados.add(new Empleados("Ayuntamiento de Gijón/Xixón", 19, 1));
		empleados.add(new Empleados("Ayuntamiento de Gijón/Xixón", 21, 2));
		empleados.add(new Empleados("Ayuntamiento de Gijón/Xixón", 22, 2));

		// Probar el método para almacenar empleados en un archivo binario de forma
		// inversa
		System.out.println("Guardando empleados en archivo binario (orden inverso)...\n");
		toArchivoBinarioInverso(empleados);

		// Probar el método para leer empleados desde un archivo binario
		System.out.println("Leyendo empleados desde archivo binario:");
		System.out.println("----------------------------------------");
		ArrayList<Empleados> empleadosLeidosBinario = leer_Empleados_b(RUTA_FICHERO_BINARIO);
		for (Empleados emp : empleadosLeidosBinario) {
			System.out.println(emp + "\n");
		}

		// Probar el método para almacenar empleados en un archivo de texto
		System.out.println("Guardando empleados en archivo de texto... \n");
		toArchivoTexto(empleados);

		// Probar el método para leer empleados desde un archivo de texto
		System.out.println("Leyendo empleados desde archivo de texto:");
		System.out.println("-----------------------------------------");
		ArrayList<Empleados> empleadosLeidosTexto1 = leer_Empleados(RUTA_FICHERO_NORMAL);
		for (Empleados emp : empleadosLeidosTexto1) {
			System.out.println(emp + "\n");
		}

	}

}
