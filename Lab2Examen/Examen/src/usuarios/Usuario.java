/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import modelo.Material;
import modelo.NivelComplejidad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mario
 */
// Polimorfismo en tiempo de ejecucion: getLimitePrestamos(), puedeAccederNivel() y
// puedeReservar() son abstractos aqui y cada subclase (UsuarioEstandar/UsuarioPremium) los
// sobrescribe con su propio comportamiento, en lugar de guardarlos como un atributo distinto.
public abstract class Usuario {

    private final String id;
    private String nombre;
    private final List<Material> prestados = new ArrayList<>();
    private LocalDate penalizadoHasta;

    protected Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public abstract int getLimitePrestamos();

    public abstract boolean puedeAccederNivel(NivelComplejidad nivel);

    public abstract boolean puedeReservar();

    public boolean estaPenalizado(LocalDate fechaRef) {
        return penalizadoHasta != null && penalizadoHasta.isAfter(fechaRef);
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

    public List<Material> getPrestados() {
        return prestados;
    }

    public LocalDate getPenalizadoHasta() {
        return penalizadoHasta;
    }

    public void setPenalizadoHasta(LocalDate penalizadoHasta) {
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
