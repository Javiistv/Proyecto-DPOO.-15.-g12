package view;
import javax.swing.*;

import util.TextFieldNumerico;
import util.ViajeTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logic.Terminal;
import logic.Viaje;

public class ReservaDialog extends JDialog {
    private JButton cancelarBtn;
    private ViajeTableModel modelo;

    public ReservaDialog(Principal parent, final Terminal t) {
        super(parent, "Reserva de Viaje", true);
        setModal(false);
        setAutoRequestFocus(false);
        setBackground(new Color(240, 240, 240));
        setSize(630, 664);
        setLocationRelativeTo(parent);

        // Tabla con viajes disponibles
        modelo = new ViajeTableModel(t.getViajes());
        cancelarBtn = new JButton("Cancelar");

        pack();
    }
}