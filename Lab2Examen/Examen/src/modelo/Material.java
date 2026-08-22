package modelo;

import java.util.ArrayList;

public abstract class Material implements Prestable, Reservable, Comparable<Material> {
    protected String codigo;
    protected String titulo;
    protected EstadoMaterial estado;
    protected int diasM;
    protected NivelComplejidad nivelC;
    protected String refImagen;
    protected ArrayList<String> colaReserva;

    public Material(String codigo, String titulo, int dias, NivelComplejidad nivel, String rutaI) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.diasM = dias;
        this.nivelC = nivel;
        this.refImagen = rutaI;
        this.colaReserva = new ArrayList<>();
        this.estado = EstadoMaterial.DISPONIBLE;

    }

    public abstract String getDescripcion();

    public abstract int calcularDiasPrestamo();

    public void prestar() {
        this.estado = EstadoMaterial.PRESTADO;
    }

    public void devolver() {
        this.estado = EstadoMaterial.DISPONIBLE;
    }

    public void reserva(){
        this.estado=EstadoMaterial.RESERVADO;
    }

    public boolean estaDisponible() {
        return estado == EstadoMaterial.DISPONIBLE;
    }

    public void reservar(String idUsuario) {
        colaReserva.add(idUsuario);
    }

    public void cancelarReserva(String idUsuario) {
        colaReserva.remove(idUsuario);
    }

    public boolean tieneReservas() {
        return !colaReserva.isEmpty();
    }

    public String getSiguienteReserva() {
        if (colaReserva.isEmpty())
            return null;
        return colaReserva.get(0);
    }
   
    public String atenderSiguienteReserva() {
        if (colaReserva.isEmpty()) {
            return null;
        }
        return colaReserva.remove(0);
    }

    public int compareTo(Material otro) {
        return this.titulo.compareTo(otro.titulo);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public EstadoMaterial getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaterial estado) {
        this.estado = estado;
    }

    public int getDiasM() {
        return diasM;
    }

    public NivelComplejidad getNivelC() {
        return nivelC;
    }

    public String getRefImagen() {
        return refImagen;
    }

    public ArrayList<String> getColaReserva() {
        return colaReserva;
    }
    
}