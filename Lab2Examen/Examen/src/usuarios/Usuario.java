/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import modelo.Material;
import modelo.NivelComplejidad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author mario
 */
public abstract class Usuario {

    private final String id;
    private String nombre;
    private final ArrayList<Material> prestados = new ArrayList<>();
    private Calendar penalizadoHasta;

    protected Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public abstract int getLimitePrestamos();

    public abstract boolean puedeAccederNivel(NivelComplejidad nivel);

    public abstract boolean puedeReservar();

    public boolean estaPenalizado(Calendar fechaRef) {
        return penalizadoHasta != null && penalizadoHasta.after(fechaRef);
    }

    public void aplicarPenalizacion(Calendar referencia, int diasRetraso) {
        Calendar nuevaFecha = (Calendar) referencia.clone();
        nuevaFecha.add(Calendar.DAY_OF_YEAR, diasRetraso);
        this.penalizadoHasta = nuevaFecha;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Material> getPrestados() {
        return prestados;
    }

    public Calendar getPenalizadoHasta() {
        return penalizadoHasta;
    }

    public void setPenalizadoHasta(Calendar penalizadoHasta) {
        this.penalizadoHasta = penalizadoHasta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return id.equals(((Usuario) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nombre + " (" + id + ")";
    }
}
