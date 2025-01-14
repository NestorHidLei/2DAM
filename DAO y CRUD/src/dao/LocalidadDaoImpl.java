package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.Empleado;
import model.Localidad;


public class LocalidadDaoImpl extends CommonDaoImpl<Localidad> implements LocalidadDaoInt {
	// Session de la base de datos
	private Session session;

	/**
	 * Constructor Localidad DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public LocalidadDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	/**
	 * Devuelve los empleados que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los empleados
	 * @return Empleados con ese nombre
	 */
	public List<Localidad> searchByNombre(final String nombre) {
		// Verificaci�n de sesi�n abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve los empleados con ese nombre
		return session.createQuery("FROM Localidad WHERE nombre='" + nombre + "'").list();
	}


	/**
	 * Devuelve la localidad
	 */
	public Localidad searchByCodigo(final int codigo) {
		// Verificacion de sesion abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve el empleado con ese dni
		return (Localidad) session.createQuery("FROM Localidad WHERE codigo='" + codigo + "'").uniqueResult();
	}
}
