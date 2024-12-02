package swing.empleado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.Citas;
import java.awt.*;
import java.util.List;

public class Reparacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JLabel lblMarcaValue;
	private JLabel lblModeloValue;
	private JLabel lblEstadoActualValue;
	private JComboBox<String> comboBoxEstado;
	private JTextPane textPane;

	/**
	 * Create the frame.
	 * 
	 * @param citas Lista de objetos Citas
	 */
	public Reparacion(List<Citas> citas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelArriba = new JPanel();
		panelArriba.setBackground(new Color(0, 128, 192));
		contentPane.add(panelArriba, BorderLayout.NORTH);

		JLabel lblActualizar = new JLabel("Actualizar Reparación");
		lblActualizar.setForeground(Color.WHITE);
		lblActualizar.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelArriba.add(lblActualizar);

		JPanel panelAbajo = new JPanel();
		contentPane.add(panelAbajo, BorderLayout.SOUTH);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
		    String matriculaSeleccionada = (String) comboBox.getSelectedItem();
		    Citas citaSeleccionada = citas.stream()
		        .filter(c -> c.getMatricula().equals(matriculaSeleccionada))
		        .findFirst()
		        .orElse(null);

		    if (citaSeleccionada != null) {
		        String nuevoEstado = (String) comboBoxEstado.getSelectedItem();
		        citaSeleccionada.setEstado(nuevoEstado);

		        if ("Completado".equals(nuevoEstado)) {
		            try {
		                double importe = Double.parseDouble(textField.getText().trim());
		                String observaciones = textPane.getText().trim();

		                if (observaciones.isEmpty()) {
		                    JOptionPane.showMessageDialog(this, 
		                        "Las observaciones no pueden estar vacías.", 
		                        "Error", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }

		                citaSeleccionada.setImporte(importe);
		                citaSeleccionada.setObservaciones(observaciones);

		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(this, 
		                    "El importe debe ser un número válido.", 
		                    "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		        }

		        // Forzar la actualización visual del comboBox
		        comboBox.repaint();

		        // Actualizar etiquetas relacionadas con la cita seleccionada
		        lblMarcaValue.setText(citaSeleccionada.getMarca());
		        lblModeloValue.setText(citaSeleccionada.getModelo());
		        lblEstadoActualValue.setText(citaSeleccionada.getEstado());

		        JOptionPane.showMessageDialog(this, 
		            "Cita actualizada correctamente.", 
		            "Éxito", JOptionPane.INFORMATION_MESSAGE);
		    } else {
		        JOptionPane.showMessageDialog(this, 
		            "No se encontró la cita seleccionada.", 
		            "Error", JOptionPane.ERROR_MESSAGE);
		    }
		});

		panelAbajo.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dispose());
		panelAbajo.add(btnCancelar);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 20));

		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblMatricula);

		comboBox = new JComboBox<>();
		panelCentral.add(comboBox);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblMarca);

		lblMarcaValue = new JLabel("");
		lblMarcaValue.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblMarcaValue);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblModelo);

		lblModeloValue = new JLabel("");
		lblModeloValue.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblModeloValue);

		JLabel lblEstadoActual = new JLabel("Estado Actual");
		lblEstadoActual.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblEstadoActual);

		lblEstadoActualValue = new JLabel("");
		lblEstadoActualValue.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblEstadoActualValue);

		JLabel lblNuevoEstado = new JLabel("Nuevo Estado");
		lblNuevoEstado.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNuevoEstado);

		comboBoxEstado = new JComboBox<>(new String[] { "Pendiente", "En Progreso", "Completado" });
		panelCentral.add(comboBoxEstado);

		JLabel lblImporte = new JLabel("Importe");
		lblImporte.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblImporte);

		textField = new JTextField();
		panelCentral.add(textField);
		textField.setColumns(10);

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblObservaciones);

		textPane = new JTextPane();
		panelCentral.add(textPane);

		// Rellenar el comboBox con las matrículas de las citas
		for (Citas cita : citas) {
			comboBox.addItem(cita.getMatricula());
		}

		// Actualizar datos al seleccionar una matrícula
		comboBox.addActionListener(e -> {
			String matriculaSeleccionada = (String) comboBox.getSelectedItem();
			Citas citaSeleccionada = citas.stream().filter(c -> c.getMatricula().equals(matriculaSeleccionada))
					.findFirst().orElse(null);

			if (citaSeleccionada != null) {
				lblMarcaValue.setText(citaSeleccionada.getMarca());
				lblModeloValue.setText(citaSeleccionada.getModelo());
				lblEstadoActualValue.setText(citaSeleccionada.getEstado());
			}
		});

		// Controlar la visibilidad de Importe y Observaciones
		comboBoxEstado.addActionListener(e -> {
			boolean esCompletado = "Completado".equals(comboBoxEstado.getSelectedItem());
			lblImporte.setVisible(esCompletado);
			textField.setVisible(esCompletado);
			lblObservaciones.setVisible(esCompletado);
			textPane.setVisible(esCompletado);

			panelCentral.revalidate();
			panelCentral.repaint();
		});

		// Por defecto, ocultar Importe y Observaciones
		lblImporte.setVisible(false);
		textField.setVisible(false);
		lblObservaciones.setVisible(false);
		textPane.setVisible(false);
	}
}
