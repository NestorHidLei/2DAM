package mainApp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conexion.Conexion;

public class MainApp {

	// Procedure
	private static final String PROCEDURE = "{call Empleados_por_sexo(?)}";

	public static void main(String[] args) throws Exception {
		// Scanner
		Scanner sc = new Scanner(System.in);
		// Conectamos con la base de datos
		try (Connection conn = Conexion.conectar(); CallableStatement st = conn.prepareCall(PROCEDURE)) {
			String opcion;
			do {
				System.out.print("Quieres mostrar hombre(H) o mujeres(M): ");
				opcion = sc.next().toUpperCase();
				// Si es M se ejecuta para mujeres
				if (opcion.equals("M")) {
					st.setString(1, "Mujer");
					System.out.println("Ejecutando procedure para mujeres...");
					st.execute();
					// Si es H se ejecuta para hombres
				} else if (opcion.equals("H")) {
					st.setString(1, "Hombre");
					System.out.println("Ejecutando procedure para hombres...");
					st.execute();
				}
				// Mientras no este vacio o blanco o la opcion no sea H o M se repite
			} while (!opcion.isBlank() && !opcion.isEmpty() && !opcion.equals("H") && !opcion.equals("M"));
			
			ResultSet rs = st.getResultSet();
			
			System.out.println("\n| ID  | Tratamiento        | Nombre          | Cargo                       |");
			System.out.println("----------------------------------------------------------------------------");

			while (rs.next()) {
			    System.out.printf("| %-3d | %-18s | %-15s | %-27s |\n",
			            rs.getInt("id"),
			            rs.getString("tratamiento"),
			            rs.getString("nombre"),
			            rs.getString("cargo"));
			}


		} catch (SQLException e) {
			System.err.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}
}
