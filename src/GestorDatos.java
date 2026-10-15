
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GestorDatos {

    private ArrayList<Videojuego> videojuegos;
    private final String nombreArchivo = "videojuegos.dat";

    public GestorDatos() {
        videojuegos = new ArrayList<>();
        cargarDatos();
    }

    // Añadir un videojuego y guardar los cambios
    public void anadirVideojuego(Videojuego videojuego)
            throws IOException {
        videojuegos.add(videojuego);
        guardarDatos();
    }

    // Obtener todos los videojuegos
    public ArrayList<Videojuego> obtenerVideojuegos() {
        return new ArrayList<>(videojuegos);
    }

    // Calcular las horas totales por género
    public Map<String, Double> obtenerHorasPorGenero() {
        Map<String, Double> resumen = new LinkedHashMap<>();

        for (Videojuego videojuego : videojuegos) {
            String genero = videojuego.getGenero();
            double horas = videojuego.getHoras();

            resumen.put(
                    genero,
                    resumen.getOrDefault(genero, 0.0) + horas
            );
        }

        return resumen;
    }

    // Guardar la colección en un archivo binario
    public void guardarDatos() throws IOException {
        try (ObjectOutputStream salida =
                     new ObjectOutputStream(
                             new FileOutputStream(nombreArchivo))) {

            salida.writeObject(videojuegos);
        }
    }

    // Cargar los videojuegos al iniciar la aplicación
    public void cargarDatos() {
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            videojuegos = new ArrayList<>();
            return;
        }

        try (ObjectInputStream entrada =
                     new ObjectInputStream(
                             new FileInputStream(archivo))) {

            Object objeto = entrada.readObject();

            if (objeto instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) objeto;
                ArrayList<Videojuego> cargados = new ArrayList<>();

                for (Object elemento : lista) {
                    if (!(elemento instanceof Videojuego)) {
                        throw new IOException(
                                "El archivo contiene datos incompatibles."
                        );
                    }

                    cargados.add((Videojuego) elemento);
                }

                videojuegos = cargados;
            } else {
                throw new IOException(
                        "El archivo no contiene una colección válida."
                );
            }

        } catch (IOException | ClassNotFoundException e) {
            videojuegos = new ArrayList<>();

            System.err.println(
                    "Error al cargar los videojuegos: " + e.getMessage()
            );
        }
    }
}
