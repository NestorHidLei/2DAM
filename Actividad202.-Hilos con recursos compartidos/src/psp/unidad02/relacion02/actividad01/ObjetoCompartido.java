package psp.unidad02.relacion02.actividad01;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenedor usados por los workers para almacenar los números primos que encuentran
 */
public class ObjetoCompartido {

	//Lista de primos
	private final List<Integer> primos = new ArrayList<>();

	/**
	 * Metodo para agregar un primo
	 * @param primo - Numero primo a agragar a la lista de primos
	 */
    public synchronized void agregarPrimo(int primo) {
        primos.add(primo);
    }

    /**
     * Metodo para obtener los primos.
     * Usamos synchronized para que solo un hilo pueda acceder a lista.
     * @return Una lista de primos
     */
    public synchronized List<Integer> getPrimos() {
        return new ArrayList<>(primos);
    }
    
}
