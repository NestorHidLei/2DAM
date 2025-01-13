package psp.unidad02.relacion02.actividad02;

import java.util.Random;

public class Pizzero implements Runnable {
	//Atributos
    private final int id;
    private final Bandeja bandeja;
    private final Random random = new Random();

    /**
     * Constructor de un pizzero
     * @param id - id del pizzero
     * @param bandeja - Bandeja donde dejara la pizza(almacen)
     */
    public Pizzero(int id, Bandeja bandeja) {
        this.id = id;
        this.bandeja = bandeja;
    }

    @Override
    public void run() {
        System.out.println("Pizzero " + id + " ha comenzado a trabajar.");
        while (bandeja.estaAbierta()) {
            try {
                System.out.println("Pizzero " + id + " está preparando una pizza.");
                // Espera entre 5 y 10 segundos
                Thread.sleep(5000 + random.nextInt(6000)); 
                bandeja.depositarPizza();
            } catch (InterruptedException e) {
                System.out.println("Pizzero " + id + " termina su turno.");
                break;
            }
        }
        System.out.println("Pizzero " + id + " termina su turno.");
    }
}
