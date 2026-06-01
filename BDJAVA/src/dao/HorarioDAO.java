package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Horario;

public class HorarioDAO {

    public List<Horario> obtenerTodos() throws SQLException {
        List<Horario> lista = new ArrayList<>();
        String sql = "SELECT * FROM HORARIO";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Horario(
                        rs.getInt("id_horario"),
                        rs.getString("dia_semana"),
                        rs.getTime("hora_inicio"),
                        rs.getTime("hora_fin")
                ));
            }
        }
        return lista;
    }

    public List<Horario> obtenerHorariosPorRecurso(int idRecurso) throws SQLException {
        List<Horario> lista = new ArrayList<>();
        String sql = "SELECT h.* FROM HORARIO h " +
                "JOIN DISPONIBLEEN d ON h.id_horario = d.id_horario " +
                "WHERE d.id_recurso = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idRecurso);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Horario(
                            rs.getInt("id_horario"),
                            rs.getString("dia_semana"),
                            rs.getTime("hora_inicio"),
                            rs.getTime("hora_fin")
                    ));
                }
            }
        }
        return lista;
    }

    public void insertar(Horario h) throws SQLException {
        String sql = "INSERT INTO HORARIO (dia_semana, hora_inicio, hora_fin) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, h.getDiaSemana());
            ps.setTime(2, h.getHoraInicio());
            ps.setTime(3, h.getHoraFin());
            ps.executeUpdate();
        }
    }

    public void modificar(Horario h) throws SQLException {
        String sql = "UPDATE HORARIO SET dia_semana = ?, hora_inicio = ?, hora_fin = ? WHERE id_horario = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, h.getDiaSemana());
            ps.setTime(2, h.getHoraInicio());
            ps.setTime(3, h.getHoraFin());
            ps.setInt(4, h.getIdHorario());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idHorario) throws SQLException {
        String sql = "DELETE FROM HORARIO WHERE id_horario = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHorario);
            ps.executeUpdate();
        }
    }
}