package model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Restaurante {
    @Id
    private String codRest;

    private String nombre;
    private String licenciaFiscal;
    private String domicilio;
    private Date fechaApertura;
    private String horario;

    @ManyToOne
    @JoinColumn(name = "cod_rest")
    private Titular Titular;
    
    @OneToMany(mappedBy = "restaurante", cascade= CascadeType.ALL)
    private List<Existencias> listaExistencia;
    
    @OneToOne
    @JoinColumn(name = "cod_localidad")
    private Localidad listaLocalidad;

  
	public Restaurante(String codRest, String nombre, String licenciaFiscal, String domicilio, Date fechaApertura,
			String horario, Titular titular, Localidad listaLocalidad, List<Existencias> listaExistencia) {
		super();
		this.codRest = codRest;
		this.nombre = nombre;
		this.licenciaFiscal = licenciaFiscal;
		this.domicilio = domicilio;
		this.fechaApertura = fechaApertura;
		this.horario = horario;
		Titular = titular;
		this.listaLocalidad = listaLocalidad;
		this.listaExistencia = listaExistencia;
	}

	public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicenciaFiscal() {
        return licenciaFiscal;
    }

    public void setLicenciaFiscal(String licenciaFiscal) {
        this.licenciaFiscal = licenciaFiscal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Localidad getLocalidad() {
        return listaLocalidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.listaLocalidad = localidad;
    }
}
