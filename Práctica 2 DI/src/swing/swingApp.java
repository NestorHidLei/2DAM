package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import utils.Clientes;
import utils.Productos;

public class swingApp extends JFrame {

    private JPanel midPanel;  // Panel que contiene el JTable o los formularios
    private DefaultTableModel model;  // Modelo de la tabla
    private ArrayList<Clientes> listaClientes;  // Lista de clientes
    private ArrayList<Productos> listaProductos;  // Lista de productos
    private JPanel midTopPanel; // Panel para la barra de opciones

    public swingApp() {
        // Configuración de la ventana
        setTitle("Gestión Clientes");
        setSize(610, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        getContentPane().setLayout(new BorderLayout());

        listaClientes = new ArrayList<>();  // Inicializar la lista de clientes
        listaProductos = new ArrayList<>(); // Inicializar la lista de productos
        cargarClientesPrueba();  // Cargar clientes de prueba
        cargarProductosPrueba(); // Cargar productos de prueba

        // Panel superior con el logo y el nombre de la institución
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(128, 128, 255));
        FlowLayout fl_topPanel = new FlowLayout(FlowLayout.CENTER);
        fl_topPanel.setVgap(10);

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

        // Panel para la barra superior de opciones
        midTopPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) midTopPanel.getLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        agregarBarraOpciones(); // Agregar la barra superior con opciones

        // Agregar los paneles al layout principal
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(midPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Mostrar la pantalla principal con la tabla
        mostrarPantallaPrincipal();
    }

    // Método para cargar productos de prueba
    private void cargarProductosPrueba() {
        listaProductos.add(new Productos("Leche", 1.20, true));
        listaProductos.add(new Productos("Pan", 0.80, true));
        listaProductos.add(new Productos("Manzanas", 1.50, true));
        listaProductos.add(new Productos("Carne", 10.00, false));
        listaProductos.add(new Productos("Pescado", 15.00, false));
        listaProductos.add(new Productos("Arroz", 0.90, false));
        listaProductos.add(new Productos("Azúcar", 1.00, false));
        listaProductos.add(new Productos("Café", 3.50, false));
        listaProductos.add(new Productos("Aceite", 4.00, false));
        listaProductos.add(new Productos("Pastas", 1.80, false));
        listaProductos.add(new Productos("Queso", 2.50, true));
        listaProductos.add(new Productos("Yogur", 0.90, true));
        listaProductos.add(new Productos("Galletas", 2.20, false));
        listaProductos.add(new Productos("Cereales", 3.00, false));
        listaProductos.add(new Productos("Jugo", 1.70, true));
        listaProductos.add(new Productos("Frutas Mixtas", 5.00, true));
        listaProductos.add(new Productos("Verduras Mixtas", 4.00, true));
        listaProductos.add(new Productos("Pasta de Dientes", 1.30, false));
        listaProductos.add(new Productos("Champú", 2.00, false));
        listaProductos.add(new Productos("Detergente", 3.00, false));
    }

    // Método para agregar la barra de opciones
    private void agregarBarraOpciones() {
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
        // Agregar MouseListener al label de Cliente
        lblProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Mostrar opciones al hacer clic en el label de Cliente
                String[] options = {"Dar de alta productos", "Listar Productos", "Salir"};
                int choice = JOptionPane.showOptionDialog(swingApp.this, 
                        "Seleccione una opción", 
                        "Productos", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.INFORMATION_MESSAGE, 
                        null, 
                        options, 
                        options[0]);

                switch (choice) {
                    case 0:
                        replaceWithAltaProductos();
                        break;
                    case 1:
                        replaceWithListarProductos();
                        break;
                    default:
                        break;
                }
            }
        });
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
        lblUsuario.setForeground(new Color(255, 255, 255));
        lblUsuario.setBackground(new Color(128, 128, 192));
        midTopPanel.add(lblUsuario);

        midPanel.add(midTopPanel, BorderLayout.NORTH);  // Añadir la barra al midPanel
    }

    

	// Método para mostrar el panel principal con la tabla de clientes
    public void mostrarPantallaPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Tabla de clientes
        JTable table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Edad");
        model.addColumn("Provincia");

        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        cargarClientes();  // Cargar los datos en la tabla

        midPanel.removeAll();  // Limpiar midPanel antes de cambiar contenido
        midPanel.add(midTopPanel, BorderLayout.NORTH);  // Asegurar que la barra se mantenga
        midPanel.add(panelPrincipal, BorderLayout.CENTER);  // Añadir la tabla
        midPanel.revalidate();
        midPanel.repaint();
    }

    // Método para cargar los datos de los clientes en la tabla
    private void cargarClientes() {
        model.setRowCount(0); // Limpiar la tabla
        for (Clientes cliente : listaClientes) {
            model.addRow(new Object[]{cliente.getNombre(), cliente.getApellidos(), cliente.getEdad(), cliente.getProvicia()});
        }
    }

    // Método para reemplazar el panel actual por el formulario de altaClientes
    private void replaceWithAltaClientes() {
        altaClientes panelAlta = new altaClientes(listaClientes, () -> mostrarPantallaPrincipal(), swingApp.this);  // Pasar la lista y el Runnable
        midPanel.removeAll();  // Limpiar midPanel antes de cambiar contenido
        midPanel.add(midTopPanel, BorderLayout.NORTH);  // Asegurar que la barra se mantenga
        midPanel.add(panelAlta, BorderLayout.CENTER);  // Añadir el formulario de alta
        midPanel.revalidate();
        midPanel.repaint();
    }

    // Método para reemplazar el panel actual por la opción de bajaClientes
    private void replaceWithBajaClientes() {
        bajaClientes panelBaja = new bajaClientes(listaClientes, () -> mostrarPantallaPrincipal());  // Pasar la lista y el Runnable
        midPanel.removeAll();  // Limpiar midPanel antes de cambiar contenido
        midPanel.add(midTopPanel, BorderLayout.NORTH);  // Asegurar que la barra se mantenga
        midPanel.add(panelBaja, BorderLayout.CENTER);  // Añadir el formulario de baja
        midPanel.revalidate();
        midPanel.repaint();
    }
    
    protected void replaceWithAltaProductos() {
        altaProductos panelAltaProductos = new altaProductos(listaProductos); // Pasar la lista de productos
        midPanel.removeAll(); // Limpiar midPanel antes de cambiar contenido
        midPanel.add(midTopPanel, BorderLayout.NORTH); // Asegurar que la barra se mantenga
        midPanel.add(panelAltaProductos, BorderLayout.CENTER); // Añadir el formulario de alta
        midPanel.revalidate();
        midPanel.repaint();
    }


    protected void replaceWithListarProductos() {
        listarProductos panelListarProductos = new listarProductos(listaProductos); // Pasar la lista de productos
        midPanel.removeAll(); // Limpiar midPanel antes de cambiar contenido
        midPanel.add(midTopPanel, BorderLayout.NORTH); // Asegurar que la barra se mantenga
        midPanel.add(panelListarProductos, BorderLayout.CENTER); // Añadir el panel de listar productos
        midPanel.revalidate();
        midPanel.repaint();
    }

    // Cargar clientes de prueba para llenar la tabla inicialmente
    private void cargarClientesPrueba() {
        listaClientes.add(new Clientes("John", "Doe", 30, "Almería"));
        listaClientes.add(new Clientes("Jane", "Smith", 25, "Sevilla"));
        listaClientes.add(new Clientes("Juan", "Pérez", 30, "Málaga"));
        listaClientes.add(new Clientes("Ana", "Gómez", 28, "Sevilla"));
        listaClientes.add(new Clientes("Luis", "Martínez", 35, "Cádiz"));
        listaClientes.add(new Clientes("Marta", "Fernández", 22, "Granada"));
        listaClientes.add(new Clientes("Carlos", "López", 40, "Almería"));
        listaClientes.add(new Clientes("Laura", "García", 26, "Córdoba"));
        listaClientes.add(new Clientes("José", "Sánchez", 33, "Jaén"));
        listaClientes.add(new Clientes("Carmen", "Jiménez", 29, "Huelva"));
    }
}
