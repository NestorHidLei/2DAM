package models;


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
    
    
    @ManyToOne
    @JoinColumn(name= "cod_rest")
    private Restaurante restaurante;

    public Titular() {
    	
    }
    
    /**
     * Constructor
     * @param dniTitular
     * @param nombre
     * @param domicilio
     * @param codRest
     */
    public Titular(String dniTitular, String nombre, String domicilio, String codRest) {
        this.dniTitular = dniTitular;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.codRest = codRest;
    }

    /**
     * Getter
     * @return
     */
    public String getDniTitular() {
        return dniTitular;
    }

    /**
     * Setter
     * @param codRest
     */
    public void setDniTitular(String dniTitular) {
        this.dniTitular = dniTitular;
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
     * @param codRest
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter
     * @return
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Setter
     * @param codRest
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Getter
     * @return
     */
    public String getCodRest() {
        return codRest;
    }

    /**
     * Setter
     * @param codRest
     */
    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }
}

