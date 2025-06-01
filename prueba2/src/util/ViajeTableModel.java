package util;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import logic.Pasajero;
import logic.Viaje;

public class ViajeTableModel extends DefaultTableModel {
    
    private static final String[] COLUMN_NAMES = {
        "ID", "Destino", "Fecha Partida", "Hora Salida", "Fecha LLegada", "Omnibus", "Conductor", "KM", "Precio"
    };

    public ViajeTableModel(ArrayList<Viaje> viajes) {
        super(COLUMN_NAMES, 0); 
        cargarDatos(viajes);
            }

    private void cargarDatos(ArrayList<Viaje> viajes) {
        for (Viaje c : viajes) {
            Object[] fila = {
  
            		c.getId(),
            		c.getDestino(),
            		c.getFechaPartida(),
            		c.getHoraPartida(),
            		c.getFechaLLegada(),
            		c.getOmnibus().getMatricula(),
            		c.getConductor().getName(),
            		c.getCantKM(),
            		c.calcularPrecioViaje(c.getCantKM())
            		
            };
            addRow(fila);
        }
    }
    


    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    }
}