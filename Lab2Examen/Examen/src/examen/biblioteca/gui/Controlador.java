/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.biblioteca.gui;

/**
 *
 * @author gabri
 */

import java.util.ArrayList;
import java.util.Calendar;

import modelo.Material;
import modelo.NivelComplejidad;
import modelo.Prestable;
import servicio.Biblioteca;
import usuarios.Prestamo;
import usuarios.Usuario;

public class Controlador {

    private Biblioteca biblioteca;

    public Controlador(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void agregarMaterial(Material m) {
        biblioteca.agregarMaterial(m);
    }

    public void agregarUsuario(Usuario u) {
        biblioteca.agregarUsuario(u);
    }

    public Prestamo prestar(String idUsuario, String codigo) throws BibliotecaException {
        return biblioteca.prestar(idUsuario, codigo);
    }

    public void devolver(String codigo) throws BibliotecaException {
        biblioteca.devolver(codigo);
    }

    public void reservar(String idUsuario, String codigo) throws BibliotecaException {
        biblioteca.reservar(idUsuario, codigo);
    }

    public Material buscarPorCodigo(String codigo) {
        return biblioteca.buscarPorCodigo(codigo, 0);
    }

    public Material buscarPorTitulo(String titulo) {
        return biblioteca.buscarPorTitulo(titulo, 0);
    }

    public ArrayList<Material> buscarPorNivel(NivelComplejidad nivel) {
        return biblioteca.buscarPorNivel(nivel, 0);
    }

    public ArrayList<Material> buscarDisponiblesDeAutor(String autor) {
        return biblioteca.buscarDisponiblesDeAutor(autor, 0);
    }

    public ArrayList<Material> filtrarLibros() {
        return new ArrayList<Material>(biblioteca.filtrarPorTipo(Libro.class));
    }

    public ArrayList<Material> filtrarRevistas() {
        return new ArrayList<Material>(biblioteca.filtrarPorTipo(Revista.class));
    }

    public ArrayList<Material> filtrarAudiovisuales() {
        return new ArrayList<Material>(biblioteca.filtrarPorTipo(Audiovisual.class));
    }

    public ArrayList<Material> ordenarPorTitulo() {
        return biblioteca.ordenarPorTitulo();
    }

    public ArrayList<Material> ordenarPorComplejidad() {
        return biblioteca.ordenarPorComplejidad();
    }

    public ArrayList<Prestable> listarPrestables() {
        return biblioteca.listarPrestables();
    }

    public ArrayList<Prestamo> proximosAVencer(Calendar referencia, int dias) {
        return biblioteca.proximosAVencer(referencia, dias);
    }

    public ArrayList<Prestamo> vencidosPendientes(Calendar referencia) {
        return biblioteca.vencidosPendientes(referencia);
    }

    public int consultarPenalizacion(String idUsuario, Calendar referencia) {
        return biblioteca.consultarPenalizacion(idUsuario, referencia);
    }

    public ArrayList<Material> masSolicitados() {
        return biblioteca.masSolicitados();
    }

    public ArrayList<String> recorridoPolimorfico() {
        return biblioteca.recorridoPolimorfico();
    }

    public ArrayList<Material> getMateriales() {
        return biblioteca.getMateriales();
    }

    public ArrayList<Usuario> getUsuarios() {
        return biblioteca.getUsuarios();
    }

    public ArrayList<Prestamo> getHistorial() {
        return biblioteca.getHistorial();
    }
}