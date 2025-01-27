package hlc.ud04.appsec.sampleapp.autenticator;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

/**
 * Autenticador que usa un algoritmo matemático
 * Se ofrece al usuario una operación aritmética y un número. El usuario
 * tiene que aplicar la operación y el número a su clave y devolver el resulado
 * Tiene 4 usuarios a piñón ya que es una versión de prueba
 * <ul>
 *   <li>usuario1 Clave 1000</li>
 *   <li>usuario2 Clave 2000</li>
 *   <li>usuario3 Clave 3000</li>
 *   <li>usuario4 Clave 4000</li>
 * </ul>
 *
 */
public class AutenticadorMath implements Autenticador {

  // Datos de los usuarios
  private static DatosUsuario[] USUARIOS;
  
  // Rangos mínimos y máximo para la generación del número aleatorio
  private int rangoMinimo;
  private int rangoMaximo;
  
  /**
   * Construye un autenticador matemático
   * @param rangoMinimo Valor mínimo para el número aleatorio a generar
   * @param rangoMaximo Valor máximo para el número aleatorio a generar
   */
  public AutenticadorMath(int rangoMinimo, int rangoMaximo) {
    this.rangoMinimo = rangoMinimo;
    this.rangoMaximo = rangoMaximo;
    iniciaUsuarios();
  }

  @Override
  public Desafio iniciaAutenticacion(String identificador) {
    // Obtiene un nuevo desafío usando los datos del usuario y el rango
    return new DesafioMath(identificador, rangoMinimo, rangoMaximo);
  }

  @Override
  public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
    // Obtiene el desafío
    DesafioMath desafioMath = (DesafioMath)desafio;
    
    // Si la respuesta del usuario es correcta
    if (esRespuestaCorrecta(desafio, respuesta)) {
      // Busca al usuario en el array y devuelve un nuevo objeto
      // Usuario con el id
      for (int i = 0; i < USUARIOS.length; i++) {
        if (USUARIOS[i].nombre.equals(desafioMath.getIdentificador())) {
          return new Usuario(USUARIOS[i].uid);
        }
      }
      // No debe llegar aqui
      return null;
    } else {
      // La respuesta no es correcta. Devuelve no autenticado (null)
      return null;
    }
  }

  /**
   * Comprueba que la respuesta al desafío es correcta
   * @param desafio Desafío
   * @param respuesta Respuesta
   * @return true si la respuesta es correcta. false en caso contrario
   */
  private boolean esRespuestaCorrecta(Desafio desafio, RespuestaDesafio respuesta) {
    // Accedemos a los objetos reales de Deasfio y respuesta
    DesafioMath desafiom = (DesafioMath)desafio;
    RespuestaDesafioMath respuestam = (RespuestaDesafioMath)respuesta;
    // Obtenemos la clave del usuario desde el identificador contenido en el desafío
    int clave = getClave(desafiom.getIdentificador());
    // Según la operación
    switch (desafiom.getOperacion()) {
      case '+':
        // Calculamos la respuesta y devolvemos true si coincide o false si no
        return respuestam.getSolucion() == clave + desafiom.getNumero();
      case '-':
        return respuestam.getSolucion() == clave - desafiom.getNumero();
      case '*':
        return respuestam.getSolucion() == clave * desafiom.getNumero();
      default: return false;
    }
  }

  /**
   * Obtenemos la clave del usuario a partir de su identificador
   * @param identificador Identificador del usuario
   * @return clave del usuario (número entero)
   */
  private int getClave(String identificador) {
    // Para cada usuario
    for (int i = 0; i < USUARIOS.length; i++) {
      // Si lo encuentra
      if (USUARIOS[i].nombre.equals(identificador)) {
        // Devuelve la clave
        return USUARIOS[i].clave;
      }
    }
    // No se encontró. Devuelve -1
    return -1;
  }

  /**
   * Inicia el array de usuarios con los valores de prueba
   */
  private void iniciaUsuarios() {
    USUARIOS = new DatosUsuario[4];
    USUARIOS[0] = new DatosUsuario("usuario1", 1000, 1);
    USUARIOS[1] = new DatosUsuario("usuario2", 2000, 2);
    USUARIOS[2] = new DatosUsuario("usuario3", 3000, 3);
    USUARIOS[3] = new DatosUsuario("usuario4", 4000, 4);
  }

}

/**
 * Clase interna para almacenar los datos de los usuarios
 */
class DatosUsuario {
  String nombre;
  int clave;
  long uid;
  
  DatosUsuario(String nombre, int clave, long uid) {
    this.nombre = nombre;
    this.clave = clave;
    this.uid = uid;
  }
}
