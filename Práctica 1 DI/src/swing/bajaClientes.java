package swing;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import resources.Clientes;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class bajaClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<Clientes> model;
    private swingApp mainFrame;
    private JTextField textBuscarClientes;
    private JList<Clientes> listClientes; 

    /**
     * Create the frame.
     */
    public bajaClientes(swingApp frame) {
        this.mainFrame = frame;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 398);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 106, 414, 189);
        contentPane.add(scrollPane);

        model = new DefaultListModel<>();
        // Inicializar el modelo con los clientes
        for (Clientes cliente : swingApp.listaCliente) {
            model.addElement(cliente);
        }

        listClientes = new JList<>(model);
        scrollPane.setViewportView(listClientes);
        contentPane.add(scrollPane);

        JLabel lblBajaCliente = new JLabel("Baja de Clientes");
        lblBajaCliente.setFont(new Font("Palatino Linotype", Font.BOLD, 27));
        lblBajaCliente.setBounds(112, 26, 204, 37);
        contentPane.add(lblBajaCliente);

        JLabel lblBuscarClientes = new JLabel("Buscar clientes: ");
        lblBuscarClientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblBuscarClientes.setBounds(29, 74, 111, 21);
        contentPane.add(lblBuscarClientes);

        textBuscarClientes = new JTextField();
        textBuscarClientes.setBounds(150, 76, 274, 20);
        contentPane.add(textBuscarClientes);
        textBuscarClientes.setColumns(10);
        
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });
        btnEliminarCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnEliminarCliente.setBounds(121, 306, 164, 42);
        contentPane.add(btnEliminarCliente);

        // Añadir un DocumentListener para la búsqueda en tiempo real
        textBuscarClientes.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarClientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarClientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarClientes();
            }
        });
    }

    protected void eliminarCliente() {
        // Obtener el cliente seleccionado
        Clientes clienteSeleccionado = listClientes.getSelectedValue();
        
        // Verificar si hay un cliente seleccionado
        if (clienteSeleccionado != null) {
            // Eliminar el cliente de la lista principal
            swingApp.listaCliente.remove(clienteSeleccionado);
            // Actualizar el modelo de la lista para reflejar la eliminación
            actualizarListaClientes();
            mainFrame.actualizarListaClientes();
            // Mensaje de confirmación
            JOptionPane.showMessageDialog(this, "Cliente eliminado: " + clienteSeleccionado.getNombre());
        } else {
            // Mensaje de error si no hay cliente seleccionado
            JOptionPane.showMessageDialog(this, "Selecciona un Cliente para eliminar");
        }
    }

    public void actualizarListaClientes() {
        model.clear();
        for (Clientes cliente : swingApp.listaCliente) {
            model.addElement(cliente);
        }
    }

    public void updateListModel() {
        // Limpiar el modelo antes de actualizar
        model.clear();
        for (Clientes cliente : swingApp.listaCliente) {
            model.addElement(cliente);
        }
    }

    public void buscarClientes() {
        String buscarNombre = textBuscarClientes.getText().trim().toLowerCase();
        DefaultListModel<Clientes> resultado = new DefaultListModel<>();

        for (int i = 0; i < model.getSize(); i++) {
            Clientes cliente = model.getElementAt(i);
            if (cliente.getNombre().toLowerCase().contains(buscarNombre) || 
                cliente.getApellidos().toLowerCase().contains(buscarNombre)) {
                resultado.addElement(cliente);
            }
        }

        // Actualizar el JList con los resultados de búsqueda
        listClientes.setModel(resultado);
    }
}
