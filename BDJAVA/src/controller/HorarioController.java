package controller;

import dao.HorarioDAO;
import modelo.Horario;
import java.sql.SQLException;
import java.sql.Time;
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
            System.out.println("Error al cargar los horarios: " + e.getMessage());
            return null;
        }
    }

    public void insertarHorario(String dia, String inicio, String fin) {
        try {
            Time timeInicio = Time.valueOf(inicio + ":00");
            Time timeFin = Time.valueOf(fin + ":00");

            Horario h = new Horario(0, dia, timeInicio, timeFin);
            dao.insertar(h);
            System.out.println("¡Horario insertado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al insertar el horario: " + e.getMessage());
        }
    }
}