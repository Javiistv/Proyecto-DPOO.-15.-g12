package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logic.Pasajero;
import logic.Reserva;
import logic.Terminal;
import logic.Viaje;
import util.ViajeTableModel;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroReservaDialog extends JDialog {

    private JPanel panelDatos;
    private JLabel lbldescrip;
    private JLabel lblNombre;
    private JLabel lblCIdescrip;
    private JLabel lblCI;
    private JPanel panelViajes;
    private JScrollPane scrollPaneTabla;
    private ViajeTableModel modelo;
    private Terminal t;
    private JTable tablaViajes;
    private Pasajero p;
    private JButton btnReservar;
    private JLabel lblNewLabel;

    /**
     * Create the dialog.
     * @param princ 
     */
    public RegistroReservaDialog(Principal princ, Terminal t, Pasajero p) {
    	setTitle("Reservar");
        this.t = t;
        this.p = p;
        
        setBounds(100, 100, 604, 550);
        getContentPane().setLayout(null);

        getContentPane().add(getPanelDatos());
        getContentPane().add(getPanelViajes());
        getContentPane().add(getBotonReservar()); // Agregamos el botón en la parte inferior
        getContentPane().add(getLblNewLabel());
    }

    private JPanel getPanelDatos() {
        if (panelDatos == null) {
            panelDatos = new JPanel();
            panelDatos.setBounds(35, 13, 520, 64);
            panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del Pasajero"));
            panelDatos.setLayout(new GridLayout(2, 2, 15, 15));
            panelDatos.setBorder(new EmptyBorder(15, 15, 15, 15));
            panelDatos.add(getLbldescrip());
            panelDatos.add(getLblNombre());
            panelDatos.add(getLblCIdescrip());
            panelDatos.add(getLblCI());
        }
        return panelDatos;
    }

    private JLabel getLbldescrip() {
        if (lbldescrip == null) {
            lbldescrip = new JLabel("Nombre:");
            lbldescrip.setFont(new Font("Constantia", Font.PLAIN, 13));
        }
        return lbldescrip;
    }

    private JLabel getLblNombre() {
        if (lblNombre == null) {
            lblNombre = new JLabel(p.getNombre());
            lblNombre.setFont(new Font("Constantia", Font.PLAIN, 13));
        }
        return lblNombre;
    }

    private JLabel getLblCIdescrip() {
        if (lblCIdescrip == null) {
            lblCIdescrip = new JLabel("Carnet de identidad:");
            lblCIdescrip.setFont(new Font("Constantia", Font.PLAIN, 13));
        }
        return lblCIdescrip;
    }

    private JLabel getLblCI() {
        if (lblCI == null) {
            lblCI = new JLabel(p.getNumID());
            lblCI.setFont(new Font("Constantia", Font.PLAIN, 13));
        }
        return lblCI;
    }

    private JPanel getPanelViajes() {
        if (panelViajes == null) {
            panelViajes = new JPanel(new BorderLayout());
            panelViajes.setBounds(12, 92, 562, 366);
            panelViajes.setBorder(BorderFactory.createTitledBorder("Escoja el viaje deseado"));
            panelViajes.setBorder(new EmptyBorder(15, 15, 15, 15));
            panelViajes.add(getScrollPaneTabla(), BorderLayout.CENTER);
        }
        return panelViajes;
    }

    private JScrollPane getScrollPaneTabla() {
        if (scrollPaneTabla == null) {
            scrollPaneTabla = new JScrollPane(getTablaViajes());
        }
        return scrollPaneTabla;
    }

    private JTable getTablaViajes() {
        if (tablaViajes == null) {
            modelo = new ViajeTableModel(t.getViajes());
            tablaViajes = new JTable(modelo);
            tablaViajes.setFillsViewportHeight(true);
        }
        return tablaViajes;
    }

    private JButton getBotonReservar() {
        if (btnReservar == null) {
            btnReservar = new JButton("Reservar");
            btnReservar.setFont(new Font("Constantia", Font.PLAIN, 13));
            btnReservar.setBounds(0, 473, 586, 30);
            btnReservar.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) {
            		int selectedRow = tablaViajes.getSelectedRow(); 
            		if (selectedRow == -1) { 
            			JOptionPane.showMessageDialog(null, "Debe seleccionar un viaje antes de continuar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            		} else { 			
            			int id = (int) tablaViajes.getValueAt(selectedRow, 0);
            			
            			Viaje select = t.buscarViajePorID(id);
            			if(select != null){
            				if(select.addPasajeros(p)){
            					Reserva nueva = new Reserva(p, select.getPasajeros().size(), select.getDestino(), LocalDate.now(), select.getFechaPartida());
            					if (p.addReserva(nueva)){
            						String mensaje = "Reserva completada:\n" +
            								"Pasajero: " + p.getNombre() + "\n" +
            								"Num. Reserva: " + select.getPasajeros().size() + "\n" +
            								"Destino: " + select.getDestino() + "\n" +
            								"Fecha de reserva: " + LocalDate.now() + "\n" +
            								"Fecha de partida: " + select.getFechaPartida();

            						JOptionPane.showMessageDialog(null, mensaje, "Reserva Confirmada", JOptionPane.INFORMATION_MESSAGE);
            					}else
            						JOptionPane.showMessageDialog(null, "No se pudo completar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
            				}		
            			}}
            		dispose();
            	}
            });
            btnReservar.setPreferredSize(new Dimension(120, 30));
            
        }
        return btnReservar;
    }
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon("D:\\fondo3.png"));
			lblNewLabel.setBounds(0, 0, 586, 470);
		}
		return lblNewLabel;
	}
}