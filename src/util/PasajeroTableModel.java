package util;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import logic.Pasajero;

public class PasajeroTableModel extends DefaultTableModel {
    
    private static final String[] COLUMN_NAMES = {
        "Usuario", "Nombre", "Carnet Identidad"
    };
    private JLabel labelfondoPasajero;

    public PasajeroTableModel(ArrayList<Pasajero> pasajeros) {
        super(COLUMN_NAMES, 0); 
        cargarDatos(pasajeros);
    }

    private void cargarDatos(ArrayList<Pasajero> pasajeros) {
        for (Pasajero c : pasajeros) {
            Object[] fila = {
  
            		c.getUsername(),
            		c.getNombre(),
            		c.getNumID(),
            		
            };
            addRow(fila);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    }
    private JLabel getLabelfondoPasajero() {
		if ( labelfondoPasajero == null) {
			 labelfondoPasajero = new JLabel("");
			 labelfondoPasajero.setBounds(0, 0, 355, 429);
			 labelfondoPasajero.setIcon(new ImageIcon("src/images/fondologin1.png"));
		}
		return   labelfondoPasajero;
	}
}
