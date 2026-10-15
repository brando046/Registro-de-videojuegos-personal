
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class InterfazVideojuegos extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPlataforma;
    private JTextField txtHoras;

    private JComboBox<String> comboGenero;
    private JTextArea areaVideojuegos;

    private GestorDatos gestor;

    public InterfazVideojuegos() {
        gestor = new GestorDatos();

        setTitle("Registro de Videojuegos Personales");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
        actualizarLista();
    }

    private void crearInterfaz() {
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada
        JPanel panelFormulario = new JPanel(
                new GridLayout(5, 2, 8, 8)
        );

        panelFormulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Datos del videojuego"
                )
        );

        txtNombre = new JTextField();
        txtPlataforma = new JTextField();
        txtHoras = new JTextField();

        String[] generos = {
                "Acción",
                "RPG",
                "Deportes",
                "Aventura",
                "Otros"
        };

        comboGenero = new JComboBox<>(generos);

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Plataforma:"));
        panelFormulario.add(txtPlataforma);

        panelFormulario.add(new JLabel("Género:"));
        panelFormulario.add(comboGenero);

        panelFormulario.add(new JLabel("Horas jugadas:"));
        panelFormulario.add(txtHoras);

        JButton btnAnadir = new JButton("Añadir videojuego");
        JButton btnResumen = new JButton("Resumen por género");

        panelFormulario.add(btnAnadir);
        panelFormulario.add(btnResumen);

        // Área de visualización
        areaVideojuegos = new JTextArea();
        areaVideojuegos.setEditable(false);
        areaVideojuegos.setFont(
                new Font(Font.MONOSPACED, Font.PLAIN, 13)
        );

        JScrollPane scroll = new JScrollPane(areaVideojuegos);
        scroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Videojuegos registrados"
                )
        );

        add(panelFormulario, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Eventos
        btnAnadir.addActionListener(e -> anadirVideojuego());
        btnResumen.addActionListener(e -> mostrarResumen());
    }

    // Añadir un videojuego
    private void anadirVideojuego() {
        String nombre = txtNombre.getText().trim();
        String plataforma = txtPlataforma.getText().trim();
        String genero = (String) comboGenero.getSelectedItem();
        String textoHoras = txtHoras.getText().trim();

        if (nombre.isEmpty() || plataforma.isEmpty()
                || textoHoras.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debes rellenar todos los campos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double horas;

        try {
            // Permite introducir decimales con coma o con punto
            horas = Double.parseDouble(
                    textoHoras.replace(',', '.')
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Las horas deben ser un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!Double.isFinite(horas) || horas < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Las horas no pueden ser negativas "
                            + "ni un valor no válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Videojuego videojuego = new Videojuego(
                nombre, plataforma, genero, horas
        );

        try {
            gestor.anadirVideojuego(videojuego);

            actualizarLista();
            limpiarCampos();

            JOptionPane.showMessageDialog(
                    this,
                    "Videojuego guardado correctamente."
            );

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudieron guardar los datos: "
                            + e.getMessage(),
                    "Error de archivo",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Mostrar todos los videojuegos
    private void actualizarLista() {
        StringBuilder texto = new StringBuilder();

        for (Videojuego videojuego : gestor.obtenerVideojuegos()) {
            texto.append(videojuego).append("\n");
        }

        if (texto.length() == 0) {
            texto.append("No hay videojuegos registrados.");
        }

        areaVideojuegos.setText(texto.toString());
        areaVideojuegos.setCaretPosition(0);
    }

    // Mostrar las horas acumuladas por género
    private void mostrarResumen() {
        Map<String, Double> resumen =
                gestor.obtenerHorasPorGenero();

        if (resumen.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay videojuegos para calcular el resumen."
            );
            return;
        }

        StringBuilder texto = new StringBuilder();

        for (Map.Entry<String, Double> entrada
                : resumen.entrySet()) {

            texto.append(entrada.getKey())
                    .append(" -> ")
                    .append(entrada.getValue())
                    .append(" horas\n");
        }

        JOptionPane.showMessageDialog(
                this,
                texto.toString(),
                "Resumen de horas por género",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPlataforma.setText("");
        txtHoras.setText("");
        comboGenero.setSelectedIndex(0);
        txtNombre.requestFocus();
    }
}
