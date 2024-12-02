package swing;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swing.cliente.Cita;
import swing.cliente.ListaReparaciones;
import utils.Citas;
import utils.Usuario;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param usuarios 
	 * @param email 
	 * @param citas 
	 */
	public Cliente(List<Usuario> usuarios, String email, List<Citas> citas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelEncabezado = new JPanel();
		panelEncabezado.setBackground(new Color(0, 128, 255));
		contentPane.add(panelEncabezado, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("    Talleres Picasso");
		lblNewLabel.setForeground(new Color(0, 64, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setIcon(new ImageIcon(Cliente.class.getResource("/resources/cocheAzul.png")));
		panelEncabezado.add(lblNewLabel);
		
		JPanel panelInferior = new JPanel();
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		JLabel lblCerrarSesion = new JLabel("");
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCerrarSesion.setIcon(new ImageIcon(Cliente.class.getResource("/resources/logout.png")));
		GroupLayout gl_panelInferior = new GroupLayout(panelInferior);
		gl_panelInferior.setHorizontalGroup(
			gl_panelInferior.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelInferior.createSequentialGroup()
					.addContainerGap(360, Short.MAX_VALUE)
					.addComponent(lblCerrarSesion))
		);
		gl_panelInferior.setVerticalGroup(
			gl_panelInferior.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelInferior.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblCerrarSesion))
		);
		panelInferior.setLayout(gl_panelInferior);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBienvenido = new JPanel();
		panelCentral.add(panelBienvenido, BorderLayout.NORTH);
		
		JLabel lblBienvenido = new JLabel("Bienvenido/a " + clienteObtenido(usuarios, email).getNombre() + " " + clienteObtenido(usuarios, email).getApellidos());
		lblBienvenido.setHorizontalAlignment(SwingConstants.RIGHT);
		panelBienvenido.add(lblBienvenido);
		
		JPanel panelCentral2 = new JPanel();
		panelCentral.add(panelCentral2, BorderLayout.CENTER);
		panelCentral2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCita = new JLabel("Pedir cita previa");
		lblCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cita cita = new Cita(citas);
				cita.setVisible(true);
				
			}
		});
		lblCita.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCita.setHorizontalAlignment(SwingConstants.CENTER);
		lblCita.setVerticalTextPosition(JLabel.BOTTOM);
		lblCita.setIcon(new ImageIcon(Cliente.class.getResource("/resources/calendario.png")));
		panelCentral2.add(lblCita);
		
		JLabel lblReparacion = new JLabel("Ver mis reparaciones");
		lblReparacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListaReparaciones lstReparaciones = new ListaReparaciones(citas);
				lstReparaciones.setVisible(true);
			}
		});
		lblReparacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblReparacion.setIcon(new ImageIcon(Cliente.class.getResource("/resources/reparaciones.png")));
		lblReparacion.setHorizontalTextPosition(JLabel.CENTER);
		lblReparacion.setVerticalTextPosition(JLabel.BOTTOM);
		panelCentral2.add(lblReparacion);
	}

	private Usuario clienteObtenido(List<Usuario> usuarios, String email) {
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getEmail().equals(email)) {
				return usuarios.get(i);
			}
		}
		return null;
	}
}
