package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import swing.AdminFrame;
import swing.Login;
import swing.UserFrame;

public class mainApp {
	
	   private static final String USUARIOS = "Usuarios.csv";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		verificarSesionActiva();
	}

	private static void verificarSesionActiva() {
	    boolean sesionEncontrada = false; // Variable para rastrear si se encontró una sesión activa

	    try (BufferedReader entrada = new BufferedReader(new FileReader(USUARIOS))) {
	        String linea;
	        while ((linea = entrada.readLine()) != null) {
	            String[] partes_usuario = linea.replace("\"", "").split(";");
	            String username = partes_usuario[4];
	            String perfil = partes_usuario[3];
	            boolean sesionActiva = Boolean.parseBoolean(partes_usuario[6]);

	            if (sesionActiva) {
	                sesionEncontrada = true; // Se encontró una sesión activa
	                // Si la sesión está activa, abre el marco correspondiente
	                if ("Administrador".equals(perfil)) {
	                    new AdminFrame(username).setVisible(true);
	                } else if ("Cliente".equals(perfil)) {
	                    new UserFrame().setVisible(true);
	                }
	                break; // Salir del bucle si se encontró una sesión activa
	            }
	        }
	    } catch (FileNotFoundException e) {
	        JOptionPane.showMessageDialog(null, "Archivo de usuarios no encontrado");
	        e.printStackTrace();
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Error al leer el archivo de usuarios");
	        e.printStackTrace();
	    }

	    // Si no se encontró ninguna sesión activa, abrir el login
	    if (!sesionEncontrada) {
	        new Login().setVisible(true);
	    }
	}

}
