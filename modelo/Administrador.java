package modelo;

import java.sql.Date;

public class Administrador extends Usuario {
    public Administrador() {
        setTipoUsuario("ADMIN");
    }

    public Administrador(String correo, String contraseña, String nombre, Date fechaNacimiento) {
        super(correo, contraseña, nombre, fechaNacimiento, "ADMIN");
    }
}