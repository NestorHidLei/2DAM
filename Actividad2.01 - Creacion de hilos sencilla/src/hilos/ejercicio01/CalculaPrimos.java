package hilos.ejercicio01;

class CalculaPrimos implements Runnable {
    private int inicio;
    private int fin;

    public CalculaPrimos(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        System.out.println("---------------------------------------------");
        System.out.println("Calculando números primos entre " + inicio + " y " + fin + "...");
        System.out.println("---------------------------------------------");

        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                System.out.println("🔥 " + i + " es un número primo! 🔥");
            }
        }

        System.out.println("---------------------------------------------");
        System.out.println("Cálculo completado.");
        System.out.println("---------------------------------------------");
    }

    // Método para determinar si un número es primo
    private boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}
