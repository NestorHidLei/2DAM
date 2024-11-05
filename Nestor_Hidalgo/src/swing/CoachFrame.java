package swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import swing.entrenador.AniadirEquipos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
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

public class CoachFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String currentUserEmail;
	AniadirEquipos AniadirEquipos;
	
	 private static final String USUARIOS = "Usuarios.csv";

	/**
	 * Create the frame.
	 */
	public CoachFrame(String email) {
        this.currentUserEmail = email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Marcar la sesión como activa en el archivo CSV
        actualizarSesion(true);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCabecera = new JPanel();
		panelCabecera.setBackground(new Color(0, 128, 192));
		contentPane.add(panelCabecera, BorderLayout.NORTH);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(CoachFrame.class.getResource("/resources/Logo.png")));
		panelCabecera.add(lblLogo);
		
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBackground(new Color(0, 215, 232));
		contentPane.add(panelIzquierda, BorderLayout.WEST);
		panelIzquierda.setLayout(new GridLayout(0, 1, 100, 10));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CoachFrame.class.getResource("/resources/Home.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelIzquierda.add(lblNewLabel);
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel panelEquipo = new JPanel();
				panelCentral.add(panelEquipo);
				panelEquipo.setPreferredSize(new Dimension(50,120));
				panelEquipo.setLayout(new BorderLayout(0, 0));
				AniadirEquipos = new AniadirEquipos(this);
				JScrollPane scrollPane = new JScrollPane(AniadirEquipos);
				panelEquipo.add(scrollPane);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(CoachFrame.class.getResource("/resources/Equipo.png")));
		panelIzquierda.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(CoachFrame.class.getResource("/resources/Jugadores.png")));
		panelIzquierda.add(lblNewLabel_3);
		
		JLabel lblCerrarSesion = new JLabel("");
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Marcar la sesión como inactiva
				actualizarSesion(false); 
                Login login = new Login();
                login.setVisible(true);
                // Cerrar CoachFrame
                dispose(); 

			}
		});
		lblCerrarSesion.setIcon(new ImageIcon(CoachFrame.class.getResource("/resources/Logout.png")));
		panelIzquierda.add(lblCerrarSesion);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		contentPane.add(panel, BorderLayout.SOUTH);
		
		
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
