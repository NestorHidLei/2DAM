package swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelos.Serie;
import modelos.Temporada;
import modelos.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MisSeries extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JLabel lblPlataformaValue;
	private JLabel lblNumeroTempValue;

	/**
	 * Create the frame.
	 * @param misSeries 
	 * @param lstTemporadas 
	 * @param usuarios 
	 */
	public MisSeries(List<Usuario> usuarios, List<Temporada> lstTemporadas, List<Serie> misSeries, String email) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 696, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArriba = new JPanel();
		panelArriba.setBackground(Color.BLACK);
		contentPane.add(panelArriba, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("SerieFli");
		lblNewLabel_1.setIcon(new ImageIcon(MisSeries.class.getResource("/resources/logo.png")));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelArriba.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArribaCentro = new JPanel();
		panelArribaCentro.setBackground(Color.BLACK);
		panel.add(panelArribaCentro, BorderLayout.NORTH);
		panelArribaCentro.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				panel_1.setBackground(new Color(192, 192, 192));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				panel_1.setBackground(new Color(0, 0, 0));

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Home home = new Home(usuarios, lstTemporadas, misSeries, email);
				home.setVisible(true);
				dispose();
			}
		});
		panel_1.setBackground(Color.BLACK);
		panelArribaCentro.add(panel_1);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setForeground(Color.RED);
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblHome);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.BLACK);
		panelArribaCentro.add(panel_1_1);
		
		JLabel lblMisSeries_1 = new JLabel("Mis Series");
		lblMisSeries_1.setForeground(Color.RED);
		lblMisSeries_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1_1.add(lblMisSeries_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				panel_2.setBackground(new Color(192, 192, 192));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				panel_2.setBackground(new Color(0, 0, 0));

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Aniadir aniadir = new Aniadir(usuarios, lstTemporadas, misSeries, email);
				aniadir.setVisible(true);
				dispose();
			}
		});
		panel_2.setBackground(Color.BLACK);
		panelArribaCentro.add(panel_2);
		
		JLabel lblAniadir = new JLabel("A\u00F1adir");
		lblAniadir.setForeground(Color.RED);
		lblAniadir.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblAniadir);
		
		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				panel_3.setBackground(new Color(192, 192, 192));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				panel_3.setBackground(new Color(0, 0, 0));

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		panel_3.setBackground(Color.BLACK);
		panelArribaCentro.add(panel_3);
		
		JLabel lblCerrarSesion = new JLabel("Cerrar Sesion");
		lblCerrarSesion.setForeground(Color.RED);
		lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblCerrarSesion);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(192, 192, 192));
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(4, 2, 0, 50));
		
		JLabel lblSerie = new JLabel("Serie");
		lblSerie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSerie.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblSerie);
		
		comboBox = new JComboBox<String>();
		panel_4.add(comboBox);
		
		JLabel lblPlataforma = new JLabel("Plataforma");
		lblPlataforma.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPlataforma.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblPlataforma);
		
		lblPlataformaValue = new JLabel((String) null);
		lblPlataformaValue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPlataformaValue.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblPlataformaValue);
		
		JLabel lblNumeroDeTemporadas = new JLabel("Numero de Temporadas");
		lblNumeroDeTemporadas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumeroDeTemporadas.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNumeroDeTemporadas);
		
		lblNumeroTempValue = new JLabel((String) null);
		lblNumeroTempValue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumeroTempValue.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNumeroTempValue);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("A\u00F1adir a mis series");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clienteObtenido(usuarios, email).setMisSeries(misSeries);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(btnNewButton);
		
		for (Serie serie : misSeries) {
			comboBox.addItem(serie.getNombre());
		}
		
		comboBox.addActionListener(e -> {
			String tituloSeleccionada = (String) comboBox.getSelectedItem();
			Serie serieSeleccionada = misSeries.stream().filter(c -> c.getNombre().equals(tituloSeleccionada))
					.findFirst().orElse(null);

			if (serieSeleccionada != null) {
				lblPlataformaValue.setText(serieSeleccionada.getPlataforma());
				lblNumeroTempValue.setText((serieSeleccionada.getLstTemporadas().getLast().getNumeroTemporada()));
			}
		});
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
