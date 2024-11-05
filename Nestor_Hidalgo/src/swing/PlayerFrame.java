package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


import swing.player.SinEquipo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

public class PlayerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String currentUserEmail;
    SinEquipo sinEquipo;

    private static final String USUARIOS = "Usuarios.csv";
    private JPanel panelCentral;
    private JPanel panelSinEquipo;

    public PlayerFrame(String email) {
        this.currentUserEmail = email; // Almacenar el email del usuario actual
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panelCabecera = new JPanel();
        panelCabecera.setBackground(new Color(0, 128, 192));
        contentPane.add(panelCabecera, BorderLayout.NORTH);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(PlayerFrame.class.getResource("/resources/Logo.png")));
        panelCabecera.add(lblLogo);
        
        JPanel panelIzquierda = new JPanel();
        panelIzquierda.setBackground(new Color(0, 215, 232));
        contentPane.add(panelIzquierda, BorderLayout.WEST);
        panelIzquierda.setLayout(new GridLayout(0, 1, 100, 10));
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(PlayerFrame.class.getResource("/resources/Home.png")));
        panelIzquierda.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        	}
        });
        lblNewLabel_1.setIcon(new ImageIcon(PlayerFrame.class.getResource("/resources/Equipo.png")));
        panelIzquierda.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		actualizarSesion(false); // Marcar la sesión como inactiva
                Login login = new Login();
                login.setVisible(true);
                dispose(); // Cerrar AdminFrame
        	}
        });
        lblNewLabel_2.setIcon(new ImageIcon(PlayerFrame.class.getResource("/resources/Logout.png")));
        panelIzquierda.add(lblNewLabel_2);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 192));
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        panelCentral = new JPanel();
        contentPane.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout(0, 0));
        
        panelSinEquipo = new JPanel();
        panelCentral.add(panelSinEquipo, BorderLayout.CENTER);
        
       

        // Marcar la sesión como activa en el archivo CSV
        actualizarSesion(true);
        perteneceEquipo();
    }

    private void perteneceEquipo() {
    	 try (BufferedReader entrada = new BufferedReader(new FileReader(USUARIOS))) {
    		 String linea;
             while ((linea = entrada.readLine()) != null) {
                 String[] partesUsuario = linea.split(";");
                 if (partesUsuario[7].equals("false")) {
                	panelCentral.add(panelSinEquipo);
             		panelSinEquipo.setLayout(new GridLayout(1, 0, 0, 0));
             		panelSinEquipo.setPreferredSize(new Dimension(50,120));
             		sinEquipo = new SinEquipo(this);

            		
                 } else {
                	 
                 }
             }
    	 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    // Método para actualizar el estado de sesión en el archivo CSV
    private void actualizarSesion(boolean estadoSesion) {
        List<String> usuariosActualizados = new ArrayList<>();

        try (BufferedReader entrada = new BufferedReader(new FileReader(USUARIOS))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partesUsuario = linea.split(";");
                if (partesUsuario[4].equals(currentUserEmail)) {
                    // Modificar el último campo (estado de sesión) a true o false según estadoSesion
                    partesUsuario[6] = String.valueOf(estadoSesion);
                    linea = String.join(";", partesUsuario);
                }
                usuariosActualizados.add(linea); // Agregar la línea (modificada o no) a la lista
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios");
        }

        // Sobrescribir el archivo con la lista actualizada
        try (PrintWriter salida = new PrintWriter(new FileWriter(USUARIOS))) {
            for (String usuario : usuariosActualizados) {
                salida.println(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el archivo de usuarios");
        }
    }
}
