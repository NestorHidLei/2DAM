package swing;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginUsuario extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final String USUARIOS = "Usuarios.csv";
    private Login login;

    private JTextField usernameField;
    private JPasswordField passwordField_1;

    public LoginUsuario(Login login) {
        this.login = login;
        setBackground(new Color(255, 255, 255));
        

        JLabel lblEmail = new JLabel("Usuario:");
        lblEmail.setFont(new Font("Verdana", Font.PLAIN, 20));

        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setFont(new Font("Verdana", Font.PLAIN, 20));

        usernameField = new JTextField(20);

        JLabel lblNewLabel = new JLabel(" Iniciar Sesión");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblNewLabel.setBackground(new Color(0, 120, 180));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblNewLabel.setBackground(new Color(0, 214, 232));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                iniciarSesion();
            }
        });
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBackground(new Color(0, 214, 232));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 17));

        JLabel lblPulsaAquPara = new JLabel("¿No tienes cuenta?");
        lblPulsaAquPara.setHorizontalAlignment(SwingConstants.CENTER);
        lblPulsaAquPara.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent e) {
                lblPulsaAquPara.setBackground(new Color(0, 120, 180));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblPulsaAquPara.setBackground(new Color(0, 214, 232));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	Registro registro = new Registro();
                registro.setVisible(true);
            }
        });
        lblPulsaAquPara.setOpaque(true);
        lblPulsaAquPara.setForeground(Color.WHITE);
        lblPulsaAquPara.setFont(new Font("Verdana", Font.BOLD, 17));
        lblPulsaAquPara.setBackground(new Color(0, 214, 232));

        passwordField_1 = new JPasswordField();
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(42)
        					.addComponent(lblEmail)
        					.addGap(10)
        					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(10)
        					.addComponent(lblContrasenia)
        					.addGap(4)
        					.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblPulsaAquPara, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(45)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblEmail)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(7)
        					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(6)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblContrasenia)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(7)
        					.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(11)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblPulsaAquPara, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
        			.addGap(57))
        );
        setLayout(groupLayout);
    }

    protected void iniciarSesion() {
        boolean encontrado = false;

        try (BufferedReader entrada = new BufferedReader(new FileReader(USUARIOS))) {
            String linea;
            while ((linea = entrada.readLine()) != null && !encontrado) {
                String[] partes_usuario = linea.replace("\"", "").split(";");
                String username = partes_usuario[4];
                String password = partes_usuario[5];
                boolean sesionActiva = Boolean.parseBoolean(partes_usuario[6]);

                String inputUsername = usernameField.getText();
                String inputPassword = new String(passwordField_1.getPassword());

                if (username.equals(inputUsername) && password.equals(inputPassword)) {
                    encontrado = true;
                    if (partes_usuario[3].equals("Jugador/a")) {
                        new PlayerFrame(username).setVisible(true);
                    } else if (partes_usuario[3].equals("Entrenador/a")) {
                        new CoachFrame(username).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Perfil desconocido");
                    }
                    login.cerrar();
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Archivo de usuarios no encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios");
            e.printStackTrace();
        }
    }
}
