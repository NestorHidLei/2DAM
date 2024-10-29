package hlc.unidad02.actividad203clase;

public class Parametros {

  private static final String MENSAJE_POR_DEFECTO = "mensaje";
  private static final String PASSWORD_POR_DEFECTO = "password";
  
  private String mensaje;
  private String password;
  
  public Parametros(String[] argumentos) {
    if (argumentos.length < 1) {
      mensaje = MENSAJE_POR_DEFECTO;
    } else {
      mensaje = argumentos[0];
    }
    if (argumentos.length < 2) {
      password = PASSWORD_POR_DEFECTO;
    } else {
      password = argumentos[1];
    }
  }

  /**
   * @return the mensaje
   */
  public String getMensaje() {
    return mensaje;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
}
