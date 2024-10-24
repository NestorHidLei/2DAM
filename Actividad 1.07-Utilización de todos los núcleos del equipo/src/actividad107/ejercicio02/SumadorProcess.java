package actividad107.ejercicio02;

public class SumadorProcess {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Se deben proporcionar dos parámetros: num1, num2");
            System.exit(1);
        }

        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        int suma = 0;

        for (int i = num1; i <= num2; i++) {
            suma += i;
        }

        // Imprimir la suma parcial
        System.out.println(suma);
    }
}
