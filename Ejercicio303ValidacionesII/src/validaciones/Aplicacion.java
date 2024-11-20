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

            // Expresi�n regular para dividir por comas pero respetar comillas dobles
            Pattern regexSplit = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            
            while ((linea = rd.readLine()) != null) {
                lineaNumero++;
                String[] campos = regexSplit.split(linea);

                if (campos.length != 10) {
                    System.out.println("Error en la l�nea " + lineaNumero + ": n�mero de campos incorrecto.");
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
                    System.out.println("L�nea " + lineaNumero + ": T�tulo inv�lido -> " + titulo);
                }
                if (!validarNombre(nombre)) {
                    System.out.println("L�nea " + lineaNumero + ": Nombre inv�lido -> " + nombre);
                }
                if (!validarApellidos(apellidos)) {
                    System.out.println("L�nea " + lineaNumero + ": Apellidos inv�lidos -> " + apellidos);
                }
                if (!validarTelefono(telefono)) {
                    System.out.println("L�nea " + lineaNumero + ": Tel�fono inv�lido -> " + telefono);
                }
                if (!validarCP(cp)) {
                    System.out.println("L�nea " + lineaNumero + ": C�digo Postal inv�lido -> " + cp);
                }
                if (!validarEmail(email)) {
                    System.out.println("L�nea " + lineaNumero + ": Email inv�lido -> " + email);
                }
                if (!validarURL(url)) {
                    System.out.println("L�nea " + lineaNumero + ": URL inv�lida -> " + url);
                }
                if (!validarUsername(username)) {
                    System.out.println("L�nea " + lineaNumero + ": Username inv�lido -> " + username);
                }
                if (!validarPassword(password)) {
                    System.out.println("L�nea " + lineaNumero + ": Contrase�a inv�lida -> " + password);
                }
                if (!validarFecha(fechaRegistro)) {
                    System.out.println("L�nea " + lineaNumero + ": Fecha de registro inv�lida -> " + fechaRegistro);
                }
                
                System.out.println(titulo + " " + nombre+ " " + apellidos+ " " + telefono+ " " + cp+ " " + email+ " " + url+ " " + username+ " " + password+ " " + fechaRegistro);
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
	
}
