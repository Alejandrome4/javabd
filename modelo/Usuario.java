package modelo;

import java.sql.Date;


public abstract class Usuario {
    private String correoElectronico;
    private String contraseña;
    private String nombre;
    private Date fechaNacimiento;
    private String tipoUsuario;


    public Usuario() {
    }

    public Usuario(String correoElectronico, String contraseña, String nombre, Date fechaNacimiento, String tipoUsuario) {
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoUsuario = tipoUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return contraseña;
    }

    public void setPassword(String password) {
        this.contraseña = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-18s | %-8s |",
                correoElectronico, nombre, tipoUsuario);
    }
}



