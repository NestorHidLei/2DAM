package swing;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resources.Clientes;
import resources.Productos;

import javax.swing.DefaultListModel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import javax.swing.JScrollPane;

public class swingApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static List<Clientes> listaCliente = new ArrayList<>();
	private JList listClientes;
	private DefaultListModel<Clientes> model;

	/**
	 * Create the frame.
	 */
	public swingApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 464);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		JMenuItem mnAltaCliente = new JMenuItem("Alta Clientes");
		mnAltaCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							altaClientes frame = new altaClientes(swingApp.this);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mnClientes.add(mnAltaCliente);

		JMenuItem mnBajaCliente = new JMenuItem("Baja Clientes");
		mnBajaCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							bajaClientes frame = new bajaClientes(swingApp.this);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

		});
		mnClientes.add(mnBajaCliente);

		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);

		JMenuItem mnAltaProductos = new JMenuItem("Alta Productos");
		mnAltaProductos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							altaProductos frame = new altaProductos();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mnProductos.add(mnAltaProductos);

		JMenuItem mnListarProductos = new JMenuItem("Listar Productos");
		mnListarProductos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							listarProductos frame = new listarProductos();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mnProductos.add(mnListarProductos);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 16, 481, 360);

		model = new DefaultListModel<>();
	    model.clear();
	    listaCliente.add(new Clientes("Néstor", "Hidalgo Leiva", 19, "Málaga"));
	    listaCliente.add(new Clientes("María", "Fernández García", 25, "Sevilla"));
	    listaCliente.add(new Clientes("Juan", "Pérez López", 30, "Córdoba"));
	    listaCliente.add(new Clientes("Laura", "Sánchez Rodríguez", 28, "Granada"));
	    listaCliente.add(new Clientes("Carlos", "Martínez Hernández", 35, "Jaén"));
	    listaCliente.add(new Clientes("Ana", "González Morales", 22, "Almería"));
	    listaCliente.add(new Clientes("Pedro", "Ramírez Ruiz", 27, "Huelva"));
	    listaCliente.add(new Clientes("Lucía", "Díaz Torres", 31, "Cádiz"));
	    listaCliente.add(new Clientes("Sofía", "Romero Gil", 24, "Málaga"));
	    listaCliente.add(new Clientes("Javier", "Muñoz Ortega", 29, "Almería"));
	    listaCliente.add(new Clientes("Cristina", "López Fernández", 26, "Córdoba"));

	    
		for (int i = 0; i < listaCliente.size(); i++) {
			model.addElement(listaCliente.get(i)); 
		}
		listClientes = new JList(model);
		scrollPane.setViewportView(listClientes);
		contentPane.add(scrollPane);
	}
	
	public DefaultListModel<Clientes> getModelClientes() {
		return model;
	}
	
	public void actualizarListaClientes() {
        model.clear();
        for (Clientes cliente : listaCliente) {
            model.addElement(cliente);
        }
    }
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					swingApp frame = new swingApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
