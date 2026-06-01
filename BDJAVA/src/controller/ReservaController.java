package controller;

import dao.ReservaDAO;
import modelo.Reserva;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaController {
    private ReservaDAO dao = new ReservaDAO();

    public boolean guardar(Reserva r){
        try{
            dao.insertar(r);
            return true;
        } catch (SQLException e) {
            System.out.println("Error:  " +e.getMessage());
            return false;
        }
    }

    public List<Reserva> listar() {
        try{
            return dao.obtenerTodas();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}