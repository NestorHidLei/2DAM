package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import utils.Usuario;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String USUARIOS = "Usuarios.csv";
	private JPanel contentPane;
	private JTextField textApeliidos;
	private JTextField textNombre;
	private JTextField textEmail;
	private JPasswordField passwordField_1;
	private JComboBox<String> comboPerfil;
	private JDateChooser dateChooser;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public Registro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registro.class.getResource("/resources/logoApp.png")));
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 493, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCabezeraRegistro = new JPanel();
		panelCabezeraRegistro.setBackground(new Color(0, 215, 232));
		panelCabezeraRegistro.setBounds(0, 0, 477, 75);
		contentPane.add(panelCabezeraRegistro);
		panelCabezeraRegistro.setLayout(new BorderLayout(0, 0));

		JLabel lblRegistro = new JLabel("Registro de Usuarios");
		lblRegistro.setBackground(new Color(0, 215, 232));
		lblRegistro.setForeground(new Color(255, 255, 255));
		lblRegistro.setFont(new Font("Verdana", Font.BOLD, 20));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		panelCabezeraRegistro.add(lblRegistro, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNombre.setBounds(35, 116, 77, 31);
		contentPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblApellidos.setBounds(35, 171, 92, 31);
		contentPane.add(lblApellidos);

		textApeliidos = new JTextField();
		textApeliidos.setColumns(10);
		textApeliidos.setBounds(232, 174, 230, 31);
		contentPane.add(textApeliidos);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEmail.setBounds(35, 347, 92, 31);
		contentPane.add(lblEmail);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblFechaDeNacimiento.setBounds(35, 230, 187, 31);
		contentPane.add(lblFechaDeNacimiento);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(232, 230, 230, 31);
		contentPane.add(dateChooser);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(232, 116, 230, 31);
		contentPane.add(textNombre);

		comboPerfil = new JComboBox<String>();
		comboPerfil.setBounds(232, 290, 230, 31);
		contentPane.add(comboPerfil);
		comboPerfil.addItem("");
		comboPerfil.addItem("Cliente");
		comboPerfil.addItem("Administrador");

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(232, 347, 230, 31);
		contentPane.add(textEmail);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblPerfil.setBounds(35, 290, 187, 31);
		contentPane.add(lblPerfil);

		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblContrasea.setBounds(35, 402, 110, 31);
		contentPane.add(lblContrasea);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contraseña:");
		lblConfirmarContrasea.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblConfirmarContrasea.setBounds(35, 457, 187, 31);
		contentPane.add(lblConfirmarContrasea);

		passwordField = new JPasswordField();
		passwordField.setBounds(232, 402, 230, 31);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(232, 457, 230, 31);
		contentPane.add(passwordField_1);

		JLabel lblEnviar = new JLabel("    ENVIAR");
		lblEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registrarUser();
				 
			}
			@Override
        	public void mouseEntered(MouseEvent e) {
				lblEnviar.setBackground(new Color(0, 120, 180));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblEnviar.setBackground(new Color(0, 214, 232));
        	}
		});
		lblEnviar.setOpaque(true);
		lblEnviar.setBackground(new Color(0, 214, 232));
		lblEnviar.setForeground(new Color(255, 255, 255));
		lblEnviar.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnviar.setBounds(232, 515, 110, 31);
		contentPane.add(lblEnviar);
	}

	protected void registrarUser() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(USUARIOS, true))) {
				String nombre = textNombre.getText();
				String apellidos = textApeliidos.getText();
				Date fechaNacimiento = dateChooser.getDate();
				String perfil = comboPerfil.getSelectedItem().toString();
				String email = textEmail.getText();
				char[] contrasenia = passwordField.getPassword();
				char[] contrasenia2 = passwordField_1.getPassword();
				
				 if (nombre.isEmpty() || apellidos.isEmpty() || fechaNacimiento == null ||
				            perfil.isEmpty() || email.isEmpty() || contrasenia.length == 0 ||
				            contrasenia2.length == 0 || !Arrays.equals(contrasenia, contrasenia2)) {
					 JOptionPane.showMessageDialog(this, "Error: Rellena todos los campos",
								"Campos por rellenar", JOptionPane.ERROR_MESSAGE);
				 }
				 
				 if(emailCorrecto(email)) {
					Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, perfil, email, contrasenia,
							false);
					bw.write(usuario.toString());
					bw.newLine();
					limpiarCampos();
					this.dispose();
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean emailCorrecto(String email) {
		try (BufferedReader entrada = new BufferedReader(new FileReader(USUARIOS))) {
			String linea;

			// Leer línea por línea
			while ((linea = entrada.readLine()) != null) {
				// Dividir la línea en partes y eliminar comillas
				String[] partes_usuario = linea.replace("\"", "").split(";");

				// Verificar que la parte del email existe y es igual al email que estamos
				// verificando
				if (partes_usuario[4].equals(email)) {
					// Si se encuentra el email, mostrar mensaje y retornar false
					JOptionPane.showMessageDialog(this, "Error: Ya existe una cuenta con este email.",
							"Email Duplicado", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Si no se encontró el email, retornar true
		return true;
	}

	private void limpiarCampos() {
		// Limpiar campos de texto
		textNombre.setText("");
		textApeliidos.setText("");
		textEmail.setText("");

		// Limpiar campos de contraseña
		passwordField.setText("");
		passwordField_1.setText("");

		// Limpiar comboBox
		comboPerfil.setSelectedIndex(0); // Selecciona el primer ítem (vacío)

		// Limpiar JDateChooser
		dateChooser.setDate(null); // Resetea la fecha
	}
}
