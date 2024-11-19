package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Superusuarios")
public class Usuario implements Serializable {
	// Comentario mas grande
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlumno;

	@Column
	private String nombre;

	@Column
	private String apellido;

	@Column
	private String user;

//	@OneToOne(cascade = CascadeType.ALL)
//	@PrimaryKeyJoinColumn
//	private Matricula matricula; 

	/**
	 * Constructor vacio
	 */
	public Usuario() {
		super();
	}
	
	/**
	 * Contructor del usuario
	 * @param nombre - nombre del usuario
	 * @param apellido - apellidos del usuario
	 * @param user - Estructur (2DAM + 2PrimerasLetrasNombre + 2PrimerasLetrasApellidos)
	 */
	public Usuario(String nombre, String apellido, String user) {
		if (nombre.isBlank() || nombre.isEmpty() || apellido.isBlank() || apellido.isEmpty() || user.isBlank()
				|| user.isEmpty() || user.length() != 8) {
			throw new IllegalArgumentException();
		}
		this.nombre = nombre;
		this.apellido = apellido;
		this.user = user;
	}

	/**
	 * Geters y seters
	 * 
	 */
	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int num) {
		this.idAlumno = num;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUser() {
		return apellido;
	}

	public void setUser(String apellido) {
		this.apellido = apellido;
	}
}
