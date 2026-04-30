package view;

import controller.HorarioController;
import modelo.Horario;
import java.util.List;

public class HorarioView {
    private HorarioController controller = new HorarioController();

    public void mostrarHorariosDeRecurso(int idRecurso) {
        List<Horario> lista = controller.listarPorRecurso(idRecurso);

        if (lista == null || lista.isEmpty()) {
            System.out.println("  (sin un horario asignado)");
        } else {
            System.out.println("    HORARIOS DISPONIBLES:");
            for (Horario h : lista) {
                System.out.println("    - " + h.toString());
            }
        }
    }
}