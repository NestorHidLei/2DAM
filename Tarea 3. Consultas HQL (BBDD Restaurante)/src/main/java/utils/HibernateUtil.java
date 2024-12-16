package utils;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.query.Query;

import model.Existencias;
import model.Localidad;
import model.Restaurante;
import model.Titular;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;

	/**
	 * M�todo que devuelve el objeto Session.
	 * 
	 * @return
	 *         <ul>
	 *         <li>Si la sesi�n no est� creada: la crea y la abre.</li>
	 *         <li>Si la sesi�n est� creada: simplemente devuelve la sesi�n
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
	 * M�todo que cierra el objeto Session de HibernateUtil
	 */
	public static void closeSession() {
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		if (session != null) {
			session.close();
		}
		closeSessionFactory();
	}

	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();

		}
		return sessionFactory;
	}

	private static void closeSessionFactory() {
		if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
			sessionFactory.close();
		}
	}

	/**
	 * Metodo que hace un select de restaurantes
	 */
	public static void selectRestaurantes() {
		try {
			// Obtener sesión
			session = getSessionFactory().openSession();

			String hql = "SELECT r.codRest, r.nombre FROM Restaurante r";
			Query<Object[]> query = HibernateUtil.getSession().createQuery(hql, Object[].class);

			// Ejecuta la consulta
			List<Object[]> restaurantes = query.list();

			for (Object[] restaurante : restaurantes) {
				// El primer valor es 'codRest' y el segundo es 'nombre'
				String codRest = (String) restaurante[0];
				String nombre = (String) restaurante[1];

				System.out.println("Código Localidad: " + codRest + ", Nombre: " + nombre);
			}
		} catch (Exception e) {
			System.err.println("Error durante la select " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Metodo que hace un select de restaurantes pero con el nombre de su localidad
	 * correspondiente
	 */
	public static void selectConJoin() {
		try {
			// Obtener sesión
			session = getSessionFactory().openSession();
			String hql = "SELECT r.codRest, r.nombre, l.nombre FROM Restaurante r INNER JOIN r.localidad l ORDER BY r.codRest";
			Query<Object[]> query = HibernateUtil.getSession().createQuery(hql, Object[].class);

			// Ejecuta la consulta
			List<Object[]> restaurantes = query.list();

			for (Object[] restaurante : restaurantes) {

				// El primer valor es 'codRest', el segundo es 'nombre' y el tercero es 'nombre'
				// de la localidad
				String codRest = (String) restaurante[0];
				String nombre = (String) restaurante[1];
				String localidad = (String) restaurante[2];

				System.out
						.println("Código Localidad: " + codRest + ", Nombre: " + nombre + ", Localidad: " + localidad);
			}
		} catch (Exception e) {
			System.err.println("Error durante la select " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	/**
	 * Metodo que hace un select cuyo resultado es el nombre de la localidad y un
	 * contador de restaurantes
	 */
	public static void selectConGroupBy() {
		try {
			// Obtener sesión
			session = getSessionFactory().openSession();
			String hql = "SELECT l.nombre, COUNT(r.codRest) FROM Restaurante r INNER JOIN r.localidad l GROUP BY l.nombre";
			Query<Object[]> query = HibernateUtil.getSession().createQuery(hql, Object[].class);

			// Ejecuta la consulta
			List<Object[]> results = query.list();

			for (Object[] result : results) {

				// El primer valor es el 'nombre' de la localidad y el segundo es la cantidad de
				// restaurantes (COUNT(r.codRest))
				String localidad = (String) result[0];
				Long cantidadRestaurantes = (Long) result[1];

				System.out.println("Localidad: " + localidad + ", Cantidad de Restaurantes: " + cantidadRestaurantes);
			}

		} catch (Exception e) {
			System.err.println("Error durante la select " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Método para insertar localidades
	 * 
	 * @param localidad - Localidad que se quiere añadir
	 */
	public static void insertLocalidad(Localidad localidad) {
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
	 * Método para insertar Restaurantes
	 * 
	 * @param restaurante - Restaurante que se quiere añadir
	 */
	public static void insertRestaurante(Restaurante restaurante) {
		Transaction transaction = null;
		Session session = null;

		try {
			// Obtener sesión
			session = getSessionFactory().openSession();
			// Iniciar transacción
			transaction = session.beginTransaction();

			// Guardar la localidad en la base de datos
			session.merge(restaurante);

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
	 * Método para insertar existencias
	 * 
	 * @param existencias - Existencias que se quieren añadir
	 */
	public static void insertExistencias(Existencias existencias) {
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
	 * Método para insertar Titulares.
	 * 
	 * @param titular - titular a insertar
	 */
	public static void insertTitular(Titular titular) {
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
