/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.biblioteca.gui;

/**
 *
 * @author gabri
 */
import modelo.Audiovisual;
import modelo.Libro;
import modelo.NivelComplejidad;
import modelo.Periodicidad;
import modelo.Revista;

import usuarios.UsuarioEstandar;
import usuarios.UsuarioPremium;


public class DatosPrueba {
    public static void cargar(Controlador controlador) {
        //Libro(String codigo, String titulo, int dia, NivelComplejidad nivel, String rutaI, String autor, int numeroP, String ISBN){
        Libro libro1 = new Libro("L001", "Ciencia Avanzada",5, NivelComplejidad.ALTO,"examen/recursos/libro_alto.jpg", "John Smith", 1000, "978-0123456789");
        Libro libro2 = new Libro("L002", "Cuentos para Ninos",10, NivelComplejidad.BAJO,"examen/recursos/libro_bajo.jpg", "Maria Garcia", 20, "978-9876543210");
        Libro libro3 = new Libro("L003", "Estudios Basicos",4, NivelComplejidad.MEDIO, "examen/recursos/Libro_medio.jpg","Roberto Martin", 300, "978-2468013579");
        Libro libro4 = new Libro("L004", "Programacion 2",2, NivelComplejidad.ALTO,"examen/recursos/rutaParaProbarSinPortada.jpg", "Erick Amaya", 500, "978-0000000000");
    
        //public Revista(String codigo, String titulo, int dias, NivelComplejidad nivel, String rutaI, int numeroEdicion, Periodicidad periodo) {
        Revista revista1 = new Revista("R001", "M Magazine",2, NivelComplejidad.BAJO, "examen/recursos/revista_mensual.jpg",50, Periodicidad.MENSUAL);
        Revista revista2 = new Revista("R002", "Tecnologia Revolucionaria y Cultura Urbana",  5,NivelComplejidad.MEDIO, "examen/recursos/revista_semanal.jpg",12, Periodicidad.SEMANAL);

        //public Audiovisual(String codigo, String titulo, int diasBasePrestamo, NivelComplejidad nivel, String rutaI,int duracion, String formato)
        Audiovisual audiovisual1 = new Audiovisual("A001", "Celestial Nexus", 4 ,NivelComplejidad.MEDIO, "examen/recursos/Bluray_generico.jpg",120, "Blu-ray");
        Audiovisual audiovisual2 = new Audiovisual("A002", "Silhouette",2 ,NivelComplejidad.ALTO, "examen/recursos/DVD_generico.jpg",95, "DVD");
        
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