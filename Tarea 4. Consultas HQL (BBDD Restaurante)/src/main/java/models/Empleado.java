package models;

import java.sql.Date;

public class Empleado {
	/**
	 * Atributos
	 */
    private String dniEmpleado;
    private String nombre;
    private String domicilio;
    private Date fechaNacimiento;

    /**
     * Constructor
     * @param dniEmpleado
     * @param nombre
     * @param domicilio
     * @param fechaNacimiento
     */
    public Empleado(String dniEmpleado, String nombre, String domicilio, Date fechaNacimiento) {
        this.dniEmpleado = dniEmpleado;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Getter
     * @return
     */
    public String getDniEmpleado() {
        return dniEmpleado;
    }

    /**
     * Setter
     * @param dniEmpleado
     */
    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
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
     * @param dniEmpleado
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
     * @param dniEmpleado
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    /**
     * Getter
     * @return
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Setter
     * @param dniEmpleado
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
