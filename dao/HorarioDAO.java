package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Horario;

public class HorarioDAO {

    public List<Horario> obtenerHorariosPorRecurso(int idRecurso) throws SQLException {
        List<Horario> lista = new ArrayList<>();
        String sql = "SELECT h.* FROM HORARIO h " +
                "JOIN DISPONIBLEEN d ON h.id_horario = d.id_horario " +
                "WHERE d.id_recurso = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idRecurso);
            ResultSet rs = ps.executeQuery();

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
}