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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import exmaen.excepciones.AutorizacionRequeridaException;
import examen.excepciones.LimitePrestamosException;
import examen.excepciones.MaterialNoDisponibleException;
import examen.excepciones.UsuarioPenalizadoException;
import examen.excepciones.BibliotecaException;
import examen.usuarios.Prestamo;

public class PanelPrestamos extends JPanel {

    private Controlador controlador;
    private JTextField campoIdUsuario;
    private JTextField campoCodigoMaterial;
    private DefaultTableModel modeloTabla;
    private JTable tablaPrestamos;

    public PanelPrestamos(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(construirPanelAcciones(), BorderLayout.NORTH);
        add(construirPanelTabla(), BorderLayout.CENTER);
    }

    private String formatearFecha(Calendar fecha) {
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int anio = fecha.get(Calendar.YEAR);
        
        String diaTexto;
        String mesTexto;
        
        if (dia < 10){
            diaTexto ="0" + dia;
        } else{
            diaTexto = ""+dia;
        }
        if (mes <10){
            mesTexto = "0" + mes;
        } else {
            mesTexto = "" + mes;
        }
        return diaTexto + "/" + mesTexto + "/" + anio;
    }

    private JPanel construirPanelAcciones() {
        JPanel contenedor = new JPanel(new BorderLayout(5, 5));
        contenedor.setBorder(BorderFactory.createTitledBorder("Prestar / devolver / reservar"));

        JPanel campos = new JPanel(new GridLayout(1, 4, 5, 5));
        campos.add(new JLabel("Id usuario:"));
        campoIdUsuario = new JTextField();
        campos.add(campoIdUsuario);
        campos.add(new JLabel("Codigo material:"));
        campoCodigoMaterial = new JTextField();
        campos.add(campoCodigoMaterial);

        JPanel botones = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton botonPrestar = new JButton("Prestar");
        botonPrestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                prestar();
            }
        });
        JButton botonDevolver = new JButton("Devolver");
        botonDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                devolver();
            }
        });
        JButton botonReservar = new JButton("Reservar");
        botonReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservar();
            }
        });
        botones.add(botonPrestar);
        botones.add(botonDevolver);
        botones.add(botonReservar);

        contenedor.add(campos, BorderLayout.CENTER);
        contenedor.add(botones, BorderLayout.SOUTH);
        return contenedor;
    }

    private JPanel construirPanelTabla() {
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createTitledBorder("Historial de prestamos"));

        String[] columnas = {"Usuario", "Material", "Fecha prestamo", "Fecha prevista", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaPrestamos = new JTable(modeloTabla);
        contenedor.add(new JScrollPane(tablaPrestamos), BorderLayout.CENTER);
        actualizarTabla();
        return contenedor;
    }

    private void prestar() {
        try {
            Prestamo prestamo = controlador.prestar(campoIdUsuario.getText().trim(), campoCodigoMaterial.getText().trim());
            JOptionPane.showMessageDialog(this, "Prestamo realizado. Fecha prevista de devolucion: "
                    + formatearFecha(prestamo.getFechaPrevista()), "Prestamo realizado", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        } catch (MaterialNoDisponibleException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Material no disponible", JOptionPane.ERROR_MESSAGE);
        } catch (LimitePrestamosException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Limite de prestamos superado", JOptionPane.ERROR_MESSAGE);
        } catch (AutorizacionRequeridaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Autorizacion requerida", JOptionPane.ERROR_MESSAGE);
        } catch (UsuarioPenalizadoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Usuario penalizado", JOptionPane.ERROR_MESSAGE);
        } catch (BibliotecaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al prestar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void devolver() {
        try {
            controlador.devolver(campoCodigoMaterial.getText().trim());
            JOptionPane.showMessageDialog(this, "Devolucion registrada correctamente");
            actualizarTabla();
        } catch (MaterialNoDisponibleException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Material no disponible", JOptionPane.ERROR_MESSAGE);
        } catch (BibliotecaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al devolver", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reservar() {
        try {
            controlador.reservar(campoIdUsuario.getText().trim(), campoCodigoMaterial.getText().trim());
            JOptionPane.showMessageDialog(this, "Reserva realizada correctamente");
            actualizarTabla();
        } catch (MaterialNoDisponibleException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Material no disponible", JOptionPane.ERROR_MESSAGE);
        } catch (AutorizacionRequeridaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Autorizacion requerida", JOptionPane.ERROR_MESSAGE);
        } catch (BibliotecaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al reservar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < controlador.getHistorial().size(); i++) {
            Prestamo p = controlador.getHistorial().get(i);
            String estado = p.estaActivo() ? "Activo" : "Devuelto";
            if (p.estaActivo()){
                estado = "Activo";
            } else {
                estado = "Devuelto";
            }
            modeloTabla.addRow(new Object[]{p.getUsuario().getNombre() ,p.getMaterial().getTitulo(),
                formatearFecha(p.getFechaPrestamo()),formatearFecha(p.getFechaPrevista()),
                estado
            });
        }
    }
}