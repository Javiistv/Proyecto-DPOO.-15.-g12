package util;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import logic.Conductor;;

public class ChoferTableModel extends DefaultTableModel {
    
    private static final String[] COLUMN_NAMES = {
        "Nombre", "Carnet Identidad", "Categoría", "Cant. Años", "Salario"
    };

    public ChoferTableModel(ArrayList<Conductor> conductores) {
        super(COLUMN_NAMES, 0); 
        cargarDatos(conductores);
    }

    private void cargarDatos(ArrayList<Conductor> conductores) {
        for (Conductor c : conductores) {
            Object[] fila = {
            	c.getName(),
            	c.getNumID(),
            	c.getCategoria(),
            	c.getCantYears(),
            	c.calcularSalario(), 
            };
            addRow(fila);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    }
}