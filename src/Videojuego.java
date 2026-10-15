
import java.io.Serializable;

public class Videojuego implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String plataforma;
    private String genero;
    private double horas;

    public Videojuego(String nombre, String plataforma,
                      String genero, double horas) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.genero = genero;
        this.horas = horas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public double getHoras() {
        return horas;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + " | Plataforma: " + plataforma
                + " | Género: " + genero
                + " | Horas jugadas: " + horas;
    }
}
