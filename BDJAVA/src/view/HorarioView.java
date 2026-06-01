package view;

import dao.HorarioDAO;
import modelo.Horario;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class HorarioView {
    private HorarioDAO dao;
    private Scanner sc;

    public HorarioView() {
        this.dao = new HorarioDAO();
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        int opcion = -1;
        do {
            System.out.println("\n--- 3. GESTIÓN DE HORARIOS ---");
            System.out.println("1. Alta horario");
            System.out.println("2. Baja horario");
            System.out.println("3. Modificar horario");
            System.out.println("4. Listar todos los horarios");
            System.out.println("5. Listar horarios por recurso");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Día de la semana: ");
                        String dia = sc.nextLine();
                        System.out.print("Hora Inicio (HH:MM): ");
                        Time inicio = Time.valueOf(sc.nextLine() + ":00");
                        System.out.print("Hora Fin (HH:MM): ");
                        Time fin = Time.valueOf(sc.nextLine() + ":00");
                        dao.insertar(new Horario(0, dia, inicio, fin));
                        System.out.println("¡Horario guardado!");
                    }
                    case 2 -> {
                        System.out.print("ID del horario a eliminar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        dao.eliminar(id);
                        System.out.println("¡Horario eliminado!");
                    }
                    case 3 -> {
                        System.out.print("ID del horario a modificar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo Día: ");
                        String dia = sc.nextLine();
                        System.out.print("Nueva Hora Inicio (HH:MM): ");
                        Time inicio = Time.valueOf(sc.nextLine() + ":00");
                        System.out.print("Nueva Hora Fin (HH:MM): ");
                        Time fin = Time.valueOf(sc.nextLine() + ":00");
                        dao.modificar(new Horario(id, dia, inicio, fin));
                        System.out.println("¡Horario modificado!");
                    }
                    case 4 -> {
                        List<Horario> lista = dao.obtenerTodos();
                        if (lista != null && !lista.isEmpty()) {
                            for (Horario h : lista) {
                                System.out.println("ID: " + h.getIdHorario() + " | " + h.toString());
                            }
                        } else {
                            System.out.println("No hay horarios registrados en el sistema.");
                        }
                    }
                    case 5 -> {
                        System.out.print("ID RECURSO: ");
                        int idRecurso = Integer.parseInt(sc.nextLine());
                        List<Horario> lista = dao.obtenerHorariosPorRecurso(idRecurso);
                        for (Horario h : lista) {
                            System.out.println("ID: " + h.getIdHorario() + " | " + h.toString());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}