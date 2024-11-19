package utils;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

import model.Usuario;

public class HibernateUtil {
	private static final String CURSO = "2DAM";
	private static SessionFactory sessionFactory;
	private static Session session;

	/**
	 * Método que devuelve el objeto Session.
	 * 
	 * @return
	 *         <ul>
	 *         <li>Si la sesión no está creada: la crea y la abre.</li>
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
	 * Método que añade los usuarios desde el csv a la tabla Superusuaeios
	 * @param ruta - ruta del archivo csv
	 */
	public static void aniadirUsuariosCSV(String ruta) {
		Transaction tx = null;
		Session session = null;
		//intentamos leer del archivo
        try (BufferedReader rd = new BufferedReader(new FileReader(ruta))) {
        	//Creamos la sesion y la transacción
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();

            String linea;
            int count = 0;
            
            //Mientras que linea no sea null que saque los usuarios
            while ((linea = rd.readLine()) != null) {
                String[] datos = linea.split(",");
          
                String nombre = datos[1].trim(); 
                String apellidos =datos[0].trim();  
                String usser = CURSO + nombre.substring(0,2) + apellidos.substring(0,2); 
                
                Usuario usuario = new Usuario(nombre, apellidos, usser);
                
                //Guarda los usuario
                session.merge(usuario);
                
                count++;
                // Si el contador es divisible entre 2 hace el flush y el clear
                if (count % 2 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            //comiteamos y cerramos
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();            
        } finally {
        	//cerramos la sesion
        	if(session != null) {
        		session.close();
        	}
        }
    }

}
