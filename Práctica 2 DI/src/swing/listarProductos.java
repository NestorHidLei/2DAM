package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import utils.Productos;

import java.awt.*;
import java.util.ArrayList;

public class listarProductos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel model;  // Modelo de la tabla

    // Constructor que recibe una lista de productos
    public listarProductos(ArrayList<Productos> productos) {
        setLayout(new BorderLayout());

        // Crear modelo de la tabla
        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Es Perecedero");

        // Rellenar la tabla con los datos de los productos
        for (Productos producto : productos) {
            model.addRow(new Object[]{
                    producto.getNombreProducto(),
                    producto.getPrecio(),
                    producto.isEsPerecedero() ? "Sí" : "No"  // Mostrar como "Sí" o "No"
            });
        }

        // Crear la tabla y configurar el modelo
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Añadir la tabla al panel
        add(scrollPane, BorderLayout.CENTER);

        // Configuración de la tabla
        table.setFillsViewportHeight(true); // La tabla llenará el área de vista
    }
}
