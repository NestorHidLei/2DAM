package swing.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtApellido;
    private DefaultTableModel model;
    private List<Object[]> clientesData;

    /**
     * Create the frame.
     */
    public VerClientes() {
        setTitle("Listar Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700, 300, 493, 384);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Panel de cabecera con título
        JPanel panelCabecera = new JPanel();
        panelCabecera.setBackground(new Color(0, 215, 232));
        contentPane.add(panelCabecera, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Listar Clientes");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelCabecera.add(lblTitulo);

        // Modelo de tabla con columnas
        String[] columnNames = {"Nombre", "Apellidos", "Fecha de nacimiento", "Email"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        
        // Cargar los datos desde el archivo .csvº
        cargarDatosDesdeCSV("Usuarios.csv");

        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para el filtro
        JPanel panelFiltro = new JPanel();
        contentPane.add(panelFiltro, BorderLayout.SOUTH);
        
        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelFiltro.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setColumns(15);
        panelFiltro.add(txtApellido);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelFiltro.add(btnFiltrar);

        // Acción del botón Filtrar
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filtroApellido = txtApellido.getText().toLowerCase();
                if (filtroApellido.isEmpty()) {
                    // Si el campo de texto está vacío, muestra todos los datos
                    model.setRowCount(0);
                    for (Object[] cliente : clientesData) {
                        model.addRow(cliente);
                    }
                } else {
                    // Filtro de los datos por apellido
                    model.setRowCount(0); // Limpiar la tabla
                    for (Object[] cliente : clientesData) {
                        if (cliente[1].toString().toLowerCase().contains(filtroApellido)) {
                            model.addRow(cliente);
                        }
                    }
                }
            }
        });
    }

    /**
     * Carga los datos desde un archivo CSV y los guarda en el modelo de la tabla.
     * Solo carga los registros con el rol "Cliente".
     */
    private void cargarDatosDesdeCSV(String rutaArchivo) {
        clientesData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                if (valores.length >= 5) {
                    // Solo agregar los clientes (ignorar los administradores)
                    String rol = valores[3];
                    if (rol.equalsIgnoreCase("Cliente")) {
                        // Extraer solo los campos necesarios
                        String nombre = valores[0];
                        String apellidos = valores[1];
                        String fechaNacimiento = valores[2];
                        String email = valores[4];
                        
                        Object[] cliente = {nombre, apellidos, fechaNacimiento, email};
                        clientesData.add(cliente);
                        model.addRow(cliente);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo CSV", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
