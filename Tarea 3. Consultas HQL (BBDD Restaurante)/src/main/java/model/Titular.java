package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "titular")
public class Titular {
	@Id
    private String dniTitular;
	@Column
    private String nombre;
	@Column
    private String domicilio;
	@Column
    private String codRest;
    
    // Uno a muchos de titular
    @ManyToOne
    @JoinColumn(name= "cod_rest")
    private Restaurante restaurante;

    public Titular() {
    	
    }
    
    // Constructor
    public Titular(String dniTitular, String nombre, String domicilio, String codRest) {
        this.dniTitular = dniTitular;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.codRest = codRest;
    }

    // Getters and Setters
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

    public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }
}

