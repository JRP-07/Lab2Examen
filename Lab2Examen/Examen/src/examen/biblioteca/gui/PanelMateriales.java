/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.biblioteca.gui;

/**
 *
 * @author gabri
 */

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;   
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Audiovisual;
import modelo.Libro;
import modelo.Material;
import modelo.NivelComplejidad;
import modelo.Periodicidad;
import modelo.Revista;

public class PanelMateriales extends JPanel{

    private Controlador controlador;

    private JComboBox<String> comboTipo;
    private JTextField campoCodigo;
    private JTextField campoTitulo;
    private JTextField campoRutaImagen;
    private JComboBox<NivelComplejidad> comboComplejidad;

    private JTextField campoAutor;
    private JTextField campoPaginas;
    private JTextField campoIsbn;

    private JTextField campoEdicion;
    private JComboBox<Periodicidad> comboPeriodicidad;

    private JTextField campoDuracion;
    private JTextField campoFormato;

    private CardLayout cardLayout;
    
    private JPanel panelCampos;

    private DefaultListModel<Material> modeloLista;
    private JList<Material> listaMateriales;

    private JLabel etiquetaImagen;
    private JLabel etiquetaDetalle;
    private JLabel etiquetaNivel;

    private JTextField campoBusqueda;

    public PanelMateriales(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(construirPanelAlta(), BorderLayout.NORTH);
        add(construirPanelListado(), BorderLayout.CENTER);
        add(construirPanelDetalle(), BorderLayout.EAST);
    }

    private JPanel construirPanelAlta() {
        JPanel contenedor = new JPanel(new BorderLayout(5, 5));
        contenedor.setBorder(BorderFactory.createTitledBorder("Alta de material"));

        JPanel filaSuperior = new JPanel(new GridLayout(2, 4, 5, 5));
        filaSuperior.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<String>(new String[]{"Libro", "Revista", "Audiovisual"});
        comboTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCampos, (String) comboTipo.getSelectedItem());
            }
        });
        filaSuperior.add(comboTipo);

        filaSuperior.add(new JLabel("Codigo:"));
        campoCodigo = new JTextField();
        filaSuperior.add(campoCodigo);

        filaSuperior.add(new JLabel("Titulo:"));
        campoTitulo = new JTextField();
        filaSuperior.add(campoTitulo);

        filaSuperior.add(new JLabel("Nivel complejidad:"));
        comboComplejidad = new JComboBox<NivelComplejidad>(NivelComplejidad.values());
        filaSuperior.add(comboComplejidad);

        JPanel filaImagen = new JPanel(new BorderLayout(5, 5));
        filaImagen.add(new JLabel("Ruta imagen:"), BorderLayout.WEST);
        campoRutaImagen = new JTextField();
        filaImagen.add(campoRutaImagen, BorderLayout.CENTER);
        JButton botonExaminar = new JButton("Examinar...");
        botonExaminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser selector = new JFileChooser();
                int resultado = selector.showOpenDialog(PanelMateriales.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    campoRutaImagen.setText(selector.getSelectedFile().getPath());
                }
            }
        });
        filaImagen.add(botonExaminar, BorderLayout.EAST);

        cardLayout = new CardLayout();
        panelCampos = new JPanel(cardLayout);

        JPanel panelLibro = new JPanel(new GridLayout(1, 6, 5, 5));
        panelLibro.add(new JLabel("Autor:"));
        campoAutor = new JTextField();
        panelLibro.add(campoAutor);
        panelLibro.add(new JLabel("Paginas:"));
        campoPaginas = new JTextField();
        panelLibro.add(campoPaginas);
        panelLibro.add(new JLabel("ISBN:"));
        campoIsbn = new JTextField();
        panelLibro.add(campoIsbn);
        panelCampos.add(panelLibro, "Libro");

        JPanel panelRevista = new JPanel(new GridLayout(1, 4, 5, 5));
        panelRevista.add(new JLabel("Numero edicion:"));
        campoEdicion = new JTextField();
        panelRevista.add(campoEdicion);
        panelRevista.add(new JLabel("Periodicidad:"));
        comboPeriodicidad = new JComboBox<Periodicidad>(Periodicidad.values());
        panelRevista.add(comboPeriodicidad);
        panelCampos.add(panelRevista, "Revista");

        JPanel panelAudiovisual = new JPanel(new GridLayout(1, 4, 5, 5));
        panelAudiovisual.add(new JLabel("Duracion (min):"));
        campoDuracion = new JTextField();
        panelAudiovisual.add(campoDuracion);
        panelAudiovisual.add(new JLabel("Formato (DVD/Blu-ray):"));
        campoFormato = new JTextField();
        panelAudiovisual.add(campoFormato);
        panelCampos.add(panelAudiovisual, "Audiovisual");

        JButton botonAgregar = new JButton("Agregar material");
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarMaterial();
            }
        });

        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        panelInferior.add(panelCampos, BorderLayout.CENTER);
        panelInferior.add(botonAgregar, BorderLayout.EAST);

        JPanel panelSuperiorCompleto = new JPanel(new BorderLayout(5, 5));
        panelSuperiorCompleto.add(filaSuperior, BorderLayout.NORTH);
        panelSuperiorCompleto.add(filaImagen, BorderLayout.SOUTH);

        contenedor.add(panelSuperiorCompleto, BorderLayout.NORTH);
        contenedor.add(panelInferior, BorderLayout.SOUTH);
        return contenedor;
    }

    private void agregarMaterial() {
        String codigo = campoCodigo.getText().trim();
        String titulo = campoTitulo.getText().trim();
        String ruta = campoRutaImagen.getText().trim();
        NivelComplejidad nivel = (NivelComplejidad) comboComplejidad.getSelectedItem();
        String tipo = (String) comboTipo.getSelectedItem();

        if (codigo.isEmpty() || titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El codigo y el titulo son obligatorios", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Material nuevo = null;
        try {
            if (tipo.equals("Libro")) {
                int paginas = Integer.parseInt(campoPaginas.getText().trim());
                nuevo = new Libro(codigo, titulo, 14, nivel, ruta, campoAutor.getText().trim(), paginas, campoIsbn.getText().trim());
            } else if (tipo.equals("Revista")) {
                int edicion = Integer.parseInt(campoEdicion.getText().trim());
                Periodicidad periodicidad = (Periodicidad) comboPeriodicidad.getSelectedItem();
                nuevo = new Revista(codigo, titulo, 5, nivel, ruta, edicion, periodicidad);
            } else {
                int duracion = Integer.parseInt(campoDuracion.getText().trim());
                nuevo = new Audiovisual(codigo, titulo, 3, nivel, ruta, duracion, campoFormato.getText().trim());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los campos numericos deben contener numeros validos", "Dato invalido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controlador.agregarMaterial(nuevo);
        actualizarListado();
        JOptionPane.showMessageDialog(this, "Material agregado correctamente");
    }

    private JPanel construirPanelListado() {
        JPanel contenedor = new JPanel(new BorderLayout(5, 5));
        contenedor.setBorder(BorderFactory.createTitledBorder("Catalogo"));

        JPanel panelBusqueda = new JPanel(new BorderLayout(5, 5));
        campoBusqueda = new JTextField();
        panelBusqueda.add(new JLabel("Buscar (codigo o titulo):"), BorderLayout.WEST);
        panelBusqueda.add(campoBusqueda, BorderLayout.CENTER);

        JPanel panelBotonesBusqueda = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton botonBuscarCodigo = new JButton("Buscar por codigo");
        botonBuscarCodigo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigo();
            }
        });
        JButton botonBuscarTitulo = new JButton("Buscar por titulo");
        botonBuscarTitulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPorTitulo();
            }
        });
        JButton botonOrdenTitulo = new JButton("Ordenar por titulo");
        botonOrdenTitulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarLista(controlador.ordenarPorTitulo());
            }
        });
        JButton botonOrdenComplejidad = new JButton("Ordenar por complejidad");
        botonOrdenComplejidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarLista(controlador.ordenarPorComplejidad());
            }
        });
        panelBotonesBusqueda.add(botonBuscarCodigo);
        panelBotonesBusqueda.add(botonBuscarTitulo);
        panelBotonesBusqueda.add(botonOrdenTitulo);
        panelBotonesBusqueda.add(botonOrdenComplejidad);

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        panelSuperior.add(panelBusqueda, BorderLayout.NORTH);
        panelSuperior.add(panelBotonesBusqueda, BorderLayout.SOUTH);

        modeloLista = new DefaultListModel<Material>();
        listaMateriales = new JList<Material>(modeloLista);
        listaMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMateriales.setCellRenderer(new javax.swing.ListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(JList lista, Object valorObjeto,
                    int indice, boolean seleccionado, boolean tieneFoco) {
                Material valor = (Material) valorObjeto;
                JLabel etiqueta = new JLabel(valor.getCodigo() + " - " + valor.getTitulo() + " [" + valor.getEstado() + "]");
                etiqueta.setOpaque(true);
                if (seleccionado) {
                    etiqueta.setBackground(lista.getSelectionBackground());
                    etiqueta.setForeground(lista.getSelectionForeground());
                } else {
                    etiqueta.setBackground(lista.getBackground());
                    etiqueta.setForeground(lista.getForeground());
                }
                return etiqueta;
            }
        });
        listaMateriales.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetalle(listaMateriales.getSelectedValue());
                }
            }
        });

        JPanel panelFiltros = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton botonSoloLibros = new JButton("Solo libros");
        botonSoloLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarLista(controlador.filtrarLibros());
            }
        });
        JButton botonSoloRevistas = new JButton("Solo revistas");
        botonSoloRevistas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarLista(controlador.filtrarRevistas());
            }
        });
        JButton botonTodos = new JButton("Ver todos");
        botonTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarListado();
            }
        });
        panelFiltros.add(botonSoloLibros);
        panelFiltros.add(botonSoloRevistas);
        panelFiltros.add(botonTodos);

        contenedor.add(panelSuperior, BorderLayout.NORTH);
        contenedor.add(new JScrollPane(listaMateriales), BorderLayout.CENTER);
        contenedor.add(panelFiltros, BorderLayout.SOUTH);
        return contenedor;
    }

    private void buscarPorCodigo() {
        Material encontrado = controlador.buscarPorCodigo(campoBusqueda.getText().trim());
        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "No se encontro ningun material con ese codigo", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            cargarLista(new ArrayList<Material>());
            modeloLista.addElement(encontrado);
            listaMateriales.setSelectedIndex(0);
        }
    }

    private void buscarPorTitulo() {
        Material encontrado = controlador.buscarPorTitulo(campoBusqueda.getText().trim());
        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "No se encontro ningun material con ese titulo", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            cargarLista(new ArrayList<Material>());
            modeloLista.addElement(encontrado);
            listaMateriales.setSelectedIndex(0);
        }
    }

    private JPanel construirPanelDetalle() {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BorderLayout(5, 5));
        contenedor.setBorder(BorderFactory.createTitledBorder("Detalle"));
        contenedor.setPreferredSize(new java.awt.Dimension(220, 0));

        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);

        etiquetaDetalle = new JLabel("<html>Selecciona un material</html>");
        etiquetaDetalle.setVerticalAlignment(JLabel.TOP);

        etiquetaNivel = new JLabel(" ", JLabel.CENTER);
        etiquetaNivel.setOpaque(true);
        etiquetaNivel.setFont(new Font("SansSerif", Font.BOLD, 12));

        contenedor.add(etiquetaImagen, BorderLayout.NORTH);
        contenedor.add(new JScrollPane(etiquetaDetalle), BorderLayout.CENTER);
        contenedor.add(etiquetaNivel, BorderLayout.SOUTH);
        return contenedor;
    }

    private void mostrarDetalle(Material m) {
        if (m == null) {
            etiquetaImagen.setIcon(null);
            etiquetaDetalle.setText("<html>Selecciona un material</html>");
            etiquetaNivel.setText(" ");
            etiquetaNivel.setBackground(this.getBackground());
            return;
        }
        etiquetaImagen.setIcon(UtilImagen.cargar(m.getRefImagen(), 150, 210));
        etiquetaDetalle.setText("<html><body style='width:180px'>" + m.getDescripcion()
                + "<br><br>Estado: " + m.getEstado()
                + "<br>Dias de prestamo: " + m.calcularDiasPrestamo()
                + "<br>Reservas pendientes: " + (m.tieneReservas() ? "si" : "no")
                + "</body></html>");

        NivelComplejidad nivel = m.getNivelC();
        etiquetaNivel.setText(nivel.name() + " (" + nivel.getDescripcion() + ")");
        if (nivel == NivelComplejidad.BAJO) {
            etiquetaNivel.setBackground(new Color(150, 220, 150));
        } else if (nivel == NivelComplejidad.MEDIO) {
            etiquetaNivel.setBackground(new Color(240, 220, 120));
        } else {
            etiquetaNivel.setBackground(new Color(230, 140, 140));
        }
    }

    private void cargarLista(ArrayList<Material> lista) {
        modeloLista.clear();
        for (int i = 0; i < lista.size(); i++) {
            modeloLista.addElement(lista.get(i));
        }
    }

    public void actualizarListado() {
        cargarLista(controlador.getMateriales());
    }
}