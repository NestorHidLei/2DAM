package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Titular implements java.io.Serializable {

	@Id
	@Column(name="dni_titular")
	private String dniTitular;
	@ManyToOne
	@JoinColumn(name="cod_rest")
	private Restaurante restaurante;
	@Column
	private String nombre;
	@Column
	private String domicilio;

	public Titular() {
	}

	public Titular(String dniTitular, String nombre) {
		this.dniTitular = dniTitular;
		this.nombre = nombre;
	}

	public Titular(String dniTitular, Restaurante restaurante, String nombre, String domicilio) {
		this.dniTitular = dniTitular;
		this.restaurante = restaurante;
		this.nombre = nombre;
		this.domicilio = domicilio;
	}

	public String getDniTitular() {
		return this.dniTitular;
	}

	public void setDniTitular(String dniTitular) {
		this.dniTitular = dniTitular;
	}

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

}
