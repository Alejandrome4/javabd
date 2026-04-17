import java.sql.*;
import java.util.Scanner;

public class GestionRecursos {
    static Connection cnx;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/sistema_reservas";
        String user = "admin";
        String password = "admin";

        try {
            cnx = DriverManager.getConnection(url, user, password);
            int opcion;
            do {
                menu();
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1 -> altaRecurso();
                    case 2 -> bajaRecurso();
                    case 3 -> modificarRecurso();
                    case 4 -> listarRecursos();
                    case 5 -> buscarPorNombre();
                }
            } while (opcion != 0);
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void menu() {
        System.out.println("\n--- GESTIÓN DE RECURSOS ---");
        System.out.println("1 - Alta de un recurso");
        System.out.println("2 - Baja de un recurso");
        System.out.println("3 - Modificar recurso");
        System.out.println("4 - Listar todos los recursos");
        System.out.println("5 - Buscar por nombre");
        System.out.println("0 - Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void altaRecurso() throws SQLException {
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Descripción: "); String desc = sc.nextLine();
        System.out.print("Ubicación: "); String ubi = sc.nextLine();
        System.out.print("Capacidad: "); int cap = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO RECURSO (nombre, descripcion, ubicacion, capacidad) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setString(2, desc);
        ps.setString(3, ubi);
        ps.setInt(4, cap);
        ps.executeUpdate();
        System.out.println("Recurso dado de alta correctamente.");
    }

    private static void bajaRecurso() throws SQLException {
        System.out.print("ID del recurso a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        PreparedStatement ps = cnx.prepareStatement("DELETE FROM RECURSO WHERE id_recurso = ?");
        ps.setInt(1, id);
        int filas = ps.executeUpdate();
        System.out.println(filas > 0 ? "Recurso eliminado." : "No se encontró el ID.");
    }

    private static void modificarRecurso() throws SQLException {
        System.out.print("ID del recurso a modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nuevo nombre: "); String nom = sc.nextLine();
        System.out.print("Nueva capacidad: "); int cap = Integer.parseInt(sc.nextLine());

        PreparedStatement ps = cnx.prepareStatement("UPDATE RECURSO SET nombre = ?, capacidad = ? WHERE id_recurso = ?");
        ps.setString(1, nom);
        ps.setInt(2, cap);
        ps.setInt(3, id);
        ps.executeUpdate();
        System.out.println("Recurso actualizado.");
    }

    private static void listarRecursos() throws SQLException {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM RECURSO");
        System.out.println("\nID | NOMBRE | UBICACIÓN | CAPACIDAD");
        while (rs.next()) {
            System.out.printf("%d | %s | %s | %d\n",
                    rs.getInt("id_recurso"), rs.getString("nombre"), rs.getString("ubicacion"), rs.getInt("capacidad"));
        }
    }

    private static void buscarPorNombre() throws SQLException {
        System.out.print("Nombre a buscar: ");
        String busqueda = sc.nextLine();
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM RECURSO WHERE nombre LIKE ?");
        ps.setString(1, "%" + busqueda + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("Encontrado: " + rs.getString("nombre") + " en " + rs.getString("ubicacion"));
        }
    }
}
