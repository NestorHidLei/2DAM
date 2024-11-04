package swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swing.user.ReservarClase;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String currentUserEmail;

	 private static final String USUARIOS = "Usuarios.csv";

	/**
	 * Create the frame.
	 */
	public UserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Marcar la sesión como activa en el archivo CSV
        actualizarSesion(true);
		
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
				 actualizarSesion(false);
				Login login = new Login();
            	login.setVisible(true); 
            	dispose();
			}
		});
		lblCerrarSesion.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCerrarSesion.setIcon(new ImageIcon(UserFrame.class.getResource("/resources/cierreSesion.png")));
		lblCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
		panelCentral.add(lblCerrarSesion);
	}
	
	// Método para actualizar el estado de sesión en el archivo CSV
    private void actualizarSesion(boolean estadoSesion) {
        List<String> usuariosActualizados = new ArrayList<>();

        try (BufferedReader entrada = new BufferedReader(new FileReader(USUARIOS))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partesUsuario = linea.split(";");
                if (partesUsuario[4].equals(currentUserEmail)) {
                    // Modificar el último campo (estado de sesión) a true o false según estadoSesion
                    partesUsuario[6] = String.valueOf(estadoSesion);
                    linea = String.join(";", partesUsuario);
                }
                usuariosActualizados.add(linea); // Agregar la línea (modificada o no) a la lista
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios");
        }

        // Sobrescribir el archivo con la lista actualizada
        try (PrintWriter salida = new PrintWriter(new FileWriter(USUARIOS))) {
            for (String usuario : usuariosActualizados) {
                salida.println(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el archivo de usuarios");
        }
    }
}
