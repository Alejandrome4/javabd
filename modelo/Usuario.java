package modelo;

import java.time.LocalDate;

public class Usuario {
    private String correo;
    private String contraseña;
    private String nombre;
    private LocalDate fechaNacimiento;

    public Usuario() {}

    public Usuario(String correo, String contraseña, String nombre, LocalDate fechaNacimiento) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre= nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo(){ return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasenia() { return contraseña; }
    public void setContraseña(String contrasenia){ this.contraseña = contrasenia; }
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}

