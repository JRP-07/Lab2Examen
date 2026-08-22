/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.biblioteca.gui;

/**
 *
 * @author gabri
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Calendar;
import usuarios.Usuario;
import usuarios.UsuarioEstandar;
import usuarios.UsuarioPremium;

public class PanelUsuarios extends JPanel{
    private Controlador controlador;

    private JTextField campoId;
    private JTextField campoNombre;
    private JComboBox<String> comboTipo;

    private DefaultListModel<Usuario> modeloLista;
    private JList<Usuario> listaUsuarios;
    private JTextArea etiquetaDetalle;

    public PanelUsuarios(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(construirPanelAlta(), BorderLayout.NORTH);
        add(construirPanelListado(), BorderLayout.CENTER);
    }

    private JPanel construirPanelAlta() {
        JPanel contenedor = new JPanel(new BorderLayout(5, 5));
        contenedor.setBorder(BorderFactory.createTitledBorder("Alta de usuario"));

        JPanel campos = new JPanel(new GridLayout(1, 6, 5, 5));
        campos.add(new JLabel("Id:"));
        campoId = new JTextField();
        campos.add(campoId);
        campos.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        campos.add(campoNombre);
        campos.add(new JLabel("Perfil:"));
        comboTipo = new JComboBox<String>(new String[]{"Estandar", "Premium"});
        campos.add(comboTipo);

        JButton botonAgregar = new JButton("Agregar usuario");
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });

        contenedor.add(campos, BorderLayout.CENTER);
        contenedor.add(botonAgregar, BorderLayout.EAST);
        return contenedor;
    }

    private void agregarUsuario() {
        String id = campoId.getText().trim();
        String nombre = campoNombre.getText().trim();
        if (id.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El id y el nombre son obligatorios", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Usuario nuevo;
        if (comboTipo.getSelectedItem().equals("Estandar")) {
            nuevo = new UsuarioEstandar(id, nombre);
        } else {
            nuevo = new UsuarioPremium(id, nombre);
        }
        controlador.agregarUsuario(nuevo);
        actualizarListado();
        JOptionPane.showMessageDialog(this, "Usuario agregado correctamente");
    }

    private JPanel construirPanelListado() {
        JPanel contenedor = new JPanel(new BorderLayout(5, 5));
        contenedor.setBorder(BorderFactory.createTitledBorder("Usuarios registrados"));

        modeloLista = new DefaultListModel<Usuario>();
        listaUsuarios = new JList<Usuario>(modeloLista);
        listaUsuarios.setCellRenderer(new javax.swing.ListCellRenderer() {
            public java.awt.Component getListCellRendererComponent(JList lista, Object valorObjeto, int indice, boolean seleccionado, boolean tieneFoco) {
                Usuario valor = (Usuario) valorObjeto;
                String perfil = valor instanceof UsuarioPremium ? "Premium" : "Estandar";
                JLabel etiqueta = new JLabel(valor.getId() + " - " + valor.getNombre() + " (" + perfil + ")");
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
        listaUsuarios.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetalle(listaUsuarios.getSelectedValue());
                }
            }
        });

        etiquetaDetalle = new JTextArea("Selecciona un usuario para ver su detalle");
        etiquetaDetalle.setEditable(false);
        etiquetaDetalle.setOpaque(false);

        JButton botonPenalizacion = new JButton("Consultar dias de penalizacion acumulados");
        botonPenalizacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarPenalizacion();
            }
        });

        JPanel panelSur = new JPanel(new BorderLayout(5, 5));
        panelSur.add(etiquetaDetalle, BorderLayout.CENTER);
        panelSur.add(botonPenalizacion, BorderLayout.SOUTH);

        contenedor.add(new JScrollPane(listaUsuarios), BorderLayout.CENTER);
        contenedor.add(panelSur, BorderLayout.SOUTH);
        return contenedor;
    }

    private void mostrarDetalle(Usuario u) {
        if (u == null) {
            etiquetaDetalle.setText("Selecciona un usuario para ver su detalle");
            return;
        }
        boolean penalizado = u.estaPenalizado(Calendar.getInstance());
        etiquetaDetalle.setText("Id: " + u.getId()
                + "\nNombre: " + u.getNombre()
                + "\nLimite de prestamos: " + u.getLimitePrestamos()
                + "\nPrestamos activos: " + u.getPrestados().size()
                + "\nPuede reservar: " + (u.puedeReservar() ? "si" : "no")
                + "\nPenalizado ahora: " + (penalizado ? "si" : "no"));
    }

    private void consultarPenalizacion() {
        Usuario seleccionado = listaUsuarios.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario primero", "Sin seleccion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int dias = controlador.consultarPenalizacion(seleccionado.getId(), Calendar.getInstance());
        JOptionPane.showMessageDialog(this, seleccionado.getNombre() + " acumula " + dias + " dias de penalizacion por retrasos", "Penalizacion", JOptionPane.INFORMATION_MESSAGE);
    }

    public void actualizarListado() {
        modeloLista.clear();
        for (int i = 0; i < controlador.getUsuarios().size(); i++) {
            modeloLista.addElement(controlador.getUsuarios().get(i));
        }
    }
}