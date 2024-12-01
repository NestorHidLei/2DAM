package swing.empleado;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Reparacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reparacion frame = new Reparacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reparacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArriba = new JPanel();
		panelArriba.setBackground(new Color(0, 128, 192));
		contentPane.add(panelArriba, BorderLayout.NORTH);
		
		JLabel lblActualizar = new JLabel("Actualizar Reparación");
		lblActualizar.setForeground(new Color(255, 255, 255));
		lblActualizar.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelArriba.add(lblActualizar);
		
		JPanel panelAbajo = new JPanel();
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Aceptar");
		panelAbajo.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelAbajo.add(btnCancelar);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 20));
		
		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblMatricula);
		
		JComboBox comboBox = new JComboBox();
		panelCentral.add(comboBox);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblMarca);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel_3);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblModelo);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel_5);
		
		JLabel lblEstadoActual = new JLabel("Estado Actual");
		lblEstadoActual.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblEstadoActual);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNewLabel_1);
		
		JLabel lblNuevoEstado = new JLabel("Nuevo Estado");
		lblNuevoEstado.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNuevoEstado);
		
		JComboBox comboBox_1 = new JComboBox();
		panelCentral.add(comboBox_1);
		
		JLabel lblImporte = new JLabel("Importe");
		lblImporte.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblImporte);
		
		textField = new JTextField();
		panelCentral.add(textField);
		textField.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblObservaciones);
		
		JTextPane textPane = new JTextPane();
		panelCentral.add(textPane);
	}

}
