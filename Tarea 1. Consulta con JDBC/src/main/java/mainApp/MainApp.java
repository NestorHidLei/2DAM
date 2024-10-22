package mainApp;

import java.sql.Timestamp;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import conexion.Conexion;

public class MainApp {

	public static void main(String[] args) {
		try(Connection connect = Conexion.conectar()){
			PreparedStatement st = connect.prepareStatement("Select * from actor");
			ResultSet rs = st.executeQuery();
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("tabla_actores.txt"))) {
				while(rs.next()) {
					int id = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellidos =rs.getString(3);
					Timestamp date = rs.getTimestamp(4);
					String linea = "ID: " + id + " nombre: " + nombre + " apellidos: " + apellidos + " Last-Update: " + date;				
					bw.write(linea);	
					bw.newLine();
				}
			}
			
			
			
			
		} catch (Exception e) {
			
		}
	}

}
