package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.Usuario;

public class UsuarioDaoImpl extends CommonDaoImpl<Usuario> implements UsuarioDaoInt {
	// Session de la base de datos
	private Session session;

	/**
	 * Constructor Usuario DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public UsuarioDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	/**
	 * Busca un usuario por su nombre
	 * 
	 * @param nombre Nombre del usuario
	 * @return Usuario con el nombre especificado
	 */
	public Usuario searchByNombre(final String nombre) {
		// Verificación de sesión abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve el usuario con el nombre especificado
		return (Usuario) session.createQuery("FROM Usuario WHERE nombre = :nombre").setParameter("nombre", nombre)
				.uniqueResult();
	}

	/**
	 * Busca usuarios por su apellido
	 * 
	 * @param apellidos Apellido del usuario
	 * @return Lista de usuarios con el apellido especificado
	 */
	public List<Usuario> searchByApellido(final String apellidos) {
		// Devuelve los usuarios con el apellido especificado
		return session.createQuery("FROM Usuario WHERE apellidos = :apellidos").setParameter("apellidos", apellidos)
				.list();
	}

	/**
	 * Inserta un nuevo usuario en la base de datos
	 * 
	 * @param usuario Objeto Usuario a insertar
	 */
	public void insertUsuario(final Usuario usuario) {
		try {
			// Verificación de sesión abierta
			if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
				session.getTransaction().begin();
			}

			// Guarda el usuario en la base de datos
			session.save(usuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza un usuario existente
	 * 
	 * @param usuario Objeto usuario con los datos actualizados
	 */
	@Override
	public void updateUsuario(final Usuario usuario) {
		try {

			// Actualiza el usuario en la base de datos
			session.update(usuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un usuario por su ID
	 * 
	 * @param usuarioId ID del usuario a eliminar
	 */
	@Override
	public void deleteUsuario(final Long usuarioId) {
		try {
			// Verificación de sesión abierta
			if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
				session.getTransaction().begin();
			}

			// Busca el usuario por ID y lo elimina
			Usuario usuario = session.get(Usuario.class, usuarioId);
			if (usuario != null) {
				session.delete(usuario);
				session.getTransaction().commit();
			} else {
				session.getTransaction().rollback();
				System.out.println("Usuario no encontrado con ID: " + usuarioId);
			}
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

}
