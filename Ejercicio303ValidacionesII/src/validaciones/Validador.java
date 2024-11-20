package validaciones;


public class Validador {
	
	/**
	 * Valida el titulo de los usuario (Doctor, SeÒor o SeÒora)
	 * 
	 * @param titulo - titulo de los usuarios
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarTitulo(String titulo) {
        return titulo.matches("Doctor|SeÒor|SeÒora");
    }
    
    /**
	 * Valida el nombre de los usuarios
	 * 
	 * @param nombre - nombre de los ususarios
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarNombre(String nombre) {
        return nombre.matches("[a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄¸‹\\s]{1,50}");
    }
    
    /**
	 * Valida los apellidos del usuario
	 * 
	 * @param apellidos- apellidos del usuario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarApellidos(String apellidos) {
        return apellidos.matches("[a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄¸‹\\s]{1,100}");
    }
    
    /**
	 * Valida el telÈfono del usuario
	 * 
	 * @param telefono - telefono del usuario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarTelefono(String telefono) {
        return telefono.matches("[6789]\\d{8}");
    }
    
    /**
	 * Valida el codigo postal del usuario
	 * 
	 * @param cp - codigo postal del usuario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarCP(String cp) {
        return cp.matches("(0[1-9]|[1-4]\\d|5[0-2])\\d{3}");
    }
    
    /**
	 * Valida el email del usuario
	 * 
	 * @param email del usuario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    
    /**
	 * Valida la url
	 * 
	 * @param url
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarURL(String url) {
        return url.isEmpty() || url.matches("https?:\\/\\/[^\\s,]+");
    }
    
    /**
	 * Valida el username del usuario
	 * 
	 * @param username - username del usuario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarUsername(String username) {
        return username.matches("[a-zA-Z0-9_-]{1,10}");
    }
    
    /**
	 * Valida la contraseÒa del usuario
	 * 
	 * @param password - contraseÒa del usuario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarPassword(String password) {
        return password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.;,:/\\*&%$()]).{8,16}");
    }
    
    /**
	 * Valida la fecha
	 * 
	 * @param fecha - Fecha del ususario
	 * @return False - No pasa la validacion, True - Si la pasa
	 */
    protected static boolean validarFecha(String fecha) {
        return fecha.matches("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])");
    }
}
