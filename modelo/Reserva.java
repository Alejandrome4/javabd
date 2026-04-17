package modelo;

import java.time.LocalDate;

public class Reserva {
    private int idReserva;
    private String correoUsuario;
    private int idRecurso;
    private LocalDate fechaReserva;
    private String comentarios;

    public Reserva() {}

    public Reserva(int idReserva, String correoUsuario, int idRecurso, LocalDate fechaReserva, String comentarios) {
        this.idReserva = idReserva;
        this.correoUsuario = correoUsuario;
        this.idRecurso = idRecurso;
        this.fechaReserva = fechaReserva;
        this.comentarios = comentarios;
    }

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }
    public String getCorreoUsuario() { return correoUsuario; }
    public void setCorreoUsuario(String correoUsuario) { this.correoUsuario = correoUsuario; }
    public int getIdRecurso() { return idRecurso; }
    public void setIdRecurso(int idRecurso) { this.idRecurso = idRecurso; }
    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }
    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
}