package dao;

import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void insertar(Reserva r) throws SQLException {
        String sql = "INSERT INTO RESERVA (correo_usuario, id_recurso, fecha, hora_inicio, hora_fin, num_plazas, motivo, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, r.getUsuario().getCorreoElectronico());
            ps.setInt(2, r.getRecurso().getIdRecurso());
            ps.setDate(3, r.getFecha());
            ps.setString(4, r.getHoraInicio());
            ps.setString(5, r.getHoraFin());
            ps.setInt(6, r.getNumPlazas());
            ps.setString(7, r.getMotivo());
            ps.setString(8, r.getObservaciones());
            ps.executeUpdate();
        }
    }

    public List<Reserva> obtenerTodas() throws SQLException {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT res.*, u.nombre as nom_u, rec.nombre as nom_r FROM RESERVA res " +
                "JOIN USUARIO u ON res.correo_usuario = u.correo_electronico " +
                "JOIN RECURSO rec ON res.id_recurso = rec.id_recurso";

        try (Connection cnx = DBConnection.getConnection();
             Statement st= cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new UsuarioNormal(); u.setNombre(rs.getString("nom_u"));
                u.setCorreoElectronico(rs.getString("correo_usuario"));

                Recurso rec = new Recurso(); rec.setNombre(rs.getString("nom_r"));
                rec.setIdRecurso(rs.getInt("id_recurso"));

                lista.add(new Reserva(
                        rs.getInt("id_reserva"), u, rec, rs.getDate("fecha"),
                        rs.getString("hora_inicio"), rs.getString("hora_fin"),
                        rs.getInt("num_plazas"), rs.getString("motivo"), rs.getString("observaciones")
                ));
            }
        }
        return lista;
    }
}

