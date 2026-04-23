package controller;

import dao.UsuarioDAO;
import modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO dao =new UsuarioDAO();

    public void insertar(Usuario u) {
        try {
            dao.insertar(u);
            System.out.println("Usuario guardado.");
        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
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