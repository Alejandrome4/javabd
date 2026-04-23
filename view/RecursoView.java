package view;

import controller.RecursoController;
import modelo.Recurso;
import java.util.Scanner;

public class RecursoView {
    private RecursoController controller = new RecursoController();
    private Scanner sc = new Scanner(System.in);

    public void ejecutarMenu() {
        int opcion = -1;

        do {
            System.out.println("\n--- GESTIÓN DE RECURSOS ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Insertar nuevo");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> controller.obtener().forEach(System.out::println);
                case 2 -> buscarPorId();
                case 3 -> insertar();
                case 4 -> eliminar();
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void buscarPorId() {
        System.out.print("ID del recurso: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Recurso r = controller.obtenerPorId(id);
            System.out.println(r != null ? r : "Recurso no encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("ID no válido.");
        }
    }

    private void insertar() {
        System.out.print("Nombre: ");
        String nom = sc.nextLine();
        System.out.print("Descripción: ");
        String des = sc.nextLine();
        System.out.print("Ubicación: ");
        String ubi = sc.nextLine();
        System.out.print("Capacidad: ");
        try {
            int cap = Integer.parseInt(sc.nextLine());
            if (controller.insertar(new Recurso(nom, des, ubi, cap))) {
                System.out.println("Insertado correctamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Capacidad no válida.");
        }
    }

    private void eliminar() {
        System.out.print("ID a eliminar: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            if (controller.eliminar(id)) {
                System.out.println("Eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no válido.");
        }
    }
}