package utils;

import java.util.Date;

public class Citas {
	
	private String matricula;
	private String marca;
	private String modelo;
	private Date Fecha;
	
	public Citas(String matricula, String marca, String modelo, Date fecha) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		Fecha = fecha;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public Date getFecha() {
		return Fecha;
	}
	
	
}
