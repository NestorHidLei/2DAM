package psp.unidad02.relacion02.actividad02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bandeja {
	//La bandeja empieza vacia
    private int pizzas = 0;
    // Hacemos el rentalLock para que un hilo pase el mismo bloqueo varias veces sin bloquearse
    private final Lock lock = new ReentrantLock();
    // Variable que indica si la pizzería sigue abierta.
    private boolean pizzeriaAbierta = true; 


    /**
     * Metodo que deposita una pizza
     */
    public void depositarPizza() {
    	//Bloquea el hilo
        lock.lock();
        try {
        	//sumamos una pizza
            pizzas++;
            System.out.println("Pizzero ha dejado una pizza. Pizzas en bandeja: " + pizzas);
        } finally {
        	//Desbloque el hilo
            lock.unlock();
        }
    }

    /**
     * Metodo para tomar una pizza
     * @return true si el cliente toma una pizza de una bandeja y false si no se encontraron pizzas
     */
    public boolean tomarPizza() {
        lock.lock();
        try {
            if (pizzas > 0) {
                pizzas--;
                System.out.println("Cliente tomó una pizza. Pizzas restantes en bandeja: " + pizzas);
                return true;
            } else {
                System.out.println("Cliente no encontró pizzas en la bandeja.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Obtiene el numero de las Pizzas 
     * @return el numero de pizzas
     */
    public int obtenerNumeroDePizzas() {
        lock.lock();
        try {
            return pizzas;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Metodo que dice si esta abierta la pizzeria
     * Usamos synchronized para que solo un hilo pueda acceder a lista.
     * @return pizzeriaAbierta - true si esta abierta y false si no lo esta
     */
    public synchronized boolean estaAbierta() {
        return pizzeriaAbierta;
    }

    /**
     * Metodo para cerrar la pizzeria
     * Usamos synchronized para que solo un hilo pueda acceder a lista.
     */
    public synchronized void cerrarPizzeria() {
        pizzeriaAbierta = false;
        System.out.println("Pizzería cerrada. No se harán más pizzas.");
    }
}
