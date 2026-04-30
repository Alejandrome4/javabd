package test;

import dao.ReservaDAO;
import dao.UsuarioDAO;
import dao.RecursoDAO;
import modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaDAOTest {

    private ReservaDAO resDao;
    private UsuarioDAO usuDao;
    private RecursoDAO recDao;

    @BeforeEach
    void setUp() {
        resDao = new ReservaDAO();
        usuDao = new UsuarioDAO();
        recDao = new RecursoDAO();
    }

    @Test
    void testInsertarYListarReserva() throws SQLException {
        List<Usuario> usuarios = usuDao.obtenerTodos();
        List<Recurso> recursos = recDao.listarTodos();

        assertFalse(usuarios.isEmpty(), "Debe haber al menos un usuario en la BD para el test");
        assertFalse(recursos.isEmpty(), "Debe haber al menos un recurso en la BD para el test");

        Usuario u = usuarios.get(0);
        Recurso r = recursos.get(0);

        Reserva nuevaReserva = new Reserva(
                u,
                r,
                Date.valueOf("2026-05-30"),
                "8:00",
                "14:00",
                5,
                "Practicas",
                "Quiero hacerlas"
        );

        assertDoesNotThrow(() -> resDao.insertar(nuevaReserva));
        List<Reserva> lista = resDao.obtenerTodas();
        assertFalse(lista.isEmpty(), "La lista de reservas no deberia estar vacía");

        Reserva guardada = lista.get(lista.size() - 1);
        assertEquals(u.getCorreoElectronico(), guardada.getUsuario().getCorreoElectronico());
        assertEquals(r.getIdRecurso(), guardada.getRecurso().getIdRecurso());

        System.out.println(" Test de reserva ha sido superado con éxito.");
    }
}