/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mario
 */
// Metodos estaticos que reciben el historial de prestamos como parametro y devuelven listados
// ya filtrados y ordenados. No conocen ninguna clase de servicio: eso permite probar este
// punto con un main propio antes de que el resto del proyecto exista.
public final class SeguimientoPrestamos {

    private SeguimientoPrestamos() {
    }

    /** Prestamos activos cuya fechaPrevista cae entre fechaRef y fechaRef + diasMargen (hacia adelante). */
    public static List<Prestamo> proximosAVencer(List<Prestamo> prestamos, LocalDate fechaRef, int diasMargen) {
        LocalDate limite = fechaRef.plusDays(diasMargen);
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.estaActivo()
                    && !p.getFechaPrevista().isBefore(fechaRef)
                    && !p.getFechaPrevista().isAfter(limite)) {
                resultado.add(p);
            }
        }
        Collections.sort(resultado);
        return resultado;
    }

    /** Prestamos activos cuya fechaPrevista ya paso respecto de fechaRef, sin devolucion (hacia atras). */
    public static List<Prestamo> vencidosPendientes(List<Prestamo> prestamos, LocalDate fechaRef) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.estaActivo() && p.getFechaPrevista().isBefore(fechaRef)) {
                resultado.add(p);
            }
        }
        Collections.sort(resultado);
        return resultado;
    }
}
