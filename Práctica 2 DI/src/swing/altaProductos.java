package swing;

import javax.swing.*;

import utils.Productos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import resources.*;
public class altaProductos extends JPanel {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JCheckBox chkPerecedero;
    private JLabel lblMensaje;
    private ArrayList<Productos> listaProductos;  // Lista de productos

    public altaProductos(ArrayList<Productos> productos) {
        // Inicializar la lista de productos
        this.listaProductos = productos;

        setLayout(new GridLayout(5, 2));

        // Crear los componentes del formulario
        JLabel lblNombre = new JLabel("Nombre del Producto:");
        txtNombre = new JTextField();

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField();

        JLabel lblPerecedero = new JLabel("¿Es perecedero?");
        chkPerecedero = new JCheckBox();

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        lblMensaje = new JLabel("");

        // Añadir componentes al panel
        add(lblNombre);
        add(txtNombre);
        add(lblPrecio);
        add(txtPrecio);
        add(lblPerecedero);
        add(chkPerecedero);
        add(new JLabel());  // Espacio vacío
        add(btnGuardar);
        add(lblMensaje);
    }

    private void guardarProducto() {
        // Obtener datos del formulario
        String nombre = txtNombre.getText();
        String precioStr = txtPrecio.getText();

        // Validar datos
        if (nombre.isEmpty() || precioStr.isEmpty()) {
            lblMensaje.setText("Todos los campos son obligatorios.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            lblMensaje.setText("El precio debe ser un número válido.");
            return;
        }

        // Crear nuevo producto y añadir a la lista compartida
        Productos nuevoProducto = new Productos(nombre, precio, chkPerecedero.isSelected());
        listaProductos.add(nuevoProducto);

        // Mostrar mensaje de confirmación
        lblMensaje.setText("Producto guardado con éxito: " + nombre);

        // Limpiar los campos del formulario
        txtNombre.setText("");
        txtPrecio.setText("");
        chkPerecedero.setSelected(false);
        
        // Redirigir a la pantalla principal
        ((swingApp) SwingUtilities.getWindowAncestor(this)).mostrarPantallaPrincipal(); // Regresar a la pantalla principal
    }

}
