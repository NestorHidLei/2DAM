package utils;

import java.util.Arrays;


public class Usuario {
	
	private String nombre; 
	private String apellidos;
	private int telefono;
	private String email;
	private String contacto;
	private char[] contrasenia;
	private boolean isClient;
	
	public Usuario(String nombre, String apellidos, int telefono, String contacto, String email,
			char[] contrasenia, boolean isClient) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.contacto = contacto;
		this.email = email;
		this.contrasenia = contrasenia;
		this.isClient = isClient;
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

	public boolean isClient() {
		return isClient;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email
				+ ", contacto=" + contacto + ", contrasenia=" + Arrays.toString(contrasenia) + "]";
	}

}
