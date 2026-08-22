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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Calendar;

import examen.usuarios.Prestamo;

public class PanelSeguimiento extends JPanel {

    private Controlador controlador;

    private JSpinner spinnerDias;
    private DefaultTableModel modeloProximos;
    private DefaultTableModel modeloVencidos;

    public PanelSeguimiento(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Dias de antelacion para 'proximos a vencer':"));
        spinnerDias = new JSpinner(new SpinnerNumberModel(3, 1, 90, 1));
        panelSuperior.add(spinnerDias);
        JButton botonActualizar = new JButton("Actualizar listados");
        botonActualizar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        panelSuperior.add(botonActualizar);

        JTabbedPane pestanas = new JTabbedPane();

        String[] columnas = {"Usuario", "Material", "Fecha prevista", "Dias de retraso / restantes"};

        modeloProximos = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        JTable tablaProximos = new JTable(modeloProximos);
        pestanas.addTab("Proximos a vencer", new JScrollPane(tablaProximos));

        modeloVencidos = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        JTable tablaVencidos = new JTable(modeloVencidos);
        pestanas.addTab("Vencidos pendientes de penalizacion", new JScrollPane(tablaVencidos));

        add(panelSuperior, BorderLayout.NORTH);
        add(pestanas, BorderLayout.CENTER);

        actualizar();
    }

    private String formatearFecha(Calendar fecha) {
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int anio = fecha.get(Calendar.YEAR);
        String diaTexto = dia < 10 ? "0" + dia : "" + dia;
        String mesTexto = mes < 10 ? "0" + mes : "" + mes;
        return diaTexto + "/" + mesTexto + "/" + anio;
    }

    public void actualizar() {
        Calendar hoy = Calendar.getInstance();
        int dias = (Integer) spinnerDias.getValue();

        modeloProximos.setRowCount(0);
        ArrayList<Prestamo> proximos = controlador.proximosAVencer(hoy, dias);
        for (int i = 0; i < proximos.size(); i++) {
            Prestamo p = proximos.get(i);
       
            Calendar aux = (Calendar) hoy.clone();
            int diasRestantes = 0;
        
            while (aux.before(p.getFechaPrevista())) {
                aux.add(Calendar.DAY_OF_YEAR, 1);
                diasRestantes++;
            }

            modeloProximos.addRow(new Object[]{
                    p.getUsuario().getNombre(),
                    p.getMaterial().getTitulo(),
                    formatearFecha(p.getFechaPrevista()),
                    diasRestantes + " dias restantes"
            });
        }

        modeloVencidos.setRowCount(0);
        ArrayList<Prestamo> vencidos = controlador.vencidosPendientes(hoy);
        for (int i = 0; i < vencidos.size(); i++) {
            Prestamo p = vencidos.get(i);
            modeloVencidos.addRow(new Object[]{
                    p.getUsuario().getNombre(),
                    p.getMaterial().getTitulo(),
                    formatearFecha(p.getFechaPrevista()),
                    p.getDiasRetraso(hoy) + " dias de retraso"
            });
        }
    }
}
