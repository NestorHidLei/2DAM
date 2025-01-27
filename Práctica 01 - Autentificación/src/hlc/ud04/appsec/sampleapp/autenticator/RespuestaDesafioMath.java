package hlc.ud04.appsec.sampleapp.autenticator;

import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;

/**
 * Respuesta del usuario al desafío matemático (un número entero resultado del cálculo)
 */
public class RespuestaDesafioMath implements RespuestaDesafio {
  
  // Solución al desafio
  private int solucion;
  
  /**
   * Constructor de desafio
   * @param solucion Valor numérico de solución al desafío
   */
  public RespuestaDesafioMath(int solucion) {
    this.solucion = solucion;
  }

  /**
   * Obtiene la solución
   * @return Solución
   */
  public int getSolucion() {
    return solucion;
  }
}
