package swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swing.user.ReservarClase;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserFrame() {
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
		lblLogo.setIcon(new ImageIcon(UserFrame.class.getResource("/resources/logoApp.png")));
		panelCabecera.add(lblLogo);
		
		JLabel lblTitulo = new JLabel("GYM Picasso");
		lblTitulo.setForeground(new Color(0, 64, 128));
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 30));
		panelCabecera.add(lblTitulo);
		
		JPanel panelFotter = new JPanel();
		panelFotter.setBackground(new Color(198, 242, 244));
		contentPane.add(panelFotter, BorderLayout.SOUTH);
		
		JLabel lblFooter = new JLabel("Enrique Martínez Fernández 14/11/2023");
		lblFooter.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelFotter.add(lblFooter);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 20, 20));
		
		JLabel lblReservarClase = new JLabel("Reservar clase", SwingConstants.CENTER);
		lblReservarClase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservarClase reservas = new ReservarClase();
				reservas.setVisible(true);   
			}
		});
		lblReservarClase.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReservarClase.setIcon(new ImageIcon(UserFrame.class.getResource("/resources/apuntaAClase.png")));
		lblReservarClase.setHorizontalTextPosition(SwingConstants.CENTER);
		panelCentral.add(lblReservarClase);
		
		JLabel lblCerrarSesion = new JLabel("Cierra sesión", SwingConstants.CENTER);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
            	login.setVisible(true); 
			}
		});
		lblCerrarSesion.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCerrarSesion.setIcon(new ImageIcon(UserFrame.class.getResource("/resources/cierreSesion.png")));
		lblCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
		panelCentral.add(lblCerrarSesion);
	}

}
