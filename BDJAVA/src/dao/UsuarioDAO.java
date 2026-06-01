package dao;

import modelo.Administrador;
import modelo.Usuario;
import modelo.UsuarioNormal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO USUARIO (correo_electronico, contraseña, nombre, fecha_nacimiento, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, u.getCorreoElectronico());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getNombre());
            ps.setDate(4, u.getFechaNacimiento());

            String tipoEntrada = (u.getTipoUsuario() != null) ? u.getTipoUsuario().toUpperCase() : "NORMAL";
            String tipoCorrectoBD;

            if (tipoEntrada.contains("ADMIN")) {
                tipoCorrectoBD = "Administrador";
            } else {
                tipoCorrectoBD = "Normal";
            }

            ps.setString(5, tipoCorrectoBD);
            ps.executeUpdate();
        }
    }

    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }
        return lista;
    }

    public Usuario findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE correo_electronico = ?";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo_usuario");
        Usuario u;

        if (tipo != null && tipo.toUpperCase().contains("ADMIN")) {
            u = new Administrador();
        } else {
            u = new UsuarioNormal();
        }

        u.setCorreoElectronico(rs.getString("correo_electronico"));
        u.setPassword(rs.getString("contraseña"));
        u.setNombre(rs.getString("nombre"));
        u.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        u.setTipoUsuario(tipo);

        return u;
    }

    public void eliminar(String email) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE correo_electronico = ?";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }
}