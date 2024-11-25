package hilos.ejercicio02;

import java.util.ArrayList;
import java.util.List;

public class CalculaPrimosHilosApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java CalculaPrimosHilosApp <inicio> <fin>");
            return;
        }

        try {
            int inicio = Integer.parseInt(args[0]);
            int fin = Integer.parseInt(args[1]);

            int numProcesadores = Runtime.getRuntime().availableProcessors();
            System.out.println("Número de procesadores: " + numProcesadores);

            int rango = (fin - inicio + 1) / numProcesadores;
            List<List<Integer>> resultados = new ArrayList<>();
            List<Thread> hilos = new ArrayList<>();
            Object lock = new Object(); // Objeto de bloqueo para sincronizar la salida

            // Inicializa la lista de resultados para cada hilo
            for (int i = 0; i < numProcesadores; i++) {
                resultados.add(new ArrayList<>());
            }

            for (int i = 0; i < numProcesadores; i++) {
                int rangoInicio = inicio + i * rango;
                int rangoFin = (i == numProcesadores - 1) ? fin : rangoInicio + rango - 1;

                final int hiloId = i;

                Thread hilo = new Thread(() -> {
                    synchronized (lock) {
                        System.out.println("Hilo " + hiloId + " calculando números primos entre " + rangoInicio + " y " + rangoFin + "...");
                    }
                    for (int num = rangoInicio; num <= rangoFin; num++) {
                        if (esPrimo(num)) {
                            resultados.get(hiloId).add(num);
                        }
                    }
                    synchronized (lock) {
                        System.out.println("Hilo " + hiloId + " ha terminado.");
                    }
                });

                hilos.add(hilo);
                hilo.start();
            }

            for (Thread hilo : hilos) {
                hilo.join();
            }

            // Imprimir los resultados en orden
            for (int i = 0; i < resultados.size(); i++) {
                synchronized (lock) {
                    System.out.println("Resultados del Hilo " + i + ":");
                    for (int primo : resultados.get(i)) {
                        System.out.println(primo);
                    }
                }
            }

            System.out.println("Cálculo completado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("Los parámetros deben ser números enteros.");
        } catch (InterruptedException e) {
            System.out.println("Error: un hilo fue interrumpido.");
        }
    }

    private static boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}
