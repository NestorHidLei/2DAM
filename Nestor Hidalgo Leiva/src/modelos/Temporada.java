package modelos;

import java.time.LocalDate;


public class Temporada {
	
	private String numeroTemporada;
	private LocalDate inicioTemporada;
	private LocalDate finTemporada;
	private int numerocapitulos;
	
	
	public Temporada(String numeroTemporada, LocalDate inicioTemporada, LocalDate finTemporada, int numerocapitulos) {
		super();
		this.numeroTemporada = numeroTemporada;
		this.inicioTemporada = inicioTemporada;
		this.finTemporada = finTemporada;
		this.numerocapitulos = numerocapitulos;
	}


	public String getNumeroTemporada() {
		return numeroTemporada;
	}


	public void setNumeroTemporada(String numeroTemporada) {
		this.numeroTemporada = numeroTemporada;
	}


	public LocalDate getInicioTemporada() {
		return inicioTemporada;
	}


	public void setInicioTemporada(LocalDate inicioTemporada) {
		this.inicioTemporada = inicioTemporada;
	}


	public LocalDate getFinTemporada() {
		return finTemporada;
	}


	public void setFinTemporada(LocalDate finTemporada) {
		this.finTemporada = finTemporada;
	}


	public int getNumerocapitulos() {
		return numerocapitulos;
	}


	public void setNumerocapitulos(int numerocapitulos) {
		this.numerocapitulos = numerocapitulos;
	}
	
	
	
	
	

}
