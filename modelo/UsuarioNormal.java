package modelo;

import java.time.LocalDate;

public class UsuarioNormal extends Usuario {
    private String direccion;
    private String fotografia;

    public UsuarioNormal() {
        super();
    }

    public UsuarioNormal(String correo, String contraseña, String nombre, LocalDate fechaNacimiento, String direccion, String fotografia) {
        super(correo, contraseña, nombre, fechaNacimiento);
        this.direccion = direccion;
        this.fotografia = fotografia;
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getFotografia() { return fotografia; }
    public void setFotografia(String fotografia) { this.fotografia = fotografia; }
}