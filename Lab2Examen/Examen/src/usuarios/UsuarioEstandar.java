/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import modelo.NivelComplejidad;

/**
 *
 * @author mario
 */
public class UsuarioEstandar extends Usuario {

    private static final int LIMITE_PRESTAMOS = 3;

    public UsuarioEstandar(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public int getLimitePrestamos() {
        return LIMITE_PRESTAMOS;
    }

    @Override
    public boolean puedeAccederNivel(NivelComplejidad nivel) {
        return !nivel.reqAutorizacion();
    }

    @Override
    public boolean puedeReservar() {
        return false;
    }
}
