package psp.unidad01.relacion03;

public class EchoCommandLineOptional {
	public static void main(String args[]) {
	// Mira que la longitud de los argumentos no sea 0
			if (args.length < 2) {
				args[0] = "Parametro 0";
				args[1] = "Parametro 1";
			} else {
				System.out.println("Argumentos recibidos:");
				// Recorre e imprime cada argumentos recibido
				for (int i = 0; i < args.length; i++) {
					System.out.println("Argumento " + (i + 1) + ": " + args[i]);
				}
			}
		}
}