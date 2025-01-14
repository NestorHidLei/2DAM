package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.Empleado;
import model.Localidad;
import model.Restaurante;


public class RestauranteDaoImpl extends CommonDaoImpl<Restaurante> implements RestauranteDaoInt {
	// Session de la base de datos
	private Session session;

	/**
	 * Constructor Restaurante DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public RestauranteDaoImpl(Session session) {
		super(session);
		this.session = session;
	}


	public List<Restaurante> searchByNombre(final String nombre) {
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		return session.createQuery("FROM Restaurante WHERE nombre='" + nombre + "'").list();
	}

	/**
	 * Método que consulta todos los restaurantes, pudiendo hacer filtros por uno o varios de los siguientes campos 
	 * @param nombre
	 * @param fecha_apertura
	 * @param horario
	 * @return
	 */
	public List<Restaurante> searchByParams(final String nombre, final Date fecha_apertura, final String horario) {
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		String filtro = " WHERE ";
		
		if (nombre!=null) filtro = filtro + " nombre='" + nombre + "'" + " AND";
		if (fecha_apertura!=null) filtro = filtro + " fecha_apertura='" + fecha_apertura + "'" + " AND";
		if (horario!=null) filtro = filtro + " horario='" + horario + "'" + " AND";
		
		if (filtro.equals(" WHERE ")) return null;
		
		filtro = filtro.substring(0, filtro.length()-4);
		
		return session.createQuery("FROM Restaurante " + filtro).list();
	}

	
	/**
	 * Método que muestra todos los restaurantes filtrando por nombre de localidad. 
	 * Y para cada restaurante, los datos de sus empleados.
	 * @param localidad
	 * @return
	 */
	public List<Object[]> searchRestYEmpleadosByLocalidad(final String localidad) {
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}
		
		Query query = session.createQuery(
				"SELECT r, re.id.empleado " + 
				"FROM Restaurante r inner join r.restEmpleados re inner join r.localidad loc " +
				"WHERE loc.nombre='" + localidad + "'");
		
		return query.list();
	}
	
	/**
	 * Método que devuelve el restaurante que coincida con el campo de entrada
	 * @param codigo
	 * @return
	 */
	public Restaurante searchByCodigo(final int codigo) {
		// Verificacion de sesion abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve el restaurante con ese codigo
		return (Restaurante) session.createQuery("FROM Restaurante WHERE cod_rest='" + codigo + "'").uniqueResult();
	}
}
