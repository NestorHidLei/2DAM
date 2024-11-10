package psp.unidad01.actividadobligatoria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrimosMaestraApp {

    private static final int VALOR_MINIMO = 2;
    private static final int VALOR_MAXIMO = 2147483647;
    private static final int PROCESOS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        if (args.length < 2) {
            mostrarUso("Insuficiente número de parámetros");
            return;
        }

        try {
            int parametro1 = Integer.parseInt(args[0]);
            int parametro2 = Integer.parseInt(args[1]);

            if (parametro1 < VALOR_MINIMO || parametro1 > VALOR_MAXIMO || parametro2 < VALOR_MINIMO || parametro2 > VALOR_MAXIMO) {
                mostrarUso("Enteros no válidos, valores válidos entre " + VALOR_MINIMO + " y " + VALOR_MAXIMO);
                return;
            }

            if (parametro1 > parametro2) {
                System.err.println("ERROR: El valor inicial debe ser menor o igual al valor final.");
                return;
            }

            int numCores = args.length == 3 ? Integer.parseInt(args[2]) : 1;

            if (numCores <= 0) {
                mostrarUso("El valor del número de procesos no es positivo o no es un entero");
                return;
            }

            if (numCores > PROCESOS) {
                mostrarUso("Se solicitan más procesos que núcleos disponibles (" + PROCESOS + ") tiene el sistema");
                return;
            }

            int rangoTotal = parametro2 - parametro1 + 1;
            if (numCores > rangoTotal) {
                mostrarUso("Se solicitan más procesos que números se quieren analizar");
                return;
            }

            int rango = rangoTotal / numCores;
            List<Integer> primosTotales = new ArrayList<>();
            long tiempoInicioTotal = System.currentTimeMillis();
            int totalPrimos = 0;
            int totalNumerosAnalizados = 0;

            for (int i = 0; i < numCores; i++) {
                int inicio = parametro1 + i * rango;
                int fin = (i == numCores - 1) ? parametro2 : inicio + rango - 1;

                ProcessBuilder proceso = new ProcessBuilder("java", "-cp", "./bin",
                        "psp.unidad01.practicaobligatoria.esclava.PrimosEsclavaApp",
                        String.valueOf(inicio), String.valueOf(fin));

                long tiempoInicio = System.currentTimeMillis();
                Process procesoEsclavo = proceso.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(procesoEsclavo.getInputStream()))) {
                    List<Integer> primosParciales = new ArrayList<>();
                    int contadorPrimos = 0;
                    int numerosAnalizados = fin - inicio + 1;

                    String line;
                    while ((line = reader.readLine()) != null) {
                        primosParciales.add(Integer.parseInt(line));
                        contadorPrimos++;
                    }

                    long tiempoFinal = System.currentTimeMillis();
                    long tiempoEmpleado = tiempoFinal - tiempoInicio;

                    primosTotales.addAll(primosParciales);
                    totalPrimos += contadorPrimos;
                    totalNumerosAnalizados += numerosAnalizados;

                    System.out.printf("Proceso P%d tiempo empleado: %d ms se han encontrado: %d números primos entre los %d analizados%n",
                            i, tiempoEmpleado, contadorPrimos, numerosAnalizados);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            long tiempoTotalEmpleado = System.currentTimeMillis() - tiempoInicioTotal;
            System.out.printf("Tiempo total empleado en el programa: %d ms se han encontrado %d números primos entre los %d analizados%n",
                    tiempoTotalEmpleado, totalPrimos, totalNumerosAnalizados);

            System.out.println(primosTotales);

        } catch (NumberFormatException e) {
            mostrarUso("El valor del número de procesos no es positivo o no es un entero");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarUso(String mensajeError) {
        System.err.println(mensajeError);
        System.out.println("USO:\njava -jar maestra.jar valor1 valor2 valor3");
        System.out.println("valor1 -> (obligatorio) primer extremo del rango de valores a analizar (entre " + VALOR_MINIMO + " y " + VALOR_MAXIMO + ")");
        System.out.println("valor2 -> (obligatorio) segundo extremo del rango de valores a analizar (entre " + VALOR_MINIMO + " y " + VALOR_MAXIMO + ")");
        System.out.println("valor3 -> (opcional) número de procesos a lanzar. Su equipo tiene " + PROCESOS + " núcleos");
    }
}
