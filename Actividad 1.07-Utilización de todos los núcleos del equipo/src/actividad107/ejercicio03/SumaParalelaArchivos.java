package actividad107.ejercicio03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumaParalelaArchivos {

    // Clase interna que implementa Callable para la tarea de sumar cantidades en un archivo
    static class SumaArchivoTask implements Callable<Integer> {
        private final Path archivo;

        public SumaArchivoTask(Path archivo) {
            this.archivo = archivo;
        }

        @Override
        public Integer call() {
            int suma = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo.toFile()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    suma += Integer.parseInt(linea.trim());
                }
            } catch (IOException e) {
                System.err.println("Error leyendo el archivo " + archivo + ": " + e.getMessage());
            }
            return suma;
        }
    }

    public static void main(String[] args) {
        // Lista de nombres de archivos
        String[] nombresArchivos = {
            "informatica.txt",
            "gerencia.txt",
            "contabilidad.txt",
            "comercio.txt",
            "rrhh.txt"
        };

        // Crear una lista de rutas para los archivos
        List<Path> archivos = new ArrayList<>();
        for (String nombreArchivo : nombresArchivos) {
            archivos.add(Paths.get(nombreArchivo));
        }

        // Obtener el número de núcleos disponibles
        int numNucleos = Runtime.getRuntime().availableProcessors();
        System.out.println("Número de núcleos disponibles: " + numNucleos);

        // Crear un ExecutorService con un pool de hilos basado en el número de núcleos
        ExecutorService executor = Executors.newFixedThreadPool(numNucleos);
        List<Future<Integer>> resultados = new ArrayList<>();

        // Enviar tareas de suma para cada archivo
        for (Path archivo : archivos) {
            SumaArchivoTask tarea = new SumaArchivoTask(archivo);
            Future<Integer> resultado = executor.submit(tarea);
            resultados.add(resultado);
        }

        // Recoger los resultados y sumar el total
        int sumaTotal = 0;
        for (Future<Integer> futuro : resultados) {
            try {
                sumaTotal += futuro.get(); // Esperar y obtener el resultado de cada tarea
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error al obtener el resultado: " + e.getMessage());
            }
        }

        // Cerrar el executor
        executor.shutdown();

        // Mostrar el resultado final
        System.out.println("La suma total de las cantidades en todos los archivos es: " + sumaTotal);
    }
}
