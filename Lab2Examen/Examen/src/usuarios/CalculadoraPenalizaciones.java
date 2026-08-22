/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author mario
 */
public final class CalculadoraPenalizaciones {

    private CalculadoraPenalizaciones() {
    }

    public static int calcularPenalizacionAcumulada(ArrayList<Prestamo> prestamos, int indice, Calendar fechaRef) {
        if (indice >= prestamos.size()) {
            return 0;
        }
        int retraso = prestamos.get(indice).getDiasRetraso(fechaRef);
        return retraso + calcularPenalizacionAcumulada(prestamos, indice + 1, fechaRef);
    }

    public static int calcularPenalizacionAcumulada(ArrayList<Prestamo> prestamos, int indice) {
        return calcularPenalizacionAcumulada(prestamos, indice, Calendar.getInstance());
    }
}
