package controller;

import dao.HorarioDAO;
import modelo.Horario;
import java.sql.SQLException;
import java.util.List;

public class HorarioController {
    private HorarioDAO dao;

    public HorarioController() {
        this.dao = new HorarioDAO();
    }

    public List<Horario> listarPorRecurso(int idRecurso) {
        try {
            return dao.obtenerHorariosPorRecurso(idRecurso);
        } catch (SQLException e) {
            System.out.println("error al cargar los horarios: " + e.getMessage());
            return null;
        }
    }
}