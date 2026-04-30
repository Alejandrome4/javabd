package view;

import controller.UsuarioController;
import modelo.Usuario;
import modelo.UsuarioNormal;
import modelo.Administrador;
import java.util.Scanner;
import java.sql.Date;

public class UsuarioView {

    private UsuarioController controller = new UsuarioController();
    private Scanner sn = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Insertar nuevo usuario");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            try {
                opcion = Integer.parseInt(sn.nextLine());
            } catch (Exception e) {
                opcion = -1;
            }

            switch(opcion) {
                case 1 -> {
                    System.out.println("\n+----------------------+--------------------+----------+");
                    System.out.println("| EMAIL                | NOMBRE             | TIPO     |");
                    System.out.println("+----------------------+--------------------+----------+");
                    controller.listar().forEach(System.out::println);
                    System.out.println("+----------------------+--------------------+----------+");
                }
                case 2 -> insertar();
                case 0 -> System.out.println("Saliendo al menú principal...");
            }
        } while (opcion != 0);
    }

    private void insertar() {
        try {
            String email = "";
            while (true) {
                System.out.print("Email: ");
                email = sn.nextLine();

                if (email.contains("@") && email.contains(".")) break;
                System.out.println("Error,el email debe tener un formato valido");
            
            }
            System.out.print("Contraseña: ");
            String pass = sn.nextLine();
            System.out.print("Nombre: ");
            String nom = sn.nextLine();



            System.out.print("Fecha Nacimiento (AAAA-MM-DD): ");
            String fecha = sn.nextLine();
            System.out.print("Tipo (ADMIN/NORMAL): ");
            String tipo = sn.nextLine().toUpperCase();

            Usuario u;
            if (tipo.equals("ADMIN")) {
                u = new Administrador(email, pass, nom, Date.valueOf(fecha));
            } else {
                u = new UsuarioNormal(email, pass, nom, Date.valueOf(fecha));
            }

            if (controller.insertar(u)) {
                System.out.println("Usuario guardado con éxito.");
            }
        } catch (Exception e) {
            System.out.println("Error en los datos: " + e.getMessage());


            }
        }
    }
