package swing.user;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReservarClase extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxClase;
    private JComboBox<String> comboBoxTurno;

    /**
     * Create the frame.
     */
    public ReservarClase() {
        setTitle("Reservar Clase");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700, 300, 409, 263);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Panel de cabecera con título
        JPanel panelCabecera = new JPanel();
        panelCabecera.setBackground(new Color(0, 215, 232));
        panelCabecera.setBounds(0, 0, 400, 50);
        contentPane.add(panelCabecera);

        JLabel lblTitulo = new JLabel("Reservar Clase");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelCabecera.add(lblTitulo);

        // Etiqueta y ComboBox para Clase
        JLabel lblClase = new JLabel("Clase");
        lblClase.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblClase.setBounds(50, 70, 100, 25);
        contentPane.add(lblClase);

        comboBoxClase = new JComboBox<>();
        comboBoxClase.setFont(new Font("Verdana", Font.PLAIN, 14));
        comboBoxClase.setBounds(150, 70, 180, 25);
        comboBoxClase.addItem("Yoga");
        comboBoxClase.addItem("Pilates");
        comboBoxClase.addItem("Spinning");
        comboBoxClase.addItem("CrossFit");
        contentPane.add(comboBoxClase);

        // Etiqueta y ComboBox para Turno
        JLabel lblTurno = new JLabel("Turno");
        lblTurno.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblTurno.setBounds(50, 110, 100, 25);
        contentPane.add(lblTurno);

        comboBoxTurno = new JComboBox<>();
        comboBoxTurno.setFont(new Font("Verdana", Font.PLAIN, 14));
        comboBoxTurno.setBounds(150, 110, 180, 25);
        comboBoxTurno.addItem("Mañana");
        comboBoxTurno.addItem("Tarde");
        contentPane.add(comboBoxTurno);

        // Botón Reservar
        JButton btnReservar = new JButton("Reservar");
        btnReservar.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnReservar.setBounds(130, 160, 120, 30);
        contentPane.add(btnReservar);

        // Acción del botón Reservar
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = JOptionPane.showInputDialog("Ingrese su nombre:");
                if (nombreUsuario != null && !nombreUsuario.trim().isEmpty()) {
                    String claseSeleccionada = (String) comboBoxClase.getSelectedItem();
                    String turnoSeleccionado = (String) comboBoxTurno.getSelectedItem();
                    agregarReservaAlCSV(nombreUsuario, claseSeleccionada, turnoSeleccionado);
                    JOptionPane.showMessageDialog(contentPane, "Reserva añadida con éxito.");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Nombre de usuario no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Agrega una reserva al archivo CSV.
     */
    private void agregarReservaAlCSV(String nombre, String clase, String turno) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("reservas.csv", true))) {
            pw.println(nombre + ";" + clase + ";" + turno);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo CSV", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReservarClase frame = new ReservarClase();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
