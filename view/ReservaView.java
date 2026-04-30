package view;

import controller.ReservaController;
import controller.RecursoController;
import modelo.*;
import java.util.Scanner;
import java.sql.Time;
import java.sql.Date;

public class ReservaView {

    private ReservaController controller = new ReservaController();
    private Scanner sn = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n ----- GESTION RESERVAS-----");
            System.out.println("1. Ver todas las reservas");
            System.out.println("2. Crear nueva reserva");
            System.out.println("0. Volver");
            System.out.print("-- OPCIONES: ");
            try {
                opcion = Integer.parseInt(sn.nextLine());
                if (opcion == 1) listar();
                else if (opcion == 2) insertar();
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
                opcion = -1;
            }
        } while (opcion != 0);
    }

    private void listar() {
        System.out.println("\n=== LISTADO DE RESERVAS ===");
        controller.listar().forEach(System.out::println);
    }

    private void insertar() {
        RecursoController recursoCtrl = new RecursoController();
        // ... (parte de mostrar recursos igual)

        try {
            System.out.print("\nCorreo Usuario: ");
            String correo = sn.nextLine();

            System.out.print("ID RECURSO: ");
            int idRecurso = Integer.parseInt(sn.nextLine());

            System.out.print("Fecha (AAAA-MM-DD): ");
            String fechaStr = sn.nextLine();

            System.out.print("Hora Inicio (HH:MM): ");
            String hInicio = sn.nextLine(); // Lo dejamos como String[cite: 15]

            System.out.print("Hora Fin (HH:MM): ");
            String hFin = sn.nextLine(); // Lo dejamos como String[cite: 15]

            System.out.print("Número de plazas: ");
            int plazas = Integer.parseInt(sn.nextLine());

            System.out.print("Motivo: ");
            String motivo = sn.nextLine();

            // CORRECCIÓN 1: Instanciar clase concreta[cite: 3]
            Usuario u = new UsuarioNormal();
            u.setCorreoElectronico(correo);

            // CORRECCIÓN 2: Instanciar recurso[cite: 4]
            Recurso rec = new Recurso();
            rec.setIdRecurso(idRecurso);

            // CORRECCIÓN 3: Pasar hInicio y hFin como String (sin Time.valueOf)
            Reserva r = new Reserva(0, u, rec, Date.valueOf(fechaStr), hInicio, hFin, plazas, motivo, "");

            if (controller.guardar(r)) {
                System.out.println(" Reserva guardada correctamente.");
            } else {
                System.out.println(" No se pudo guardar la reserva.");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n ERROR: ID o plazas deben ser números.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n ERROR: Formato de fecha incorrecto (AAAA-MM-DD).");
        }
    }
}