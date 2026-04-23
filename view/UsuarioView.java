package view;

import controller.UsuarioController;
import modelo.Usuario;
import java.util.Scanner;
import java.sql.Date;

public class UsuarioView {

    private UsuarioController controller = new UsuarioController();
    private Scanner sn = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Insertar nuevo usuario");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = sn.nextInt();
            sn.nextLine();

            switch(opcion) {
                case 1:
                    controller.listar().forEach(System.out::println);
                    break;
                case 2:
                    insertar();
                    break;
            }
        } while (opcion != 0);
    }

    private void insertar() {
        System.out.print("Email: ");
        String email = sn.nextLine();
        System.out.print("Contraseña: ");
        String pass = sn.nextLine();
        System.out.print("Nombre: ");
        String nom = sn.nextLine();
        System.out.print("Fecha Nacimiento (AAAA-MM-DD): ");
        String fecha = sn.nextLine();
        System.out.print("Tipo (ADMIN/NORMAL): ");
        String tipo = sn.nextLine();

        Usuario u = new Usuario(email, pass, nom, Date.valueOf(fecha), tipo);
        controller.insertar(u);
    }
}