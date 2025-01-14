package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Restaurante implements java.io.Serializable {

	@Id
	@Column(name="cod_rest")
	private String codRest;
	@ManyToOne
	@JoinColumn(name="cod_localidad")
	private Localidad localidad;
	@Column
	private String nombre;
	@Column(name="licencia_fiscal")
	private String licenciaFiscal;
	@Column
	private String domicilio;
	@Column(name="fecha_apertura")
	private Date fechaApertura;
	@Column
	private String horario;
	
	@OneToMany(mappedBy="id.restaurante",cascade= CascadeType.ALL)
	private Set<RestEmpleado> restEmpleados = new HashSet<RestEmpleado>(0);
	@OneToMany(mappedBy="restaurante",cascade= CascadeType.ALL)
	private Set<Titular> titulars = new HashSet<Titular>(0);
	@OneToMany(mappedBy="restaurante",cascade= CascadeType.ALL)
	private Set<Existencias> existenciases = new HashSet<Existencias>(0);

	public Restaurante() {
	}

	public Restaurante(String codRest, String nombre, String licenciaFiscal, Date fechaApertura, String horario) {
		this.codRest = codRest;
		this.nombre = nombre;
		this.licenciaFiscal = licenciaFiscal;
		this.fechaApertura = fechaApertura;
		this.horario = horario;
	}

	public Restaurante(String codRest, Localidad localidad, String nombre, String licenciaFiscal, String domicilio,
			Date fechaApertura, String horario, Set<RestEmpleado> restEmpleados, Set<Titular> titulars, Set<Existencias> existenciases) {
		this.codRest = codRest;
		this.localidad = localidad;
		this.nombre = nombre;
		this.licenciaFiscal = licenciaFiscal;
		this.domicilio = domicilio;
		this.fechaApertura = fechaApertura;
		this.horario = horario;
		this.restEmpleados = restEmpleados;
		this.titulars = titulars;
		this.existenciases = existenciases;
	}

	public String getCodRest() {
		return this.codRest;
	}

	public void setCodRest(String codRest) {
		this.codRest = codRest;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLicenciaFiscal() {
		return this.licenciaFiscal;
	}

	public void setLicenciaFiscal(String licenciaFiscal) {
		this.licenciaFiscal = licenciaFiscal;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getHorario() {
		return this.horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Set getRestEmpleados() {
		return this.restEmpleados;
	}

	public void setRestEmpleados(Set<RestEmpleado> restEmpleados) {
		this.restEmpleados = restEmpleados;
	}

	public Set getTitulars() {
		return this.titulars;
	}

	public void setTitulars(Set<Titular> titulars) {
		this.titulars = titulars;
	}

	public Set getExistenciases() {
		return this.existenciases;
	}

	public void setExistenciases(Set<Existencias> existenciases) {
		this.existenciases = existenciases;
	}

	@Override
	public String toString() {
		return "Restaurante [codRest=" + codRest + ", localidad=" + localidad + ", nombre=" + nombre
				+ ", licenciaFiscal=" + licenciaFiscal + ", domicilio=" + domicilio + ", fechaApertura=" + fechaApertura
				+ ", horario=" + horario + "]";
	}

}
