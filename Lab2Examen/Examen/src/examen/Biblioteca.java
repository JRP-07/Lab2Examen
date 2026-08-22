package examen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

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

    public Prestamo prestar(String idUsuario, String codigo) throws BibliotecaException {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            throw new BibliotecaException("No existe un usuario con id " + idUsuario);
        }
        Material material = buscarPorCodigo(codigo, 0);
        if (material == null) {
            throw new BibliotecaException("No existe un material con codigo " + codigo);
        }
        if (!material.estaDisponible()) {
            throw new MaterialNoDisponibleException("El material " + material.getTitulo() + " ya esta prestado");
        }
        LocalDate hoy = LocalDate.now();
        if (usuario.estaPenalizado(hoy)) {
            throw new UsuarioPenalizadoException(usuario.getNombre() + " esta penalizado hasta " + usuario.getPenalizadoHasta());
        }
        if (usuario.getPrestados().size() >= usuario.getLimitePrestamos()) {
            throw new LimitePrestamosException(usuario.getNombre() + " alcanzo su limite de " + usuario.getLimitePrestamos() + " prestamos");
        }
        if (!usuario.puedeAccederNivel(material.getNivel())) {
            throw new AutorizacionRequeridaException(usuario.getNombre() + " no tiene autorizacion para el nivel " + material.getNivel());
        }

        material.prestar();
        usuario.getPrestados().add(material);
        LocalDate fechaPrevista = hoy.plusDays(material.calcularDiasPrestamo());
        Prestamo prestamo = new Prestamo(material, usuario, hoy, fechaPrevista);
        historial.add(prestamo);
        return prestamo;
    }

    public void devolver(String codigo) throws BibliotecaException {
        Material material = buscarPorCodigo(codigo, 0);
        if (material == null) {
            throw new BibliotecaException("No existe un material con codigo " + codigo);
        }
        Prestamo prestamo = buscarPrestamoActivo(material);
        if (prestamo != null) {
            prestamo.setFechaDevolucion(LocalDate.now());
            prestamo.getUsuario().getPrestados().remove(material);
        }
        if (material.tieneReservas()) {
            material.setEstado(EstadoMaterial.RESERVADO);
            material.getSiguienteReserva();
        } else {
            material.devolver();
        }
    }

    public void reservar(String idUsuario, String codigo) throws BibliotecaException {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            throw new BibliotecaException("No existe un usuario con id " + idUsuario);
        }
        Material material = buscarPorCodigo(codigo, 0);
        if (material == null) {
            throw new BibliotecaException("No existe un material con codigo " + codigo);
        }
        if (!usuario.puedeReservar()) {
            throw new BibliotecaException(usuario.getNombre() + " no tiene permiso para reservar materiales");
        }
        material.reservar(idUsuario);
    }

    private Prestamo buscarPrestamoActivo(Material material) {
        for (int i = 0; i < historial.size(); i++) {
            Prestamo p = historial.get(i);
            if (p.getMaterial() == material && p.estaActivo()) {
                return p;
            }
        }
        return null;
    }

    public <T extends Material> ArrayList<T> filtrarPorTipo(Class<T> tipo) {
        ArrayList<T> resultado = new ArrayList<T>();
        for (int i = 0; i < materiales.size(); i++) {
            if (tipo.isInstance(materiales.get(i))) {
                resultado.add(tipo.cast(materiales.get(i)));
            }
        }
        return resultado;
    }

    public ArrayList<Material> ordenarPorTitulo() {
        ArrayList<Material> copia = new ArrayList<Material>(materiales);
        Collections.sort(copia);
        return copia;
    }

    public ArrayList<Material> ordenarPorComplejidad() {
        ArrayList<Material> copia = new ArrayList<Material>(materiales);
        Collections.sort(copia, new ComparadorPorComplejidad());
        return copia;
    }

    public ArrayList<Prestable> listarPrestables() {
        ArrayList<Prestable> resultado = new ArrayList<Prestable>();
        for (int i = 0; i < materiales.size(); i++) {
            resultado.add(materiales.get(i));
        }
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
