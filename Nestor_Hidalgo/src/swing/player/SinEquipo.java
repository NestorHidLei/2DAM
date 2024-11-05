package swing.player;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import swing.PlayerFrame;

import java.awt.Font;
import java.awt.Color;

public class SinEquipo extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SinEquipo(PlayerFrame playerFrame) {
		setLayout(new BorderLayout(10, 10));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(10, 10));
		
		JLabel lblNewLabel = new JLabel("Actualmente no tienes equipo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(SinEquipo.class.getResource("/resources/ImgJugadorError.png")));
		panel.add(lblNewLabel_1, BorderLayout.CENTER);
		
	}

}
