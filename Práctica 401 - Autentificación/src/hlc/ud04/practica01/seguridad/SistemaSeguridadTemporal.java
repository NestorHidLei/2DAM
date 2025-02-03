package hlc.ud04.practica01.seguridad;

import java.util.Scanner;
import hlc.ud04.appsec.seguridad.autenticacion.*;
import hlc.ud04.appsec.seguridad.controlacceso.*;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;
import hlc.ud04.practica01.totp.*;

public class SistemaSeguridadTemporal implements SistemaSeguridad {
    private final Autenticador gestorAutenticacion;

    public SistemaSeguridadTemporal(Autenticador gestorAutenticacion) {
        this.gestorAutenticacion = gestorAutenticacion;
    }

    /**
     * Inicia el proceso de autenticación temporal mediante un desafío y verifica la respuesta del usuario.
     * @return Usuario autenticado si la validación es exitosa.
     */
    @Override
    public Usuario autentica() {
        	Scanner entrada = new Scanner(System.in);
            // Pedir identificador de usuario
            System.out.print("Ingrese su identificador: ");
            String idUsuario = entrada.nextLine();

            // Generar desafío temporal para la autenticación
            DesafioTemporal reto = (DesafioTemporal) gestorAutenticacion.iniciaAutenticacion(idUsuario);

            // Pedir código temporal
            System.out.print("Introduzca su código de acceso (6 dígitos): ");
            String codigo = entrada.nextLine();

            // Validar autenticación y devolver resultado
            return gestorAutenticacion.finalizaAutenticacion(reto, new RespuestaDesafioTemporal(codigo));
    }

    @Override
    public boolean estaPermitido(Usuario usuario, Operacion accion, Recurso objetivo) {
        return true;
    }
}
