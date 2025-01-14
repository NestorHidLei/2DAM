package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.EmpleadoDaoImpl;
import dao.LocalidadDaoImpl;
import dao.RestauranteDaoImpl;
import model.Empleado;
import model.Localidad;
import model.RestEmpleado;
import model.RestEmpleadoId;
import model.Restaurante;


public class Main_restaurante {

	public static void main(String[] args) {
		// Abro sesión
		Session session = HibernateUtil.getSession();
		
		try {
			// Insertar localidad Málaga 
			System.out.println("Insertar localidad Málaga");
			LocalidadDaoImpl locDao = new LocalidadDaoImpl(session);

			Localidad localidad = new Localidad(29, "Málaga");
			locDao.insert(localidad);
			System.out.println("-------------------");
			
			// Crear dos restaurantes en Málaga.
			System.out.println("Crear dos restaurantes en Málaga");
			RestauranteDaoImpl restaDao = new RestauranteDaoImpl(session);
			
			Restaurante rest1 = new Restaurante("1", "Málaga querida", "Z12312312", new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01"), "Mañana");
			Restaurante rest2 = new Restaurante("2", "Málaga querida 2", "Z12312313", new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01"), "Mañana");
			rest1.setLocalidad(localidad);
			rest2.setLocalidad(localidad);
			System.out.println("-------------------");
			
			// Crear un empleado.
			System.out.println("Crear un empleado");
			EmpleadoDaoImpl empleadoDao = new EmpleadoDaoImpl(session);
			
			Empleado empleNuevo = new Empleado("22222222J", "El currante", new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01"));
			empleadoDao.insert(empleNuevo);
			System.out.println("-------------------");
			
			// Asignar ese empleado al restaurante de Málaga que tú quieras con la función que quieras. 
			// Asignar el empleado con DNI 1111111H a ese mismo restaurante con la función CAMARERO.
			Empleado emp11H = empleadoDao.searchByDNI("1111111H");
			RestEmpleado restEmpleado1 = new RestEmpleado(new RestEmpleadoId(empleNuevo, rest1), "ESPETERO");
			RestEmpleado restEmpleado2 = new RestEmpleado(new RestEmpleadoId(emp11H, rest1), "ESPETERO");
			
			Set<RestEmpleado> setEmpleados = new HashSet<RestEmpleado>();
			setEmpleados.add(restEmpleado1);
			setEmpleados.add(restEmpleado2);
			
			rest1.setRestEmpleados(setEmpleados);

			// Vuelco a BBDD
			restaDao.insert(rest1);
			restaDao.insert(rest2);
			

			
			// Mostrar todos los restaurantes, pudiendo hacer filtros por uno o varios de los 
			// siguientes campos (nombre, fecha_apertura, horario).
			System.out.println("");
			System.out.println("Mostrar todos los restaurantes, pudiendo hacer filtros por uno o varios campos:");

			RestauranteDaoImpl restDao = new RestauranteDaoImpl(session);
			
			List<Restaurante> listaRest = restDao.searchByParams(null, null, "Mañana");
			for (Restaurante rest : listaRest) {
				System.out.println(rest);
			}
			
			System.out.println("-------------------");
			
			
			
			// EJEMPLO Consulta de todos los Restaurantes filtrando por nombre de la localidad. 
			// Y los empleados de cada uno
			System.out.println("");
			System.out.println("EJEMPLO Consulta de todos los Restaurantes filtrando por nombre de la localidad. Y los empleados de cada uno:");
		
			List<Object[]> lista1 = restDao.searchRestYEmpleadosByLocalidad("Málaga");		
			
			System.out.println("----------------------------------------------------------------");
			for (Object[] elem : lista1) {
				Restaurante p = (Restaurante) elem[0];
				System.out.println("RESTAURANTE: " + p.getNombre());

				Empleado emp = (Empleado) elem[1];
				System.out.println("Empleado NOMBRE: " + emp.getNombre());
				
				System.out.println("----------------------------------------------------------------");
			}	
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
