package utils;

public class Productos {
	
	private String nombreProducto;
	private double precio;
	private boolean esPerecedero;
	
	public Productos(String nombreProducto, double precio, boolean esPerecedero) {
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.esPerecedero = esPerecedero;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public boolean isEsPerecedero() {
		return esPerecedero;
	}

	@Override
	public String toString() {
	    String perecederoText = esPerecedero ? "Sí" : "No";
	    return String.format("Producto: %s | Precio: %.2f | Perecedero: %s", nombreProducto, precio, perecederoText);
	}

}
