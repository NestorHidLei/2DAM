package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser; // Importar JDateChooser

public class altaClientes extends JPanel {

    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtEmail;
    private JComboBox<String> comboProvincia;
    private JDateChooser dateChooser; // Usar JDateChooser para la fecha de nacimiento

    public altaClientes() {
        setLayout(new GridLayout(6, 2)); // Usar un GridLayout para el formulario

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
        dateChooser = new JDateChooser(); // Inicializar JDateChooser
        dateChooser.setDateFormatString("dd/MM/yyyy"); // Establecer el formato de fecha

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar al pulsar el botón Guardar
                guardarCliente();
            }
        });

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
        add(new JLabel()); // Espacio vacío para alinear el botón
        add(btnGuardar);
    }

    private void guardarCliente() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String provincia = (String) comboProvincia.getSelectedItem(); // Obtener la provincia seleccionada
        String fechaNacimiento = dateChooser.getDate() != null ? dateChooser.getDate().toString() : "No especificada"; // Obtener fecha

        // Aquí puedes añadir la lógica para guardar los datos del cliente, como una base de datos
        // Por ejemplo, imprimir los datos en la consola
        System.out.println("Cliente guardado:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Email: " + email);
        System.out.println("Provincia: " + provincia);
        System.out.println("Fecha de Nacimiento: " + fechaNacimiento);
        
        // Limpiar los campos después de guardar
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        comboProvincia.setSelectedIndex(0); // Restablecer a la primera provincia
        dateChooser.setDate(null); // Restablecer el selector de fecha
    }
}
