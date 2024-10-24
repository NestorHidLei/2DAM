// Ejemplo para bajaClientes.java
package swing;

import javax.swing.*;
import java.awt.*;

public class bajaClientes extends JPanel {
    public bajaClientes() {
        setLayout(new BorderLayout());
        // Añadir tus componentes aquí
        JLabel label = new JLabel("Formulario de Baja de Clientes");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
        // Otros componentes pueden ser añadidos aquí
    }
}
