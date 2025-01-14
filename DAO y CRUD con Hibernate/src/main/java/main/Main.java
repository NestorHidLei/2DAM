package main;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import model.Juego;
import model.Usuario;
import dao.JuegoDaoImpl;
import dao.UsuarioDaoImpl;

public class Main {
    public static void main(String[] args) {
        // Configurar la sesión de Hibernate
        SessionFactory factory = new Configuration().configure("./resources/hibernate.cfg.xml")
                .addAnnotatedClass(Juego.class)
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();

        // Iniciar una sesión
        Session session = factory.openSession();

        // Crear instancias de JuegoDaoImpl y UsuarioDaoImpl
        JuegoDaoImpl juegoDao = new JuegoDaoImpl(session);
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(session);

        try {
            // Empezar una transacción
            session.beginTransaction();

            // **Pruebas con la clase JuegoDaoImpl**

            // Insertar un nuevo juego
            Juego nuevoJuego = new Juego("The Witcher 3","es un videojuego de rol de acción en un mundo abierto donde Geralt de Rivia busca a su hija adoptiva, Ciri, mientras enfrenta decisiones difíciles y criaturas sobrenaturales.", "PC", 9.0);
            juegoDao.insertJuego(nuevoJuego);
            System.out.println("Juego insertado: " + nuevoJuego.toString());

            // Buscar un juego por nombre
            Juego juegoBuscado = juegoDao.searchByNombre("The Witcher 3");
            System.out.println("Juego encontrado: " + juegoBuscado.toString());

            // Buscar juegos por plataforma
            List<Juego> juegosPorPlataforma = juegoDao.searchByPlataformas("PC");
            System.out.println("Juegos encontrados por plataforma: " + juegosPorPlataforma);

            // Actualizar un juego
            if (juegoBuscado != null) {
                juegoBuscado.setNombre("The Witcher 3 - Wild Hunt");
                juegoDao.updateJuego(juegoBuscado);
                System.out.println("Juego actualizado: " + juegoBuscado);
            }

            // Eliminar un juego por ID
            if (juegoBuscado != null) {
                juegoDao.deleteJuego(juegoBuscado.getId());
                System.out.println("Juego eliminado");
            }

            
            // **Pruebas con la clase UsuarioDaoImpl**

            // Insertar un nuevo usuario
            Usuario nuevoUsuario = new Usuario("John", "Doe", "johndoe@example.com", "Jhony", "123");
            usuarioDao.insertUsuario(nuevoUsuario);
            System.out.println("Usuario insertado: " + nuevoUsuario);

            // Buscar un usuario por nombre
            Usuario usuarioBuscado = usuarioDao.searchByNombre("John");
            System.out.println("Usuario encontrado: " + usuarioBuscado);

            // Buscar usuarios por apellido
            List<Usuario> usuariosPorApellido = usuarioDao.searchByApellido("Doe");
            System.out.println("Usuarios encontrados por apellido: " + usuariosPorApellido);

            // Actualizar un usuario
            if (usuarioBuscado != null) {
                usuarioBuscado.setEmail("john.doe@newmail.com");
                usuarioDao.updateUsuario(usuarioBuscado);
                System.out.println("Usuario actualizado: " + usuarioBuscado);
            }

            // Eliminar un usuario por ID
            if (usuarioBuscado != null) {
                usuarioDao.deleteUsuario(usuarioBuscado.getUsuarioId());
                System.out.println("Usuario eliminado");
            }

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
