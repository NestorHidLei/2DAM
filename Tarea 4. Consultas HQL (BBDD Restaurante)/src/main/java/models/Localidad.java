package models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "localidad")
public class Localidad {
	@Id
	@Column(name = "cod_localidad")
    private int codLocalidad;
  
	@Column
	private String nombre;
    
    @OneToMany(mappedBy = "localidad")
    private List<Restaurante> listaRestaurantes;
    
    public Localidad() {
    	
    }

    /**
     * Constructor
     * @param codLocalidad
     * @param nombre
     */
    public Localidad(int codLocalidad, String nombre) {
        this.codLocalidad = codLocalidad;
        this.nombre = nombre;
    }

    /**
     * Getter
     * @return
     */
    public int getCodLocalidad() {
        return codLocalidad;
    }

    /**
     * Setter
     * @param codLocalidad
     */
    public void setCodLocalidad(int codLocalidad) {
        this.codLocalidad = codLocalidad;
    }

    /**
     * Getter
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter
     * @param codLocalidad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
