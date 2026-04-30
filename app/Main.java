package main;

import view.RecursoView;
import view.ReservaView;
import view.UsuarioView;
import java.util.Scanner;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class Main {
    public static void main(String[] args) {
        Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        RecursoView vistaRecurso = new RecursoView();
        ReservaView vistaReserva = new ReservaView();
        UsuarioView vistaUsuario = new UsuarioView();

        do {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE GESTION DE RESERVAS");
            System.out.println("========================================");
            System.out.println("1. Gestión de Recursos");
            System.out.println("2. Gestión de Usuarios");
            System.out.println("3. Gestión de Reservas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("por favor, tienes q introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> vistaRecurso.ejecutarMenu();
                case 2 -> vistaUsuario.menu();
                case 3 -> vistaReserva.menu();
                case 0 -> System.out.println("Saliendo del sistema... byebye");
                default -> System.out.println(" Opcion no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}