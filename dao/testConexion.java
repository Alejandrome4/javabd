package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class testConexion {
    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("conexión OK");
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
        }
    }
}