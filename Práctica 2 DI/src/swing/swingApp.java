package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class swingApp extends JFrame {

    public swingApp() {
        // Configuración de la ventana
        setTitle("Gestión Clientes");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Panel superior con el logo y el nombre de la institución
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(128, 128, 255)); 
        topPanel.setPreferredSize(new Dimension(600, 100));

        JLabel logo = new JLabel(new ImageIcon(swingApp.class.getResource("/resources/twitch_PNG3 (1).png")));
        logo.setFont(new Font("Tahoma", Font.PLAIN, 5));

        // Panel lateral con las opciones
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(128, 128, 192));
        leftPanel.setPreferredSize(new Dimension(600, 120)); // Se ajusta para que quede debajo del topPanel

        // Crear las opciones del menú
        JLabel clientesLabel = new JLabel("Clientes", SwingConstants.CENTER);
        clientesLabel.setForeground(Color.WHITE);

        JLabel productosLabel = new JLabel("Productos", SwingConstants.CENTER);
        productosLabel.setForeground(Color.WHITE);

        JLabel facturasLabel = new JLabel("Facturas", SwingConstants.CENTER);
        facturasLabel.setForeground(Color.WHITE);

        JLabel usuarioLabel = new JLabel("Usuario", SwingConstants.CENTER);
        usuarioLabel.setForeground(Color.BLACK);
        usuarioLabel.setBackground(new Color(224, 224, 224));
        usuarioLabel.setOpaque(true);

        // Añadir comportamiento desplegable
        JPopupMenu clientesMenu = new JPopupMenu();
        JMenuItem altaClientes = new JMenuItem("Alta Clientes");
        JMenuItem bajaClientes = new JMenuItem("Baja Clientes");
        clientesMenu.add(altaClientes);
        clientesMenu.add(bajaClientes);

        clientesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clientesMenu.show(clientesLabel, e.getX(), e.getY());
            }
        });

        JPopupMenu productosMenu = new JPopupMenu();
        JMenuItem altaProductos = new JMenuItem("Alta Productos");
        JMenuItem listarProductos = new JMenuItem("Listar Productos");
        productosMenu.add(altaProductos);
        productosMenu.add(listarProductos);

        productosLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                productosMenu.show(productosLabel, e.getX(), e.getY());
            }
        });

        // Panel inferior con el nombre de usuario
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(128, 128, 255));
        bottomPanel.setPreferredSize(new Dimension(600, 30));

        JLabel userLabel = new JLabel("Néstor Hidalgo Leiva");
        userLabel.setBackground(new Color(128, 128, 255));

        // Crear un panel principal para contener todo (top, left, center, bottom)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(128, 128, 255));
        GroupLayout gl_topPanel = new GroupLayout(topPanel);
        gl_topPanel.setHorizontalGroup(
        	gl_topPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_topPanel.createSequentialGroup()
        			.addGap(5)
        			.addComponent(logo))
        );
        gl_topPanel.setVerticalGroup(
        	gl_topPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_topPanel.createSequentialGroup()
        			.addGap(5)
        			.addComponent(logo))
        );
        topPanel.setLayout(gl_topPanel);
        
                // Panel central donde irá el contenido dinámico
                JPanel centerPanel = new JPanel();
                centerPanel.setBackground(new Color(192, 192, 192)); 
                GroupLayout gl_centerPanel = new GroupLayout(centerPanel);
                gl_centerPanel.setHorizontalGroup(
                	gl_centerPanel.createParallelGroup(Alignment.LEADING)
                		.addGap(0, 600, Short.MAX_VALUE)
                );
                gl_centerPanel.setVerticalGroup(
                	gl_centerPanel.createParallelGroup(Alignment.LEADING)
                		.addGap(0, 110, Short.MAX_VALUE)
                );
                centerPanel.setLayout(gl_centerPanel);
        GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
        gl_leftPanel.setHorizontalGroup(
        	gl_leftPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_leftPanel.createSequentialGroup()
        			.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_leftPanel.createSequentialGroup()
        					.addComponent(clientesLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(productosLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(facturasLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(usuarioLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        				.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        gl_leftPanel.setVerticalGroup(
        	gl_leftPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_leftPanel.createSequentialGroup()
        			.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_leftPanel.createSequentialGroup()
        					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(productosLabel, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        						.addComponent(clientesLabel, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED))
        				.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(usuarioLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(facturasLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        leftPanel.setLayout(gl_leftPanel);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(userLabel);

        // Agregar el panel principal al frame principal
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
        gl_mainPanel.setHorizontalGroup(
        	gl_mainPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_mainPanel.createSequentialGroup()
        			.addGap(147)
        			.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
        		.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addGroup(gl_mainPanel.createSequentialGroup()
        			.addGap(251)
        			.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
        );
        gl_mainPanel.setVerticalGroup(
        	gl_mainPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_mainPanel.createSequentialGroup()
        			.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
        			.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        mainPanel.setLayout(gl_mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	swingApp frame = new swingApp();
            frame.setVisible(true);
        });
    }
}
