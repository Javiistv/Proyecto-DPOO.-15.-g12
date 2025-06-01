package view;

import javax.swing.*;

import util.TextFieldNumerico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import logic.Conductor;
import logic.Conductor.Categoria;
import logic.Terminal;

public class IngresarChoferDialog extends JDialog {
    
	private Principal padre;
    private JTextField txtNombre;
    private TextFieldNumerico txtNumeroIdentidad;
    private JTextField txtNumLicencia;
    private JSpinner spnExperiencia;
    private ButtonGroup categoriaGroup;
    private Terminal t;
    
    public IngresarChoferDialog(Principal padre, final Terminal t) {
        super(padre, "Ingresar Datos del Chofer", true);
        setSize(428, 335);
        getContentPane().setLayout(null);
        this.t = t;
        		
        // Nombre
        JLabel label = new JLabel("Nombre:");
        label.setBounds(0, 2, 202, 43);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label);
        txtNombre = new JTextField(20);
        txtNombre.setBounds(207, 2, 202, 43);
        getContentPane().add(txtNombre);

        // Número de Identidad (usando TextFieldNumerico)
        JLabel label_1 = new JLabel("Número de Identidad:");
        label_1.setBounds(0, 50, 202, 43);
        label_1.setHorizontalAlignment(SwingConstants.RIGHT);
        label_1.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label_1);
        txtNumeroIdentidad = new TextFieldNumerico(11);
        txtNumeroIdentidad.setBounds(207, 50, 202, 43);
        getContentPane().add(txtNumeroIdentidad);

        // Categoría (RadioButtons)
        JLabel label_2 = new JLabel("Categoría:");
        label_2.setBounds(0, 98, 202, 43);
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        label_2.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label_2);
        JPanel panelCategoria = new JPanel(new FlowLayout());
        panelCategoria.setBounds(207, 98, 202, 43);
        categoriaGroup = new ButtonGroup();
        String[] categorias = {"A", "B", "C"};
        
        for (String cat : categorias) {
            JRadioButton radioButton = new JRadioButton(cat);
            categoriaGroup.add(radioButton);
            panelCategoria.add(radioButton);
        }
        getContentPane().add(panelCategoria);

        // Años de experiencia (Spinner)
        JLabel label_3 = new JLabel("Años de experiencia:");
        label_3.setBounds(0, 146, 202, 43);
        label_3.setHorizontalAlignment(SwingConstants.RIGHT);
        label_3.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label_3);
        spnExperiencia = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        spnExperiencia.setBounds(207, 146, 202, 43);
        getContentPane().add(spnExperiencia);

        // Número de licencia
        JLabel label_4 = new JLabel("Número de Licencia:");
        label_4.setBounds(0, 194, 202, 43);
        label_4.setHorizontalAlignment(SwingConstants.RIGHT);
        label_4.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label_4);
        txtNumLicencia = new TextFieldNumerico(11);
        txtNumLicencia.setBounds(207, 194, 202, 43);
        getContentPane().add(txtNumLicencia);

        // Botón para confirmar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try{
        		 String nombre = txtNombre.getText();
        	        String numeroIdentidad = txtNumeroIdentidad.getText();
        	        String numLicencia = txtNumLicencia.getText();
        	        int experiencia = (int) spnExperiencia.getValue();

        	        // Obtener categoría seleccionada
        	        Categoria categoriaSeleccionada = null;
        	        for (Enumeration<AbstractButton> buttons = categoriaGroup.getElements(); buttons.hasMoreElements();) {
        	            AbstractButton button = buttons.nextElement();
        	            if (button.isSelected()) {
        	                categoriaSeleccionada = Categoria.valueOf(button.getText());
        	            }
        	        }
        	        if(categoriaSeleccionada == null)
        	        	throw new IllegalArgumentException("No se seleccionó categoría");
        	        
        	        Conductor cond = new Conductor(nombre, numeroIdentidad, categoriaSeleccionada, experiencia, numLicencia);
        	        if(t.addConductor(cond)){ 
        	        	JOptionPane.showMessageDialog(null, "Datos guardados:\n" + 
        	                    "Nombre: " + nombre + "\n" +
        	                    "Número de Identidad: " + numeroIdentidad + "\n" +
        	                    "Categoría: " + (String) categoriaSeleccionada.toString() + "\n" +
        	                    "Años de experiencia: " + experiencia + "\n" +
        	                    "Número de licencia: " + numLicencia);
        	        	dispose();
        	        
        	        }else{
        	        	throw new IllegalArgumentException("El conductor ya existe en la terminal");
        	}
        	        }catch(IllegalArgumentException ex){
        	        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        	        }
        	        }
        });
        
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 15));
        btnGuardar.setBounds(217, 250, 170, 25);
        getContentPane().add(btnGuardar);

        setLocationRelativeTo(padre);
    }
}