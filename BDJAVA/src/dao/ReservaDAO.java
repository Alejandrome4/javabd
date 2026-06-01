package dao;

import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void insertar(Reserva r) throws SQLException {
        String sql = "INSERT INTO RESERVA (id_recurso, id_reserva_local, id_usuario, fecha, hora_inicio, hora_fin, coste, numero_plazas, motivo, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(1, r.getRecurso().getIdRecurso());
            ps.setInt(2, r.getIdReserva());

            int idUsuarioBD = 2;

            String sqlBuscaId = "SELECT u.id_usuario FROM USUARIO u JOIN USUARIONORMAL un ON u.id_usuario = un.id_usuario WHERE u.correo_electronico = ?";
            try (PreparedStatement psId = cnx.prepareStatement(sqlBuscaId)) {
                psId.setString(1, r.getUsuario().getCorreoElectronico());
                try (ResultSet rsId = psId.executeQuery()) {
                    if (rsId.next()) {
                        idUsuarioBD = rsId.getInt("id_usuario");
                    }
                }
            }

            if (idUsuarioBD == -1) {
                String sqlPrimerUsuario = "SELECT id_usuario FROM USUARIONORMAL LIMIT 1";
                try (Statement stId = cnx.createStatement();
                     ResultSet rsId = stId.executeQuery(sqlPrimerUsuario)) {
                    if (rsId.next()) {
                        idUsuarioBD = rsId.getInt("id_usuario");
                    } else {
                        idUsuarioBD = 2;
                    }
                }
            }

            ps.setInt(3, idUsuarioBD);

            ps.setDate(4, r.getFecha());
            ps.setString(5, r.getHoraInicio());
            ps.setString(6, r.getHoraFin());
            ps.setBigDecimal(7, java.math.BigDecimal.ZERO);
            ps.setInt(8, r.getNumPlazas());
            ps.setString(9, r.getMotivo());
            ps.setString(10, r.getObservaciones());

            ps.executeUpdate();
        }
    }

    public List<Reserva> obtenerTodas() throws SQLException {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT res.*, u.nombre as nom_u, u.correo_electronico as correo_u, rec.nombre as nom_r FROM RESERVA res " +
                "JOIN USUARIO u ON res.id_usuario = u.id_usuario " +
                "JOIN RECURSO rec ON res.id_recurso = rec.id_recurso";

        try (Connection cnx = DBConnection.getConnection();
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new UsuarioNormal();
                u.setNombre(rs.getString("nom_u"));
                u.setCorreoElectronico(rs.getString("correo_u"));

                Recurso rec = new Recurso();
                rec.setNombre(rs.getString("nom_r"));
                rec.setIdRecurso(rs.getInt("id_recurso"));

                String hInicio = rs.getTime("hora_inicio") != null ? rs.getTime("hora_inicio").toString() : "";
                String hFin = rs.getTime("hora_fin") != null ? rs.getTime("hora_fin").toString() : "";

                lista.add(new Reserva(
                        rs.getInt("id_reserva_local"),
                        u,
                        rec,
                        rs.getDate("fecha"),
                        hInicio,
                        hFin,
                        rs.getInt("numero_plazas"),
                        rs.getString("motivo"),
                        rs.getString("observaciones")
                ));
            }
        }
        return lista;
    }

    public void eliminar(int idReservaLocal, int idRecurso) throws SQLException {
        String sql = "DELETE FROM RESERVA WHERE id_reserva_local = ? AND id_recurso = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReservaLocal);
            ps.setInt(2, idRecurso);
            ps.executeUpdate();
        }
    }

    public void modificarMotivo(int idReservaLocal, int idRecurso, String nuevoMotivo) throws SQLException {
        String sql = "UPDATE RESERVA SET motivo = ? WHERE id_reserva_local = ? AND id_recurso = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoMotivo);
            ps.setInt(2, idReservaLocal);
            ps.setInt(3, idRecurso);
            ps.executeUpdate();
        }
    }
}