package view;

import javax.swing.*;

import util.ChoferTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import logic.Conductor;
import logic.Omnibus;
import logic.Terminal;
import logic.Omnibus.Comodidad;
import logic.Omnibus.Disponibilidad;

public class IngresarBusDialog extends JDialog {
	private JTable tablaConductores;
	private ChoferTableModel modeloTabla;
	private JTextField txtMatricula;
	private JSpinner spinner;
	private JCheckBox cbAireAcondicionado, cbBano, cbTV;
	private JButton btnGuardar, btnSeleccionar;
	private Terminal terminal;

	public IngresarBusDialog(Principal parent, final Terminal terminal) {
		super(parent, "Registrar Ómnibus", true);
		this.terminal = terminal;

		setSize(592, 497);
		setLocationRelativeTo(parent);

		// Obtener lista de conductores y mostrar tabla
		modeloTabla = new ChoferTableModel(terminal.getConductores());
		getContentPane().setLayout(null);

		// Panel de formulario
		JPanel panelFormulario = new JPanel();
		panelFormulario.setBounds(28, 33, 503, 152);
		getContentPane().add(panelFormulario);
		panelFormulario.setLayout(null);

		JLabel label = new JLabel("Matrícula:");
		label.setFont(new Font("Constantia", Font.PLAIN, 13));
		label.setBounds(77, 23, 91, 30);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFormulario.add(label);
		txtMatricula = new JTextField(15);
		txtMatricula.setBounds(180, 23, 200, 30);
		panelFormulario.add(txtMatricula);

		JLabel label_1 = new JLabel("Cantidad de asientos:");
		label_1.setFont(new Font("Constantia", Font.PLAIN, 13));
		label_1.setBounds(6, 66, 162, 30);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFormulario.add(label_1);
		spinner = new JSpinner(new SpinnerNumberModel(17, 17, 45, 1));
		spinner.setBounds(180, 66, 224, 30);
		panelFormulario.add(spinner);

		JLabel label_2 = new JLabel("Comodidades:");
		label_2.setFont(new Font("Constantia", Font.PLAIN, 13));
		label_2.setBounds(10, 109, 162, 30);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFormulario.add(label_2);
		JPanel panelComodidades = new JPanel();
		panelComodidades.setBounds(180, 109, 259, 30);
		cbAireAcondicionado = new JCheckBox("Aire Acondicionado");
		cbAireAcondicionado.setFont(new Font("Constantia", Font.PLAIN, 13));
		cbBano = new JCheckBox("Baño");
		cbBano.setFont(new Font("Constantia", Font.PLAIN, 13));
		cbTV = new JCheckBox("TV");
		cbTV.setFont(new Font("Constantia", Font.PLAIN, 13));
		panelComodidades.add(cbAireAcondicionado);
		panelComodidades.add(cbBano);
		panelComodidades.add(cbTV);
		panelFormulario.add(panelComodidades);
		tablaConductores = new JTable(modeloTabla);
		tablaConductores.setFont(new Font("Constantia", Font.PLAIN, 13));
		tablaConductores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPaneConductores = new JScrollPane(tablaConductores);
		scrollPaneConductores.setBounds(153, 214, 409, 165);
		getContentPane().add(scrollPaneConductores);

		// Botón para guardar
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Constantia", Font.PLAIN, 13));
		btnGuardar.setBounds(454, 398, 91, 42);
		getContentPane().add(btnGuardar);

		// Acción del botón Guardar
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula = txtMatricula.getText();
				int cantidadAsientos = (int) spinner.getValue();
				ArrayList<Comodidad> comodidadesSeleccionadas = new ArrayList<>();

				if (cbAireAcondicionado.isSelected()) comodidadesSeleccionadas.add(Comodidad.Climatizado);
				if (cbBano.isSelected()) comodidadesSeleccionadas.add(Comodidad.Banio);
				if (cbTV.isSelected()) comodidadesSeleccionadas.add(Comodidad.TV);
				
				int[] filasSeleccionadas = tablaConductores.getSelectedRows();

				if (filasSeleccionadas.length > 3) {
				    JOptionPane.showMessageDialog(null, "Solo puedes seleccionar hasta 3 conductores.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				    return;
				}

				ArrayList<String> idsSeleccionados = new ArrayList<>();

				for (int fila : filasSeleccionadas) {
				    String idConductor = (String) modeloTabla.getValueAt(fila, 1); // Suponiendo que la columna 0 contiene el ID
				    idsSeleccionados.add(idConductor);
				}
				Omnibus nuevo = new Omnibus(matricula, cantidadAsientos, comodidadesSeleccionadas, Disponibilidad.Disponible);
				for (String id : idsSeleccionados) {
				    Conductor conductor = terminal.buscarConductorPorId(id); // Método de búsqueda en Terminal
				    if (conductor != null) {
				        if(!nuevo.addChoferes(conductor)){
				        	JOptionPane.showMessageDialog(null, "El conductor" + conductor.getName() + "ya forma parte de los conductores del omnibus", "Error", JOptionPane.WARNING_MESSAGE);
				        }
				    }
				}  
				if (terminal.addOmnibus(nuevo)) {
					JOptionPane.showMessageDialog(null, "Ómnibus agregado exitosamente:\nMatrícula: " + matricula +
							"\nAsientos: " + cantidadAsientos + "\nComodidades: " + comodidadesSeleccionadas);
				} else {
					JOptionPane.showMessageDialog(null, "El ómnibus ya existe en la flota.", "Error", JOptionPane.WARNING_MESSAGE);
				}
				dispose();
			}
		});

		// Botón para seleccionar conductores
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setFont(new Font("Constantia", Font.PLAIN, 13));
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] filasSeleccionadas = tablaConductores.getSelectedRows();

				if (filasSeleccionadas.length > 3) {
				    JOptionPane.showMessageDialog(null, "Solo puedes seleccionar hasta 3 conductores.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				    return;
				}

				ArrayList<String> idsSeleccionados = new ArrayList<>();

				for (int fila : filasSeleccionadas) {
				    String idConductor = (String) modeloTabla.getValueAt(fila, 1); // Suponiendo que la columna 0 contiene el ID1
				    idsSeleccionados.add(idConductor);
				}
				for (String id : idsSeleccionados) {
				    Conductor conductor = terminal.buscarConductorPorId(id); // Método de búsqueda en Terminal
				    if (conductor != null) {
				        ;
				    }
				}
			}
		});
		btnSeleccionar.setBounds(28, 215, 113, 30);
		getContentPane().add(btnSeleccionar);
		
		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 582, 486);
		getContentPane().add(fondo);
		fondo.setIcon(new ImageIcon("src/images/fondo2.png"));
	}
}
