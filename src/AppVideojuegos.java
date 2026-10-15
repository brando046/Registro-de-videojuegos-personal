
import javax.swing.SwingUtilities;

public class AppVideojuegos {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazVideojuegos ventana =
                    new InterfazVideojuegos();

            ventana.setVisible(true);
        });
    }
}
