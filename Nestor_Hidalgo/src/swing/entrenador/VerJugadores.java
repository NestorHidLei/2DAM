package swing.entrenador;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class VerJugadores extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;
    private DefaultTableModel model;
	private List<Object[]> jugadoresData;

	/**
	 * Create the panel.
	 */
	public VerJugadores() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		// Modelo de tabla con columnas
        String[] columnNames = {"Nombre", "Apellidos", "Fecha de nacimiento", "Email"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        
        // Cargar los datos desde el archivo .csvº
        cargarDatosDesdeCSV("Usuarios.csv");

        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelFiltro = new JPanel();
		panel.add(panelFiltro, BorderLayout.NORTH);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelFiltro.add(lblApellido);
		
		textField = new JTextField();
		textField.setColumns(15);
		panelFiltro.add(textField);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelFiltro.add(btnFiltrar);

	}
	
	private void cargarDatosDesdeCSV(String rutaArchivo) {
		jugadoresData = new ArrayList<>();
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
                        jugadoresData.add(cliente);
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
