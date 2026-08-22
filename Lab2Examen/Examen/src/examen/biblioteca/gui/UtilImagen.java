/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.biblioteca.gui;

/**
 *
 * @author gabri
 */

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

public class UtilImagen {
    private static final String RUTA_SIN_PORTADA = "recursos/sin_portada.jpg";

    public static ImageIcon cargar(String ruta, int ancho, int alto) {
        ImageIcon icono = null;

        if (ruta != null && !ruta.trim().isEmpty()) {
            File archivo = new File(ruta);
            if (archivo.exists() && archivo.isFile()) {
                ImageIcon prueba = new ImageIcon(ruta);
                if (prueba.getIconWidth() > 0) {
                    icono = prueba;
                }
            }
        }

        if (icono == null) {
            File generica = new File(RUTA_SIN_PORTADA);
            if (generica.exists()) {
                icono = new ImageIcon(RUTA_SIN_PORTADA);
            } else {
                return null;
            }
        }

        Image escalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(escalada);
    }
}
