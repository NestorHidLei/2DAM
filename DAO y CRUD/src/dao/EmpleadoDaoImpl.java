package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.Empleado;


public class EmpleadoDaoImpl extends CommonDaoImpl<Empleado> implements EmpleadoDaoInt {
	// Session de la base de datos
	private Session session;

	/**
	 * Constructor Empleado DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public EmpleadoDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	/**
	 * Devuelve los empleados que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los empleados
	 * @return Empleados con ese nombre
	 */
	public List<Empleado> searchByNombre(final String nombre) {
		// Verificaci�n de sesi�n abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve los empleados con ese nombre
		return session.createQuery("FROM Empleado WHERE nombre='" + nombre + "'").list();
	}


	/**
	 * Devuelve el empleado que tenga el DNI informado
	 */
	public Empleado searchByDNI(final String DNI) {
		// Verificacion de sesion abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve el empleado con ese dni
		return (Empleado) session.createQuery("FROM Empleado WHERE dni_empleado='" + DNI + "'").uniqueResult();
	}
}
