package psp.unidad02.relacion02.actividad02;

import java.util.Random;

public class Cliente implements Runnable {
	//Atributos
    private final int id;
    private final Bandeja bandeja;
    private final Random random = new Random();

    /**
     * Constructor de un cliente
     * @param id - id de cliente
     * @param bandeja - Bandeja de donde recojera la pizza, si hay. (Almacen)
     */
    public Cliente(int id, Bandeja bandeja) {
        this.id = id;
        this.bandeja = bandeja;
    }

    @Override
    public void run() {
        System.out.println("Cliente " + id + " ha entrado en la pizzería.");
        int pizzasComidas = 0;

        while (pizzasComidas < 5) {
            System.out.println("Cliente " + id + " intenta tomar una pizza.");
            boolean comio = bandeja.tomarPizza();

            if (comio) {
                pizzasComidas++;
                System.out.println("Cliente " + id + " ha comido " + pizzasComidas + " pizzas.");
                // Paseo entre 20 y 30 segundos
                pasear(20000, 30000);
            } else {
            	// Paseo entre 10 y 15 segundos
                pasear(10000, 15000); 
            }
        }

        System.out.println("Cliente " + id + " está satisfecho y se va a casa.");
    }

    /**
     * Metodo que simula el paseo del cliente
     * @param min - valor minimo del paseo
     * @param max - valor maximo del paseo
     */
    private void pasear(int min, int max) {
        try {
        	//Calcula cuanto tiempo tardara en daar el paseo
            int tiempo = min + random.nextInt(max - min + 1);
            System.out.println("Cliente " + id + " está paseando (" + tiempo + " ms).");
            Thread.sleep(tiempo);
            System.out.println("Cliente " + id + " terminó de pasear.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
