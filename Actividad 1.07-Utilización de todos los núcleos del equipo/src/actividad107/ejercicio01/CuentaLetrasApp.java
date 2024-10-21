package actividad107.ejercicio01;

import java.io.*;
import java.nio.file.*;

public class CuentaLetrasApp {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java actividad107.ejercicio01.CuentaLetrasApp <vocal> <archivoTexto>");
            return;
        }

        char vocal = args[0].charAt(0);  // La vocal que queremos contar
        String nombreArchivoTexto = args[1];

        try {
            // Leemos el contenido del archivo de texto
            String contenido = new String(Files.readAllBytes(Paths.get(nombreArchivoTexto)));

            // Contamos cuántas veces aparece la vocal (minúscula y mayúscula)
            int cuenta = contarVocal(contenido, vocal);

            // Guardamos el resultado en un archivo "resultado_[vocal].txt"
            String nombreArchivoResultado = "resultado_" + vocal + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivoResultado));
            writer.write(Integer.toString(cuenta));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que cuenta cuántas veces aparece la vocal en el texto
    private static int contarVocal(String texto, char vocal) {
        int count = 0;
        char vocalMayuscula = Character.toUpperCase(vocal);
        char vocalMinuscula = Character.toLowerCase(vocal);

        // Recorremos cada carácter del texto
        for (char c : texto.toCharArray()) {
            if (c == vocalMayuscula || c == vocalMinuscula) {
                count++;
            }
        }

        return count;  // Devolvemos el número de veces que aparece la vocal
    }
}
