package modelo;

import java.sql.Date;

public class UsuarioNormal extends Usuario {
    public UsuarioNormal() {
        setTipoUsuario("NORMAL");
    }

    public UsuarioNormal(String correo, String contraseña, String nombre, Date fechaNacimiento) {
        super(correo, contraseña, nombre, fechaNacimiento, "NORMAL");
    }
}