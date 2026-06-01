package controller;

import dao.UsuarioDAO;
import modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO dao = new UsuarioDAO();

    public boolean insertar(Usuario u) {
        try {
            dao.insertar(u);
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Controller: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> listar() {
        try {
            return dao.obtenerTodos();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}