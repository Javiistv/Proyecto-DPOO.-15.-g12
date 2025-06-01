package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.value.ChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import logic.Conductor;
import logic.Omnibus;
import logic.Terminal;
import logic.Viaje;

import com.toedter.calendar.JDateChooser;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroViajeDialog extends JDialog {

    private JDateChooser fechaPartidaPicker, fechaLlegadaPicker;
    private JSpinner horaPartidaSpinner;
    private JComboBox<String> destinoBox;
    private JTextField precioField;
    private JComboBox<Omnibus> omnibusBox;
    private JComboBox<Conductor> conductorBox;

    private static final String[] DESTINOS = {
        "La Habana", "Santiago de Cuba", "Varadero", "Camagüey", "Holguín", "Cienfuegos", "Trinidad"
    };

    public RegistroViajeDialog(Principal parent, final Terminal t) {
        super(parent, "Registro de Viaje", true);
        setSize(445, 447); 
        setLocationRelativeTo(parent);

        JPanel panelDatos = new JPanel(new GridLayout(8, 2, 15, 15)); 

        fechaPartidaPicker = new JDateChooser();
        fechaLlegadaPicker = new JDateChooser();
        horaPartidaSpinner = new JSpinner(new SpinnerDateModel());

        destinoBox = new JComboBox<>();
        for (String destino : DESTINOS) {
            destinoBox.addItem(destino);
        }

        precioField = new JTextField();
        omnibusBox = new JComboBox<>();
        for (Omnibus a : t.getParqueo()) 
            omnibusBox.addItem(a);
        conductorBox = new JComboBox<>();
        conductorBox.setEnabled(false); 

        fechaPartidaPicker.setMinSelectableDate(new Date());

        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(horaPartidaSpinner, "HH:mm");
        horaPartidaSpinner.setEditor(timeEditor);

        fechaPartidaPicker.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    horaPartidaSpinner.setValue(fechaPartidaPicker.getDate());
                }
            }
        });

        fechaLlegadaPicker.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date fechaPartida = fechaPartidaPicker.getDate();
                    Date fechaLlegada = fechaLlegadaPicker.getDate();

                    if (fechaPartida != null && fechaLlegada != null && fechaLlegada.before(fechaPartida)) {
                        JOptionPane.showMessageDialog(null, "La fecha de llegada no puede ser menor que la fecha de salida.", 
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                        fechaLlegadaPicker.setDate(null); 
                    }
                }
            }
        });

        omnibusBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Omnibus seleccionado = (Omnibus) omnibusBox.getSelectedItem();
                conductorBox.removeAllItems(); 

                if (seleccionado != null) {
                    for (Conductor c : seleccionado.getChoferes()) { 
                        conductorBox.addItem(c);
                    }
                    conductorBox.setEnabled(true); 
                } else {
                    conductorBox.setEnabled(false); 
                }
            }
        });

        panelDatos.add(createLabel("Fecha de Partida:"));
        panelDatos.add(fechaPartidaPicker);
        panelDatos.add(createLabel("Hora de Partida:"));
        panelDatos.add(horaPartidaSpinner);
        panelDatos.add(createLabel("Destino:"));
        panelDatos.add(destinoBox);
        panelDatos.add(createLabel("Fecha de Llegada:"));
        panelDatos.add(fechaLlegadaPicker);
        panelDatos.add(createLabel("Cantidad de KM:"));
        panelDatos.add(precioField);
        panelDatos.add(createLabel("Omnibus Asignado:"));
        panelDatos.add(omnibusBox);
        panelDatos.add(createLabel("Conductor Asignado:"));
        panelDatos.add(conductorBox);

        JButton confirmarBtn = new JButton("Confirmar");
        confirmarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    LocalDate fechaPartida = fechaPartidaPicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    Date horaSeleccionada = (Date) horaPartidaSpinner.getValue();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(horaSeleccionada);
                    LocalTime horaPartida = LocalTime.of(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
                    String destino = (String) destinoBox.getSelectedItem();
                    LocalDate fechaLlegada = fechaLlegadaPicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    double cantKM = precioField.getText().isEmpty() ? 0 : Double.parseDouble(precioField.getText());

                    Omnibus omnibus = (Omnibus) omnibusBox.getSelectedItem();
                    Conductor conductor = (Conductor) conductorBox.getSelectedItem();

                    if (conductor == null || omnibus == null) {
                        throw new IllegalArgumentException("Debe seleccionar un ómnibus y un conductor.");
                    }

                    int id = t.getViajes().size();
                    Viaje nuevo = new Viaje(id, fechaPartida, horaPartida, destino, fechaLlegada, cantKM, omnibus, conductor);

                    if (conductor.addViajes(nuevo)) {
                        t.getViajes().add(nuevo);
                        JOptionPane.showMessageDialog(null, "Nuevo Viaje Creado\n\n" +
                                "Fecha de Partida: " + fechaPartida + "\n" +
                                "Hora de Partida: " + horaPartida + "\n" +
                                "Destino: " + destino + "\n" +
                                "Fecha de Llegada: " + fechaLlegada + "\n" +
                                "Cantidad de KM: " + cantKM + "\n" +
                                "Omnibus: " + omnibus + "\n" +
                                "Conductor: " + conductor,
                                "Viaje Registrado", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        throw new IllegalArgumentException("El conductor seleccionado ya tiene un viaje planificado ese día.");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        confirmarBtn.setFont(new Font("Arial", Font.BOLD, 15));
        confirmarBtn.setPreferredSize(new Dimension(200, 40));

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(confirmarBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelDatos, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);

        pack();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        return label;
    }
}

