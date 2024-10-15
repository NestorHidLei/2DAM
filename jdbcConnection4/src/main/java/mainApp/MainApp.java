package mainApp;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import conexion.Conexion;
import resources.Asignatura;
import resources.Profesor;

public class MainApp {

    // Queries SQL
    private static final String INSERTAR_PROFESORES = "INSERT INTO Profesor(NIF, Nombre, Especialidad, Telefono) VALUES (?,?,?,?)";
    private static final String INSERTAR_ASIGNATURAS = "INSERT INTO Asignatura(CodAsignatura, Nombre) VALUES(?,?)";

    public static void main(String[] args) {
    	//Declarar
        String linea = "";
        List<Profesor> listaProfesores = new ArrayList<>();
        List<Asignatura> listaAsignaturas = new ArrayList<>();
        // Conexión a la base de datos 
        try (Connection connect = Conexion.conectar()) {
            // Leer el CSV file
            try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/Fichero.csv"))) {
            	//Mientras la linea no sea null
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith("P")) { // Linea Profesor
                    	//Splitea la linea para sacar los datos de los profesores
                        String[] campos = linea.split(";");
                        String NIF = campos[1];
                        String Nombre = campos[2];
                        String Especialidad = campos[3];
                        String Telefono = campos[4];
                        //Creo los profesores
                        Profesor profesor = new Profesor(NIF, Nombre, Especialidad, Telefono);
                        //Los añado a la lista de profesores
                        listaProfesores.add(profesor);
                    } else if (linea.startsWith("A")) { // Linea Asignatura
                    	//Splitea la linea para sacar los datos de las asignaturas
                        String[] campos = linea.split(";");
                        String codAsignatura = campos[1];
                        String Nombre = campos[2];
                        //Creo las asignatura
                        Asignatura asignatura = new Asignatura(codAsignatura, Nombre);
                        //Los añado a la lista de asignaturas
                        listaAsignaturas.add(asignatura);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }

            // Insertar en la base de datos
            try {
            	//Quitamos el auto-commit
                connect.setAutoCommit(false);

                // Insert Profesores
                PreparedStatement st = connect.prepareStatement(INSERTAR_PROFESORES, PreparedStatement.RETURN_GENERATED_KEYS);
                for (Profesor profesor : listaProfesores) {
                    st.setString(1, profesor.getNIF());
                    st.setString(2, profesor.getNombre());
                    st.setString(3, profesor.getEspecialidad());
                    st.setString(4, profesor.getTeléfono()); 
                    st.executeUpdate();
                }

                // Insert Asignaturas
                PreparedStatement st2 = connect.prepareStatement(INSERTAR_ASIGNATURAS, PreparedStatement.RETURN_GENERATED_KEYS);
                for (Asignatura asignatura : listaAsignaturas) {
                    st2.setString(1, asignatura.getCodAsignatura());
                    st2.setString(2, asignatura.getNombre());
                    st2.executeUpdate();
                }

                // Commit la transaction
                connect.commit();  

            } catch (Exception e) {
                System.err.println("Error a la hora de insertar: " + e.getMessage());
                // Rollback si hay errpr
                connect.rollback(); 
            }

        } catch (Exception e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
}
