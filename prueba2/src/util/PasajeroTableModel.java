package util;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import logic.Pasajero;

public class PasajeroTableModel extends DefaultTableModel {
    
    private static final String[] COLUMN_NAMES = {
        "Usuario", "Nombre", "Carnet Identidad"
    };

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
}