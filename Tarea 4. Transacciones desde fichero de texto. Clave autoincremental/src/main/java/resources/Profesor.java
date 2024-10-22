package resources;

public class Profesor {

	//Atributos
	private String NIF;
	private String Nombre;
	private String Especialidad;
	private String Teléfono;
	
	/**
	 * Constructor vacio del profesor
	 */
	public Profesor() {
	}

	/**
	 * Constructor de un profesor
	 * @param idProfesor - Id del profesor
	 * @param nIF - nif del profesor
	 * @param nombre - nombre del profesor
	 * @param especialidad - Especialidades del profesor
	 * @param teléfono - telefono del profesor
	 */
	public Profesor(String nIF, String nombre, String especialidad, String teléfono) {
		this.NIF = nIF;
		this.Nombre = nombre;
		this.Especialidad = especialidad;
		this.Teléfono = teléfono;
	}

	public String getNIF() {
		return NIF;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getEspecialidad() {
		return Especialidad;
	}

	public String getTeléfono() {
		return Teléfono;
	}
	
	
}
