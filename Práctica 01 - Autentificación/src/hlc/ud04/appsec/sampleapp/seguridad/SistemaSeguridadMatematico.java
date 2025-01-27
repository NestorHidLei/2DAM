package hlc.ud04.appsec.sampleapp.seguridad;

import java.util.Scanner;

import hlc.ud04.appsec.sampleapp.autenticator.DesafioMath;
import hlc.ud04.appsec.sampleapp.autenticator.RespuestaDesafioMath;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

/**
 * Sistema de seguridad matemático
 * Utiliza un Autenticador matemático (propone una operación y un número 
 * y el usuario debe calcular el resultado de aplicar la operación y el número a su clave)
 * El control de acceso empleado es simple. El usuario con ID 1 tiene acceso total
 * el de ID 2 sólo lectura y el de ID 3 sólo escritura. Cualquier otro ID no tiene acceso
 */
public class SistemaSeguridadMatematico implements SistemaSeguridad {

  // Autenticador
  private Autenticador autenticador;
  // Control de acceso
  private ControlAcceso controlAcceso;
  
  /**
   * Constructor
   * @param autenticador Autenticador a usar
   * @param controlAcceso Control de acceso a usar
   */
  public SistemaSeguridadMatematico(Autenticador autenticador, ControlAcceso controlAcceso) {
    this.autenticador = autenticador;
    this.controlAcceso = controlAcceso;
  }
  
  @Override
  public Usuario autentica() {
    
    // Scanner para interactuar con el usuario
    Scanner sc = new Scanner(System.in);
    
    // Solicita el nombre de usuario
    System.out.print("Introduzca el identificador del usuario: ");
    String usuario = sc.nextLine();
    
    // Crea un nuevo desafío matemático usando el autenticador y proporcionando el ID
    DesafioMath desafio = (DesafioMath) autenticador.iniciaAutenticacion(usuario);
    
    // Muestra el desafio generado al usuario
    System.out.println("Operacion: " + desafio.getOperacion());
    System.out.println("Factor: " + desafio.getNumero());
    // Solicita la respuesta
    System.out.print("Introduzca la respuesta: ");
    String respuesta = sc.nextLine();
    // Termina la autenticacion enviando el resultado al autenticador
    // Devuelve el resultado de la autenticación
    return autenticador.finalizaAutenticacion(desafio, new RespuestaDesafioMath(Integer.parseInt(respuesta)));
  }

  @Override
  public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
    return controlAcceso.estaPermitido(usuario, operacion, recurso);
  }

}
