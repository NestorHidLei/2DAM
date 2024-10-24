package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class swingApp extends JFrame {

    private JPanel midPanel;  // Panel que contiene el JTable o los formularios

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

        // Crear un panel intermedio que contenga tanto el leftPanel como el centerPanel
        midPanel = new JPanel();
        midPanel.setLayout(new BorderLayout(0, 0));

        // Agregar los paneles al layout principal
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(midPanel, BorderLayout.CENTER);

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
        
        // Agregar MouseListener al label de Cliente
        lblCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Mostrar opciones al hacer clic en el label de Cliente
                String[] options = {"Dar de alta clientes", "Dar de baja clientes", "Salir"};
                int choice = JOptionPane.showOptionDialog(swingApp.this, 
                        "Seleccione una opción", 
                        "Clientes", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.INFORMATION_MESSAGE, 
                        null, 
                        options, 
                        options[0]);

                switch (choice) {
                    case 0:
                        replaceWithAltaClientes();
                        break;
                    case 1:
                        replaceWithBajaClientes();
                        break;
                    case 2:
                        System.exit(0); // Salir de la aplicación
                        break;
                    default:
                        break;
                }
            }
        });

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
        
        // Crear tabla con los datos de los clientes
        createClientTable(); // Crea la tabla inicialmente
    }

    private void createClientTable() {
        // Crear tabla con los datos de los clientes
        String[] columnNames = { "Nombre", "Apellidos", "Email", "Provincia", "Edad" };
        Object[][] data = {
                { "Juan", "Pérez", "juan.perez@example.com", "Málaga", 30 },
                { "Ana", "Gómez", "ana.gomez@example.com", "Sevilla", 28 },
                { "Luis", "Martínez", "luis.martinez@example.com", "Cádiz", 35 },
                { "Marta", "Fernández", "marta.fernandez@example.com", "Granada", 22 },
                { "Carlos", "López", "carlos.lopez@example.com", "Almería", 40 },
                { "Laura", "García", "laura.garcia@example.com", "Córdoba", 26 },
                { "José", "Sánchez", "jose.sanchez@example.com", "Jaén", 33 },
                { "Carmen", "Jiménez", "carmen.jimenez@example.com", "Huelva", 29 },
                { "Antonio", "Torres", "antonio.torres@example.com", "Málaga", 38 },
                { "Patricia", "Moreno", "patricia.moreno@example.com", "Sevilla", 31 },
                { "Javier", "Vazquez", "javier.vazquez@example.com", "Cádiz", 27 },
                { "Sara", "Castro", "sara.castro@example.com", "Granada", 25 },
                { "Diego", "Mendoza", "diego.mendoza@example.com", "Almería", 36 },
                { "Silvia", "Ramos", "silvia.ramos@example.com", "Córdoba", 23 },
                { "Roberto", "Ruiz", "roberto.ruiz@example.com", "Jaén", 34 },
                { "Raquel", "Reyes", "raquel.reyes@example.com", "Huelva", 32 },
                { "Álvaro", "González", "alvaro.gonzalez@example.com", "Málaga", 37 },
                { "Elena", "Díaz", "elena.diaz@example.com", "Sevilla", 24 },
                { "Ricardo", "Hernández", "ricardo.hernandez@example.com", "Cádiz", 30 },
                { "Claudia", "Paredes", "claudia.paredes@example.com", "Granada", 29 }
        };

        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Añadir la tabla a un JScrollPane para que tenga barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        midPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void replaceWithAltaClientes() {
        midPanel.removeAll();  // Limpiar el panel

        altaClientes altaClientesPanel = new altaClientes(); // Crear la instancia de altaClientes
        altaClientesPanel.setSize(610, 500); // Asegúrate de que el tamaño es correcto
        midPanel.add(altaClientesPanel, BorderLayout.CENTER); // Añadir el nuevo panel
        midPanel.revalidate();  // Actualiza el panel
        midPanel.repaint(); // Repaint para asegurar que se vea
    }

    private void replaceWithBajaClientes() {
        midPanel.removeAll();  // Limpiar el panel
        bajaClientes bajaClientesPanel = new bajaClientes(); // Crear la instancia de bajaClientes
        bajaClientesPanel.setSize(610, 500); // Asegúrate de que el tamaño es correcto
        midPanel.add(bajaClientesPanel, BorderLayout.CENTER); // Añadir el nuevo panel
        midPanel.revalidate();  // Actualiza el panel
        midPanel.repaint(); // Repaint para asegurar que se vea
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            swingApp frame = new swingApp();
            frame.setVisible(true);
        });
    }
}
