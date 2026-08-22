/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.biblioteca.gui;

/**
 *
 * @author gabri
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

public class UtilImagen {

    private static final String RUTA_SIN_PORTADA = "/examen/recursos/sin_portada.jpg";

    public static ImageIcon cargar(String ruta, int ancho, int alto) {
        ImageIcon icono = cargarIconoOriginal(ruta);

        if (icono == null) {
            icono = cargarIconoGenerico();
        }

        if (icono == null) {
            return new ImageIcon(generarPlaceholder(ancho, alto));
        }

        Image escalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(escalada);
    }

    private static ImageIcon cargarIconoOriginal(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            return null;
        }

        File archivo = new File(ruta);
        if (archivo.exists() && archivo.isFile()) {
            ImageIcon prueba = new ImageIcon(ruta);
            if (prueba.getIconWidth() > 0) {
                return prueba;
            }
        }

        String rutaClasspath = ruta.startsWith("/") ? ruta : "/" + ruta;
        URL recurso = UtilImagen.class.getResource(rutaClasspath);
        if (recurso != null) {
            ImageIcon prueba = new ImageIcon(recurso);
            if (prueba.getIconWidth() > 0) {
                return prueba;
            }
        }

        return null;
    }

    private static ImageIcon cargarIconoGenerico() {
        URL recurso = UtilImagen.class.getResource(RUTA_SIN_PORTADA);
        if (recurso == null) {
            return null;
        }
        ImageIcon icono = new ImageIcon(recurso);
        return icono.getIconWidth() > 0 ? icono : null;
    }

    private static BufferedImage generarPlaceholder(int ancho, int alto) {
        int anchoFinal = ancho > 0 ? ancho : 150;
        int altoFinal = alto > 0 ? alto : 210;
        BufferedImage imagen = new BufferedImage(anchoFinal, altoFinal, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagen.createGraphics();
        g.setColor(new Color(220, 220, 220));
        g.fillRect(0, 0, anchoFinal, altoFinal);
        g.setColor(new Color(120, 120, 120));
        g.drawRect(0, 0, anchoFinal - 1, altoFinal - 1);
        String texto = "Sin portada";
        int anchoTexto = g.getFontMetrics().stringWidth(texto);
        g.drawString(texto, Math.max(0, (anchoFinal - anchoTexto) / 2), altoFinal / 2);
        g.dispose();
        return imagen;
    }
}