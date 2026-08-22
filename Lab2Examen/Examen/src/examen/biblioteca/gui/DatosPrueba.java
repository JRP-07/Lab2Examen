/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.biblioteca.gui;

/**
 *
 * @author gabri
 */
import examen.modelo.Audiovisual;
import examen.modelo.Libro;
import examen.modelo.NivelComplejidad;
import examen.modelo.Periodicidad;
import examen.modelo.Revista;

import examen.usuarios.UsuarioEstandar;
import examen.usuarios.UsuarioPremium;


public class DatosPrueba {
    public static void cargar(Controlador controlador) {

        Libro libro1 = new Libro("L001", "Ciencia Avanzada", "recursos/libro_alto.jpg",NivelComplejidad.ALTO, "John Smith", 1000, "978-0123456789");
        Libro libro2 = new Libro("L002", "Cuentos para Ninos", "recursos/libro_bajo.jpg", NivelComplejidad.BAJO, "Maria Garcia", 20, "978-9876543210");
        Libro libro3 = new Libro("L003", "Estudios Basicos", "recursos/Libro_medio.jpg",NivelComplejidad.MEDIO, "Robert C. Martin", 300, "978-2468013579");
        Libro libro4 = new Libro("L004", "Programacion 2", "recursos/rutaParaProbarSinPortada.jpg", NivelComplejidad.ALTO, "Erick Amaya", 500, "978-0000000000");

        Revista revista1 = new Revista("R001", "M Magazine", "recursos/revista_mensual.jpg",NivelComplejidad.BAJO, 100, Periodicidad.MENSUAL);
        Revista revista2 = new Revista("R002", "Tecnologia Revolucionaria y Cultura Urbana", "recursos/revista_semanal.jpg",NivelComplejidad.MEDIO, 12, Periodicidad.SEMANAL);

        Audiovisual audiovisual1 = new Audiovisual("A001", "Celestial Nexus", "recursos/Bluray_generico.jpg",NivelComplejidad.MEDIO, 120, "Blu-ray");
        Audiovisual audiovisual2 = new Audiovisual("A002", "Silhouette", "recursos/DVD_generico.jpg",NivelComplejidad.ALTO, 95, "DVD");
        
        controlador.agregarMaterial(libro1);
        controlador.agregarMaterial(libro2);
        controlador.agregarMaterial(libro3);
        controlador.agregarMaterial(libro4);
        controlador.agregarMaterial(revista1);
        controlador.agregarMaterial(revista2);
        controlador.agregarMaterial(audiovisual1);
        controlador.agregarMaterial(audiovisual2);

        controlador.agregarUsuario(new UsuarioEstandar("U001", "Gabriel Gutierrez"));
        controlador.agregarUsuario(new UsuarioEstandar("U002", "Jose Perez"));
        controlador.agregarUsuario(new UsuarioPremium("U003", "Mario Doubleday"));
        controlador.agregarUsuario(new UsuarioPremium("U003", "Juan Pablo Valle"));
    }
}
