package swing.cliente;


import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import utils.Citas;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaReparaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel model;



	/**
	 * Create the frame.
	 * @param citas 
	 */
    public ListaReparaciones(List<Citas> citas) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 762, 544);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panelArriba = new JPanel();
        panelArriba.setForeground(new Color(255, 255, 255));
        panelArriba.setBackground(new Color(0, 64, 128));
        contentPane.add(panelArriba, BorderLayout.NORTH);
        
        JLabel lblMisReparaciones = new JLabel("Mis Reparaciones");
        lblMisReparaciones.setForeground(new Color(255, 255, 255));
        lblMisReparaciones.setFont(new Font("Tahoma", Font.BOLD, 20));
        panelArriba.add(lblMisReparaciones);
        
        JPanel panelAbajo = new JPanel();
        contentPane.add(panelAbajo, BorderLayout.SOUTH);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        panelAbajo.add(btnVolver);
        
        // Modelo de tabla con columnas
        String[] columnNames = {"Matrícula", "Fecha", "Estado", "Importe", "Observaciones"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Agregar las citas al modelo de la tabla
        for (Citas cita : citas) {
            // Supongamos que los métodos getter de Citas son: getMatricula(), getFecha(), etc.
            Object[] row = {
                cita.getMatricula(),
                cita.getFecha(),
                cita.getEstado(),
                cita.getImporte(),
                cita.getObservaciones()
            };
            model.addRow(row);
        }
    }
}
