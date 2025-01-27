package hlc.ud04.appsec.sampleapp.autenticator;

import hlc.ud04.appsec.seguridad.autenticacion.Desafio;

/**
 * Implementación de Desafio para el protocolo de autenticación matemática
 */
public class DesafioMath implements Desafio {

  // Identificador de usuario
  private String identificador;
  // Operacion a realizar (suma, resta, producto)
  private char operacion;
  // Número a usar en la operación con la clave
  private int numero;
  
  /**
   * Constructor
   * @param identificador Identificador del usuario
   * @param minimo Valor mínimo del rango del que se va a extraer el número aleatorio
   * @param maximo Valor máximo del rango
   */
  public DesafioMath(String identificador, int minimo, int maximo) {
    // Almacena el identificador
    this.identificador = identificador;
    // Y genera la operación y el número
    this.operacion = generaOperacion();
    this.numero = generaNumero(minimo, maximo);
  }

  /**
   * Genera un número aleatorio en el rango especificado
   * @param minimo Valor mínimo del rango
   * @param maximo Valor máximo del rango
   * @return Número aleatorio en el rango especificado
   */
  private int generaNumero(int minimo, int maximo) {
    return (int)(Math.random() * (maximo - minimo + 1) + minimo);
  }

  /**
   * Genera una operación (+, -, *) por suma, resta y producto, respectivamente
   * @return Operación generada
   */
  private char generaOperacion() {
    // Obtiene un número entre 1 y 3
    int numero = (int)(Math.random() * 3);
    // Con el número 0 genera suma, con el 1, resta y con el 2 producto
    switch (numero) {
      case 0: return '+';
      case 1: return '-';
      case 2: return '*';
      default: return '+';
    }
  }

  /**
   * Obtiene el identificador
   * @return El identificador de usuario
   */
  public String getIdentificador() {
    return identificador;
  }

  /**
   * Obtiene la operación
   * @return Operación
   */
  public char getOperacion() {
    return operacion;
  }

  /**
   * Obtiene el número
   * @return Número
   */
  public int getNumero() {
    return numero;
  }

}
