package view;

import controller.RecursoController;
import modelo.Recurso;
import java.util.List;
import java.util.Scanner;

public class RecursoView {
    private RecursoController controller = new RecursoController();
    private Scanner sc = new Scanner(System.in);

    public void ejecutarMenu() {
        int opcion = -1;

        do {
            System.out.println("\n--- GESTIÓN DE RECURSOS ---");
            System.out.println("1. Listar todos (ver disponibilidad)");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Insertar nuevo");
            System.out.println("4. Eliminar");
            System.out.println("5. Actualizar recurso");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> listarConHorarios();
                case 2 -> buscarPorId();
                case 3 -> insertar();
                case 4 -> eliminar();
                case 5 -> actualizar();
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarConHorarios() {
        List<Recurso> lista = controller.obtener();
        System.out.println("\n=== LISTADO DE RECURSOS ===");
        if (lista.isEmpty()) {
            System.out.println("No hay recursos registrados.");
        } else {
            for (Recurso r : lista) {
                System.out.println("\n--------------------------------------------------");
                System.out.println(r);
            }
            System.out.println("--------------------------------------------------");
        }
    }

    private void buscarPorId() {
        System.out.print("ID del recurso: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Recurso r = controller.obtenerPorId(id);
            if (r != null) {
                System.out.println("\n[ DATOS ENCONTRADOS ]");
                System.out.println(r);
            } else {
                System.out.println("Recurso no encontrado.");
            }
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
                System.out.println(" Insertado correctamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Capacidad no válida.");
        }
    }

    private void eliminar() {
        System.out.print("ID a eliminar: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            if (controller.eliminar(id)) {
                System.out.println(" Eliminado correctamente.");
            } else {
                System.out.println(" No se pudo eliminar (revisa si tiene reservas activas).");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no válido.");
        }
    }

    private void actualizar() {
        try {
            System.out.print("ID del recurso a modificar: ");
            int id = Integer.parseInt(sc.nextLine());

            Recurso r = controller.obtenerPorId(id);
            if (r == null) {
                System.out.println("El recurso no existe.");
                return;
            }

            System.out.print("Nuevo nombre (actual: " + r.getNombre() + "): ");
            String nom = sc.nextLine();
            if(!nom.isBlank()) r.setNombre(nom);

            System.out.print("Nueva ubicación (actual: " + r.getUbicacion() + "): ");
            String ubi = sc.nextLine();
            if(!ubi.isBlank()) r.setUbicacion(ubi);

            System.out.print("Nueva capacidad (actual: " + r.getCapacidad() + "): ");
            String capStr = sc.nextLine();
            if(!capStr.isBlank()) r.setCapacidad(Integer.parseInt(capStr));

            if (controller.actualizar(r)) {
                System.out.println(" Recurso actualizado con éxito.");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Entrada numérica no válida.");
        }
    }
}