package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "existencias")
public class Existencias {
	@Id
	private String codArticulo;
	@Column
	private String nombre;
	@Column
	private int cantidad;
	@Column
	private double precio;

	@Column(name = "cod_rest")
	private String codRest;

	@ManyToOne
	@JoinColumn(name = "cod_rest", insertable=false, updatable=false)
	private Restaurante restaurante;

	public Existencias() {

	}

	// Constructor
	public Existencias(String codArticulo, String nombre, int cantidad, double precio, String codRest) {
		this.codArticulo = codArticulo;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.codRest = codRest;
	}

	// Getters and Setters
	public String getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCodRest() {
		return codRest;
	}

	public void setCodRest(String codRest) {
		this.codRest = codRest;
	}
}
