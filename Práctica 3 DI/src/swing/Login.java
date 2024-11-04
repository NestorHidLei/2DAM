package swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	LoginUsuario LoginUsuario;

	

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/logoApp.png")));
		setTitle("GYM Picasso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCabecera = new JPanel();
		panelCabecera.setBackground(new Color(198, 242, 244));
		contentPane.add(panelCabecera, BorderLayout.NORTH);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/resources/logoApp.png")));
		panelCabecera.add(lblLogo);
		
		JLabel lblTitulo = new JLabel("GYM Picasso");
		lblTitulo.setForeground(new Color(0, 64, 128));
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 30));
		panelCabecera.add(lblTitulo); 
		
		JPanel panelImagen = new JPanel();
		panelImagen.setBackground(new Color(255, 255, 255));
		contentPane.add(panelImagen, BorderLayout.WEST);
		panelImagen.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagen = new JLabel("");
		// Cargar la imagen original
		ImageIcon originalIcon = new ImageIcon(Login.class.getResource("/resources/imgLogin.png"));
		// Redimensionar la imagen al tamaño deseado, por ejemplo, 200x200 píxeles
		Image resizedImage = originalIcon.getImage().getScaledInstance(400, 325, Image.SCALE_SMOOTH);
		// Crear un nuevo ImageIcon con la imagen redimensionada
		lblImagen.setIcon(new ImageIcon(resizedImage));
		panelImagen.add(lblImagen, BorderLayout.NORTH);

		
		JPanel panelDerecha = new JPanel();
		contentPane.add(panelDerecha, BorderLayout.CENTER);
		panelDerecha.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLogin = new JPanel();
		panelDerecha.add(panelLogin);
		panelLogin.setLayout(new GridLayout(1, 0, 0, 0));
		panelLogin.setPreferredSize(new Dimension(50,120));
		LoginUsuario = new LoginUsuario(this);
		JScrollPane scrollPane = new JScrollPane(LoginUsuario);
		panelLogin.add(scrollPane);
		
		JPanel panelBienvenido = new JPanel();
		panelBienvenido.setBackground(new Color(0, 215, 232));
		panelDerecha.add(panelBienvenido, BorderLayout.NORTH);
		panelBienvenido.setPreferredSize(new Dimension(50,120));
		panelBienvenido.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblBienvenido = new JLabel("Bienvenido/a a la aplicaión GYM Picasso");
		lblBienvenido.setForeground(new Color(255, 255, 255));
		lblBienvenido.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		panelBienvenido.add(lblBienvenido);
	}
	
	public void cerrar() {
    	this.dispose();
    }
}
