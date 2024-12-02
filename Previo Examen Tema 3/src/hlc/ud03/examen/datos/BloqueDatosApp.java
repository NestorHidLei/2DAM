package hlc.ud03.examen.datos;

public class BloqueDatosApp {
	private static final String RUTA = "./datos_previos/datos.txt";
	private static final String ERRORES_RUTA = "./datos_previos/datos.txt";


	public static void main(String[] args) {
		BloqueDatos bloque = new BloqueDatosEnFichero(RUTA);
		
		System.out.println(bloque.contieneDato("referencia"));
		System.out.println(bloque.getDato("referencia"));

	}
}
