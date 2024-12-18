package jframeApp;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;
import utils.Film;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class SwingApp extends JFrame {

	private static final String SELECT_PELICULAS = "SELECT * FROM film;";

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Campos de texto
	private JTextField txtFilmId;
	private JTextField txtTitle;
	private JTextField txtReleaseYear;
	private JTextField txtLanguageId;
	private JTextField txtOriginalLanguageId;
	private JTextField txtRentalDuration;
	private JTextField txtRentalRate;
	private JTextField txtLength;
	private JTextField txtReplacementCost;
	private JTextField txtRating;

	private ResultSet rs;
	private Connection connect;

	private JTextArea txtSpecialFeatures;
	private JTextArea txtDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingApp frame = new SwingApp();
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
	public SwingApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblFilmId = new JLabel("Film ID:");
		JLabel lblTitle = new JLabel("Title:");
		JLabel lblDescription = new JLabel("Description:");
		JLabel lblReleaseYear = new JLabel("Release Year:");
		JLabel lblLanguageId = new JLabel("Language ID:");
		JLabel lblOriginalLanguageId = new JLabel("Original Language ID:");
		JLabel lblRentalDuration = new JLabel("Rental Duration:");
		JLabel lblRentalRate = new JLabel("Rental Rate:");
		JLabel lblLength = new JLabel("Length:");
		JLabel lblReplacementCost = new JLabel("Replacement Cost:");
		JLabel lblRating = new JLabel("Rating:");
		JLabel lblSpecialFeatures = new JLabel("Special Features:");

		// Campos de texto para cada campo de la película
		txtFilmId = new JTextField();
		txtFilmId.setEditable(false);
		txtFilmId.setColumns(10);

		txtTitle = new JTextField();
		txtTitle.setColumns(10);

		txtReleaseYear = new JTextField();
		txtReleaseYear.setColumns(10);

		txtLanguageId = new JTextField();
		txtLanguageId.setColumns(10);

		txtOriginalLanguageId = new JTextField();
		txtOriginalLanguageId.setEditable(false);
		txtOriginalLanguageId.setColumns(10);

		txtRentalDuration = new JTextField();
		txtRentalDuration.setColumns(10);

		txtRentalRate = new JTextField();
		txtRentalRate.setColumns(10);

		txtLength = new JTextField();
		txtLength.setColumns(10);

		txtReplacementCost = new JTextField();
		txtReplacementCost.setColumns(10);

		txtRating = new JTextField();
		txtRating.setColumns(10);

		JButton btnPrimero = new JButton("Primero");
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFilmId.setEditable(false);
				txtTitle.setEditable(false);
				txtReleaseYear.setEditable(false);
				txtLanguageId.setEditable(false);
				txtOriginalLanguageId.setEditable(false);
				txtRentalDuration.setEditable(false);
				txtRentalRate.setEditable(false);
				txtLength.setEditable(false);
				txtReplacementCost.setEditable(false);
				txtRating.setEditable(false);
				txtDescription.setEditable(false);
				txtSpecialFeatures.setEditable(false);
				mostrarPrimero();
			}
		});

		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAnterior();
			}
		});

		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSiguiente();
			}
		});

		JButton btnultimo = new JButton("Último");
		btnultimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarUltimo();
			}
		});

		txtDescription = new JTextArea();
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);

		txtSpecialFeatures = new JTextArea();
		txtSpecialFeatures.setWrapStyleWord(true);
		txtSpecialFeatures.setLineWrap(true);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFilmId.setText("");
				txtTitle.setText("");
				txtReleaseYear.setText("");
				txtLanguageId.setText("");
				txtOriginalLanguageId.setText("");
				txtRentalDuration.setText("");
				txtRentalRate.setText("");
				txtLength.setText("");
				txtReplacementCost.setText("");
				txtRating.setText("");
				txtDescription.setText("");
				txtSpecialFeatures.setText("");
			}
		});

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearPeliculaNueva();
			}
		});

		// Configuración del layout
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(18, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblDescription)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblFilmId).addComponent(lblTitle)
														.addComponent(lblReleaseYear).addComponent(lblLanguageId))
												.addGap(55))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblOriginalLanguageId).addGap(18))))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(txtDescription)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtOriginalLanguageId).addComponent(txtLanguageId)
										.addComponent(txtReleaseYear).addComponent(txtTitle)
										.addComponent(txtFilmId, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
						.addGap(43)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblRentalRate).addComponent(lblRentalDuration)
												.addComponent(lblLength))
										.addGap(34))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblReplacementCost).addComponent(lblSpecialFeatures)
												.addComponent(lblRating))
										.addGap(18)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(txtSpecialFeatures)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtRating).addComponent(txtReplacementCost)
										.addComponent(txtLength).addComponent(txtRentalRate).addComponent(
												txtRentalDuration, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))
						.addGap(8))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnPrimero, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addGap(86)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAnterior, GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE))
								.addGap(111)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnSiguiente, GroupLayout.PREFERRED_SIZE, 98,
														GroupLayout.PREFERRED_SIZE)
												.addGap(118).addComponent(btnultimo, GroupLayout.PREFERRED_SIZE, 98,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(30, GroupLayout.PREFERRED_SIZE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(69)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false).addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblFilmId)
										.addComponent(txtFilmId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblTitle)
										.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtOriginalLanguageId, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblOriginalLanguageId))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblReleaseYear).addComponent(txtReleaseYear,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLanguageId).addComponent(txtLanguageId,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDescription).addComponent(txtDescription,
												GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblRentalDuration))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txtRentalDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtRentalRate, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRentalRate))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(txtLength, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtReplacementCost, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblReplacementCost)))
										.addComponent(lblLength))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtRating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRating))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSpecialFeatures).addComponent(txtSpecialFeatures,
												GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
				.addGap(69)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPrimero, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAnterior, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSiguiente, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnultimo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(38)));
		contentPane.setLayout(gl_contentPane);

		cargarDatos();

	}

	private void cargarDatos() {
		try {
			connect = Conexion.conectar(); // Open the connection
			PreparedStatement st = connect.prepareStatement(SELECT_PELICULAS, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery();

			if (rs.next()) {
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarDatos() {
		try {
			// Cargar datos del ResultSet a los campos de texto
			txtFilmId.setText(rs.getString("film_id"));
			txtTitle.setText(rs.getString("title"));
			txtDescription.setText(rs.getString("description"));
			txtReleaseYear.setText(rs.getString("release_year"));
			txtLanguageId.setText(rs.getString("language_id"));
			txtOriginalLanguageId.setText(rs.getString("original_language_id"));
			txtRentalDuration.setText(rs.getString("rental_duration"));
			txtRentalRate.setText(rs.getString("rental_rate"));
			txtLength.setText(rs.getString("length"));
			txtReplacementCost.setText(rs.getString("replacement_cost"));
			txtRating.setText(rs.getString("rating"));
			txtSpecialFeatures.setText(rs.getString("special_features"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarPrimero() {
		try {
			if (rs != null) {
				rs.first();
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarSiguiente() {
		try {
			if (rs.next()) {
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarAnterior() {
		try {
			if (rs.previous()) {
				mostrarDatos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarUltimo() {
		try {
			rs.last();
			mostrarDatos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void crearPeliculaNueva() {
	    // Recoger los datos de los campos de texto
	    String title = txtTitle.getText();
	    String description = txtDescription.getText();
	    String releaseYear = txtReleaseYear.getText();
	    String languageId = txtLanguageId.getText();
	    String originalLanguageId = null	;
	    String rentalDuration = txtRentalDuration.getText();
	    String rentalRate = txtRentalRate.getText();
	    String length = txtLength.getText();
	    String replacementCost = txtReplacementCost.getText();
	    String rating = txtRating.getText();
	    String specialFeatures = txtSpecialFeatures.getText();

	    // Definir la consulta SQL para insertar una nueva película
	    String insertSQL = "INSERT INTO film (title, description, release_year, language_id, "
	            + "original_language_id, rental_duration, rental_rate, length, "
	            + "replacement_cost, rating, special_features) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection connect = Conexion.conectar();
	         PreparedStatement st = connect.prepareStatement(insertSQL)) {
	        
	        // Establecer los parámetros de la consulta
	        st.setString(1, title);
	        st.setString(2, description);
	        st.setString(3, releaseYear);
	        st.setString(4, languageId);
	        st.setString(5, originalLanguageId);
	        st.setString(6, rentalDuration);
	        st.setString(7, rentalRate);
	        st.setString(8, length);
	        st.setString(9, replacementCost);
	        st.setString(10, rating);
	        st.setString(11, specialFeatures);

	        
	        st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
