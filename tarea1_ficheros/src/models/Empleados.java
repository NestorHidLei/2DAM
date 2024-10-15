package models;

import java.io.Serializable;

import utils.UtilsFicheroArray;

public class Empleados implements Serializable{
	
	//Atributos del empleado
	private String empresa;
	private int edad;
	private int num_empleados;
	
	
	/**
	 * Constructor vacio
	 */
	public Empleados() {}

	/**
	 * Constructor de un empleado
	 * @param empresa - Empresa del empleado
	 * @param edad -  Edad del empleado
	 * @param num_empleados - Números de empleados a su cargo
	 */
	public Empleados(String empresa, int edad, int num_empleados) {
		this.empresa = empresa;
		this.edad = edad;
		this.num_empleados = num_empleados;
	}

	/**
	 * Devuelve la empresa del empleado
	 * @return La empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Pone el nombre de la empresa
	 * @param empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Devuelve la edad del empleado
	 * @return La edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Pone la edad del empleado
	 * @param empresa
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Devuelve el numero de empleados del empleado
	 * @return La empresa
	 */
	public int getNum_empleados() {
		return num_empleados;
	}
	
	/**
	 * Pone el numero de empleados del empleario
	 * @param empresa
	 */
	public void setNum_empleados(int num_empleados) {
		this.num_empleados = num_empleados;
	}

	@Override
	public String toString() {
		return "Empresa: " + empresa + "\nEdad: " + edad + "\nNúmero de empleados: " + num_empleados;
	}
	
	
}
