package dao;

import java.util.List;

import model.Curso;
import model.Usuario;

/**
 * Clase interfaz LocalidadDaoInt
 */
public interface UsuarioDaoInt extends CommonDaoInt<Usuario> {
	
	/**
	 * Devuelve los Curso que tienen ese Usuario en la base de datos
	 * 
	 * @param Usuario - usuario
	 * @return Cursos de ese usuario
	 */
	public List<Usuario> getUsuariosByCurso(final Curso curso);


	/**
	 * Devuelve el usuario cuyo nombre es el seleccionado
	 * 
	 * @param nombre - nombre del usuario
	 * @return Usuario con dicho nombre
	 */
	public Usuario searchByNombre(final String nombre);


	/**
	 * Inserta a un usuario
	 * 
	 * @param usuario
	 */
	public void insertUsuario(final Usuario usuario);

	/**
	 * Updatea el usuario
	 * 
	 * @param usuario
	 */
	public void updateUsuario(final Usuario usuario);

	/**
	 * Deletea al usuario
	 * 
	 * @param usuarioId
	 */
	public void deleteUsuario(final int usuarioId);

}
