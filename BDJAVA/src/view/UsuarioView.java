package view;

import dao.UsuarioDAO;
import modelo.Usuario;
import modelo.UsuarioNormal;

import java.util.List;
import java.util.Scanner;

public class UsuarioView {
    private UsuarioDAO dao;
    private Scanner sc;

    public UsuarioView() {
        this.dao = new UsuarioDAO();
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        int opcion = -1;
        do {
            System.out.println("\n--- 1. GESTIÓN DE USUARIOS ---");
            System.out.println("1. Alta usuario");
            System.out.println("2. Baja usuario (por correo)");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Buscar usuario por correo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Correo: ");
                        String correo = sc.nextLine();
                        System.out.print("Contraseña: ");
                        String pass = sc.nextLine();
                        System.out.print("Tipo (Administrador/Normal): ");
                        String tipo = sc.nextLine();

                        Usuario u = new UsuarioNormal();
                        u.setNombre(nombre);
                        u.setCorreoElectronico(correo);
                        u.setPassword(pass);
                        u.setTipoUsuario(tipo);

                        dao.insertar(u);
                        System.out.println("Usuario dado de alta.");
                    }
                    case 2 -> {
                        System.out.print("Correo del usuario a eliminar: ");
                        String correo = sc.nextLine();
                        dao.eliminar(correo);
                        System.out.println("Usuario eliminado.");
                    }
                    case 3 -> {
                        List<Usuario> lista = dao.obtenerTodos();
                        for (Usuario u : lista) System.out.println(u.getNombre() + " - " + u.getCorreoElectronico());
                    }
                    case 4 -> {
                        System.out.print("Correo a buscar: ");
                        String correo = sc.nextLine();
                        Usuario u = dao.findByEmail(correo);
                        System.out.println(u != null ? u.getNombre() + " - " + u.getCorreoElectronico() : "No encontrado.");
                    }
                    case 0 -> System.out.println("Volviendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}