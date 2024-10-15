package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import models.Empleados;

public class UtilsFicheroArray {

	public static String RUTA_FICHERO_NORMAL = "src/utils/Empleados.txt";

	public static String RUTA_FICHERO_BINARIO = "src/utils/Empleados_binario.txt";

	
	/*
	 * Almacena todos los Empleados de un Array o ArrayList en un archivo de texto.
	 */
	public static void toArchivoTexto(ArrayList<Empleados> empleados) {
		// Intenta escribir en el archivo
		try (BufferedWriter salida = new BufferedWriter(new FileWriter(RUTA_FICHERO_NORMAL))) {
			// Recorre el arraylist y lo escribe
			for (int i = 0; i < empleados.size(); i++) {
				salida.write("\n" + empleados.get(i).getEmpresa() + "," + empleados.get(i).getEdad() + ","
						+ empleados.get(i).getNum_empleados());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Almacena todos los Empleados del Array o ArrayList en un archivo binario, de
	 * forma inversa, empezando por el último.
	 * 
	 * @param empleados - lista que almacename los empleados
	 */
	public static void toArchivoBinarioInverso(ArrayList<Empleados> empleados) {
		// Intenta escribir en el archivo
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO_BINARIO));) {
			for (int i = empleados.size() - 1; i >= 0; i--) {
				salida.writeObject(empleados.get(i));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Que lea empleados de un archivo de texto y los imprima por pantalla.
	 * 
	 * @param ruta - ruta del archivo
	 * @return Un Arraylist de empleados
	 */
	public static ArrayList<Empleados> leer_Empleados(String ruta) {
		// Creamos el arraylist
		ArrayList<Empleados> listaEmpleados = new ArrayList<Empleados>();
		// Intenta leer el archivo
		try (BufferedReader entrada = new BufferedReader(new FileReader(RUTA_FICHERO_NORMAL))) {
			String linea = entrada.readLine();

			// Mientras que la linea no sea null
			while ((linea = entrada.readLine()) != null) {
				// divide esa liena en partes y remplaza los ("") y la splitea en tres partes
				String[] partes_empleados = linea.replace("\"", "").split(",");
				// Creamos el empleados y lo añadimos al arrayList
				Empleados empleado = new Empleados(partes_empleados[0], Integer.parseInt(partes_empleados[1]),
						Integer.parseInt(partes_empleados[2]));
				listaEmpleados.add(empleado);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Returnea el array
		return listaEmpleados;
	}

	/**
	 * Lea empleados de un archivo binario y los imprima por pantalla.
	 * 
	 * @param ruta - ruta del archivo
	 * @return Un arraylist de empleados
	 */
	public static ArrayList<Empleados> leer_Empleados_b(String ruta) {
		// Creamos el arraylist
		ArrayList<Empleados> listaEmpleados = new ArrayList<Empleados>();
		// Intenta leer el archivo
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(RUTA_FICHERO_BINARIO))) {
			// Mientras que la linea no sea null
			while (true) {
				Empleados empleados = (Empleados) entrada.readObject();
				// Creamos el empleados y lo añadimos al arrayList
				listaEmpleados.add(empleados);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Returnea el array
		return listaEmpleados;
	}

}
