package hlc.auth.totp;

public class AutenticadorTOTP {

    private SistemaSeguridadTOTP sistemaSeguridadTOTP;

    public AutenticadorTOTP() {
        this.sistemaSeguridadTOTP = new SistemaSeguridadTOTP();
    }

    /**
     * Autentica al usuario verificando el código TOTP.
     * @param username Nombre de usuario.
     * @param codigo Código TOTP proporcionado por el usuario.
     * @return true si la autenticación es exitosa, false en caso contrario.
     */
    public boolean autenticarUsuario(String username, String codigo) {
        // Obtener el secreto del usuario desde la base de datos
        String secreto = obtenerSecretoDesdeBD(username);

        if (secreto == null) {
            return false; // Usuario no encontrado
        }

        // Verificar el código TOTP
        return sistemaSeguridadTOTP.verificarTOTP(secreto, codigo);
    }

    /**
     * Simula la obtención del secreto del usuario desde la base de datos.
     * @param username Nombre de usuario.
     * @return Secreto del usuario en formato hexadecimal.
     */
    private String obtenerSecretoDesdeBD(String username) {
        // Simulación de una consulta a la base de datos
        if ("usuario1".equals(username)) {
            return "6a542e818715b34b"; // Secreto del usuario1
        } else if ("usuario2".equals(username)) {
            return "e20800dba48808a0"; // Secreto del usuario2
        }
        return null; // Usuario no encontrado
    }
}