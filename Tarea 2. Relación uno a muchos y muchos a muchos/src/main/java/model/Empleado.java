package model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Empleado {
    @Id
    private String dniEmpleado;
    
    private String nombre;
    private String domicilio;
    private Date fechaNacimiento;
    
    /**
     * Constructor de empleado
     * @param dniEmpleado - dni del empleado
     * @param nombre - nombre del empleado
     * @param domicilio - domicilio del empleado
     * @param fechaNacimiento - fecha de nacimiento del empleado
     * @param restaurante - Restaurante en el que trabaja el empleado
     */
    public Empleado(String dniEmpleado, String nombre, String domicilio, Date fechaNacimiento,
			Restaurante restaurante) {
		super();
		this.dniEmpleado = dniEmpleado;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;
		this.restaurante = restaurante;
	}

    private Restaurante restaurante;

	/**
	 * @return el dni del empleado
	 */
    public String getDniEmpleado() {
        return dniEmpleado;
    }

    /**
     * Cambia el dni del empleado
     * @param dniEmpleado - dni del empleado
     */
    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    /**
	 * @return el nombre del empleado
	 */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del empleado
     * @param nombre - nombre del empleado
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
	 * @return el domicilio del empleado
	 */
    public String getDomicilio() {
        return domicilio;
    }
    
    /**
     * Cambia el domicilio del empleado
     * @param domicilio - domicilio del empleado
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    /**
	 * @return la fecha de nacimiento del empleado
	 */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    /**
     * Cambia la fecha de nacimiento del empleado
     * @param fechaNacimiento - fecha de nacimiento del empleado
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
	 * @return el restaurante del empleado
	 */
    public Restaurante getRestaurante() {
        return restaurante;
    }

    /**
     * Cambia el restaurante del empleado
     * @param restaurante - restaurante del empleado
     */
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
