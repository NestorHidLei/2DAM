package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import conexion.Conexion;
import model.Peliculas;

public class UtilsBaseDatos {
	
	//Querys sql
	private static final String CREATE_TABLA_PELICULAS_CORTAS = "CREATE TABLE PeliculasCortas (\r\n"
			+ "    film_id SMALLINT ,\r\n"
			+ "    title VARCHAR(128) ,\r\n"
			+ "    description TEXT,\r\n"
			+ "    release_year YEAR ,\r\n"
			+ "    language_id TINYINT UNSIGNED ,\r\n"
			+ "    length SMALLINT,\r\n"
			+ "    rating ENUM('G', 'PG', 'PG-13', 'R', 'NC-17') DEFAULT 'G',\r\n"
			+ "    PRIMARY KEY (film_id),\r\n"
			+ "    FOREIGN KEY (language_id) REFERENCES language (language_id) \r\n"
			+ "    ON DELETE RESTRICT \r\n"
			+ "    ON UPDATE CASCADE\r\n"
			+ ");";

	
	
	private static final String SELECT_FILMS = "SELECT * FROM film where length < 100;";
	
	private static final String INSERT_TABLA_PELICULAS_CORTAS = "INSERT INTO PeliculasCortas (film_id, title, description, release_year, language_id, length, rating) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?);";

	//Ruta del fichero
	protected static final String RUTA_FICHERO_NORMAL = "pelis.csv";
	
	/**
	 * Metodo que crea la tabla de PeliculasCortas
	 */
	public static void crearTablaPeliculas() {
		try (Connection connect = Conexion.conectar()) {
			PreparedStatement st = connect.prepareStatement(CREATE_TABLA_PELICULAS_CORTAS);
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que inserta registros de la tabla film a la tabla pelisculas cortas
	 */
	public static void insertarRegistrosTablaPeliculas() {
	    try (Connection connect = Conexion.conectar()) {
	        PreparedStatement st = connect.prepareStatement(SELECT_FILMS);
	        PreparedStatement st2 = connect.prepareStatement(INSERT_TABLA_PELICULAS_CORTAS);

	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            int filmId = rs.getInt("film_id");
	            String title = rs.getString("title");
	            String description = rs.getString("description");
	            int releaseYear = rs.getInt("release_year");
	            int languageId = rs.getInt("language_id");
	            int length = rs.getInt("length");
	            String rating = rs.getString("rating");

	            // Only insert films shorter than or equal to 100 minutes
	            if (length < 100) {
	                st2.setInt(1, filmId);
	                st2.setString(2, title);
	                st2.setString(3, description);
	                st2.setInt(4, releaseYear);
	                st2.setInt(5, languageId);
	                st2.setInt(6, length);
	                st2.setString(7, rating);

	                st2.executeUpdate();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Metodo que añade las peliculas a partir de un archivo .csv
	 * @param ruta - Ruta del archivo
	 */
	public static void cargarPeliculas (String ruta) {
		if (!ruta.endsWith(".csv")) {
            throw new IllegalArgumentException("El archivo debe de ser .csv");
        }
		try (Connection conn = Conexion.conectar()) {
        	try {
            // Desactivar autocommit para control manual de la transacción
            conn.setAutoCommit(false);

            // Preparar la sentencia
            PreparedStatement pst = conn.prepareStatement(INSERT_TABLA_PELICULAS_CORTAS);
            int contador = 0;
    		try (BufferedReader entrada = new BufferedReader(new FileReader(RUTA_FICHERO_NORMAL))) {
    			String linea = entrada.readLine();
    			// Mientras que la linea no sea null
    			while ((linea = entrada.readLine()) != null) {
    				// divide esa liena en partes y remplaza los ("") y la splitea en ";" 
    				String[] partes_peliculas = linea.replace("\"", "").split(";");
    				pst.setInt(1, contador + 1);
                	pst.setString(2, partes_peliculas[1]);
                	pst.setString(3, partes_peliculas[2]);
                	pst.setInt(4, Integer.parseInt(partes_peliculas[3]));
                	pst.setInt(5,  Integer.parseInt(partes_peliculas[4]));
                	pst.setInt(6, Integer.parseInt(partes_peliculas[5]));
                	pst.setString(7, partes_peliculas[6]);
                    contador++;

                	if (contador % 5 == 0) {
                    	// Ejecutar batch
                        pst.executeBatch(); 
                        System.out.println("Batch ejecutado para 5 peliculas");
                    }
    			}
    		}

            // Ejecutar batch restante
            pst.executeBatch(); 
            System.out.println("Batch ejecutado para los usuarios restantes");
            
            // Comitea 
            conn.commit();     

            System.out.println("Usuarios insertados correctamente");
            
        	}catch(Exception e) {
                System.err.println("Error a la hora de insertar: " + e.getMessage());
        		conn.rollback();
        	}

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
	}
}
