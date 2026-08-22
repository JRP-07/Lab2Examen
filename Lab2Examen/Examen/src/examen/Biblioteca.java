package examen;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Material> materiales;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> historial;

    public Biblioteca() {
        materiales = new ArrayList<Material>();
        usuarios = new ArrayList<Usuario>();
        historial = new ArrayList<Prestamo>();
    }

    public void agregarMaterial(Material m) {
        materiales.add(m);
    }

    public void agregarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public Usuario buscarUsuarioPorId(String id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public Material buscarPorCodigo(String codigo, int indice) {
        if (indice >= materiales.size()) {
            return null;
        }
        if (materiales.get(indice).getCodigo().equals(codigo)) {
            return materiales.get(indice);
        }
        return buscarPorCodigo(codigo, indice + 1);
    }

    public Material buscarPorTitulo(String titulo, int indice) {
        if (indice >= materiales.size()) {
            return null;
        }
        if (materiales.get(indice).getTitulo().equalsIgnoreCase(titulo)) {
            return materiales.get(indice);
        }
        return buscarPorTitulo(titulo, indice + 1);
    }

    public ArrayList<Material> buscarPorNivel(NivelComplejidad nivel, int indice) {
        ArrayList<Material> resultado = new ArrayList<Material>();
        if (indice >= materiales.size()) {
            return resultado;
        }
        if (materiales.get(indice).getNivel() == nivel) {
            resultado.add(materiales.get(indice));
        }
        resultado.addAll(buscarPorNivel(nivel, indice + 1));
        return resultado;
    }

    public ArrayList<Material> buscarDisponiblesDeAutor(String autor, int indice) {
        ArrayList<Material> resultado = new ArrayList<Material>();
        if (indice >= materiales.size()) {
            return resultado;
        }
        Material actual = materiales.get(indice);
        if (actual instanceof Libro) {
            Libro libro = (Libro) actual;
            if (libro.getAutor().equalsIgnoreCase(autor) && libro.estaDisponible()) {
                resultado.add(libro);
            }
        }
        resultado.addAll(buscarDisponiblesDeAutor(autor, indice + 1));
        return resultado;
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Prestamo> getHistorial() {
        return historial;
    }
}
