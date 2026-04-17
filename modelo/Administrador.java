package modelo;

import java.time.LocalDate;

public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public Administrador(String correo, String contraseña, String nombre, LocalDate fechaNacimiento) {
        super(correo, contraseña, nombre, fechaNacimiento);
    }
}