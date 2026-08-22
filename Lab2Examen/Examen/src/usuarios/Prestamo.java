/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import modelo.Material;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author mario
 */
// Orden natural por fechaPrevista: permite ordenar listados de prestamos con
// Collections.sort() sin necesidad de un comparador aparte.
public class Prestamo implements Comparable<Prestamo> {

    private final Material material;
    private final Usuario usuario;
    private final LocalDate fechaPrestamo;
    private final LocalDate fechaPrevista;
    private LocalDate fechaDevolucion;

    public Prestamo(Material material, Usuario usuario, LocalDate fechaPrestamo, LocalDate fechaPrevista) {
        this.material = material;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevista = fechaPrevista;
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    /** Dias de retraso respecto de fechaRef (o de la devolucion real, si ya ocurrio); nunca negativo. */
    public int diasRetraso(LocalDate fechaRef) {
        LocalDate fechaComparacion = fechaDevolucion != null ? fechaDevolucion : fechaRef;
        long dias = ChronoUnit.DAYS.between(fechaPrevista, fechaComparacion);
        return (int) Math.max(0, dias);
    }

    @Override
    public int compareTo(Prestamo otro) {
        return this.fechaPrevista.compareTo(otro.fechaPrevista);
    }

    public Material getMaterial() {
        return material;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaPrevista() {
        return fechaPrevista;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
