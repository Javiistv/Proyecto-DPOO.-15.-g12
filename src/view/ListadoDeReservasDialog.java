package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;





import logic.Terminal;
import logic.Reserva;
import logic.Pasajero;

import util.ReservasTableModel;
import util.ViajeTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


@SuppressWarnings("unused")
public class ListadoDeReservasDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableReservas;
	private DefaultTableModel tabla;
	private Terminal t;
	private Principal padre;
	/**
	 * Create the dialog.
	 * @param ReservasTableModel 
	 */

	public ListadoDeReservasDialog(Principal parent,Terminal t) {
		this.padre=padre;
		setTitle("Listado de Reservas");
		setBounds(100, 100, 603, 420);

		// Crear el modelo de tabla y la tabla
		ReservasTableModel model = new ReservasTableModel(t.getPasajeros());
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 615, 1);


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
		JScrollPane scrollPane1 = new JScrollPane(table);
		scrollPane1.setBounds(12, 84, 570, 277);
		getContentPane().add(scrollPane1);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Constantia", Font.PLAIN, 13));
		btnEliminar.setBounds(466, 37, 97, 25);
		getContentPane().add(btnEliminar);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("src/images/fondo2.png"));
		fondo.setBounds(0, 0, 593, 373);
		getContentPane().add(fondo);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String [] opciones={"Sí","No"};
				int x = table.getSelectedRow();
				if(x!=-1)
				{
					int i = JOptionPane.showOptionDialog(null, "¿Seguro que desea eliminar esta reserva?",null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
					if(i==0)
					{
						
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Para elimininar debe seleccionar una reserva de la tabla");
			}
		});






	}
}

