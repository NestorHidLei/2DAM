package mainApp;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexion.Conexion;

public class MainApp {

	private static final String CREATE_TABLA_EMPLEADO = "CREATE TABLE Empleado (" + "ID INT PRIMARY KEY, "
			+ "DNI VARCHAR(20) UNIQUE, " + "Nombre VARCHAR(100), " + "FechaNac DATE, " + "Teléfono VARCHAR(15), "
			+ "Salario DECIMAL(10,2), " + "CodLocalidad INT, "
			+ "FOREIGN KEY (CodLocalidad) REFERENCES Localidad(CodLocalidad)" + ");";

	private static final String CREATE_TABLA_LOCALIDAD = "CREATE TABLE Localidad (" + "CodLocalidad INT PRIMARY KEY, "
			+ "Nombre VARCHAR(100), " + "CodProvincia INT, "
			+ "FOREIGN KEY (CodProvincia) REFERENCES Provincia(CodProvincia)" + ");";

	private static final String CREATE_TABLA_PROVINCIA = "CREATE TABLE Provincia (" + "CodProvincia INT PRIMARY KEY, "
			+ "Nombre VARCHAR(100), " + "CodRegión INT, " + "FOREIGN KEY (CodRegión) REFERENCES Región(CodRegión)"
			+ ");";

	private static final String CREATE_TABLA_REGION = "CREATE TABLE Región (" + "CodRegión INT PRIMARY KEY, "
			+ "Nombre VARCHAR(100)" + ");";

	public static void main(String[] args) {
		try (Connection connect = Conexion.conectar()) {
			PreparedStatement st4 = connect.prepareStatement(CREATE_TABLA_REGION);
			PreparedStatement st3 = connect.prepareStatement(CREATE_TABLA_PROVINCIA);
			PreparedStatement st2 = connect.prepareStatement(CREATE_TABLA_LOCALIDAD);
			PreparedStatement st1 = connect.prepareStatement(CREATE_TABLA_EMPLEADO);
			
			st4.executeUpdate();
			st3.executeUpdate();
			st2.executeUpdate();
			st1.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
