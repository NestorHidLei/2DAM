package utils;

import java.time.LocalDate;

public class Citas {
	
	private String matricula;
	private String marca;
	private String modelo;
	private LocalDate Fecha;
	private String Estado;
	private Double Importe;
	private String Observaciones;
	
	public Citas(String matricula, String marca, String modelo, LocalDate fecha, String estado, Double importe,
			String observaciones) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		Fecha = fecha;
		Estado = estado;
		Importe = importe;
		Observaciones = observaciones;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Double getImporte() {
		return Importe;
	}

	public void setImporte(Double importe) {
		Importe = importe;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	
}
