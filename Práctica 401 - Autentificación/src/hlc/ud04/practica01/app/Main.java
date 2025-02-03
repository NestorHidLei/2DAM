package hlc.ud04.practica01.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import hlc.ud04.appsec.core.Clientes;
import hlc.ud04.appsec.core.GestorPersistencia;
import hlc.ud04.appsec.interfaz.Interfaz;
import hlc.ud04.appsec.interfaz.consola.InterfazConsola;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;
import hlc.ud04.practica01.seguridad.SistemaSeguridadTemporal;
import hlc.ud04.practica01.totp.AutenticadorTemporal;

public class Main {
    /**
     * Base de datos SQLite almacenada localmente con las tablas "Usuarios" y "Clientes".
     */
    private static final String URL_BD = "jdbc:sqlite:base.db";
    
    public static void main(String[] args) {
        
        try (Connection conexion = DriverManager.getConnection(URL_BD)) {
            GestorPersistencia gestorPersistencia = new GestorPersistenciaSqlite("base.db");
            Clientes gestorClientes = new Clientes(gestorPersistencia);
            
            // Implementamos el sistema de seguridad temporal con autenticador
            SistemaSeguridad seguridad = new SistemaSeguridadTemporal(new AutenticadorTemporal());
            
            // Configuración de la interfaz de usuario en consola
            Interfaz ui = new InterfazConsola(gestorClientes, seguridad);
            ui.run();
        } catch (SQLException ex) {
            System.err.println("Error al conectar con la base de datos: " + ex.getMessage());
        }
    }
}
