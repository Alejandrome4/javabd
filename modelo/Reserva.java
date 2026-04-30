package modelo;

import java.sql.Date;

public class Reserva {
    private int idReserva;
    private Usuario usuario;
    private Recurso recurso;
    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private int numPlazas;
    private String motivo;
    private String observaciones;

    public Reserva(Usuario usuario, Recurso recurso, Date fecha, String horaInicio,
                   String horaFin, int numPlazas, String motivo, String observaciones) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.numPlazas = numPlazas;
        this.motivo = motivo;
        this.observaciones = observaciones;
    }


    public Reserva(int idReserva, Usuario usuario, Recurso recurso, Date fecha, String horaInicio,
                   String horaFin, int numPlazas, String motivo, String observaciones) {
        this(usuario, recurso, fecha, horaInicio, horaFin, numPlazas, motivo, observaciones);
        this.idReserva = idReserva;
    }


    public int getIdReserva() {
        return idReserva;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Recurso getRecurso() {
        return recurso;
    }
    public Date getFecha() {
        return fecha;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public String getHoraFin() {
        return horaFin;
    }
    public int getNumPlazas() {
        return numPlazas;
    }
    public String getMotivo()
    { return motivo;
    }
    public String getObservaciones() {
        return observaciones;
    }

    @Override
    public String toString() {
        return String.format("| %-3d | %-15s | %-15s | %-10s | %-5s-%-5s | %-3d |",
                idReserva, usuario.getNombre(), recurso.getNombre(), fecha, horaInicio, horaFin, numPlazas);
    }
}

