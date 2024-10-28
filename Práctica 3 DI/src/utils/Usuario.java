package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario {
	
	private String nombre; 
	private String apellidos;
	private Date fechaNcimiento;
	private String perfil;
	private String email;
	private char[] contrasenia;
	private boolean isConected = false;
	
	public Usuario(String nombre, String apellidos, Date fechaNcimiento, String perfil, String email,
			char[] contrasenia, boolean isConected) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNcimiento = fechaNcimiento;
		this.perfil = perfil;
		this.email = email;
		this.contrasenia = contrasenia;
		this.isConected = isConected;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public Date getFechaNcimiento() {
		return fechaNcimiento;
	}

	public String getPerfil() {
		return perfil;
	}

	public String getEmail() {
		return email;
	}

	public String getContrasenia() {
		return new String(contrasenia);
	}

	public boolean isConected() {
		return isConected;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String fecha = sdf.format(fechaNcimiento);
		return nombre + ";" + apellidos + ";" + fecha
				+ ";" + perfil + ";" + email + ";" + getContrasenia() + ";"
				+ isConected;
	}
	
	

}
