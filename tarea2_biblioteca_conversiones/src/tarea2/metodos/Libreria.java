package tarea2.metodos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Libreria {

    private static final String LOG_FILE = "src/log_csv.txt";

    // Método para registrar el log de cada operación
    private static void registrarLog(String operacion, String archivoEntrada, String archivoSalida) {
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            logWriter.write(timestamp + " - " + operacion + ": " + archivoEntrada + " -> " + archivoSalida);
            logWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el log: " + e.getMessage());
        }
    }

    public static void fichero_CSV_To_Binario(String ruta) {
        if (!ruta.endsWith(".csv")) {
            throw new IllegalArgumentException("El archivo debe de ser .csv");
        }

        ArrayList<String> filasCSV = new ArrayList<>();
        String linea;

        try (BufferedReader salida = new BufferedReader(new FileReader(ruta))) {
            while ((linea = salida.readLine()) != null) {
                filasCSV.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String archivoBinario = ruta.replace(".csv", ".dat");
        try (ObjectOutputStream salidas = new ObjectOutputStream(new FileOutputStream(archivoBinario))) {
            salidas.writeObject(filasCSV);
            registrarLog("CSV a Binario", ruta, archivoBinario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fichero_Binario_To_CSV(String ruta) {
        if (!ruta.endsWith(".dat")) {
            throw new IllegalArgumentException("El archivo debe de ser .dat");
        }

        ArrayList<String> filasBinario = leerBinario(ruta);
        String archivoCSV = ruta.replace(".dat", ".csv");

        try (BufferedWriter salidas = new BufferedWriter(new FileWriter(archivoCSV))) {
            for (String linea : filasBinario) {
                salidas.write(linea);
                salidas.newLine();
            }
            registrarLog("Binario a CSV", ruta, archivoCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ordenar_Archivo_CSV(String ruta) {
        if (!ruta.endsWith(".csv")) {
            throw new IllegalArgumentException("El archivo debe de ser .csv");
        }

        ArrayList<String> listaCSV = new ArrayList<>();
        try (BufferedReader entrada = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                listaCSV.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ordenar ignorando mayúsculas/minúsculas
        Collections.sort(listaCSV, String.CASE_INSENSITIVE_ORDER);
        String archivoOrdenado = ruta.replace(".csv", "_ord.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoOrdenado))) {
            for (String linea : listaCSV) {
                writer.write(linea);
                writer.newLine();
            }
            registrarLog("Ordenar CSV", ruta, archivoOrdenado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ordenar_Archivo_Binario(String ruta) {
        if (!ruta.endsWith(".dat")) {
            throw new IllegalArgumentException("El archivo debe de ser .dat");
        }

        ArrayList<String> lista = leerBinario(ruta);
        Collections.sort(lista, String.CASE_INSENSITIVE_ORDER);

        String archivoOrdenado = ruta.replace(".dat", "_ord.dat");
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(archivoOrdenado))) {
            writer.writeObject(lista);
            registrarLog("Ordenar Binario", ruta, archivoOrdenado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fichero_Binario_To_CSV_Ordenado(String ruta) {
        if (!ruta.endsWith(".dat")) {
            throw new IllegalArgumentException("El archivo debe de ser .dat");
        }

        ArrayList<String> lista = leerBinario(ruta);
        Collections.sort(lista, String.CASE_INSENSITIVE_ORDER);

        String archivoCSVOrdenado = ruta.replace(".dat", "_ord.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCSVOrdenado))) {
            for (String linea : lista) {
                writer.write(linea);
                writer.newLine();
            }
            registrarLog("Binario a CSV Ordenado", ruta, archivoCSVOrdenado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> leerBinario(String ruta) {
        ArrayList<String> lista = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta))) {
            lista = (ArrayList<String>) entrada.readObject();
        } catch (EOFException e) {
            System.err.println("Fin del archivo alcanzado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        fichero_CSV_To_Binario("src/Archivo.csv");
        ordenar_Archivo_CSV("src/Archivo.csv");
        ordenar_Archivo_Binario("src/Archivo.dat");
        fichero_Binario_To_CSV("src/Archivo.dat");
        fichero_Binario_To_CSV_Ordenado("src/Archivo.dat");
    }
}
