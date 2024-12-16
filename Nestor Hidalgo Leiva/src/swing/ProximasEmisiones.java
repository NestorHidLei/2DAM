package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class ProximasEmisiones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
    private DefaultTableModel model;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField textField;



	/**
	 * Create the frame.
	 */
	public ProximasEmisiones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArriba = new JPanel();
		panelArriba.setBackground(new Color(0, 0, 0));
		contentPane.add(panelArriba, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("SerieFli");
		lblNewLabel_1.setIcon(new ImageIcon(Home.class.getResource("/resources/logo.png")));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelArriba.add(lblNewLabel_1);
		
		JPanel panelAbajo = new JPanel();
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panelAbajo.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Plataformas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_2.add(textField);
		
		JPanel panel_3 = new JPanel();
		panelAbajo.add(panel_3);
		
		JButton btnCancelar = new JButton("Cerrar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_3.add(btnCancelar);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArribaCentro = new JPanel();
		panelArribaCentro.setBackground(new Color(0, 0, 0));
		panelCentro.add(panelArribaCentro, BorderLayout.NORTH);
		panelArribaCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.setBackground(new Color(0, 0, 0));
		panelArribaCentro.add(panel);
		
		JLabel lblProximo = new JLabel("En el pr\u00F3ximo...");
		lblProximo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProximo.setForeground(new Color(255, 0, 0));
		panel.add(lblProximo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panelArribaCentro.add(panel_1);
		
		JRadioButton rdbtnSemana = new JRadioButton("Semana");
		rdbtnSemana.setBackground(new Color(0, 0, 0));
		rdbtnSemana.setForeground(new Color(255, 0, 0));
		rdbtnSemana.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rdbtnSemana);
		panel_1.add(rdbtnSemana);
		
		JRadioButton rdbtnMes = new JRadioButton("Mes");
		rdbtnMes.setBackground(new Color(0, 0, 0));
		rdbtnMes.setForeground(new Color(255, 0, 0));
		rdbtnMes.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rdbtnMes);
		panel_1.add(rdbtnMes);
		
		JRadioButton rdbtnAnio = new JRadioButton("A\u00F1o");
		rdbtnAnio.setBackground(new Color(0, 0, 0));
		rdbtnAnio.setForeground(new Color(255, 0, 0));
		rdbtnAnio.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rdbtnAnio);
		panel_1.add(rdbtnAnio);
		
		scrollPane = new JScrollPane((Component) null);
		panelCentro.add(scrollPane, BorderLayout.CENTER);
		
        String[] columnNames = {"Serie", "Plataformas", "Temporada", "Episodio", "Fecha"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
		scrollPane.setViewportView(table);
	}

}
