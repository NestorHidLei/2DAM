package model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="rest_empleado")
public class RestEmpleado implements java.io.Serializable {

	@EmbeddedId
	private RestEmpleadoId id;
	
	@Column
	private String funcion;
	

	public RestEmpleado() {
	}

	public RestEmpleado(RestEmpleadoId id) {
		this.id = id;
	}

	public RestEmpleado(RestEmpleadoId id, String funcion) {
		this.id = id;
		this.funcion = funcion;
	}

	public RestEmpleadoId getId() {
		return this.id;
	}

	public void setId(RestEmpleadoId id) {
		this.id = id;
	}

	public String getFuncion() {
		return this.funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

}
