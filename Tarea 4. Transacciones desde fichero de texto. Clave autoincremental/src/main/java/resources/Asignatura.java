package resources;

public class Asignatura {
	
	//Atributos
	private String CodAsignatura;
	private String Nombre;
	
	/**
	 * Constructor de Asignatura
	 */
	public Asignatura() {
	}

	/**
	 * Constructor de Asignaturas
	 * @param codAsignatura - codigo de la asignatura 
	 * @param nombre - nombnre de la asignatura
	 * @param idProfesor - id del profesor
	 */
	public Asignatura(String codAsignatura, String nombre) {
		this.CodAsignatura = codAsignatura;
		this.Nombre = nombre;
	}

	public String getCodAsignatura() {
		return CodAsignatura;
	}

	public String getNombre() {
		return Nombre;
	}
	
}
