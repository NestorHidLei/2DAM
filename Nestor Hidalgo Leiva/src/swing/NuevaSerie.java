package swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;


import modelos.Serie;
import modelos.Temporada;
import modelos.Usuario;

import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class NuevaSerie extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textPlataforma;
	private JDateChooser dateChooserInicio;
	private JDateChooser dateFin;
	private JTextField textNumero;

	/**
	 * Create the frame.
	 * @param misSeries 
	 * @param lstTemporadas 
	 * @param usuarios 
	 * @param email 
	 */
	public NuevaSerie(List<Usuario> usuarios, List<Temporada> lstTemporadas, List<Serie> misSeries, String email) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Nueva Serie");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setBackground(new Color(0, 0, 0));
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				registrarSerie(usuarios, lstTemporadas, misSeries, email);
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panel_1.add(btnNewButton_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		contentPane.add(panel_2, BorderLayout.WEST);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		contentPane.add(panel_3, BorderLayout.EAST);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 2, 0, 50));

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_6.add(lblNewLabel_1);

		textNombre = new JTextField();
		panel_6.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Plataforma");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_6.add(lblNewLabel_2);

		textPlataforma = new JTextField();
		panel_6.add(textPlataforma);
		textPlataforma.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(4, 2, 0, 50));

		JLabel lblTemporada = new JLabel("Temporada");
		lblTemporada.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemporada.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_5.add(lblTemporada);

		JLabel lblNewLabel_5 = new JLabel("1");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_5.add(lblNewLabel_5);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_5.add(lblFechaInicio);

		dateChooserInicio = new JDateChooser();
		panel_5.add(dateChooserInicio);

		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_5.add(lblFechaFin);

		dateFin = new JDateChooser();
		panel_5.add(dateFin);

		JLabel lblCapitulos = new JLabel("N\u00FAmero de Cap\u00EDtulos");
		lblCapitulos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapitulos.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_5.add(lblCapitulos);

		textNumero = new JTextField();
		panel_5.add(textNumero);
		textNumero.setColumns(10);
	}

	protected void registrarSerie(List<Usuario> usuarios, List<Temporada> lstTemporadas, List<Serie> misSeries, String email) {
		String nombre = textNombre.getText();
		String plataforma = textPlataforma.getText();
		String numeroTemporada = "1";
		LocalDate fechaInicio = dateChooserInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaFin = dateFin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int numeroCap = Integer.parseInt(textNumero.getText());

		if (nombre.isEmpty() || plataforma.isEmpty() || numeroCap == 0) {
			JOptionPane.showMessageDialog(this, "Error: Rellena todos los campos", "Campos por rellenar",
					JOptionPane.ERROR_MESSAGE);
		} else {
			Temporada temporada = new Temporada(numeroTemporada, fechaInicio, fechaFin, numeroCap);
			lstTemporadas.add(temporada);
			Serie serie = new Serie(nombre, plataforma, lstTemporadas);
			misSeries.add(serie);
			clienteObtenido(usuarios, email).setMisSeries(misSeries);
			
			JOptionPane.showMessageDialog(this, "Serie registrada con exito");
		}
		limpiarCampos();
	}

	private void limpiarCampos() {
		// Limpiar campos de texto
		textNombre.setText("");
		textPlataforma.setText("");
		textNumero.setText("");

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
