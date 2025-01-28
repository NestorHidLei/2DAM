package dao;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.Inscripcion;
import model.Usuario;

public class InscripcionDaoImpl extends CommonDaoImpl<Inscripcion> implements InscripcionDaoInt {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor Inscripcion DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public InscripcionDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	/**
	 * Inserta una nueva inscripcion en la base de datos
	 * 
	 * @param inscripcion Objeto inscripcion a insertar
	 */
	@Override
	public void insertInscripcion(Inscripcion inscripcion) {
		try {
			// Verificación de sesión abierta
			if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
				session.getTransaction().begin();
			}
			// Guarda el usuario en la base de datos
			session.save(inscripcion);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Updatea una inscripcion existente en la base de datos
	 * 
	 * @param inscripcion Objeto inscripcion a insertar
	 */
	@Override
	public void updateInscripcion(Inscripcion inscripcion) {
		try {

			// Actualiza el usuario en la base de datos
			session.update(inscripcion);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Deletea una inscripcion en la base de datos
	 * 
	 * @param i localizador de la inscripcion
	 */
	@Override
	public void deleteInscripcion(int i) {
		try {
			// Verificación de sesión abierta
			if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
				session.getTransaction().begin();
			}

			// Busca el usuario por ID y lo elimina
			Usuario usuario = session.get(Usuario.class, i);
			if (usuario != null) {
				session.delete(usuario);
				session.getTransaction().commit();
			} else {
				session.getTransaction().rollback();
				System.out.println("Inscripcion no encontrado con ID: " + i);
			}
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}

	}

	@Override
	public Inscripcion searchById(int id) {
		// Verificación de sesión abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve el usuario con el nombre especificado
		return (Inscripcion) session.createQuery("FROM Inscripcion WHERE idInscripcion = :id").setParameter("id", id)
				.uniqueResult();
	}

}
