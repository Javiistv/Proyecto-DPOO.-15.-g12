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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import util.OmnibusTableModel;
import util.PasajeroTableModel;
import logic.Terminal;


public class ListadoPasajeroDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private Principal padre;

    /**
     * Create the dialog.
     */
    public ListadoPasajeroDialog(final Terminal t, Principal padre) {
        this.padre = padre;
        setTitle("Listado de Ómnibus");
        setBounds(100, 100, 633, 464);

        // Crear el modelo de tabla y la tabla
        PasajeroTableModel model = new PasajeroTableModel(t.getPasajeros());
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
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 53, 591, 351);
        getContentPane().add(scrollPane);
        //getRootPane().setDefaultButton(okButton);
    }

}