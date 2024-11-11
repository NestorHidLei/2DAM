package filtroEntrada.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

public class FormularioApp {

	private final static String ARCHIVO = "datos.txt";

	public static void main(String[] args) {
		Map<String, String> mapa = new HashMap();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				String key;
				mapa.put(line, line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
