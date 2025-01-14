package dao;

import java.util.List;

import model.Empleado;


/**
 * Clase interfaz EmpleadoDaoInt
 */
public interface EmpleadoDaoInt extends CommonDaoInt<Empleado> {

	/**
	 * Devuelve los usuarios que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los usuarios que queremos obtener de la base de datos
	 * @return Usuarios que contienen ese nombre
	 */
	public List<Empleado> searchByNombre(final String nombre);


	public Empleado searchByDNI(final String DNI);

}
