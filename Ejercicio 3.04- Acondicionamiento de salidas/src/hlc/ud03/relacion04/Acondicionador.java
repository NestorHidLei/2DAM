package hlc.ud03.relacion04;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Acondicionador {

	public static String acondicionaElementoHtml(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			return null;
		}

		return cadena.replace("&", "&amp;") 
				.replace("<", "&lt;") 
				.replace(">", "&gt;")
				.replace("\"", "&quot;")
				.replace("'", "&#39;"); 
	}

	
	public static String acondicionaAtributoHtml(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			return null;
		}
		return cadena.replace("&", "&amp;") 
				.replace("<", "&lt;")
				.replace("\"", "&#34;")
				.replace("'", "&#39;");

	}

	public static String acondicionaContenidoCss(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			return null;
		}
		char[] cadeanTroceada = cadena.toCharArray();
		StringBuilder cadenaNueva = new StringBuilder();
		
		for (int i = 0; i < cadeanTroceada.length; i++) {
			char c = cadeanTroceada[i];
			if (Character.isDigit(c)) {
				cadenaNueva.append(" ");
			}
			cadenaNueva.append(c);
		}
		
		
		return cadenaNueva.toString().replace("\\", "\\5c")
	                   .replace("\"", "\\22")
	                   .replace("'", "\\27")
	                   .replace("<", "\\3c")
	                   .replace(">", "\\3e")
	                   .replace("&", "\\26")
	                   .replace("(", "\\28")
	                   .replace(")", "\\29")
	                   .replace("/", "\\2f");
	}

	public static String acondicionaUrl(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			return null;
		}
	    try {
			return cadena = URLEncoder.encode(cadena, "UTF-8").replace("%40", "@").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	     return cadena;
	}

	public static String acondicionaLiteralSql(String cadena) {
	    if (cadena == null || cadena.isEmpty()) {
	        return null;
	    }
	    
	    return cadena
	        .replace("\\", "\\\\") 
	        .replace("\"", "\\\"")
	        .replace("'", "\\'")   
	        .replace("%", "\\%")   
	        .replace("_", "\\_");  
	}


}
