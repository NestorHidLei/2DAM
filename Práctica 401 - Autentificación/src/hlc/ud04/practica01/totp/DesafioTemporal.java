package hlc.ud04.practica01.totp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hlc.ud04.appsec.seguridad.autenticacion.Desafio;

public class DesafioTemporal implements Desafio {
	private static final String DATABASE = "jdbc:sqlite:base.db";
	
	private static String usuario;
	private String secreto;
	
	/**
	 * Con el nombre de usuario, buscamos el secreto del usuario
	 * @param usuario - Nombre de usuario
	 */
	public DesafioTemporal(String usuario) {
		DesafioTemporal.usuario = usuario;
		this.secreto = initSecreto();
	}
	
	/**
	 * obtenemos el usuario que ingresó  el cliente o usuario,
	 *  y buscamos en la base de datos el secreto.
	 * @return el secreto
	 */
	public static String initSecreto() {
		String query = "SELECT secreto FROM Usuarios WHERE usuario = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            String secret = rs.getString("secreto");
            rs.close();	stmt.close();
            
            return secret;
		} catch (SQLException e) {
			return null;			
		}
		
	}
	
	/**
	 * Obtiene el usuario
	 * @return el usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Setea el usuario
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		DesafioTemporal.usuario = usuario;
	}
	
	/**
	 * Obtiene el secreto
	 * @return el secreto 
	 */
	public String getSecreto() {
		return secreto;
	}
	
	/**
	 * Setea el secreto
	 * @param secreto - secreto del usuario
	 */
	public void setSecreto(String secreto) {
		this.secreto = secreto;
	}
}
