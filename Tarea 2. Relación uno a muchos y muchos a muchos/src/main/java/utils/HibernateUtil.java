package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

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

}
