package validaciones;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Aplicacion extends Validador{
	
    public static void main(String[] args) {
    	if(args.length != 1) {
    		System.out.println("Error con los parametros: java -jar <nombre del jar> <parametro1.csv>");
    	}
    	
        try (BufferedReader rd = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "UTF-8"))) {
            String linea;
            int lineaNumero = 0;

            // Expresión regular para dividir por comas pero respetar comillas dobles
            Pattern regexSplit = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            
            while ((linea = rd.readLine()) != null) {
                lineaNumero++;
                String[] campos = regexSplit.split(linea);

                if (campos.length != 10) {
                    System.out.println("Error en la línea " + lineaNumero + ": número de campos incorrecto.");
                    continue;
                }

                String titulo = campos[0];
                String nombre = campos[1];
                String apellidos = campos[2];
                String telefono = campos[3];
                String cp = campos[4];
                String email = campos[5];
                String url = campos[6];
                String username = campos[7];
                String password = campos[8];
                String fechaRegistro = campos[9];
                
                

                if (!validarTitulo(titulo)) {
                    System.out.println("Línea " + lineaNumero + ": Título inválido -> " + titulo);
                }
                if (!validarNombre(nombre)) {
                    System.out.println("Línea " + lineaNumero + ": Nombre inválido -> " + nombre);
                }
                if (!validarApellidos(apellidos)) {
                    System.out.println("Línea " + lineaNumero + ": Apellidos inválidos -> " + apellidos);
                }
                if (!validarTelefono(telefono)) {
                    System.out.println("Línea " + lineaNumero + ": Teléfono inválido -> " + telefono);
                }
                if (!validarCP(cp)) {
                    System.out.println("Línea " + lineaNumero + ": Código Postal inválido -> " + cp);
                }
                if (!validarEmail(email)) {
                    System.out.println("Línea " + lineaNumero + ": Email inválido -> " + email);
                }
                if (!validarURL(url)) {
                    System.out.println("Línea " + lineaNumero + ": URL inválida -> " + url);
                }
                if (!validarUsername(username)) {
                    System.out.println("Línea " + lineaNumero + ": Username inválido -> " + username);
                }
                if (!validarPassword(password)) {
                    System.out.println("Línea " + lineaNumero + ": Contraseña inválida -> " + password);
                }
                if (!validarFecha(fechaRegistro)) {
                    System.out.println("Línea " + lineaNumero + ": Fecha de registro inválida -> " + fechaRegistro);
                }
                
                System.out.println(titulo + " " + nombre+ " " + apellidos+ " " + telefono+ " " + cp+ " " + email+ " " + url+ " " + username+ " " + password+ " " + fechaRegistro);
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
	
}
