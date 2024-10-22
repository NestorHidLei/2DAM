package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class swingApp extends JFrame {

    public swingApp() {
        // Configuración de la ventana
        setTitle("Gestión Clientes");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        getContentPane().setLayout(new BorderLayout());

        // Panel superior con el logo y el nombre de la institución
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(128, 128, 255));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centra el contenido

        // Logo centrado
        JLabel logo = new JLabel(new ImageIcon(swingApp.class.getResource("/resources/twitch_PNG3 (1).png")));
        logo.setPreferredSize(new Dimension(100, 50)); // Ajuste del tamaño del logo
        topPanel.add(logo); // Añadimos el logo centrado

        // Panel inferior con el nombre de usuario
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(128, 128, 255));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel userLabel = new JLabel("Néstor Hidalgo Leiva");
        bottomPanel.add(userLabel);

        // Panel lateral con las opciones del menú (ahora entre el encabezado y el área de contenido)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(128, 128, 192));
        leftPanel.setLayout(new GridLayout(4, 1, 5, 5)); // Layout en cuadrícula con 4 filas

        JLabel clientesLabel = new JLabel("Clientes", SwingConstants.CENTER);
        clientesLabel.setForeground(Color.WHITE);
        JLabel productosLabel = new JLabel("Productos", SwingConstants.CENTER);
        productosLabel.setForeground(Color.WHITE);
        JLabel facturasLabel = new JLabel("Facturas", SwingConstants.CENTER);
        facturasLabel.setForeground(Color.WHITE);
        JLabel usuarioLabel = new JLabel("Usuario", SwingConstants.CENTER);
        usuarioLabel.setForeground(Color.BLACK);
        usuarioLabel.setBackground(new Color(224, 224, 224));
        usuarioLabel.setOpaque(true);

        // Añadir los elementos al panel lateral
        leftPanel.add(clientesLabel);
        leftPanel.add(productosLabel);
        leftPanel.add(facturasLabel);
        leftPanel.add(usuarioLabel);

        // Menús emergentes para Clientes y Productos
        JPopupMenu clientesMenu = new JPopupMenu();
        JMenuItem altaClientes = new JMenuItem("Alta Clientes");
        JMenuItem bajaClientes = new JMenuItem("Baja Clientes");
        clientesMenu.add(altaClientes);
        clientesMenu.add(bajaClientes);

        clientesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clientesMenu.show(clientesLabel, e.getX(), e.getY());
            }
        });

        JPopupMenu productosMenu = new JPopupMenu();
        JMenuItem altaProductos = new JMenuItem("Alta Productos");
        JMenuItem listarProductos = new JMenuItem("Listar Productos");
        productosMenu.add(altaProductos);
        productosMenu.add(listarProductos);

        productosLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                productosMenu.show(productosLabel, e.getX(), e.getY());
            }
        });

        // Panel central donde irá el contenido dinámico
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(192, 192, 192));
        GridBagLayout gbl_centerPanel = new GridBagLayout();
        gbl_centerPanel.columnWeights = new double[]{1.0};
        centerPanel.setLayout(gbl_centerPanel); // Usamos GridBagLayout para mayor flexibilidad
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Permite que el panel ocupe todo el espacio disponible

        JLabel centerLabel = new JLabel("Área de Contenido", SwingConstants.CENTER);
        centerPanel.add(centerLabel, gbc);

        // Crear un panel intermedio que contenga tanto el leftPanel como el centerPanel
        JPanel midPanel = new JPanel(new BorderLayout());
        midPanel.add(leftPanel, BorderLayout.WEST); // Panel lateral en la parte izquierda
        midPanel.add(centerPanel, BorderLayout.CENTER); // Área de contenido en el centro

        // Agregar los paneles al layout principal
        getContentPane().add(topPanel, BorderLayout.NORTH); // Panel superior
        getContentPane().add(midPanel, BorderLayout.CENTER); // Panel intermedio con el menú y área de contenido
        getContentPane().add(bottomPanel, BorderLayout.SOUTH); // Panel inferior
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            swingApp frame = new swingApp();
            frame.setVisible(true);
        });
    }
}
