package hlc.ud03.relacion04;

public class Acondicionador {

	public static String acondicionaElementoHtml(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
            return null;
        }

        // Sustituir caracteres especiales por sus entidades HTML.
        return cadena
                .replace("&", "&amp;")  // Ampersand
                .replace("<", "&lt;")   // Menor que
                .replace(">", "&gt;")   // Mayor que
                .replace("\"", "&quot;") // Comillas dobles
                .replace("'", "&#39;"); // Comillas simples
	}

	public static String acondicionaAtributoHtml(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
            return null;
        }
		// Sustituir caracteres especiales por sus entidades HTML.
		 return cadena
		            .replace("&", "&#38;")  // Ampersand
		            .replace("<", "&#60;")  // Menor que
		            .replace(">", "&#62;")  // Mayor que
		            .replace("\"", "&#34;") // Comillas dobles
		            .replace("'", "&#39;"); // Comillas simples
		
	}

	public static String acondicionaContenidoCss(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
            return null;
        }
		return cadena;
	}

	public static String acondicionaUrl(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
            return null;
        }
		return cadena;
	}

	public static String acondicionaLiteralSql(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
            return null;
        }
		return cadena;
	}

}
