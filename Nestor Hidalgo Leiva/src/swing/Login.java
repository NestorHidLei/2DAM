package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelos.Serie;
import modelos.Temporada;
import modelos.Usuario;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public List<Usuario> usuarios = new ArrayList<>();
	private List<Temporada> lstTemporadas = new ArrayList<>();
	private List<Serie> misSeries = new ArrayList<>();

	private JPanel panel_2;
	

	/**
	 * Create the frame.
	 */
	public Login() {
		usuarios.add(new Usuario("Néstor", "Hidalgo Leiva","nhidlei@gmail.com", "12345678".toCharArray(), misSeries));
		misSeries.add(new Serie("Shogun", "Disney+", lstTemporadas));
		lstTemporadas.add(new Temporada("2", LocalDate.of(2023, 11, 12), LocalDate.of(2025, 11, 12), 26));
//		
		setTitle("Seriefly");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		panel_1.setBackground(new Color(0, 0, 0));
		panel.add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("SerieFli");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/resources/logo.png")));
		panel_1.add(lblNewLabel_1);

		JPanel panelInicioSesion = new JPanel();
		panel.add(panelInicioSesion);
		panelInicioSesion.setLayout(new BorderLayout(0, 0));

		JPanel panelArribaInicio = new JPanel();
		panelInicioSesion.add(panelArribaInicio, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Inicio Sesi\u00F3n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelArribaInicio.add(lblNewLabel_2);

		JPanel panelAbajo = new JPanel();
		panelInicioSesion.add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 2, 0, 10));

		JLabel lblNewLabel_4 = new JLabel("\u00BFNo tienes cuenta?");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_4.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Registro registro = new Registro(usuarios);
				registro.setVisible(true);
			}
		});
		lblNewLabel_4.setForeground(new Color(0, 128, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panelAbajo.add(lblNewLabel_4);

		panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				panel_2.setBackground(new Color(128, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				panel_2.setBackground(new Color(255, 0, 0));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (usuarios.isEmpty()) {
					JOptionPane.showMessageDialog(panel_2, "No hay usuarios registrados.");
					return;
				}

				boolean encontrado = false;

				for (Usuario usuario : usuarios) {
					System.out.println("Verificando usuario: " + usuario.getEmail());
					if (usuario.getEmail().equalsIgnoreCase(textField.getText())
							&& Arrays.equals(usuario.getPass(), passwordField.getPassword())) {
						JOptionPane.showMessageDialog(panel_2, "Inicio de sesión correcto");
						encontrado = true;
						Home home = new Home(usuarios, lstTemporadas, misSeries, textField.getText());
						home.setVisible(true);
						dispose();
					}
				}

				if (!encontrado) {
					JOptionPane.showMessageDialog(panel_2, "Usuario o contraseña incorrectos.");
				}
			}
		});
		panel_2.setBackground(new Color(255, 0, 0));
		panelAbajo.add(panel_2);

		JLabel lblNewLabel_5_1 = new JLabel("Iniciar Sesi\u00F3n");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_1.setBackground(Color.RED);
		panel_2.add(lblNewLabel_5_1);

		JPanel panelCentralInicio = new JPanel();
		panelInicioSesion.add(panelCentralInicio, BorderLayout.CENTER);
		panelCentralInicio.setLayout(new GridLayout(2, 2, 0, 10));

		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentralInicio.add(lblNewLabel_3);

		textField = new JTextField();
		panelCentralInicio.add(textField);
		textField.setColumns(10);

		JLabel lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentralInicio.add(lblContrasenia);

		passwordField = new JPasswordField();
		panelCentralInicio.add(passwordField);

		JPanel panelCentral = new JPanel();
		panelCentral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelCentral.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelCentral.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ProximasEmisiones prox = new ProximasEmisiones();
				prox.setVisible(true);
				dispose();
			}
		});
		panelCentral.setBackground(new Color(192, 192, 192));
		contentPane.add(panelCentral, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/resources/imgLogin.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel);
	}

}
