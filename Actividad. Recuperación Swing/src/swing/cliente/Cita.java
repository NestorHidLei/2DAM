package swing.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import utils.Citas;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class Cita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMatricula;
	private JTextField textMarca;
	private JTextField textModelo;
	private JDateChooser dateChooser;

	/**
	 * Create the frame.
	 * @param citas 
	 */
	public Cita(List<Citas> citas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArriba = new JPanel();
		panelArriba.setBackground(new Color(0, 128, 192));
		contentPane.add(panelArriba, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Pedir Cita Previa");
		lblNewLabel.setBackground(new Color(0, 128, 192));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelArriba.add(lblNewLabel);
		
		JPanel panelAbajo = new JPanel();
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setBackground(new Color(173, 216, 230));

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!citaDisponible(citas, dateChooser.getDate())) {
					JOptionPane.showInputDialog("Error: Ya hay dos citas para ese dia",
							JOptionPane.ERROR_MESSAGE);
				} else {
					citas.add(new Citas(textMatricula.getText(), textMarca.getText(), textModelo.getText(), dateChooser.getDate()));
					dispose();
				}
			}
		});
		panelAbajo.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panelAbajo.add(btnCancelar);
		
		JPanel panelIzquierda = new JPanel();
		contentPane.add(panelIzquierda, BorderLayout.WEST);
		panelIzquierda.setBackground(new Color(173, 216, 230));

		
		JPanel panelDerecha = new JPanel();
		contentPane.add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setBackground(new Color(173, 216, 230));

		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(4, 2, 0, 20));

		
		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblMatricula);
		
		textMatricula = new JTextField();
		panelCentral.add(textMatricula);
		textMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblMarca);
		
		textMarca = new JTextField();
		panelCentral.add(textMarca);
		textMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblModelo);
		
		textModelo = new JTextField();
		panelCentral.add(textModelo);
		textModelo.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblFecha);
		
		dateChooser = new JDateChooser();
		panelCentral.add(dateChooser);
	}

	protected boolean citaDisponible(List<Citas> citas, Date fechaSeleccionada) {
	    int contadorCitas = 0;
	    
	    for (Citas cita : citas) {
	        if (cita.getFecha().equals(fechaSeleccionada)) {
	            contadorCitas++;
	        }
	    }
	    return contadorCitas < 2; 
	}


}
