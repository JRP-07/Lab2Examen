package examen;

import examen.biblioteca.gui.Controlador;
import examen.biblioteca.gui.DatosPrueba;
import examen.biblioteca.gui.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Examen {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Controlador controlador = new Controlador(biblioteca);
        DatosPrueba.cargar(controlador);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal(controlador);
                ventana.setVisible(true);
            }
        });
    }

}
