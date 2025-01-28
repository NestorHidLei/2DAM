package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.Curso;
import model.Usuario;

public class CursoDaoImpl extends CommonDaoImpl<Curso> implements CursoDaoInt {
    // Session de la base de datos
    private Session session;

    /**
     * Constructor Juego DAO
     * 
     * @param session Session de la base de datos
     */
    public CursoDaoImpl(Session session) {
        super(session);
        this.session = session;
    }
    
    /**
	 * Inserta un nuevo curso en la base de datos
	 * 
	 * @param curso Objeto Curso a insertar
	 */
	public void insertCurso(final Curso curso) {
		try {
			// Guarda el usuario en la base de datos
			session.save(curso);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

    
    /**
     * Actualiza un curso existente
     * 
     * @param curso Objeto Curso con los datos actualizados
     */
    public void updateCurso(final Curso curso) {
        try {

            // Actualiza el juego en la base de datos
            session.update(curso);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Elimina un curso por su ID
     * 
     * @param i ID del curso a eliminar
     */
    public void deleteCurso(final int i) {
        try {
            // Verificación de sesión abierta
            if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().begin();
            }

            // Busca el juego por ID y lo elimina
            Curso curso = session.get(Curso.class, i);
            if (curso != null) {
                session.delete(curso);
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
                System.out.println("Curso no encontrado con ID: " + i);
            }
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

	public Curso searchByNombre(String nombre) {
		 // Verificación de sesión abierta
        if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().begin();
        }

        // Devuelve el juego con ese nombre
        return (Curso) session.createQuery("FROM Curso WHERE nombreCurso='" + nombre + "'").uniqueResult();
    }

	@Override
	public List<Curso> getCursosByUsuario(Usuario usuario) {

        // Devuelve el juego con ese nombre
        return session.createQuery("FROM Curso c Join c.inscripciones i WHERE i.usuario='" + usuario  + "'", Curso.class)
        		.setParameter("usuario", usuario).list();
    }
}
