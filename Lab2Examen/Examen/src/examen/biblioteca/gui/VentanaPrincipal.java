/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.biblioteca.gui;

/**
 *
 * @author gabri
 */

import javax.swing.*;

public class VentanaPrincipal extends JFrame{
    
    private Controlador controlador;
    private PanelMateriales panelMateriales;
    private PanelUsuarios panelUsuarios;
    private PanelPrestamos panelPrestamos;
    private PanelSeguimiento panelSeguimiento;

    public VentanaPrincipal(Controlador controlador) {
        super("Sistema de Gestion de Biblioteca");
        this.controlador = controlador;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(950, 650);
        setLocationRelativeTo(null);

        JTabbedPane pestanas = new JTabbedPane();

        panelMateriales = new PanelMateriales(controlador);
        panelUsuarios = new PanelUsuarios(controlador);
        panelPrestamos = new PanelPrestamos(controlador);
        panelSeguimiento = new PanelSeguimiento(controlador);

        pestanas.addTab("Materiales", panelMateriales);
        pestanas.addTab("Usuarios", panelUsuarios);
        pestanas.addTab("Prestamos y reservas", panelPrestamos);
        pestanas.addTab("Seguimiento (proximos / vencidos)", panelSeguimiento);

        pestanas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                panelMateriales.actualizarListado();
                panelUsuarios.actualizarListado();
                panelPrestamos.actualizarTabla();
                panelSeguimiento.actualizar();
            }
        });

        add(pestanas);

        panelMateriales.actualizarListado();
        panelUsuarios.actualizarListado();
    }
}

