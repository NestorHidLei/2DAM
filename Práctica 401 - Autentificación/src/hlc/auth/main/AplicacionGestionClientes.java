package hlc.auth.main;

import java.util.Scanner;

import hlc.auth.totp.AutenticadorTOTP;

public class AplicacionGestionClientes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AutenticadorTOTP autenticador = new AutenticadorTOTP();

        // Solicitar credenciales al usuario
        System.out.print("Introduzca el identificador del usuario: ");
        String username = scanner.nextLine();

        System.out.print("Introduzca ahora el PIN actual del usuario (6 cifras): ");
        String pin = scanner.nextLine();

        // Verificar el código TOTP
        if (autenticador.autenticarUsuario(username, pin)) {
            System.out.println("Autenticación exitosa!");
            mostrarMenuPrincipal();
        } else {
            System.out.println("Autenticación fallida. El PIN no es válido o ha expirado.");
        }
    }

    private static void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("-----------------");
            System.out.println("1.- Nuevo cliente");
            System.out.println("2.- Modificar cliente");
            System.out.println("3.- Eliminar cliente");
            System.out.println("4.- Listar clientes");
            System.out.println("0.- Salir de la aplicación");
            System.out.print("Elija una opción (0-4): ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Opción seleccionada: Nuevo cliente");
                    // Lógica para añadir un nuevo cliente
                    break;
                case 2:
                    System.out.println("Opción seleccionada: Modificar cliente");
                    // Lógica para modificar un cliente
                    break;
                case 3:
                    System.out.println("Opción seleccionada: Eliminar cliente");
                    // Lógica para eliminar un cliente
                    break;
                case 4:
                    System.out.println("Opción seleccionada: Listar clientes");
                    // Lógica para listar clientes
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }
}