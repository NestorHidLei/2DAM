package mainApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import conexion.Conexion;

public class MainApp {

    // Consulta para insertar usuarios
    private static final String INSERTAR_USUARIO = "INSERT INTO usuarios (nombre, clave) VALUES (?, ?)";

    public static void main(String[] args) throws Exception {
        // Crear un mapa de usuarios (nombre, clave)
        Map<String, String> usuarios = new HashMap<>();
        usuarios.put("juan", "illo");
        usuarios.put("laura", "abc123");
        usuarios.put("javier", "password1");
        usuarios.put("marta", "pass@word2");
        usuarios.put("alberto", "clave123");
        usuarios.put("cristina", "qwerty2024");
        usuarios.put("andres", "andres2024");
        usuarios.put("sandra", "sandra_789");
        usuarios.put("pedro", "pedro987");
        usuarios.put("lucia", "lucia_pass");
        usuarios.put("david", "david567");
        usuarios.put("eva", "evita_123");
        usuarios.put("pablo", "pablo2024");
        usuarios.put("silvia", "silvia999");
        usuarios.put("gonzalo", "gonzaloX");
        usuarios.put("angela", "angela_pass");
        usuarios.put("raul", "raul999");
        usuarios.put("sofia", "sofia@pass");
        usuarios.put("alejandro", "ale_pass");
        usuarios.put("veronica", "vero123");

        // Conectamos con la base de datos
        try (Connection conn = Conexion.conectar()) {
        	try {
            // Desactivar autocommit para control manual de la transacción
            conn.setAutoCommit(false);

            // Preparar la sentencia
            PreparedStatement pst = conn.prepareStatement(INSERTAR_USUARIO);
            int contador = 0;

            // Bucle para insertar usuarios
            for (Map.Entry<String, String> entry : usuarios.entrySet()) {
            	// Nombre (clave del mapa)
                pst.setString(1, entry.getKey()); 
                // Clave (valor del mapa)
                pst.setString(2, entry.getValue());  
                // Añadir a batch
                pst.addBatch();  
                contador++;

                // Ejecutar el batch cada 5 usuarios
                if (contador % 5 == 0) {
                	// Ejecutar batch
                    pst.executeBatch(); 
                    System.out.println("Batch ejecutado para 5 usuarios");
                }
            }

            // Ejecutar batch restante
            pst.executeBatch(); 
            System.out.println("Batch ejecutado para los usuarios restantes");
            
            // Comitea 
            conn.commit();     

            System.out.println("Usuarios insertados correctamente");
            
        	}catch(Exception e) {
                System.err.println("Error a la hora de insertar: " + e.getMessage());
        		conn.rollback();
        	}

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
