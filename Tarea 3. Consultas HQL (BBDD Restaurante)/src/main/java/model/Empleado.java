package model;

import java.sql.Date;

public class Empleado {
    private String dniEmpleado;
    private String nombre;
    private String domicilio;
    private Date fechaNacimiento;

    // Constructor
    public Empleado(String dniEmpleado, String nombre, String domicilio, Date fechaNacimiento) {
        this.dniEmpleado = dniEmpleado;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters and Setters
    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
