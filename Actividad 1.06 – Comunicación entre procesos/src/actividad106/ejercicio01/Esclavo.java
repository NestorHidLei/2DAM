package actividad106.ejercicio01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Esclavo {

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			Random random = new Random();

			// Bucle que lee las líneas de la entrada estándar
			while ((line = reader.readLine()) != null) {
				// Elegir una operación aleatoria: 0 = mayusculizar, 1 = minusculizar, 2 =
				// capitalizar
				int operation = random.nextInt(3);
				String resultado;

				switch (operation) {
				// Mayusculizar
				case 0:
					resultado = line.toUpperCase();
					break;
				// Minusculizar
				case 1:
					resultado = line.toLowerCase();
					break;
				// Capitalizar
				case 2:
					resultado = capitalizarTexto(line);
					break;
				default:
					resultado = line;
				}

				// Imprimir el resultado para que el maestro lo reciba
				System.out.println(resultado);
				System.out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para capitalizar cada palabra del texto
	 * 
	 * @param texto - texto a capitalizar
	 * @return el texto capitalizado
	 */
	private static String capitalizarTexto(String texto) {
		String[] palabras = texto.split(" ");
		StringBuilder resultado = new StringBuilder();

		for (String palabra : palabras) {
			if (palabra.length() > 0) {
				resultado.append(Character.toUpperCase(palabra.charAt(0))).append(palabra.substring(1).toLowerCase())
						.append(" ");
			}
		}

		return resultado.toString().trim();
	}
}
