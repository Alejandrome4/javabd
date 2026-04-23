package testDAO;

import dao.RecursoDAO;
import modelo.Recurso;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class RecursoDAOTest {
    private static RecursoDAO dao;

    @BeforeAll
    static void setup() {
        dao = new RecursoDAO();
    }

    @Test
    void testInsertar() throws SQLException {
        Recurso r = new Recurso("Aula 111", "Informatica", "Planta 1", 25);
        int id = dao.insertar(r);

        Recurso existe = dao.findByPk(id);
        assertNotNull(existe);
        assertEquals("Aula 101", existe.getNombre());

        dao.eliminar(id);
    }

    @Test
    void testActualizar() throws SQLException {
        Recurso r = new Recurso("Original", "Desc", "Ubi", 10);
        int id = dao.insertar(r);

        Recurso modificado = new Recurso(id, "Modificado", "Nueva", "Nueva", 50);
        dao.actualizar(modificado);

        Recurso encontrada = dao.findByPk(id);
        assertNotNull(encontrada);
        assertEquals("Modificado", encontrada.getNombre());
        assertEquals(50, encontrada.getCapacidad());

        dao.eliminar(id);
    }

    @Test
    void testEliminar() throws SQLException {
        Recurso r = new Recurso("Borrar", "D", "U", 1);
        int id = dao.insertar(r);

        dao.eliminar(id);

        Recurso encontrada = dao.findByPk(id);
        assertNull(encontrada);
    }
}