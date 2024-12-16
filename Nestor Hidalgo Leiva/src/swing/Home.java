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
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelos.Serie;
import modelos.Temporada;
import modelos.Usuario;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextField;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JTextField textField;



	/**
	 * Create the frame.
	 * @param misSeries 
	 * @param lstTemporadas 
	 * @param usuarios 
	 * @param email 
	 */
	public Home(List<Usuario> usuarios, List<Temporada> lstTemporadas, List<Serie> misSeries, String email) {
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
		
		JLabel lblNewLabel = new JLabel("Plataformas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelAbajo.add(lblNewLabel);
		
		textField = new JTextField();
		panelAbajo.add(textField);
		textField.setColumns(10);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArribaCentro = new JPanel();
		panelArribaCentro.setBackground(new Color(0, 0, 0));
		panelCentro.add(panelArribaCentro, BorderLayout.NORTH);
		panelArribaCentro.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.setBackground(new Color(0, 0, 0));
		panelArribaCentro.add(panel);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHome.setForeground(new Color(255, 0, 0));
		panel.add(lblHome);
		
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
				MisSeries lasSeries = new MisSeries(usuarios, lstTemporadas, misSeries, email);
				lasSeries.setVisible(true);
				dispose();
			}
		});
		panel_1.setBackground(new Color(0, 0, 0));
		panelArribaCentro.add(panel_1);
		
		JLabel lblMisSeries_1 = new JLabel("Mis Series");
		lblMisSeries_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMisSeries_1.setForeground(new Color(255, 0, 0));
		panel_1.add(lblMisSeries_1);
		
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
		panel_2.setBackground(new Color(0, 0, 0));
		panelArribaCentro.add(panel_2);
		
		JLabel lblAniadir = new JLabel("A\u00F1adir");
		lblAniadir.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAniadir.setForeground(new Color(255, 0, 0));
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
		panel_3.setBackground(new Color(0, 0, 0));
		panelArribaCentro.add(panel_3);
		
		JLabel lblCerrarSesion = new JLabel("Cerrar Sesion");
		lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCerrarSesion.setForeground(new Color(255, 0, 0));
		panel_3.add(lblCerrarSesion);
		
		scrollPane = new JScrollPane((Component) null);
		panelCentro.add(scrollPane, BorderLayout.CENTER);
		
        String[] columnNames = {"Serie", "Plataformas", "Número de Temporadas"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
		scrollPane.setViewportView(table);
		
		for (Serie serie : misSeries) {
            // Supongamos que los métodos getter de Citas son: getMatricula(), getFecha(), etc.
            Object[] row = {
            	serie.getNombre(),
            	serie.getPlataforma(),
            	serie.getLstTemporadas().getLast().getNumeroTemporada()
            };
            model.addRow(row);
        }
	}

}
