package modelo;

import java.sql.Time;

public class Horario {

    private int idHorario;
    private String diaSemana;
    private Time horaInicio;
    private Time horaFin;

    public Horario(int idHorario, String diaSemana, Time horaInicio, Time horaFin) {
        this.idHorario = idHorario;
        this.diaSemana = diaSemana;
        this.horaInicio =horaInicio;
        this.horaFin = horaFin;

    }

    public int getIdHorario() {
        return idHorario;
    }
    public String getDiaSemana() {
        return diaSemana;
    }
    public Time getHoraInicio() {
        return horaInicio;
    }
    public Time getHoraFin() {
        return horaFin;

    }

    @Override
    public String toString() {
        return diaSemana + " | "+ horaInicio + " - " + horaFin;
    }
}