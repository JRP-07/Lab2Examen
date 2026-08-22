/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package usuarios;

import modelo.NivelComplejidad;

/**
 *
 * @author mario
 */
public class UsuarioPremium extends Usuario {

    private static final int LIMITE_PRESTAMOS = 8;

    public UsuarioPremium(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public int getLimitePrestamos() {
        return LIMITE_PRESTAMOS;
    }

    @Override
    public boolean puedeAccederNivel(NivelComplejidad nivel) {
        return true;
    }

    @Override
    public boolean puedeReservar() {
        return true;
    }
}
