package actividad106.ejercicio01;

import java.io.*;
import java.util.Scanner;

public class Maestro {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProcessBuilder proceso;
        try {
            // Crea el proceso esclavo
            proceso = new ProcessBuilder("java", "-cp", "./bin", "actividad106.ejercicio01.Esclavo");
            Process procesoEsclavo = proceso.start();

            // Obtener los flujos de entrada y salida del proceso esclavo
            OutputStream output = procesoEsclavo.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(procesoEsclavo.getInputStream()));

            String linea;

            // Bucle que envía textos al esclavo y espera su respuesta
            while (true) {
                System.out.print("Introduce un texto (línea vacía para salir):");
                linea = sc.nextLine();

                // Si la línea está vacía, salimos
                if (linea.isEmpty()) {
                    break;
                }

                writer.println(linea);
          
                // Leer la respuesta del esclavo
                String respuesta = reader.readLine();
                System.out.println("Respuesta del esclavo: " + respuesta);
            }

            // Cerrar los flujos de entrada/salida y el proceso esclavo
            output.close();
            reader.close();
            procesoEsclavo.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
