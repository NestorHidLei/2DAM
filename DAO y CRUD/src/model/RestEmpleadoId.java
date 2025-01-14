package model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class RestEmpleadoId implements java.io.Serializable {

	@ManyToOne
	@JoinColumn(name="dni_empleado")
	private Empleado empleado;
	@ManyToOne
	@JoinColumn(name="cod_rest")
	private Restaurante restaurante;
	

	public RestEmpleadoId() {
	}

	public RestEmpleadoId(Empleado empleado, Restaurante restaurante) {
		super();
		this.empleado = empleado;
		this.restaurante = restaurante;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RestEmpleadoId))
			return false;
		RestEmpleadoId castOther = (RestEmpleadoId) other;

		return Objects.equals(empleado, castOther.empleado) && Objects.equals(restaurante, castOther.restaurante);

	}

	public int hashCode() {
		return Objects.hash(empleado, restaurante);
	}

}
