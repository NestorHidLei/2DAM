package modelos;

import java.util.List;

public class Serie {

	private String nombre;
	private String plataforma;
	private List<Temporada> lstTemporadas;
	
	
	public Serie(String nombre, String plataforma, List<Temporada> lstTemporadas) {
		super();
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.lstTemporadas = lstTemporadas;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public List<Temporada> getLstTemporadas() {
		return lstTemporadas;
	}

	public void setLstTemporadas(List<Temporada> lstTemporadas) {
		this.lstTemporadas = lstTemporadas;
	}

	
	
	
	
	
	
	
}
