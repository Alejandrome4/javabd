package controller;

import dao.RecursoDAO;
import modelo.Recurso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecursoController {
    private RecursoDAO recursoDao = new RecursoDAO();

    public List<Recurso> obtener() {
        try { return recursoDao.listarTodos(); }
        catch (SQLException e) { System.out.println("Error al listar recursos"); return new ArrayList<>(); }
    }

    public Recurso obtenerPorId(int id) {
        try { return recursoDao.findByPk(id); }
        catch (SQLException e) { System.out.println("Error al obtener recurso"); return null; }
    }

    public boolean insertar(Recurso r) {
        try { recursoDao.insertar(r); return true; }
        catch (SQLException e) { System.out.println("Error al insertar"); return false; }
    }

    public boolean actualizar(Recurso r) {
        try {
            if (obtenerPorId(r.getIdRecurso()) != null) {
                recursoDao.actualizar(r); return true;
            }
            return false;
        } catch (SQLException e) { System.out.println("Error al actualizar"); return false; }
    }

    public boolean eliminar(int id) {
        try {
            if (obtenerPorId(id) != null) {
                recursoDao.eliminar(id); return true;
            }
            return false;
        } catch (SQLException e) { System.out.println("Error al eliminar"); return false; }
    }
}