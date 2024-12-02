package swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Usuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JTextField textContrasenia;
	private JTextField textConfContrasenia;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;

	/**
	 * Create the frame.
	 * @param usuarios 
	 */
	public Registro(List<Usuario> usuarios) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(0, 64, 128));

		JPanel panelEncabezado = new JPanel();
		contentPane.add(panelEncabezado, BorderLayout.NORTH);
		panelEncabezado.setBackground(new Color(0, 64, 128));

		JLabel lblNewLabel = new JLabel("Registro Cliente");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		panelEncabezado.add(lblNewLabel);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelArriba = new JPanel();
		panelArriba.setBackground(new Color(173, 216, 230));
		panel.add(panelArriba, BorderLayout.NORTH);

		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBackground(new Color(173, 216, 230));
		panel.add(panelIzquierda, BorderLayout.WEST);

		JPanel panelDerecha = new JPanel();
		panelDerecha.setBackground(new Color(173, 216, 230));
		panel.add(panelDerecha, BorderLayout.EAST);

		JPanel panelAbajo = new JPanel();
		panelAbajo.setBackground(new Color(173, 216, 230));
		panel.add(panelAbajo, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registrarUser(usuarios);
			}
			@Override
            public void mouseEntered(MouseEvent e) {
				btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
		});
		panelAbajo.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panelAbajo.add(btnNewButton_1);

		JPanel panelCentro = new JPanel();
		panel.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(7, 2, 20, 20));

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblNewLabel_1);

		textNombre = new JTextField();
		panelCentro.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblNewLabel_2);

		textApellidos = new JTextField();
		panelCentro.add(textApellidos);
		textApellidos.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Teléfono");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblNewLabel_3);

		textTelefono = new JTextField();
		panelCentro.add(textTelefono);
		textTelefono.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblNewLabel_4);

		textEmail = new JTextField();
		panelCentro.add(textEmail);
		textEmail.setColumns(10);

		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblContrasenia);

		textContrasenia = new JTextField();
		panelCentro.add(textContrasenia);
		textContrasenia.setColumns(10);

		JLabel lblConfContrasenia = new JLabel("Confirmar Contraseña");
		lblConfContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblConfContrasenia);

		textConfContrasenia = new JTextField();
		panelCentro.add(textConfContrasenia);
		textConfContrasenia.setColumns(10);

		JLabel lblContacto = new JLabel("Método de contacto");
		lblContacto.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblContacto);

		JPanel panel_1 = new JPanel();
		panelCentro.add(panel_1);

		rdbtnNewRadioButton_1 = new JRadioButton("Teléfono");
		panel_1.add(rdbtnNewRadioButton_1);
		buttonGroup.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton = new JRadioButton("Email");
		panel_1.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton);
	}

	protected List<Usuario> registrarUser(List<Usuario> usuarios) {
		String nombre = textNombre.getText();
		String apellidos = textApellidos.getText();
		int telefono = Integer.parseInt(textTelefono.getText());
		String email = textEmail.getText();
		String ocpion;
		char[] contrasenia = textContrasenia.getText().toCharArray();
		char[] confContrasenia = textConfContrasenia.getText().toCharArray();
		ButtonModel selectedModel = buttonGroup.getSelection();
		if (selectedModel.equals(rdbtnNewRadioButton.getModel())) {
			ocpion = "Email";
		} else {
			ocpion = "Teléfono";
		}

		if (nombre.isEmpty() || apellidos.isEmpty() || telefono == 0 || email.isEmpty()
				|| contrasenia.length == 0 || confContrasenia.length == 0 || !Arrays.equals(contrasenia, confContrasenia)) {
			JOptionPane.showMessageDialog(this, "Error: Rellena todos los campos", "Campos por rellenar",
					JOptionPane.ERROR_MESSAGE);
		}
		if (emailCorrecto(email, usuarios)) {
			Usuario usuario = new Usuario(nombre, apellidos, telefono, ocpion,  email, contrasenia, true);
			usuarios.add(usuario);
			JOptionPane.showMessageDialog(this, "Usuario Registrado con éxito");
		} else {
			JOptionPane.showMessageDialog(this, "Error con el email", "Email ya existente",
					JOptionPane.ERROR_MESSAGE);
		}
		limpiarCampos();
		this.dispose();
		return usuarios;

	}
	
	private boolean emailCorrecto(String email, List<Usuario> usuarios) {
	    for (Usuario usuario : usuarios) {
	        if (usuario.getEmail().equals(email)) {
	            return false;
	        }
	    }
	    return true;
	}

	private void limpiarCampos() {
		// Limpiar campos de texto
		textNombre.setText("");
		textApellidos.setText("");
		textEmail.setText("");
		textTelefono.setText("");
		textContrasenia.setText("");
		textConfContrasenia.setText("");

	}

}
