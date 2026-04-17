import java.sql.*;

public class ConsultaReservas {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/sistema_reservas";
        String user = "admin";
        String password = "admin";

        try (Connection cnx = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa a sistema_reservas\n");
            System.out.println("--- CONSULTA 1: LISTADO DE RECURSOS ---");
            String sql1 = "SELECT nombre, ubicacion, capacidad FROM RECURSO";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql1)) {
                while (rs.next()) {
                    System.out.printf("Recurso: %s | Ubicación: %s | Capacidad: %d personas\n",
                            rs.getString("nombre"), rs.getString("ubicacion"), rs.getInt("capacidad"));
                }
            }

            System.out.println("\n---------------------------------------\n");

            System.out.println("--- CONSULTA 2: USUARIOS REGISTRADOS ---");
            String sql2 = "SELECT nombre, correo_electronico, tipo_usuario FROM USUARIO";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql2)) {
                while (rs.next()) {
                    System.out.printf("Nombre: %s | Email: %s | Perfil: %s\n",
                            rs.getString("nombre"), rs.getString("correo_electronico"), rs.getString("tipo_usuario"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión o SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
