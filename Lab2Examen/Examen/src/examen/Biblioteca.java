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
