package mainApp;

import utils.UtilsBaseDatos;
import utils.UtilsBaseDatosParte2;

public class MainApp extends UtilsBaseDatos{
	public static void main(String[] args) throws Exception {
//		crearTablaPeliculas();
//		insertarRegistrosTablaPeliculas();
		cargarPeliculas(RUTA_FICHERO_NORMAL);
//		peliculasToFichero("English", true);
//		peliculasToFichero("English", false);

		
	}
}
