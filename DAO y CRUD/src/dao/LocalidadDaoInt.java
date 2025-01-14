package dao;

import java.util.List;

import model.Empleado;
import model.Localidad;


/**
 * Clase interfaz LocalidadDaoInt
 */
public interface LocalidadDaoInt extends CommonDaoInt<Localidad> {

	/**
	 * Devuelve las localidades que tienen ese nombre
	 * 
	 * @param nombre 
	 * @return 
	 */
	public List<Localidad> searchByNombre(final String nombre);


	public Localidad searchByCodigo(int codigo);

}
