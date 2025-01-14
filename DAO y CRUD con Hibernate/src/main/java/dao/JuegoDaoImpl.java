package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.Juego;

public class JuegoDaoImpl extends CommonDaoImpl<Juego> implements JuegoDaoInt {
    // Session de la base de datos
    private Session session;

    /**
     * Constructor Juego DAO
     * 
     * @param session Session de la base de datos
     */
    public JuegoDaoImpl(Session session) {
        super(session);
        this.session = session;
    }

    /**
     * Devuelve los juegos que tienen esa plataforma en la base de datos
     * 
     * @param Plataformas Plataforma de los juegos
     * @return Juegos con esa plataforma
     */
    public List<Juego> searchByPlataformas(final String Plataformas) {

        // Devuelve los juegos con esa plataforma
    	return session.createQuery("FROM Juego j WHERE j.plataforma = :plataforma", Juego.class)
                .setParameter("plataforma", Plataformas)
                .list();    }

    /**
     * Devuelve el juego que tenga el nombre especificado
     * 
     * @param nombre Nombre del juego
     * @return Juego con ese nombre
     */
    public Juego searchByNombre(final String nombre) {
        // Verificación de sesión abierta
        if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().begin();
        }

        // Devuelve el juego con ese nombre
        return (Juego) session.createQuery("FROM Juego WHERE nombre='" + nombre + "'").uniqueResult();
    }

    
    /**
	 * Inserta un nuevo usuario en la base de datos
	 * 
	 * @param usuario Objeto Usuario a insertar
	 */
	public void insertJuego(final Juego juego) {
		try {
			// Guarda el usuario en la base de datos
			session.save(juego);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

    
    /**
     * Actualiza un juego existente
     * 
     * @param juego Objeto juego con los datos actualizados
     */
    public void updateJuego(final Juego juego) {
        try {

            // Actualiza el juego en la base de datos
            session.update(juego);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Elimina un juego por su ID
     * 
     * @param juegoId ID del juego a eliminar
     */
    public void deleteJuego(final Long juegoId) {
        try {
            // Verificación de sesión abierta
            if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().begin();
            }

            // Busca el juego por ID y lo elimina
            Juego juego = session.get(Juego.class, juegoId);
            if (juego != null) {
                session.delete(juego);
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
                System.out.println("Juego no encontrado con ID: " + juegoId);
            }
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
