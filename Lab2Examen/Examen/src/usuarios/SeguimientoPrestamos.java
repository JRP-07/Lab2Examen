/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 *
 * @author mario
 */
public final class SeguimientoPrestamos {

    private SeguimientoPrestamos() {
    }

    public static ArrayList<Prestamo> proximosAVencer(ArrayList<Prestamo> prestamos, Calendar fechaRef, int diasMargen) {
        Calendar limite = (Calendar) fechaRef.clone();
        limite.add(Calendar.DAY_OF_YEAR, diasMargen);
        ArrayList<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.estaActivo()
                    && !p.getFechaPrevista().before(fechaRef)
                    && !p.getFechaPrevista().after(limite)) {
                resultado.add(p);
            }
        }
        Collections.sort(resultado);
        return resultado;
    }

    public static ArrayList<Prestamo> vencidosPendientes(ArrayList<Prestamo> prestamos, Calendar fechaRef) {
        ArrayList<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.estaActivo() && p.getFechaPrevista().before(fechaRef)) {
                resultado.add(p);
            }
        }
        Collections.sort(resultado);
        return resultado;
    }
}
