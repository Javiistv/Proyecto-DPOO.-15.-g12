package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import util.ChoferTableModel;
import util.OmnibusTableModel;
import logic.Conductor;
import logic.Terminal;
import logic.Viaje;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;


public class ListadoChoferDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private Principal padre;

    /**
     * Create the dialog.
     */
    public ListadoChoferDialog(final Terminal t, Principal padre) {
    	setFont(new Font("Constantia", Font.PLAIN, 12));
        this.padre = padre;
        setTitle("Listado de Conductores");
        setBounds(100, 100, 614, 343);

        // Crear el modelo de tabla y la tabla
        ChoferTableModel model = new ChoferTableModel(t.getConductores());
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
        scrollPane.setBounds(12, 87, 572, 191);
        getContentPane().add(scrollPane);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Constantia", Font.PLAIN, 13));
        btnEliminar.setBounds(463, 49, 97, 25);
        getContentPane().add(btnEliminar);
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("src/images/fondo4.png"));
        label.setBounds(0, 0, 603, 296);
        getContentPane().add(label);
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
	        		String [] opciones={"Sí","No"};
	        					int x = table.getSelectedRow();
					if(x!=-1)
					{
						int i = JOptionPane.showOptionDialog(null, "¿Seguro que desea eliminar este chofer?",null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
						if(i==0)
						{
							
							//comprobr q el chofer no este en ningun viaje
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Para elimininar debe seleccionar un chofer de la tabla");
				}
			
        });
       
        
        //getRootPane().setDefaultButton(okButton);
    }
}