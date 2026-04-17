import java.time.LocalDate;

public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private LocalDate fechaDenacimiento;

    public Persona() {}

    public Persona(int id, String nombre, String apellido, LocalDate fechaDenacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDenacimiento = fechaDenacimiento;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public LocalDate getFechaDenacimiento() { return fechaDenacimiento; }
    public void setFechaDenacimiento(LocalDate fechaDenacimiento) { this.fechaDenacimiento = fechaDenacimiento; }
}