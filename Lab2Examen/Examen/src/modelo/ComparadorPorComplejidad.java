package modelo;

import java.util.Comparator;

public class ComparadorPorComplejidad implements Comparator<Material>{
    public int compare(Material m1, Material m2) {
        return m1.getNivelC().getOrden() - m2.getNivelC().getOrden();
    }
}
