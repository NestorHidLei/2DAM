package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swing.admin.NuevaClase;
import swing.admin.VerClientes;
import swing.admin.VerReserva;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String currentUserEmail;

    private static final String USUARIOS = "Usuarios.csv";

    public AdminFrame(String email) {
        this.currentUserEmail = email; // Almacenar el email del usuario actual
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Marcar la sesión como activa en el archivo CSV
        actualizarSesion(true);

        // Panel superior (cabecera)
        JPanel panelCabecera = new JPanel();
        panelCabecera.setBackground(new Color(198, 242, 244));
        contentPane.add(panelCabecera, BorderLayout.NORTH);

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/logoApp.png"))); // Icono de logo
        panelCabecera.add(lblLogo);

        JLabel lblTitulo = new JLabel("GYM Picasso");
        lblTitulo.setForeground(new Color(0, 64, 128));
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 30));
        panelCabecera.add(lblTitulo);

        // Panel inferior (pie de página)
        JPanel panelFotter = new JPanel();
        panelFotter.setBackground(new Color(198, 242, 244)); // Ajuste de color de fondo
        contentPane.add(panelFotter, BorderLayout.SOUTH);

        JLabel lblFooter = new JLabel("Enrique Martínez Fernández 14/11/2023");
        lblFooter.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelFotter.add(lblFooter);

        // Panel central (contenido principal)
        JPanel panelCentral = new JPanel();
        contentPane.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new GridLayout(2, 2, 20, 20)); // GridLayout 2x2 para cuatro opciones, con separación

        // Añadir los botones principales con iconos y texto
        JLabel lblAnadirClase = new JLabel("Añade clase", JLabel.CENTER);
        lblAnadirClase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NuevaClase clase = new NuevaClase();
                clase.setVisible(true);
            }
        });
        lblAnadirClase.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/addClase.png"))); // Icono de "Añadir Clase"
        lblAnadirClase.setHorizontalTextPosition(JLabel.CENTER);
        lblAnadirClase.setVerticalTextPosition(JLabel.BOTTOM);
        panelCentral.add(lblAnadirClase);

        JLabel lblVerReservas = new JLabel("Ver reservas", JLabel.CENTER);
        lblVerReservas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VerReserva reserva = new VerReserva();
                reserva.setVisible(true);
            }
        });
        lblVerReservas.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/listarReservas.png"))); // Icono de "Ver Reservas"
        lblVerReservas.setHorizontalTextPosition(JLabel.CENTER);
        lblVerReservas.setVerticalTextPosition(JLabel.BOTTOM);
        panelCentral.add(lblVerReservas);

        JLabel lblVerClientes = new JLabel("Ver clientes", JLabel.CENTER);
        lblVerClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VerClientes clientes = new VerClientes();
                clientes.setVisible(true);
            }
        });
        lblVerClientes.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/listarUsuarios.png"))); // Icono de "Ver Clientes"
        lblVerClientes.setHorizontalTextPosition(JLabel.CENTER);
        lblVerClientes.setVerticalTextPosition(JLabel.BOTTOM);
        panelCentral.add(lblVerClientes);

        JLabel lblCerrarSesion = new JLabel("Cierra sesión", JLabel.CENTER);
        lblCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualizarSesion(false); // Marcar la sesión como inactiva
                Login login = new Login();
                login.setVisible(true);
                dispose(); // Cerrar AdminFrame
            }
        });
        lblCerrarSesion.setIcon(new ImageIcon(AdminFrame.class.getResource("/resources/cierreSesion.png"))); // Icono de "Cerrar Sesión"
        lblCerrarSesion.setHorizontalTextPosition(JLabel.CENTER);
        lblCerrarSesion.setVerticalTextPosition(JLabel.BOTTOM);
        panelCentral.add(lblCerrarSesion);
    }

    // Método para actualizar el estado de sesión en el archivo CSV
    private void actualizarSesion(boolean estadoSesion) {
        List<String> usuariosActualizados = new ArrayList<>();

        try (BufferedReader entrada = new BufferedReader(new FileReader(USUARIOS))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partesUsuario = linea.split(";");
                if (partesUsuario[4].equals(currentUserEmail)) {
                    // Modificar el último campo (estado de sesión) a true o false según estadoSesion
                    partesUsuario[6] = String.valueOf(estadoSesion);
                    linea = String.join(";", partesUsuario);
                }
                usuariosActualizados.add(linea); // Agregar la línea (modificada o no) a la lista
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios");
        }

        // Sobrescribir el archivo con la lista actualizada
        try (PrintWriter salida = new PrintWriter(new FileWriter(USUARIOS))) {
            for (String usuario : usuariosActualizados) {
                salida.println(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el archivo de usuarios");
        }
    }
}
