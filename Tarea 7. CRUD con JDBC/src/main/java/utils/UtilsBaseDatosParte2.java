package utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.Peliculas;

public class UtilsBaseDatosParte2 {

	private static final String SELECT_PELICULAS_POR_IDIOMA = "SELECT p.* FROM peliculascortas p inner join language l on l.language_id = p.language_id where l.name = ?;";

	// Método para crear el fichero de texto o binario dependiendo de los parámetros
	public static void peliculasToFichero(String language, boolean esTexto) {
		try (Connection connect = Conexion.conectar()) {
			PreparedStatement ps = connect.prepareStatement(SELECT_PELICULAS_POR_IDIOMA);
			ps.setString(1, language);

			// Ejecutar la consulta
			ResultSet rs = ps.executeQuery();

			// Lista para almacenar las películas
			List<Peliculas> peliculas = new ArrayList<>();

			// Recorrer los resultados
			while (rs.next()) {
				Peliculas pelicula = new Peliculas(rs.getInt("film_id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("length"),
						rs.getString("rating"));
				peliculas.add(pelicula);
			}

			// Verificar si se debe escribir en texto o binario
			if (esTexto) {
				// Crear el archivo CSV de texto
				try (BufferedWriter writer = new BufferedWriter(
						new FileWriter("peliculas_lang_" + language.toLowerCase() + ".csv"))) {
					// Escribir la cabecera
					writer.write("film_id;title;description;release_year;language_id;length;rating");
					writer.newLine();

					// Escribir cada película
					for (Peliculas pelicula : peliculas) {
						writer.write(pelicula.getFilm_id() + ";" + pelicula.getTitle() + ";" + pelicula.getDescription()
								+ ";" + pelicula.getRelease_year() + ";" + pelicula.getLanguage_id() + ";"
								+ pelicula.getLength() + ";" + pelicula.getRaiting());
						writer.newLine();
					}
				}
			} else {
				// Crear el archivo binario .dat
				try (ObjectOutputStream oos = new ObjectOutputStream(
						new FileOutputStream("peliculas_lang_" + language.toLowerCase() + ".dat"))) {
					// Escribir las películas como objetos Pelicula
					for (Peliculas pelicula : peliculas) {
						oos.writeObject(pelicula);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cargarPeliculasBinario(String ruta) {
		if (!ruta.endsWith(".dat")) {
            throw new IllegalArgumentException("El archivo debe de ser .dat");
        }
	}

	
}
