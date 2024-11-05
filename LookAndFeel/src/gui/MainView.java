package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox cbLookAndFeel;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public MainView() {
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		inicializaComponentes();
		rellenaCombo();
		// cambiarLookAndFeel();

	}

	private void cambiarLookAndFeel() {

		try {
			UIManager.setLookAndFeel(new FlatMacDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

	}

	private void rellenaCombo() {
		DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();

		for (LookAndFeelInfo lfi : UIManager.getInstalledLookAndFeels()) {
			cbModel.addElement(lfi.getName());
		}

		cbLookAndFeel.setModel(cbModel);
	}

	private void inicializaComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		cbLookAndFeel = new JComboBox();
		cbLookAndFeel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbLookAndFeelChange(e);
			}
		});
		cbLookAndFeel.setBounds(202, 90, 242, 22);
		contentPane.add(cbLookAndFeel);

		JButton btnChangeLF = new JButton("Cambia L&F");
		btnChangeLF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarLookAndFeel();
			}
		});
		btnChangeLF.setBounds(39, 177, 152, 23);
		contentPane.add(btnChangeLF);

		textField = new JTextField();
		textField.setBounds(233, 178, 169, 20);
		contentPane.add(textField);
		textField.setColumns(10);

	}

	protected void cbLookAndFeelChange(ActionEvent e) {

		LookAndFeelInfo lfInfo = UIManager.getInstalledLookAndFeels()[cbLookAndFeel.getSelectedIndex()];

		try {
			UIManager.setLookAndFeel(lfInfo.getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
