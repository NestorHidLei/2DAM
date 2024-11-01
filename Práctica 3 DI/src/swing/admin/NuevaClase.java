package swing.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NuevaClase extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textApeliidos; // Corrige el nombre de esta variable si es necesario
    private JTextField textNombre;

    /**
     * Create the frame.
     */
    public NuevaClase() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(NuevaClase.class.getResource("/resources/logoApp.png")));
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700, 300, 493, 384);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelCabezeraRegistro = new JPanel();
        panelCabezeraRegistro.setBackground(new Color(0, 215, 232));
        panelCabezeraRegistro.setBounds(0, 0, 477, 75);
        contentPane.add(panelCabezeraRegistro);
        panelCabezeraRegistro.setLayout(new BorderLayout(0, 0)); 

        JLabel lblClaseNueva = new JLabel("Nueva Clase");
        lblClaseNueva.setBackground(new Color(0, 215, 232));
        lblClaseNueva.setForeground(new Color(255, 255, 255));
        lblClaseNueva.setFont(new Font("Verdana", Font.BOLD, 20));
        lblClaseNueva.setHorizontalAlignment(SwingConstants.CENTER);
        panelCabezeraRegistro.add(lblClaseNueva, BorderLayout.CENTER);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Verdana", Font.PLAIN, 16));
        lblNombre.setBounds(35, 116, 77, 31);
        contentPane.add(lblNombre);

        JLabel lblProfesor = new JLabel("Profesor/a:");
        lblProfesor.setFont(new Font("Verdana", Font.PLAIN, 16));
        lblProfesor.setBounds(35, 171, 92, 31);
        contentPane.add(lblProfesor);

        textApeliidos = new JTextField();
        textApeliidos.setColumns(10);
        textApeliidos.setBounds(232, 174, 230, 31);
        contentPane.add(textApeliidos);

        textNombre = new JTextField();
        textNombre.setColumns(10);
        textNombre.setBounds(232, 116, 230, 31);
        contentPane.add(textNombre);

        JLabel lblTurno = new JLabel("Turno:");
        lblTurno.setFont(new Font("Verdana", Font.PLAIN, 16));
        lblTurno.setBounds(35, 224, 110, 31);
        contentPane.add(lblTurno);

        // Creación de los radio buttons para los turnos
        JRadioButton rdbtnManana = new JRadioButton("Mañana");
        rdbtnManana.setFont(new Font("Verdana", Font.PLAIN, 14));
        rdbtnManana.setBounds(232, 225, 100, 31);
        rdbtnManana.setBackground(Color.WHITE); // Fondo blanco para que combine con el panel
        contentPane.add(rdbtnManana);

        JRadioButton rdbtnTarde = new JRadioButton("Tarde");
        rdbtnTarde.setFont(new Font("Verdana", Font.PLAIN, 14));
        rdbtnTarde.setBounds(362, 225, 100, 31);
        rdbtnTarde.setBackground(Color.WHITE); // Fondo blanco para que combine con el panel
        contentPane.add(rdbtnTarde);

        // Grupo de botones para que solo se pueda seleccionar uno
        ButtonGroup groupTurno = new ButtonGroup();
        groupTurno.add(rdbtnManana);
        groupTurno.add(rdbtnTarde);

        JLabel lblEnviar = new JLabel("    ENVIAR");
        lblEnviar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acción al hacer clic en el botón de enviar
                String nombre = textNombre.getText().trim();
                String turno = groupTurno.getSelection() != null ? groupTurno.getSelection().getActionCommand() : null; // Obtener el turno seleccionado

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || turno == null) {
                    JOptionPane.showMessageDialog(contentPane, "Por favor, completa todos los campos.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    agregarClaseAlCSV(nombre , turno); 
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblEnviar.setBackground(new Color(0, 120, 180));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblEnviar.setBackground(new Color(0, 214, 232));
            }
        });
        lblEnviar.setOpaque(true);
        lblEnviar.setBackground(new Color(0, 214, 232));
        lblEnviar.setForeground(new Color(255, 255, 255));
        lblEnviar.setFont(new Font("Verdana", Font.PLAIN, 16));
        lblEnviar.setBounds(186, 298, 110, 31);
        contentPane.add(lblEnviar);
        
        // Asigna action command a los radio buttons para facilitar la obtención del texto
        rdbtnManana.setActionCommand("Mañana");
        rdbtnTarde.setActionCommand("Tarde");
    }
    
    /**
     * Agrega una reserva al archivo CSV.
     */
    private void agregarClaseAlCSV(String clase, String turno) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Clases.csv", true))) {
            pw.println(clase + ";" + turno);
            JOptionPane.showMessageDialog(contentPane, "Reserva añadida con éxito.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo CSV", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Método principal para ejecutar el frame
    public static void main(String[] args) {
        NuevaClase frame = new NuevaClase();
        frame.setVisible(true);
    }
}
