package dao;

import java.util.List;

import model.Curso;
import model.Usuario;


/**
 * Clase interfaz JuegoDaoInt
 */
public interface CursoDaoInt extends CommonDaoInt<Curso> {
	
	/**
	 * Devuelve los Curso que tienen ese Usuario en la base de datos
	 * 
	 * @param Usuario - usuario
	 * @return Cursos de ese usuario
	 */
	public List<Curso> getCursosByUsuario(final Usuario usuario);

	
	
	/**
	 * Inserta un Juego
	 * 
	 * @param Juego
	 */
	public void insertCurso(final Curso curso);
	
	/**
	 * Updatea el juego
	 * 
	 * @param juego - juego a updatear
	 */
	public void updateCurso(final Curso curso);
	
	/**
	 * Borra el juego seleccionado
	 * 
	 * @param juegoId - Id del juego a borrar
	 */
	public void deleteCurso(final int juegoId);

}
