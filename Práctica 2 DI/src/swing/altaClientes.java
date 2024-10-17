package swing;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;

import main.*;
import swing.*;
import utils.Clientes;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class altaClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxProvincias;
	private swingApp mainFrame; // Variable para referencia a swingApp

	public altaClientes(swingApp frame) {
		this.mainFrame = frame; // Guardar la referencia correctamente
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 604, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null); // Agregar layout

		JLabel lblNuevoCliente = new JLabel("Nuevo Cliente");
		lblNuevoCliente.setBounds(187, 13, 182, 37);
		lblNuevoCliente.setFont(new Font("Palatino Linotype", Font.BOLD, 27));
		contentPane.add(lblNuevoCliente);

		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(15, 60, 62, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);

		textNombre = new JTextField();
		textNombre.setBounds(81, 61, 150, 20);
		textNombre.setColumns(10);
		contentPane.add(textNombre);

		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setBounds(15, 99, 64, 19);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setBounds(83, 100, 148, 20);
		textApellidos.setColumns(10);
		contentPane.add(textApellidos);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento: ");
		lblFechaDeNacimiento.setBounds(249, 60, 144, 19);
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblFechaDeNacimiento);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(397, 60, 167, 20);
		contentPane.add(dateChooser);

		JLabel lblProvincia = new JLabel("Provincia: ");
		lblProvincia.setBounds(249, 99, 66, 19);
		lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblProvincia);

		// Crear JComboBox con provincias
		String[] provincias = { "Málaga", "Sevilla", "Almería", "Huelva", "Granada", "Jaén", "Córdoba", "Cádiz" };
		comboBoxProvincias = new JComboBox<>(provincias);
		comboBoxProvincias.setSelectedIndex(-1);
		comboBoxProvincias.setBounds(333, 100, 231, 20);
		contentPane.add(comboBoxProvincias);

		JButton btnAniadirCliente = new JButton("Añadir Cliente");
		btnAniadirCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAniadirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres añadir al cliente?",
						"Confirmar acción", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					añadirCliente();
				}
			}
		});
		btnAniadirCliente.setBounds(221, 164, 131, 47);
		contentPane.add(btnAniadirCliente);
	}

	protected void añadirCliente() {
        String nombreCliente = textNombre.getText();
        String apellidosCliente = textApellidos.getText();
        int edad = 2024 - dateChooser.getCalendar().getWeekYear();
        String provinciaCliente = (String) comboBoxProvincias.getSelectedItem();

        if (nombreCliente.isEmpty() || apellidosCliente.isEmpty() || provinciaCliente == null || dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }
        
        boolean existeCliente = false;
        
        for(int i = 0; i < mainFrame.getModelClientes().size(); i++) {
        	Clientes cliente = mainFrame.getModelClientes().getElementAt(i);
	        if (cliente.getNombre().equals(nombreCliente) && cliente.getApellidos().equals(apellidosCliente)) {
	        	existeCliente = true;
	        }
        }
        if(existeCliente) {
            JOptionPane.showMessageDialog(this, "Ya existe un registro con dicho nombre y apellidos");
        } else {
        	Clientes cliente = new Clientes(nombreCliente, apellidosCliente, edad, provinciaCliente);
            swingApp.listaCliente.add(cliente);
            // Actualizar el modelo en la ventana principal
            mainFrame.actualizarListaClientes(); 
        }
    }
}