package testDAO;

import dao.UsuarioDAO;
import modelo.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioDAOTest {
    private static UsuarioDAO dao;

    @BeforeAll
    static void setup() {
        dao = new UsuarioDAO();
    }

    @Test
    void testInsertarYBuscar() throws SQLException {
        String email = "test@prueba.com";
        Usuario u = new Usuario(email, "6767", "Testear", Date.valueOf("2007-08-03"), "NORMAL");

        dao.insertar(u);

        Usuario encontrado = dao.findByEmail(email);
        assertNotNull(encontrado);
        assertEquals("Testear", encontrado.getNombre());

        dao.eliminar(email);
    }

    @Test
    void testEliminar() throws SQLException {
        String email = "borrar@prueba.com";
        Usuario u = new Usuario(email, "1234", "Borrar", Date.valueOf("2000-01-01"), "NORMAL");

        dao.insertar(u);
        dao.eliminar(email);

        Usuario encontrado = dao.findByEmail(email);
        assertNull(encontrado);
    }

    @Test
    void testListar() throws SQLException {
        assertNotNull(dao.obtenerTodos());
    }
}