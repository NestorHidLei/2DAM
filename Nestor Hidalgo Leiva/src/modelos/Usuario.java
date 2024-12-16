package modelos;

import java.util.List;

public class Usuario {
	
	private String nombre;
	private String apellidos;
	private String email;
	private char[] pass;
	private List<Serie> misSeries;
	
	public Usuario(String nombre, String apellidos, String email, char[] pass,
			List<Serie> misSeries) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.pass = pass;
		this.misSeries = misSeries;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char[] getPass() {
		return pass;
	}

	public void setPass(char[] pass) {
		this.pass = pass;
	}


	public List<Serie> getMisSeries() {
		return misSeries;
	}

	public void setMisSeries(List<Serie> misSeries) {
		this.misSeries = misSeries;
	}
	
	

}
