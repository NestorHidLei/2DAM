package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

import models.Existencias;
import models.Localidad;
import models.Restaurante;
import models.Titular;

public class RestauranteHibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;

	/**
	 * Método que devuelve el objeto Session.
	 * 
	 * @return
	 *         <ul>
	 *         <li>Si la sesión no est� creada: la crea y la abre.</li>
	 *         <li>Si la sesión está creada: simplemente devuelve la sesión
	 *         abierta.</li>
	 *         </ul>
	 */
	public static Session getSession() {
		if (sessionFactory == null) {
			session = getSessionFactory().openSession();
		}

		return session;
	}

	/**
	 * Método que cierra el objeto Session de HibernateUtil
	 */
	public static void closeSession() {
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		if (session != null) {
			session.close();
		}
		closeSessionFactory();
	}

	protected static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		}
		return sessionFactory;
	}

	private static void closeSessionFactory() {
		if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
			sessionFactory.close();
		}
	}

	/**
	 * Método para insertar localidades en la base de datos.
	 * 
	 * @param localidad1 Primera localidad a insertar
	 */
	public static void insertarLocalidad(Localidad localidad) {
		Transaction transaction = null;
		Session session = null;

		try {
			// Obtener sesión
			session = getSessionFactory().openSession();
			// Iniciar transacción
			transaction = session.beginTransaction();

			// Guardar la localidad en la base de datos
			session.merge(localidad);

			// Confirmar la transacción
			transaction.commit();
			System.out.println("Localidad insertada con éxito.");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Error al insertar localidad: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * Método para insertar Restuarantes en la base de datos.
	 * 
	 * @param localidad1 Primera localidad a insertar
	 */
	public static void insertarRestaurante(Restaurante rest) {
		Transaction transaction = null;
		Session session = null;

		try {
			// Obtener sesión
			session = getSessionFactory().openSession();
			// Iniciar transacción
			transaction = session.beginTransaction();

			// Guardar la localidad en la base de datos
			session.merge(rest);

			// Confirmar la transacción
			transaction.commit();
			System.out.println("Localidad insertada con éxito.");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Error al insertar localidad: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	
	/**
	 * Método para insertar Restuarantes en la base de datos.
	 * 
	 * @param localidad1 Primera localidad a insertar
	 */
	public static void insertarExistencias(Existencias existencias) {
		Transaction transaction = null;
		Session session = null;

		try {
			// Obtener sesión
			session = getSessionFactory().openSession();
			// Iniciar transacción
			transaction = session.beginTransaction();

			// Guardar la localidad en la base de datos
			session.merge(existencias);

			// Confirmar la transacción
			transaction.commit();
			System.out.println("Localidad insertada con éxito.");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Error al insertar localidad: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * Método para insertar Restuarantes en la base de datos.
	 * 
	 * @param localidad1 Primera localidad a insertar
	 */
	public static void insertarTitular(Titular titular) {
		Transaction transaction = null;
		Session session = null;

		try {
			// Obtener sesión
			session = getSessionFactory().openSession();
			// Iniciar transacción
			transaction = session.beginTransaction();

			// Guardar la localidad en la base de datos
			session.merge(titular);

			// Confirmar la transacción
			transaction.commit();
			System.out.println("Localidad insertada con éxito.");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Error al insertar localidad: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
