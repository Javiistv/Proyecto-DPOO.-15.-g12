package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import util.ViajeTableModel;
import logic.Terminal;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ListadoViajesDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private Principal padre;
	

    /**
     * Create the dialog.
     */
    public ListadoViajesDialog(final Terminal t, Principal padre) {
        this.padre = padre;
        setTitle("Listado de Viajes");
        setBounds(100, 100, 618, 451);

        // Crear el modelo de tabla y la tabla
        ViajeTableModel model = new ViajeTableModel(t.getViajes());
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 615, 1);

        // Agregar la tabla al panel de contenido
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);

        // Panel de botones
        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(0, 407, 615, 10);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //JButton okButton = new JButton("OK");
        //okButton.addActionListener(e -> dispose());
        //buttonPane.add(okButton);

        //JButton cancelButton = new JButton("Cancel");
        //cancelButton.addActionListener(e -> dispose());
        //buttonPane.add(cancelButton);

        getContentPane().add(buttonPane);
        final JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 75, 574, 301);
        getContentPane().add(scrollPane);
        
        JLabel fondo = new JLabel("New label");
        fondo.setIcon(new ImageIcon("src/images/fondo2.png"));
        fondo.setBounds(0, 0, 615, 417);
        getContentPane().add(fondo);
       
    }
}
