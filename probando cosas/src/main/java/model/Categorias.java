package model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categorias {
    @Id
    @Column
    private int id;
    
    @Column
    private String categoria;
    
    @Column
    private String descripcion;
    
    public Categorias(){
    	
    }

	public Categorias(int id, String categoria, String descripcion) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Categorias [id=" + id + ", categoria=" + categoria + ", descripcion=" + descripcion + "]";
	}
    
    

}

