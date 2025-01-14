package dao;

import java.util.List;

import model.Usuario;

/**
 * Clase interfaz LocalidadDaoInt
 */
public interface UsuarioDaoInt extends CommonDaoInt<Usuario> {

	/**
	 * Devuelve los usuarios que tienen ese apellido en la base de datos
	 * 
	 * @param Apellidos - apellidos del usuario
	 * @return Usuarios con ese apellido
	 */
	public List<Usuario> searchByApellido(final String Apellidos);

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
	public void deleteUsuario(final Long usuarioId);

}
