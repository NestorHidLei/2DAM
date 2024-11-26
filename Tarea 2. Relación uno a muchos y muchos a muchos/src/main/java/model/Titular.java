package model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Titular {
    @Id
    private String dniTitular;

    private String nombre;
    private String domicilio;

    @OneToMany(mappedBy = "titular", cascade= CascadeType.ALL)
    private List<Restaurante> restaurante;

    
    public Titular(String dniTitular, String nombre, String domicilio, List<Restaurante> restaurante) {
		super();
		this.dniTitular = dniTitular;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.restaurante = restaurante;
	}

	public String getDniTitular() {
        return dniTitular;
    }

    public void setDniTitular(String dniTitular) {
        this.dniTitular = dniTitular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public List<Restaurante> getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(List<Restaurante> restaurante) {
        this.restaurante = restaurante;
    }
}
