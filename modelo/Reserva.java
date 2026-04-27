package modelo;

import java.sql.Date;


public class Reserva {
    private int idReserva;
    private Usuario usuario;
    private Recurso recurso;
    private Date fechaReserva;
    private String comentarios;

    public Reserva() {
    }

    public Reserva(Usuario usuario, Recurso recurso, Date fechaReserva, String comentarios) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = fechaReserva;
        this.comentarios = comentarios;
    }

    public Reserva(int idReserva, Usuario usuario, Recurso recurso, Date fechaReserva, String comentarios) {
        this.idReserva = idReserva;
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = fechaReserva;
        this.comentarios = comentarios;
    }

    public int getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


    @Override
    public String toString() {
        return "Reserva #" + idReserva + " [" + usuario.getNombre() + " -> " + recurso.getNombre() + " el " + fechaReserva + "]";
    }
}


