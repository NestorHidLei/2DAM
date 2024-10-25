package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.toedter.calendar.JDateChooser;
import utils.Clientes;

public class altaClientes extends JPanel {

    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtEmail;
    private JComboBox<String> comboProvincia;
    private JDateChooser dateChooser;
    private JLabel lblMensaje;
    private ArrayList<Clientes> listaClientes; // Lista de clientes compartida
    private Runnable actualizarTablaClientes;  // Runnable para actualizar la tabla
    private swingApp mainApp;  // Referencia a la ventana principal

    // Modificar constructor para recibir la lista, el Runnable y la referencia a la ventana principal
    public altaClientes(ArrayList<Clientes> listaClientes, Runnable actualizarTablaClientes, swingApp mainApp) {
        this.listaClientes = listaClientes;  // Asignar la lista pasada
        this.actualizarTablaClientes = actualizarTablaClientes;  // Guardar referencia al método de actualización
        this.mainApp = mainApp;  // Guardar referencia a la ventana principal

        setLayout(new GridLayout(7, 2));

        // Crear los componentes del formulario
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblApellidos = new JLabel("Apellidos:");
        txtApellidos = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        JLabel lblProvincia = new JLabel("Provincia:");
        comboProvincia = new JComboBox<>(new String[]{
                "Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga", "Sevilla"
        });

        JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();  // Guardar cliente y volver a la pantalla principal
            }
        });

        lblMensaje = new JLabel("");

        // Añadir componentes al panel
        add(lblNombre);
        add(txtNombre);
        add(lblApellidos);
        add(txtApellidos);
        add(lblEmail);
        add(txtEmail);
        add(lblProvincia);
        add(comboProvincia);
        add(lblFechaNacimiento);
        add(dateChooser);
        add(new JLabel());
        add(btnGuardar);
        add(lblMensaje);
    }

    private void guardarCliente() {
        // Obtener datos del formulario
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String provincia = (String) comboProvincia.getSelectedItem();
        int edad = 2024 - dateChooser.getCalendar().getWeekYear();

        // Crear nuevo cliente y añadir a la lista compartida
        Clientes nuevoCliente = new Clientes(nombre, apellidos, edad, provincia);
        listaClientes.add(nuevoCliente);

        // Mostrar mensaje de confirmación
        lblMensaje.setText("Cliente guardado con éxito: " + nombre + " " + apellidos);

        // Limpiar los campos del formulario
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        comboProvincia.setSelectedIndex(0);
        dateChooser.setDate(null);

        // Llamar al Runnable para actualizar la tabla en swingApp
        actualizarTablaClientes.run();

        // Volver al panel principal después de guardar
        mainApp.mostrarPantallaPrincipal();
    }
}
