package modelo;

public interface Reservable {
    void reservar(String idU);
    void cancelarReserva(String idU);
    boolean tieneReservas();
}
