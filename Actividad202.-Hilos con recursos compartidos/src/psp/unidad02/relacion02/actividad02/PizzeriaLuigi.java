package psp.unidad02.relacion02.actividad02;

import java.util.ArrayList;
import java.util.List;

public class PizzeriaLuigi {
    public static void main(String[] args) {
    	//Valores por defecto: 2 pizzeros y 5 clientes.
        int numPizzeros = 2;
        int numClientes = 5;

        // Leer parámetros de entrada
        if (args.length >= 2) {
            try {
                numPizzeros = Integer.parseInt(args[0]);
                numClientes = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Parámetros inválidos. Usando valores por defecto: 2 pizzeros, 5 clientes.");
            }
        }

        //Creamos una bandeja 
        Bandeja bandeja = new Bandeja();
        //Hazemos una lista de hilos
        List<Thread> hilosPizzeros = new ArrayList<>();
        List<Thread> hilosClientes = new ArrayList<>();


        //Creamos los hilos de los pizzeros
        for (int i = 1; i <= numPizzeros; i++) {
        	//Creamos a los pizzeros y le asignamos la bandeja
            Pizzero pizzero = new Pizzero(i, bandeja);
            Thread thread = new Thread(pizzero);
            hilosPizzeros.add(thread);
            thread.start();
        }

        // Creamos los hilos de los clientes
        for (int i = 1; i <= numClientes; i++) {
        	//Creamos a los clientes y le asignamos la bandeja
            Cliente cliente = new Cliente(i, bandeja);
            Thread thread = new Thread(cliente);
            hilosClientes.add(thread);
            thread.start();
        }

        // Esperar a que los clientes terminen
        for (Thread hilo : hilosClientes) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Cerrar la pizzería
        bandeja.cerrarPizzeria();

        // Esperar a que los pizzeros terminen
        for (Thread hilo : hilosPizzeros) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los clientes y pizzeros han terminado. Pizzería cerrada.");

    }
}
