package view;

import controller.RecursoController;
import modelo.Recurso;
import java.util.Scanner;

public class RecursoView {
    public static void main(String[] args) {
        RecursoController controller = new RecursoController();
        Scanner sc = new Scanner(System.in);
        int opcion, id;

        do {
            menu();
            if (!sc.hasNextInt()) {
                System.out.println("Por favor, introduce un número.");
                sc.nextLine();
                continue;
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:

                    controller.obtener().forEach(System.out::println);
                    break;
                case 2:
                    id = leerId(sc, "ID del recurso: ");
                    Recurso r = controller.obtenerPorId(id);
                    System.out.println(r != null ? r : "No encontrado");
                    break;
                case 3:
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Descripción: "); String des = sc.nextLine();
                    System.out.print("Ubicación: "); String ubi = sc.nextLine();
                    int cap = leerId(sc, "Capacidad: ");

                    if (controller.insertar(new Recurso(nom, des, ubi, cap)))
                        System.out.println("Insertado correctamente");
                    break;
                case 4:
                    id = leerId(sc, "ID a eliminar: ");
                    if (controller.eliminar(id))
                        System.out.println("Eliminado correctamente.");
                    else
                        System.out.println("No se pudo eliminar (ID no existe).");
                    break;
            }
        } while (opcion != 0);
    }

    public static void menu() {
        System.out.println("\n--- GESTIÓN DE RECURSOS ---");
        System.out.println("0. Salir");
        System.out.println("1. Listar todos");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Insertar nuevo");
        System.out.println("4. Eliminar");
        System.out.print("Elige una opción: ");
    }

    public static int leerId(Scanner sc, String mensaje) {
        int valor = 0; boolean valido = false;
        do {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(sc.nextLine());
                valido = true;
            } catch (Exception e) {
                System.out.println("Error: Debes introducir un número entero.");
            }
        } while (!valido);
        return valor;
    }
}