package hlc.ud04.practica01.totp;

import hlc.ud04.appsec.seguridad.autenticacion.*;
import hlc.auth.otp.*;

public class AutenticadorTemporal implements Autenticador {
    private static long tiempoSistema;

    public AutenticadorTemporal() {}

    /**
     * Inicia el proceso de autenticación generando un desafío basado en el usuario ingresado.
     * También almacena el tiempo actual del sistema.
     */
    @Override
    public Desafio iniciaAutenticacion(String idUsuario) {
        tiempoSistema = System.currentTimeMillis();
        return new DesafioTemporal(idUsuario);
    }
    
    /**
     * Completa la autenticación verificando si la respuesta del usuario coincide con el PIN correcto.
     * El PIN correcto se genera en el momento y se compara con la entrada del usuario.
     */
    @Override
    public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {
        DesafioTemporal reto = (DesafioTemporal) desafio;
        RespuestaDesafioTemporal respuestaUsuario = (RespuestaDesafioTemporal) respuesta;
        GeneradorHOTP generador = new GeneradorHOTP();
        
        String pinIngresado = respuestaUsuario.getPin();
        try {
            // Generar el PIN basado en el tiempo del sistema dividido en intervalos de 30 segundos
            if (pinIngresado.equals(generador.genera(reto.getSecreto(), tiempoSistema / 30000))) {
                System.out.println("Autenticación exitosa, iniciando sesión...");
                return new Usuario(1);
            } else {
                throw new GeneradorException("Error en la autenticación", null);
            }
        } catch (GeneradorException e) {
            // Si el PIN es incorrecto, se muestra un mensaje de error
            System.err.println("Error en la verificación de identidad");
            return null;
        }
    }

    public static long getTiempoSistema() {
        return tiempoSistema;
    }
    public static void setTiempoSistema(long nuevoTiempo) {
    	AutenticadorTemporal.tiempoSistema = nuevoTiempo;
    }
}
