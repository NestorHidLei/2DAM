package dao;

import java.util.List;

import model.Juego;
import model.Usuario;


/**
 * Clase interfaz JuegoDaoInt
 */
public interface JuegoDaoInt extends CommonDaoInt<Juego> {

	/**
	 * Devuelve los juegos que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los juegos que queremos obtener de la base de datos
	 * @return Juegos que contienen ese nombre
	 */
	public List<Juego> searchByPlataformas(final String nombre);


	/**
	 * Devuelve los juegos que esten para esta plataforma
	 * 
	 * @param Plataforma Plataforma en la que el juego esta
	 * @return El Juego
	 */
	public Juego searchByNombre(final String Plataforma);
	
	/**
	 * Inserta un Juego
	 * 
	 * @param Juego
	 */
	public void insertJuego(final Juego Juego);
	
	/**
	 * Updatea el juego
	 * 
	 * @param juego - juego a updatear
	 */
	public void updateJuego(final Juego juego);
	
	/**
	 * Borra el juego seleccionado
	 * 
	 * @param juegoId - Id del juego a borrar
	 */
	public void deleteJuego(final Long juegoId);

}
