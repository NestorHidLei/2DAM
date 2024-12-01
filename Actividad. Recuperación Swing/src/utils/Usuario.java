package utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Usuario {
	
	private String nombre; 
	private String apellidos;
	private int telefono;
	private String email;
	private String contacto;
	private char[] contrasenia;
	
	public Usuario(String nombre, String apellidos, int telefono, String contacto, String email,
			char[] contrasenia) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.contacto = contacto;
		this.email = email;
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public String getContacto() {
		return contacto;
	}

	public char[] getContrasenia() {
		return contrasenia;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email
				+ ", contacto=" + contacto + ", contrasenia=" + Arrays.toString(contrasenia) + "]";
	}

}
