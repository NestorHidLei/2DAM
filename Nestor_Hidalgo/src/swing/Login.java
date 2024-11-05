package swing;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	LoginUsuario LoginUsuario;

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCabecera = new JPanel();
		panelCabecera.setBackground(new Color(0, 128, 192));
		contentPane.add(panelCabecera, BorderLayout.NORTH);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/resources/Logo.png")));
		panelCabecera.add(lblLogo);
		
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBackground(new Color(0, 128, 192));
		contentPane.add(panelIzquierda, BorderLayout.WEST);
		panelIzquierda.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("             ");
		panelIzquierda.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panelDerecha = new JPanel();
		panelDerecha.setBackground(new Color(0, 128, 192));
		contentPane.add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("              ");
		panelDerecha.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panelAbajo = new JPanel();
		panelAbajo.setBackground(new Color(0, 128, 192));
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new BorderLayout(10, 0));
		
		JLabel lblNewLabel = new JLabel(" ");
		panelAbajo.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLogin = new JPanel();
		panelCentral.add(panelLogin);
		panelLogin.setPreferredSize(new Dimension(50,120));
		panelLogin.setLayout(new BorderLayout(0, 0));
		LoginUsuario = new LoginUsuario(this);
		JScrollPane scrollPane = new JScrollPane(LoginUsuario);
		panelLogin.add(scrollPane);
	}

	public void cerrar() {
		this.dispose();
		
	}

}
