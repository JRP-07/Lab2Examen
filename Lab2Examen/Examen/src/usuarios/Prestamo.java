/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import modelo.Material;

import java.util.Calendar;

/**
 *
 * @author mario
 */
public class Prestamo implements Comparable<Prestamo> {

    private final Material material;
    private final Usuario usuario;
    private final Calendar fechaPrestamo;
    private final Calendar fechaPrevista;
    private Calendar fechaDevolucion;

    public Prestamo(Material material, Usuario usuario, Calendar fechaPrestamo, Calendar fechaPrevista) {
        this.material = material;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevista = fechaPrevista;
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    public int getDiasRetraso(Calendar fechaRef) {
        Calendar fechaComparacion = fechaDevolucion != null ? fechaDevolucion : fechaRef;
        long milisPorDia = 24L * 60 * 60 * 1000;
        long dias = (fechaComparacion.getTimeInMillis() - fechaPrevista.getTimeInMillis()) / milisPorDia;
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

    public Calendar getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Calendar getFechaPrevista() {
        return fechaPrevista;
    }

    public Calendar getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Calendar fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
