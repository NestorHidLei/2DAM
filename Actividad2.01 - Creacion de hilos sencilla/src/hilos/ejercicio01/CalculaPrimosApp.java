package hilos.ejercicio01;

public class CalculaPrimosApp {

    public static void main(String[] args) {
        // Verifica que se pasaron los parámetros necesarios
        if (args.length != 2) {
            System.err.println("Uso: java -jar CalculaPrimosApp <inicio> <fin>");
            return;
        }

        try {
            // Obtiene los valores de inicio y fin desde los argumentos
            int inicio = Integer.parseInt(args[0]);
            int fin = Integer.parseInt(args[1]);

            // Crea y ejecuta un hilo para calcular los números primos
            Thread hilo = new Thread(new CalculaPrimos(inicio, fin));
            hilo.start();
            hilo.join(); 
            
        } catch (NumberFormatException e) {
            System.err.println("Los parámetros deben ser números enteros.");
        } catch (InterruptedException e) {
            System.err.println("Error: el hilo fue interrumpido.");
        }
    }
}
