package dao;

import model.Inscripcion;

public interface InscripcionDaoInt extends CommonDaoInt<Inscripcion>{
	
	/**
	 * Busca una Inscripcion por su id
	 * 
	 * @param id - Id a buscar
	 * @return la Inscripcion con dicho ID
	 */
	public Inscripcion searchById(final int id);
		
	
	/**
	 * Inserta a una Inscripcion
	 * 
	 * @param Inscripcion
	 */
	public void insertInscripcion(final Inscripcion inscripcion);

	/**
	 * Updatea la Inscripcion
	 * 
	 * @param usuario
	 */
	public void updateInscripcion(final Inscripcion inscripcion);

	/**
	 * Deletea la Inscripcion
	 * 
	 * @param Inscripcion
	 */
	public void deleteInscripcion(final int idInscripcion);

}
