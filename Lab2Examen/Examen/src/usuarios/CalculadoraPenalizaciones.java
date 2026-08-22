/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mario
 */
public final class CalculadoraPenalizaciones {

    private CalculadoraPenalizaciones() {
    }

    /*
     * Caso base: indice llega al final de la lista -> no queda nada por sumar, retorna 0.
     * Paso recursivo: se suma el retraso del prestamo en indice al resultado de aplicar el
     * mismo calculo al resto de la lista (indice + 1). Sin bucles for.
     */
    public static int calcularPenalizacionAcumulada(List<Prestamo> prestamos, int indice) {
        if (indice >= prestamos.size()) {
            return 0;
        }
        int retraso = prestamos.get(indice).diasRetraso(LocalDate.now());
        return retraso + calcularPenalizacionAcumulada(prestamos, indice + 1);
    }
}
