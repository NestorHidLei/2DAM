package main;

import java.sql.Date;
import java.sql.JDBCType;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.cfg.Configuration;

import model.Curso;
import model.Inscripcion;
import model.Usuario;
import dao.CursoDaoImpl;
import dao.InscripcionDaoImpl;
import dao.UsuarioDaoImpl;

public class Main {
    public static void main(String[] args) {
        // Configurar la sesión de Hibernate
        SessionFactory factory = new Configuration().configure("./resources/hibernate.cfg.xml")
                .addAnnotatedClass(Curso.class)
                .addAnnotatedClass(Inscripcion.class)
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();

        // Iniciar una sesión
        Session session = factory.openSession();

        // Crear instancias de JuegoDaoImpl y UsuarioDaoImpl
        CursoDaoImpl cursoDao = new CursoDaoImpl(session);
        InscripcionDaoImpl InscripcionDao = new InscripcionDaoImpl(session);
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(session);

        try {
            // Empezar una transacción
            session.beginTransaction();
            
            List<Inscripcion> listaInscripcion = new ArrayList<>();
            
            

            // **Pruebas con la clase CursoDaoImpl**
//
//            // Insertar un nuevo curso
            Curso nuevoCurso = new Curso("Curso1", "Descripcion Curso1", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), listaInscripcion);
            cursoDao.insertCurso(nuevoCurso);
            System.out.println("Curso insertado: " + nuevoCurso.toString());
//
//            // Buscar un Curso por nombre
//            Curso cursoBuscado = cursoDao.searchByNombre("Curso1");
//            System.out.println("Curso encontrado: " + cursoBuscado.toString());
//
//            // Actualizar un Curso
//            if (cursoBuscado != null) {
//            	cursoBuscado.setNombreCurso("Curso Actualizado");
//                cursoDao.updateCurso(cursoBuscado);
//                System.out.println("Curso actualizado: " + cursoBuscado);
//            }
//
//            //Eliminar un juego por ID
//            if (cursoBuscado != null) {
//            	cursoDao.deleteCurso(cursoBuscado.getIdCurso());
//                System.out.println("Curso eliminado");
//            }

            
            // **Pruebas con la clase UsuarioDaoImpl**

//             Insertar un nuevo usuario
            Usuario nuevoUsuario = new Usuario("John", "johndoe@example.com", Date.valueOf(LocalDate.now()), "12345678", listaInscripcion);
            usuarioDao.insertUsuario(nuevoUsuario);
            System.out.println("Usuario insertado: " + nuevoUsuario);
//
//            // Buscar un usuario por nombre
//            Usuario usuarioBuscado = usuarioDao.searchByNombre("John");
//            System.out.println("Usuario encontrado: " + usuarioBuscado);
//
//            // Actualizar un usuario
//            if (usuarioBuscado != null) {
//                usuarioBuscado.setEmail("john.doe@newmail.com");
//                usuarioDao.updateUsuario(usuarioBuscado);
//                System.out.println("Usuario actualizado: " + usuarioBuscado);
//            }
//
//            // Eliminar un usuario por ID
//            if (usuarioBuscado != null) {
//                usuarioDao.deleteUsuario(usuarioBuscado.getIdUsuario());
//                System.out.println("Usuario eliminado");
//            }
//            
            // **Pruebas con la clase InscripcionDaoImpl**

            //Insertar un nuevo Inscripcion
            Inscripcion inscripcion = new Inscripcion(nuevoUsuario, nuevoCurso, Date.valueOf(LocalDate.now()));
            InscripcionDao.insertInscripcion(inscripcion);
            System.out.println("inscripcion insertada: " + inscripcion);
            listaInscripcion.add(inscripcion);

            
            // Buscar un usuario por nombre
//            Inscripcion inscripcionBuscado = InscripcionDao.searchById(inscripcion.getIdInscripcion());
//	        System.out.println("inscripcion encontrado: " + inscripcionBuscado);
//	
//	          // Actualizar un usuario
//	          if (inscripcionBuscado != null) {
//	        	  inscripcionBuscado.setFechaInscripcion(Date.valueOf(LocalDate.of(2020, 10, 9)));
//	        	  InscripcionDao.updateInscripcion(inscripcionBuscado);
//	              System.out.println("inscripcion actualizado: " + inscripcionBuscado);
//	          }
//	
//	          // Eliminar un usuario por ID
//	          if (inscripcionBuscado != null) {
//	        	  InscripcionDao.deleteInscripcion(inscripcionBuscado.getIdInscripcion());
//	              System.out.println("inscripcion eliminado");
//	          }
            
            	
            cursoDao.getCursosByUsuario(nuevoUsuario);
            usuarioDao.getUsuariosByCurso(nuevoCurso);
            
            
        } catch (Exception e) {
        	e.printStackTrace();
            // Rollback Si la transaccion sigue activa
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
        	session.close();
            factory.close();
        }
    }
}
