package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class swingApp extends JFrame {

    public swingApp() {
        // Configuración de la ventana
        setTitle("Gestión Clientes");
        setSize(610, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        getContentPane().setLayout(new BorderLayout());

        // Panel superior con el logo y el nombre de la institución
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(128, 128, 255));
        FlowLayout fl_topPanel = new FlowLayout(FlowLayout.CENTER);
        fl_topPanel.setVgap(10);
        topPanel.setLayout(fl_topPanel); // Centra el contenido

        // Logo centrado
        JLabel logo = new JLabel(new ImageIcon(swingApp.class.getResource("/resources/twitch_PNG3 (1).png")));
        logo.setPreferredSize(new Dimension(400, 100)); // Ajuste del tamaño del logo
        topPanel.add(logo); // Añadimos el logo centrado

        // Panel inferior con el nombre de usuario
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(128, 128, 255));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel userLabel = new JLabel("Néstor Hidalgo Leiva");
        bottomPanel.add(userLabel);

        // Menús emergentes para Clientes y Productos
        JPopupMenu clientesMenu = new JPopupMenu();
        JMenuItem altaClientes = new JMenuItem("Alta Clientes");
        JMenuItem bajaClientes = new JMenuItem("Baja Clientes");
        clientesMenu.add(altaClientes);
        clientesMenu.add(bajaClientes);

        JPopupMenu productosMenu = new JPopupMenu();
        JMenuItem altaProductos = new JMenuItem("Alta Productos");
        JMenuItem listarProductos = new JMenuItem("Listar Productos");
        productosMenu.add(altaProductos);
        productosMenu.add(listarProductos);

        // Crear un panel intermedio que contenga tanto el leftPanel como el centerPanel
        JPanel midPanel = new JPanel();

        // Agregar los paneles al layout principal
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(midPanel, BorderLayout.CENTER);
        midPanel.setLayout(new BorderLayout(0, 0));
        
        JLabel centerLabel = new JLabel("Área de Contenido", SwingConstants.CENTER);
        midPanel.add(centerLabel);
        
        JPanel midTopPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) midTopPanel.getLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        midPanel.add(midTopPanel, BorderLayout.NORTH);
        
        JLabel lblCliente = new JLabel("     Cliente      ", SwingConstants.CENTER);
        lblCliente.setFont(new Font("Impact", Font.PLAIN, 28));
        lblCliente.setOpaque(true);
        lblCliente.setForeground(new Color(255, 255, 255));
        lblCliente.setBackground(new Color(128, 128, 192));
        midTopPanel.add(lblCliente);
        
        JLabel lblProductos = new JLabel("     Productos     ", SwingConstants.CENTER);
        lblProductos.setFont(new Font("Impact", Font.PLAIN, 28));
        lblProductos.setOpaque(true);
        lblProductos.setForeground(new Color(255, 255, 255));
        lblProductos.setBackground(new Color(128, 128, 192));
        midTopPanel.add(lblProductos);
        
        JLabel lblFacturas = new JLabel("     Facturas     ", SwingConstants.CENTER);
        lblFacturas.setFont(new Font("Impact", Font.PLAIN, 28));
        lblFacturas.setOpaque(true);
        lblFacturas.setForeground(new Color(255, 255, 255));
        lblFacturas.setBackground(new Color(128, 128, 192));
        midTopPanel.add(lblFacturas);
        
        JLabel lblUsuario = new JLabel("     Usuario     ", SwingConstants.CENTER);
        lblUsuario.setFont(new Font("Impact", Font.PLAIN, 28));
        lblUsuario.setOpaque(true);
        lblUsuario.setForeground(Color.BLACK);
        lblUsuario.setBackground(new Color(224, 224, 224));
        midTopPanel.add(lblUsuario);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH); // Panel inferior
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            swingApp frame = new swingApp();
            frame.setVisible(true);
        });
    }
}
