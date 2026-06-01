package view;

import dao.ReservaDAO;
import modelo.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ReservaView {
    private ReservaDAO dao;
    private Scanner sc;

    public ReservaView() {
        this.dao = new ReservaDAO();
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        int opcion = -1;
        do {
            System.out.println("\n--- 4. GESTIÓN DE RESERVAS ---");
            System.out.println("1. Alta reserva");
            System.out.println("2. Baja reserva");
            System.out.println("3. Modificar motivo reserva");
            System.out.println("4. Listar todas las reservas");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Correo Usuario: ");
                        String correo = sc.nextLine();
                        System.out.print("ID RECURSO (1-5): ");
                        int idRec = Integer.parseInt(sc.nextLine());
                        System.out.print("ID RESERVA LOCAL (Ej: 1): ");
                        int idResLocal = Integer.parseInt(sc.nextLine());
                        System.out.print("Fecha (AAAA-MM-DD): ");
                        Date fecha = Date.valueOf(sc.nextLine());
                        System.out.print("Hora Inicio (HH:MM): ");
                        String inicio = sc.nextLine();
                        System.out.print("Hora Fin (HH:MM): ");
                        String fin = sc.nextLine();
                        System.out.print("Número de plazas: ");
                        int plazas = Integer.parseInt(sc.nextLine());
                        System.out.print("Motivo: ");
                        String motivo = sc.nextLine();
                        System.out.print("Observaciones: ");
                        String obs = sc.nextLine();

                        Usuario u = new UsuarioNormal();
                        u.setCorreoElectronico(correo);
                        Recurso r = new Recurso();
                        r.setIdRecurso(idRec);

                        Reserva res = new Reserva(idResLocal, u, r, fecha, inicio, fin, plazas, motivo, obs);
                        dao.insertar(res);
                        System.out.println("¡Reserva guardada con éxito!");
                    }
                    case 2 -> {
                        System.out.print("ID Reserva Local a eliminar: ");
                        int idRes = Integer.parseInt(sc.nextLine());
                        System.out.print("ID Recurso de esa reserva: ");
                        int idRec = Integer.parseInt(sc.nextLine());
                        dao.eliminar(idRes, idRec);
                        System.out.println("¡Reserva eliminada!");
                    }
                    case 3 -> {
                        System.out.print("ID Reserva Local a modificar: ");
                        int idRes = Integer.parseInt(sc.nextLine());
                        System.out.print("ID Recurso: ");
                        int idRec = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo Motivo: ");
                        String nuevoMotivo = sc.nextLine();
                        dao.modificarMotivo(idRes, idRec, nuevoMotivo);
                        System.out.println("¡Motivo actualizado!");
                    }
                    case 4 -> {
                        List<Reserva> lista = dao.obtenerTodas();
                        for (Reserva r : lista) {
                            System.out.println("ID Local: " + r.getIdReserva() + " | Recurso: " + r.getRecurso().getNombre() + " | Usuario: " + r.getUsuario().getCorreoElectronico() + " | Motivo: " + r.getMotivo());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}