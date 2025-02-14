package utils;

public class Clientes {
	
	private String nombre; 
	private String apellidos;
	private int edad;
	private String provicia;
	
	
	public Clientes(String nombre, String apellidos, int edad, String provicia) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.provicia = provicia;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getProvicia() {
		return provicia;
	}


	public void setProvicia(String provicia) {
		this.provicia = provicia;
	}


	@Override
	public String toString() {
	    return String.format("💻 Cliente: %s %s  Edad: %d años  Provincia: %s", 
	                         nombre, apellidos, edad, provicia);
	}





	

}
