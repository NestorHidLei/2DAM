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

public class VerReserva extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtNombre;
    private DefaultTableModel model;
    private List<Object[]> reservasData;

    /**
     * Create the frame.
     */
    public VerReserva() {
        setTitle("Listar Reservas");
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

        JLabel lblTitulo = new JLabel("Listar Reservas");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelCabecera.add(lblTitulo);

        // Modelo de tabla con columnas
        String[] columnNames = {"Nombre", "Clase", "Turno"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        
        // Cargar los datos desde el archivo .csv
        cargarDatosDesdeCSV("reservas.csv");

        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para el filtro
        JPanel panelFiltro = new JPanel();
        contentPane.add(panelFiltro, BorderLayout.SOUTH);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelFiltro.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setColumns(15);
        panelFiltro.add(txtNombre);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setFont(new Font("Verdana", Font.PLAIN, 14));
        panelFiltro.add(btnFiltrar);

        // Acción del botón Filtrar
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filtroNombre = txtNombre.getText().toLowerCase();
                if (filtroNombre.isEmpty()) {
                    // Si el campo de texto está vacío, muestra todos los datos
                    model.setRowCount(0);
                    for (Object[] reserva : reservasData) {
                        model.addRow(reserva);
                    }
                } else {
                    // Filtro de los datos por nombre
                    model.setRowCount(0); // Limpiar la tabla
                    for (Object[] reserva : reservasData) {
                        if (reserva[0].toString().toLowerCase().contains(filtroNombre)) {
                            model.addRow(reserva);
                        }
                    }
                }
            }
        });
    }

    /**
     * Carga los datos desde un archivo CSV y los guarda en el modelo de la tabla.
     */
    private void cargarDatosDesdeCSV(String rutaArchivo) {
        reservasData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                if (valores.length >= 3) {
                    // Extraer los campos necesarios de cada línea
                    String nombre = valores[0];
                    String clase = valores[1];
                    String turno = valores[2];
                    
                    Object[] reserva = {nombre, clase, turno};
                    reservasData.add(reserva);
                    model.addRow(reserva);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo CSV", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	VerReserva frame = new VerReserva();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
