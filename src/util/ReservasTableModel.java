package util;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import logic.Pasajero;
import logic.Reserva;

import view.Login;
public class ReservasTableModel extends DefaultTableModel {

	private static final String[] COLUMN_NAMES = {
		"Pasajero", "Numero de Reserva", "Destino", "Fecha Reserva", "Fecha Partida"
	};

	public ReservasTableModel(ArrayList<Pasajero> pasajeros) {
		super(COLUMN_NAMES, 0); 
		cargarDatos(pasajeros);
	}

	private void cargarDatos(ArrayList<Pasajero> pasajeros) {
		for(Pasajero p: pasajeros){
			for (Reserva r: p.getReservas()) {
				Object[] fila = {
						p.getNombre(),
						r.getNumReserva(),
						r.getDestino(),
						r.getFechaReserva(),
						r.getFechaViaje(),

				};
				addRow(fila);
			}
		}
	}



	@Override
	public boolean isCellEditable(int row, int column) {
		return false; 

	}
}
