package app;

import view.RecursoView;
import view.UsuarioView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        RecursoView rv = new RecursoView();
        UsuarioView uv = new UsuarioView();
        int op;

        do {
            System.out.println("\n=== SISTEMA DE RESERVAS ===");
            System.out.println("1. Gestionar RECURSOS");
            System.out.println("2. Gestionar USUARIOS");
            System.out.println("0. SALIR");
            System.out.print("Seleccione módulo: ");

            op = sn.nextInt();
            sn.nextLine();

            if (op == 1) {
                rv.ejecutarMenu();
            } else if (op == 2) {
                uv.menu();
            }

        } while (op != 0);
    }
}
