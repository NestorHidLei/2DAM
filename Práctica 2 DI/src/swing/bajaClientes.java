package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import utils.Clientes;

public class bajaClientes extends JPanel {

    private JTable table;  // Tabla para mostrar los clientes
    private DefaultTableModel model;  // Modelo de la tabla
    private ArrayList<Clientes> listaClientes;  // Lista de clientes
    private Runnable actualizarTablaClientes;  // Runnable para actualizar la tabla
    private JTextField buscarField;  // Campo de texto para buscar clientes

    public bajaClientes(ArrayList<Clientes> listaClientes, Runnable actualizarTablaClientes) {
        this.listaClientes = listaClientes;  // Asignar la lista pasada
        this.actualizarTablaClientes = actualizarTablaClientes;  // Guardar referencia al método de actualización

        setLayout(new BorderLayout());

        // Crear panel para la búsqueda
        JPanel buscarPanel = new JPanel();
        buscarPanel.setLayout(new FlowLayout());

        buscarField = new JTextField(15); // Campo de texto para buscar
        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarClientes();
            }
        });

        buscarPanel.add(new JLabel("Buscar por nombre:"));
        buscarPanel.add(buscarField);
        buscarPanel.add(buscarButton);
        add(buscarPanel, BorderLayout.NORTH); // Añadir el panel de búsqueda al norte

        // Inicializar tabla
        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Edad");
        model.addColumn("Provincia");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para eliminar el cliente seleccionado
        JButton btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarClienteSeleccionado();
            }
        });
        add(btnEliminar, BorderLayout.SOUTH);

        // Cargar los clientes en la tabla
        cargarClientes();
    }

    private void cargarClientes() {
        model.setRowCount(0); // Limpiar la tabla
        for (Clientes cliente : listaClientes) {
            model.addRow(new Object[]{cliente.getNombre(), cliente.getApellidos(), cliente.getEdad(), cliente.getProvicia()});
        }
    }

    private void buscarClientes() {
        String busqueda = buscarField.getText().toLowerCase(); // Obtener el texto de búsqueda y convertir a minúsculas
        model.setRowCount(0); // Limpiar la tabla

        for (Clientes cliente : listaClientes) {
            if (cliente.getNombre().toLowerCase().contains(busqueda)) {
                model.addRow(new Object[]{cliente.getNombre(), cliente.getApellidos(), cliente.getEdad(), cliente.getProvicia()});
            }
        }
    }

    private void eliminarClienteSeleccionado() {
        int selectedRow = table.getSelectedRow(); // Obtener la fila seleccionada
        if (selectedRow != -1) { // Si hay una fila seleccionada
            String nombre = (String) model.getValueAt(selectedRow, 0);
            String apellidos = (String) model.getValueAt(selectedRow, 1);

            // Confirmar eliminación
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar a " + nombre + " " + apellidos + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Eliminar de la lista de clientes
                listaClientes.remove(selectedRow);
                model.removeRow(selectedRow); // Eliminar la fila de la tabla
                actualizarTablaClientes.run(); // Actualizar la tabla
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
