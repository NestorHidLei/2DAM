package main;

import java.sql.Date;
import java.util.List;

import org.hibernate.query.Query;

import models.Existencias;
import models.Localidad;
import models.Restaurante;
import models.Titular;
import utils.RestauranteHibernateUtil;

public class MainApp {

	public static void main(String[] args) {
		// insert();
		// select();
		// selectWithJoin();
		// selectWithGroupBy();

	}

	/**
	 * Metodo de insert
	 */
	private static void insert() {
		Localidad localidad = new Localidad(31, "Rincon");
		RestauranteHibernateUtil.insertarLocalidad(localidad);
		Restaurante restaurante = new Restaurante("R001", "Restaurante La Casa", "1234567890", "Calle Falsa 123",
				Date.valueOf("2024-11-22"), "08:00 - 22:00", localidad);
		RestauranteHibernateUtil.insertarRestaurante(restaurante);
		Existencias hamburguesas = new Existencias("HAMB001", "Hamburguesas", 100, 5.99, "R001");
		RestauranteHibernateUtil.insertarExistencias(hamburguesas);
		Titular titular = new Titular("11111111A", "Pepin", "Calle Trebol", "R001");
		RestauranteHibernateUtil.insertarTitular(titular);

		RestauranteHibernateUtil.closeSession();
	}

	/**
	 * Metodo de select
	 */
	private static void select() {
		String hql = "SELECT l.codLocalidad, l.nombre FROM Localidad l";
		Query<Object[]> query = RestauranteHibernateUtil.getSession().createQuery(hql, Object[].class);

		// Ejecuta la consulta
		List<Object[]> localidades = query.list();

		// Itera sobre los resultados
		for (Object[] localidad : localidades) {
			// El primer valor es 'codLocalidad' (entero) y el segundo es 'nombre' (String)
			Integer codLocalidad = (Integer) localidad[0];
			String nombre = (String) localidad[1];

			System.out.println("Código Localidad: " + codLocalidad + ", Nombre: " + nombre);
		}
	}

	/**
	 * Metodo de select usando un join
	 */
	public static void selectWithJoin() {
		String hql = "SELECT r.nombre, l.nombre " + "FROM Restaurante r " + "INNER JOIN r.localidad l ";

		Query<Object[]> query = RestauranteHibernateUtil.getSession().createQuery(hql, Object[].class);

		// Ejecuta la consulta
		List<Object[]> results = query.list();

		// Itera sobre los resultados
		for (Object[] result : results) {
			String nombreRestaurante = (String) result[0];
			String nombreLocalidad = (String) result[1];

			System.out.println("Restaurante: " + nombreRestaurante + ", Localidad: " + nombreLocalidad);
		}
	}

	/**
	 * Select usando un group by
	 */
	public static void selectWithGroupBy() {
		String hql = "SELECT l.nombre, COUNT(r.codRest) " + "FROM Restaurante r " + "INNER JOIN r.localidad l "
				+ "GROUP BY l.nombre";
		Query<Object[]> query = RestauranteHibernateUtil.getSession().createQuery(hql, Object[].class);

		// Ejecuta la consulta
		List<Object[]> results = query.list();

		// Itera sobre los resultados
		for (Object[] result : results) {
			String nombreLocalidad = (String) result[0];
			Long cantidadRestaurantes = (Long) result[1];

			System.out.println("Localidad: " + nombreLocalidad + ", Cantidad de Restaurantes: " + cantidadRestaurantes);
		}
	}

}
