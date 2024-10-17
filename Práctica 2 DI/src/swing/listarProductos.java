package swing;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Productos;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class listarProductos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<Productos> model;
    private JList<Productos> listProductos;  // Asegurarse de que JList tenga el tipo Productos

    /**
     * Create the frame.
     */
    public listarProductos() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Lista de Productos");
        lblNewLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 27));
        lblNewLabel.setBounds(89, 11, 240, 37);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 59, 372, 178);
        contentPane.add(scrollPane);

        // Inicializar el modelo y la lista
        model = new DefaultListModel<>();
        model.clear();
        altaProductos.listaProductos.add(new Productos("Manzana", 0.99, true));
        altaProductos.listaProductos.add(new Productos("Leche", 1.49, true));
        altaProductos.listaProductos.add(new Productos("Silla", 29.99, false));
        altaProductos.listaProductos.add(new Productos("Televisor", 399.99, false));
        altaProductos.listaProductos.add(new Productos("Yogurt", 0.89, true));
        
        listProductos = new JList<>(model);
        scrollPane.setViewportView(listProductos);

        // Actualizar la lista de productos al cargar la ventana
        actualizarListaProductos();
    }

    // Método para actualizar la lista de productos en base a los productos añadidos
    public void actualizarListaProductos() {
    	// Limpiar el modelo actual
        model.clear();  

        // Agregar todos los productos de la lista estática de altaProductos
        for (Productos producto : altaProductos.listaProductos) {
            model.addElement(producto);
        }
    }
}
