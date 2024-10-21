package actividad107.ejercicio01;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class CuentaVocalesApp {

    public static void main(String[] args) {
        // Lista de vocales que queremos contar
        char[] vocales = {'a', 'e', 'i', 'o', 'u'};
        List<CompletableFuture<Integer>> futuros = new ArrayList<>();

        // Para cada vocal, lanzamos un proceso que contará esa vocal
        for (char vocal : vocales) {
            CompletableFuture<Integer> futuro = contarVocal(vocal);
            futuros.add(futuro);  
        }

        // Sumamos los resultados de todos los procesos de vocales
        int totalVocales = 0;
        for (CompletableFuture<Integer> futuro : futuros) {
            totalVocales += futuro.join();  
        }

        // Mostramos el total de vocales encontradas
        System.out.println("El total de vocales es: " + totalVocales);
    }

    // Método que lanza un proceso hijo para contar una vocal específica
    private static CompletableFuture<Integer> contarVocal(char vocal) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Lanzamos el proceso para contar la vocal específica
                ProcessBuilder pb = new ProcessBuilder("java", "-cp", "./bin", "actividad107.ejercicio01.CuentaLetrasApp", Character.toString(vocal), "archivoTexto.txt");
                // Mostramos la salida del proceso hijo en la consola
                pb.inheritIO();  
                Process p = pb.start();
                // Esperamos al proceso
                p.waitFor();  

                // Leemos el resultado desde el archivo generado por el proceso hijo
                return leerResultadoDesdeArchivo("resultado_" + vocal + ".txt");

            } catch (Exception e) {
                e.printStackTrace();
                // Si hay un error, devolvemos 0
                return 0;  
            }
        });
    }

    // Método para leer el resultado desde un archivo
    private static int leerResultadoDesdeArchivo(String archivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String linea = reader.readLine();
        reader.close();
        return Integer.parseInt(linea); 
    }
}
