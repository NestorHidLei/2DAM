package psp.unidad01.relacion03;

public class EchoCommandLine {

	public static void main(String args[]) {
		// Mira que la longitud de los argumentos no sea 0
		if (args.length == 0) {
			System.out.println("No se han proporcionado argumentos.");
		} else {
			System.out.println("Argumentos recibidos:");
			// Recorre e imprime cada argumentos recibido
			for (int i = 0; i < args.length; i++) {
				System.out.println("Argumento " + (i + 1) + ": " + args[i]);
			}
		}
	}

}
