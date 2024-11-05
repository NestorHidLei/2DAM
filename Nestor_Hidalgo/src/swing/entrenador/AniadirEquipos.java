package swing.entrenador;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JTextField;

import com.toedter.calendar.JYearChooser;

import swing.CoachFrame;

import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AniadirEquipos extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String EQUIPO = "Equipo.csv";

	private JTextField textHorario;
	private JTextField textNombre;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JYearChooser yearAnio;
	private JComboBox<String> comboEntrenador;

	/**
	 * Create the panel.
	 */
	public AniadirEquipos(MouseAdapter mouseAdapter) {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Añade Equipos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblNewLabel);

		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(37, 14, 77, 31);
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 16));

		JLabel lblAnio = new JLabel("Año:");
		lblAnio.setBounds(37, 55, 92, 31);
		lblAnio.setFont(new Font("Verdana", Font.PLAIN, 16));

		JLabel lblHorario = new JLabel("Horario de entrenamiento:");
		lblHorario.setBounds(37, 104, 222, 31);
		lblHorario.setFont(new Font("Verdana", Font.PLAIN, 16));

		textHorario = new JTextField();
		textHorario.setBounds(278, 104, 218, 31);
		textHorario.setColumns(10);

		textNombre = new JTextField();
		textNombre.setBounds(278, 13, 218, 31);
		textNombre.setColumns(10);
		panelCentral.setLayout(null);
		panelCentral.add(lblNombre);
		panelCentral.add(textNombre);
		panelCentral.add(lblAnio);
		panelCentral.add(lblHorario);
		panelCentral.add(textHorario);

		yearAnio = new JYearChooser();
		yearAnio.setBounds(278, 55, 218, 37);
		panelCentral.add(yearAnio);

		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		buttonGroup.add(rdbtnMasculino);
		rdbtnMasculino.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtnMasculino.setBackground(Color.WHITE);
		rdbtnMasculino.setActionCommand("Tarde");
		rdbtnMasculino.setBounds(396, 162, 100, 31);
		panelCentral.add(rdbtnMasculino);

		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		buttonGroup.add(rdbtnFemenino);
		rdbtnFemenino.setFont(new Font("Verdana", Font.PLAIN, 14));
		rdbtnFemenino.setBackground(Color.WHITE);
		rdbtnFemenino.setActionCommand("Mañana");
		rdbtnFemenino.setBounds(278, 162, 100, 31);
		panelCentral.add(rdbtnFemenino);

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblGenero.setBounds(37, 161, 92, 31);
		panelCentral.add(lblGenero);

		JLabel lblEntrenador = new JLabel("Entrenador:");
		lblEntrenador.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEntrenador.setBounds(37, 212, 100, 31);
		panelCentral.add(lblEntrenador);

		comboEntrenador = new JComboBox<String>();
		comboEntrenador.setBounds(278, 215, 218, 31);
		panelCentral.add(comboEntrenador);

		JLabel lblAadir = new JLabel("    Añadir");
		lblAadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registrarEquipo();
				JOptionPane.showMessageDialog(panelCentral, "Equipo Registrado");
				limpiarCampos();
			}
		});
		lblAadir.setOpaque(true);
		lblAadir.setForeground(Color.WHITE);
		lblAadir.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblAadir.setBackground(new Color(0, 214, 232));
		lblAadir.setBounds(192, 269, 110, 31);
		panelCentral.add(lblAadir);

	}

	protected void registrarEquipo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(EQUIPO, true))) {
			String nombre = textNombre.getText();
			String Horaio = textHorario.getText();
			int anio = yearAnio.getYear();
			String genero = buttonGroup.getSelection() != null ? buttonGroup.getSelection().getActionCommand() : null;
			String entrenador = comboEntrenador.getSelectedItem().toString();

			if (nombre.isEmpty() || Horaio.isEmpty() || genero.isEmpty() || entrenador.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Error: Rellena todos los campos", "Campos por rellenar",
						JOptionPane.ERROR_MESSAGE);
			}
			bw.write(nombre + ";" + Horaio + ";" + anio + ";" + genero + ";" + entrenador);
			bw.newLine();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void limpiarCampos() {
		// Limpiar campos de texto
		textNombre.setText("");
		textHorario.setText("");
		buttonGroup.clearSelection();
		yearAnio.setYear(2024);
		;
	}
}
