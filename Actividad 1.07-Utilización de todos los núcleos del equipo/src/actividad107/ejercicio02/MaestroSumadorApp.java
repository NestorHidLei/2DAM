package actividad107.ejercicio02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaestroSumadorApp {
    public static void main(String[] args) {
        // Comprobar que se han introducido exactamente dos parámetros
        if (args.length != 2) {
            System.err.println("Se deben proporcionar exactamente dos parámetros enteros.");
            System.exit(1);
        }

        int num1 = 0;
        int num2 = 0;

        // Comprobar que los parámetros son enteros
        try {
            num1 = Integer.parseInt(args[0]);
            num2 = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Ambos parámetros deben ser números enteros.");
            System.exit(1);
        }

        int mayor = Math.max(num1, num2);
        int menor = Math.min(num1, num2);
        int diferencia = mayor - menor;

        int procesos = 0;

        // Determinar cuántos procesos lanzar
        if (diferencia < 25) {
            procesos = 1;
        } else if (diferencia >= 25 && diferencia <= 100) {
            procesos = 2;
        } else {
            // Obtener la cantidad de núcleos de la CPU
            procesos = Runtime.getRuntime().availableProcessors();
        }

        // Crear y ejecutar procesos de suma dividida
        int sumaTotal = 0;
        int rango = (mayor - menor + 1) / procesos;  // División del rango entre procesos
        int inicio = menor;

        for (int i = 0; i < procesos; i++) {
            try {
                // Calcular el rango que cada proceso debe sumar
                int fin = (i == procesos - 1) ? mayor : inicio + rango - 1;  // Último proceso toma el resto
                ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "./bin", "actividad107.ejercicio02.SumadorProcess", String.valueOf(inicio), String.valueOf(fin));
                Process process = processBuilder.start();

                // Leer la salida del proceso
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sumaTotal += Integer.parseInt(line);
                }

                // Esperar a que el proceso termine
                process.waitFor();

                // Actualizar el inicio para el siguiente proceso
                inicio = fin + 1;

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("La suma total es: " + sumaTotal);
    }
}
