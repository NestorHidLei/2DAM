package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurante")
public class Restaurante {
    @Id
    @Column(columnDefinition = "char(4)", name = "cod_rest")
    private String codRest;
    
    @Column
    private String nombre;
    
    @Column(columnDefinition = "char(10)", name = "licencia_fiscal")
    private String licenciaFiscal;
    
    @Column
    private String domicilio;
    
    @Column(name = "fecha_apertura")
    private Date fechaApertura;
    
    @Column
    private String horario;

	@OneToMany(mappedBy="restaurante",cascade= CascadeType.ALL)
	private List<Titular> titulares = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "cod_localidad")
    private Localidad localidad;
    
    @OneToMany(mappedBy = "restaurante")
    private List<Existencias> listaExistencias;

    public Restaurante() {
    }

    /**
     * Constructor
     * @param codRest
     * @param nombre
     * @param licenciaFiscal
     * @param domicilio
     * @param fechaApertura
     * @param horario
     * @param localidad
     */
    public Restaurante(String codRest, String nombre, String licenciaFiscal, String domicilio, 
                       Date fechaApertura, String horario, Localidad localidad) {
        this.codRest = codRest;
        this.nombre = nombre;
        this.licenciaFiscal = licenciaFiscal;
        this.domicilio = domicilio;
        this.fechaApertura = fechaApertura;
        this.horario = horario;
        this.localidad = localidad;
    }

    /**
     * Getter
     * @return
     */
    public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }

    /**
     * Getter
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter
     * @return
     */
    public String getLicenciaFiscal() {
        return licenciaFiscal;
    }
    
	/**
	 * Setter
	 * @param licenciaFiscal
	 */
    public void setLicenciaFiscal(String licenciaFiscal) {
        this.licenciaFiscal = licenciaFiscal;
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
	 * @param licenciaFiscal
	 */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Getter
     * @return
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }

    /**
	 * Setter
	 * @param licenciaFiscal
	 */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * Getter
     * @return
     */
    public String getHorario() {
        return horario;
    }

    /**
	 * Setter
	 * @param licenciaFiscal
	 */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * Getter
     * @return
     */
    public Localidad getLocalidad() {
        return localidad;
    }

    /**
	 * Setter
	 * @param licenciaFiscal
	 */
    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
}
