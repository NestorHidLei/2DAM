package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "plataformas", length = 255)
    private String plataforma;

    @Column(name = "calificacion")
    private Double calificacion;
    
    public Juego() {
    }
    
    // Constructor
    public Juego(String nombre, String descripcion, String plataforma, Double calificacion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.plataforma = plataforma;
        this.calificacion = calificacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

	@Override
	public String toString() {
		return "Juego [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", plataforma=" + plataforma
				+ ", calificacion=" + calificacion + "]";
	}
    
    
}
