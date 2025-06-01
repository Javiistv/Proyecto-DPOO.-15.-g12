package util;

import logic.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import logic.Omnibus;

public class OmnibusTableModel extends DefaultTableModel {
    
    private static final String[] COLUMN_NAMES = {
        "Matrícula", "Comodidades", "Cant. Asientos", "Estado", "Choferes"
    };

    public OmnibusTableModel(ArrayList<Omnibus> flota) {
        super(COLUMN_NAMES, 0); // Define las columnas sin filas al inicio
        cargarDatos(flota);
    }

    private void cargarDatos(ArrayList<Omnibus> flota) {
        for (Omnibus omnibus : flota) {
            Object[] fila = {
            	omnibus.getMatricula(),
                omnibus.getComodidades(),
                omnibus.getCantAsientos(),
                omnibus.getDisponibilidad(),
                omnibus.getChoferes(),
               
            };
            addRow(fila);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    }
}