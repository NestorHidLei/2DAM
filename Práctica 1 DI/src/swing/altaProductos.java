package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resources.Productos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class altaProductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPrecio;
	private JTextField textNombreProducto;
	private JCheckBox chckbxEsPerecedero;
	public static List<Productos> listaProductos = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public altaProductos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevoProducto = new JLabel("Nuevo Producto");
		lblNuevoProducto.setBounds(112, 11, 220, 37);
		lblNuevoProducto.setFont(new Font("Palatino Linotype", Font.BOLD, 27));
		contentPane.add(lblNuevoProducto);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(26, 55, 62, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		
		textPrecio = new JTextField();
		textPrecio.setBounds(308, 56, 99, 20);
		textPrecio.setColumns(10);
		contentPane.add(textPrecio);
		
		textNombreProducto = new JTextField();
		textNombreProducto.setBounds(98, 56, 136, 20);
		textNombreProducto.setColumns(10);
		contentPane.add(textNombreProducto);
		
		JLabel lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(254, 55, 64, 19);
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblPrecio);
		
		JButton btnAniadirCliente = new JButton("Añadir Producto");
		btnAniadirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aniadirProducto();
			}
		});
		btnAniadirCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAniadirCliente.setBounds(143, 147, 157, 49);
		contentPane.add(btnAniadirCliente);
		
		chckbxEsPerecedero = new JCheckBox("Es perecedero");
		chckbxEsPerecedero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxEsPerecedero.setBounds(165, 93, 134, 23);
		contentPane.add(chckbxEsPerecedero);
		
	}

	protected void aniadirProducto() {
        // Obtener datos de entrada
        String nombreProducto = textNombreProducto.getText().trim();
        String precioProductotxt = textPrecio.getText().trim();
        
        // Validación de entrada
        if (nombreProducto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double precioProducto = 0.0;
        try {
            precioProducto = Double.parseDouble(precioProductotxt);
            if (precioProducto < 0) {
                JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obtener el estado del checkbox 
        boolean esPerecedero = chckbxEsPerecedero.isSelected();

        // Crear un nuevo producto (suponiendo que tienes una clase Producto)
        Productos nuevoProducto = new Productos(nombreProducto, precioProducto, esPerecedero);

		// Añadir el nuevo producto a la lista de productos (ajusta esto según tu implementación)
        listaProductos.add(nuevoProducto);

        // Mostrar un mensaje de confirmación
        JOptionPane.showMessageDialog(this, "Producto añadido con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar los campos de texto
        textNombreProducto.setText("");
        textPrecio.setText("");
        chckbxEsPerecedero.setSelected(false);
    }
}
