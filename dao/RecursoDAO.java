package dao;

import modelo.Recurso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecursoDAO {

    public List<Recurso> listarTodos() throws SQLException {
        List<Recurso> lista = new ArrayList<>();
        String sql = "SELECT * FROM RECURSO";

        Connection cnx = DBConnection.getConnection();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id_recurso");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String ubicacion = rs.getString("ubicacion");
            int capacidad = rs.getInt("capacidad");

            Recurso r = new Recurso(id, nombre, descripcion, ubicacion, capacidad);
            lista.add(r);
        }
        rs.close();
        stm.close();
        cnx.close();
        return lista;
    }

    public int insertar(Recurso r) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "INSERT INTO RECURSO (nombre, descripcion, ubicacion, capacidad) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, r.getNombre());
        ps.setString(2, r.getDescripcion());
        ps.setString(3, r.getUbicacion());
        ps.setInt(4, r.getCapacidad());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int idGenerado = -1;
        if (rs.next()) {
            idGenerado = rs.getInt(1);
        }

        ps.close();
        cnx.close();
        return idGenerado;
    }

    public void eliminar(int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "DELETE FROM RECURSO WHERE id_recurso = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        cnx.close();
    }

    public Recurso findByPk(int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM RECURSO WHERE id_recurso = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Recurso r = null;
        if (rs.next()) {
            r = new Recurso(rs.getInt("id_recurso"), rs.getString("nombre"),
                    rs.getString("descripcion"), rs.getString("ubicacion"),
                    rs.getInt("capacidad"));
        }
        ps.close();
        cnx.close();
        return r;
    }

    public void actualizar(Recurso r) throws SQLException {
        String sql = "UPDATE RECURSO SET nombre=?, descripcion=?, ubicacion=?, capacidad=? WHERE id_recurso=?";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getDescripcion());
            ps.setString(3, r.getUbicacion());
            ps.setInt(4, r.getCapacidad());
            ps.setInt(5, r.getIdRecurso());
            ps.executeUpdate();

            ps.close();
            cnx.close();
        }
    }
}
