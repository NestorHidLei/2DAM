package psp.unidad02.relacion02.actividad01;

public class Worker implements Runnable {
	// Parametros
	private final int inicio;
	private final int fin;
	private final ObjetoCompartido compartido;

	/**
	 * Constructor de worker
	 * 
	 * @param inicio - Limite inferior
	 * @param fin - Limite superior
	 * @param compartido - Almacen de numeros primos
	 */
	public Worker(int inicio, int fin, ObjetoCompartido compartido) {
		this.inicio = inicio;
		this.fin = fin;
		this.compartido = compartido;
	}

	@Override
	/**
	 * Metodo run el cual verifica que el numero sea primo y 
	 * en caso de que lo sea lo añade al ObjetoCompartido
	 */
	public void run() {
		for (int i = inicio; i <= fin; i++) {
			if (esPrimo(i)) {
				compartido.agregarPrimo(i);
			}
		}
	}

	/**
	 * Metodo que verifica que un numero sea primo
	 * @param num - numero a verificar
	 * @return true si el numero es primo y false si no lo es
	 */
	private boolean esPrimo(int num) {
		if (num < 2) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}