package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Usuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public List<Usuario> usuarios = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Talleres Picasso - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(173, 216, 230));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Panel superior (Encabezado)
		JPanel panelEncabezado = new JPanel();
		panelEncabezado.setBackground(new Color(173, 216, 230));
		contentPane.add(panelEncabezado, BorderLayout.NORTH);
		panelEncabezado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblEncabezado = new JLabel("Talleres Picasso");
		lblEncabezado.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblEncabezado.setIcon(new ImageIcon(Login.class.getResource("/resources/cocheAzul.png")));
		lblEncabezado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncabezado.setHorizontalTextPosition(JLabel.CENTER);
		lblEncabezado.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEncabezado.setForeground(new Color(0, 64, 128));
		panelEncabezado.add(lblEncabezado);

		// Panel central (Formulario con GridLayout)
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(173, 216, 230));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(3, 2, 10, 15)); // 3 filas, 2 columnas, con espaciado

		// Etiqueta y campo para Usuario
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelCentral.add(lblUsuario);

		textField = new JTextField();
		panelCentral.add(textField);
		textField.setColumns(10);


		// Etiqueta y campo para Contraseña
		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelCentral.add(lblContrasenia);

		passwordField = new JPasswordField();
		panelCentral.add(passwordField);

		// Etiqueta para "¿No tienes cuenta?"
		JLabel lblCuenta = new JLabel("¿No tienes cuenta?");
		lblCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCuenta.setBackground(new Color(0, 120, 180));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblCuenta.setBackground(new Color(0, 214, 232));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Registro registro = new Registro(usuarios);
				registro.setVisible(true);
			}
		});
		lblCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuenta.setForeground(Color.BLUE);
		lblCuenta.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelCentral.add(lblCuenta);

		// Botón para iniciar sesión
		JButton btnIniciarSesion = new JButton("Inicia Sesión");
		btnIniciarSesion.addMouseListener(new MouseAdapter() {
			 @Override
			    public void mouseClicked(MouseEvent e) {
			        if (usuarios.isEmpty()) {
			            JOptionPane.showMessageDialog(btnIniciarSesion, "No hay usuarios registrados.");
			            return;
			        }

			        boolean encontrado = false;

			        for (Usuario usuario : usuarios) {
			            System.out.println("Verificando usuario: " + usuario.getEmail()); // Depuración
			            if (usuario.getEmail().equalsIgnoreCase(textField.getText())
			                    && Arrays.equals(usuario.getContrasenia(), passwordField.getPassword())) {
			                JOptionPane.showMessageDialog(btnIniciarSesion, "Inicio de sesión correcto"); 
			                encontrado = true;
			                Cliente cliente = new Cliente(usuarios, usuario.getEmail());
			                cliente.setVisible(true);
			            }
			        }
			        
			        if (!encontrado) {
			            JOptionPane.showMessageDialog(btnIniciarSesion, "Usuario o contraseña incorrectos.");
			        }
			        
			    }
			});
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciarSesion.setBackground(Color.LIGHT_GRAY);
		panelCentral.add(btnIniciarSesion);

		// Paneles vacíos para espaciado
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(173, 216, 230));
		contentPane.add(panelIzquierdo, BorderLayout.WEST);

		JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(173, 216, 230));
		contentPane.add(panelDerecho, BorderLayout.EAST);

		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(173, 216, 230));
		contentPane.add(panelInferior, BorderLayout.SOUTH);
	}
}
