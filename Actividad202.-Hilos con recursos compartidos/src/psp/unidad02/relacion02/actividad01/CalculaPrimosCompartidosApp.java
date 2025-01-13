package psp.unidad02.relacion02.actividad01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculaPrimosCompartidosApp {

    public static void main(String[] args) {
        //Validamos que se proporcionen 2 parametros, que sean enteros positivos
        if (args.length != 2) {
            System.err.println("Error: Deben proporcionarse dos parámetros enteros positivos (límite inferior y superior). Ejemplo: java CalculaPrimosCompartidosApp 10 50");
            return;
        }

        //Creamos los límites
        int limiteInferior;
        int limiteSuperior;
        try {
        	//Inicializamos los limites
            limiteInferior = Integer.parseInt(args[0]);
            limiteSuperior = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error: Ambos parámetros deben ser números enteros positivos.");
            return;
        }
        
        //Validamos que los limites esten bien
        if (limiteInferior <= 0 || limiteSuperior <= 0 || limiteInferior > limiteSuperior) {
            System.err.println("Error: Los parámetros deben ser positivos y el límite inferior no puede ser mayor que el límite superior.");
            return;
        }

        // Obtener número de procesadores disponibles
        int numProcesadores = Runtime.getRuntime().availableProcessors();

        // Crear el objeto compartido
        ObjetoCompartido compartido = new ObjetoCompartido();

        // Crear una lista de hilos y asignar rangos a los workers
        List<Thread> workers = new ArrayList<>();
        // Se calcula la cantidad de números que cada hilo debe procesar 
        int rango = (limiteSuperior - limiteInferior + 1) / numProcesadores;
        // Se calcula el resto para distribuir de manera equitativa los números entre los procesadores
        int resto = (limiteSuperior - limiteInferior + 1) % numProcesadores;
        
        int inicio = limiteInferior;
        // Se itera sobre el número de procesadores para crear un hilo para cada subrango asignado.
        for (int i = 0; i < numProcesadores; i++) {
            int fin = inicio + rango - 1;
            if (resto > 0) {
                fin++;
                resto--;
            }

            // Se crea un nuevo worker que procesará el subrango, utilizando el objeto compartido para almacenar los resultados.
            Worker worker = new Worker(inicio, fin, compartido);
            //Se crea un nuevo hilo para ejecutar el worker creado.
            Thread thread = new Thread(worker);
            workers.add(thread);
            thread.start();
            
            //Se actualiza el inicio del siguiente subrango para el próximo hilo.
            inicio = fin + 1;
        }

        // Esperar a que todos los workers finalicen
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                System.err.println("Error al esperar por un hilo.");
            }
        }

        // Mostrar resultados
        List<Integer> primos = compartido.getPrimos();
        Collections.sort(primos);
        System.out.println("Números primos encontrados: " + primos);
    }
}