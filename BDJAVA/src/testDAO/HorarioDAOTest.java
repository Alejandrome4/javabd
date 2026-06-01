package testDAO;

import dao.HorarioDAO;
import modelo.Horario;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class HorarioDAOTest {
    public static void main(String[] args) {
        HorarioDAO horarioDAO = new HorarioDAO();

        System.out.println("=== INICIANDO PRUEBAS DE HORARIODAO ===");

        try {
            System.out.println("\n1. Probando inserción de nuevo horario...");
            Time inicio = Time.valueOf("16:00:00");
            Time fin = Time.valueOf("18:00:00");
            Horario nuevo = new Horario(0, "Viernes", inicio, fin);

            horarioDAO.insertar(nuevo);
            System.out.println("-> ¡Horario de prueba insertado correctamente en MySQL!");
        } catch (SQLException e) {
            System.out.println("-> Error al insertar horario: " + e.getMessage());
        }

        try {
            System.out.println("\n2. Probando obtención de horarios para el Recurso ID: 1...");
            List<Horario> lista = horarioDAO.obtenerHorariosPorRecurso(1);

            if (lista != null && !lista.isEmpty()) {
                System.out.println("-> Horarios asociados encontrados en la base de datos:");
                for (Horario h : lista) {
                    System.out.println("   [ID: " + h.getIdHorario() + "] " + h.toString());
                }
            } else {
                System.out.println("-> No hay horarios vinculados al recurso 1 (Recuerda que debe existir en la tabla intermedia DISPONIBLEEN).");
            }
        } catch (SQLException e) {
            System.out.println("-> Error al consultar la base de datos: " + e.getMessage());
        }

        System.out.println("\n=== FIN DE LAS PRUEBAS UNITARIAS ===");
    }
}