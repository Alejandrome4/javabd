package dao;
import modelo.Usuario;
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
            ps.setString(5, u.getTipoUsuario());
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
                lista.add(new Usuario(
                        rs.getString("correo_electronico"),
                        rs.getString("contraseña"),
                        rs.getString("nombre"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("tipo_usuario")
                ));
            }
        }
        return lista;
    }

    public Usuario findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE correo_electronico = ?";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new Usuario(
                            rs.getString("correo_electronico"),
                            rs.getString("contraseña"),
                            rs.getString("nombre"),
                            rs.getDate("fecha_nacimiento"),
                            rs.getString("tipo_usuario")
                    );
                }
            }
        }
        return null;
    }

    public void eliminar(String email) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE correo_electronico = ?";
        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps= cnx.prepareStatement(sql)){
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }
}
