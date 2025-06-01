package view;
import javax.swing.*;

import util.TextFieldNumerico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logic.Pasajero;
import logic.Terminal;

public class RegistroPasajeroDialog extends JDialog {
    private JTextField nombreField, usuarioField;
    private TextFieldNumerico carnetField;
    private JPasswordField contrasenaField;
    private JButton crearBtn;

    public RegistroPasajeroDialog(JFrame parent, final Terminal t, final Login login) {
        super(parent, "Registro de Pasajero", true);
        setSize(404, 305);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new GridLayout(5, 2, 10, 10));

        // Crear etiquetas y campos de entrada
        JLabel label = new JLabel("Nombre:");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label);
        nombreField = new JTextField();
        getContentPane().add(nombreField);

        JLabel label_1 = new JLabel("Carnet:");
        label_1.setHorizontalAlignment(SwingConstants.RIGHT);
        label_1.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label_1);
        carnetField = new TextFieldNumerico(11);
        getContentPane().add(carnetField);

        JLabel label_2 = new JLabel("Usuario:");
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        label_2.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label_2);
        usuarioField = new JTextField();
        getContentPane().add(usuarioField);

        JLabel label_3 = new JLabel("Contraseña:");
        label_3.setHorizontalAlignment(SwingConstants.RIGHT);
        label_3.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(label_3);
        contrasenaField = new JPasswordField();
        getContentPane().add(contrasenaField);

        // Botón de creación
        crearBtn = new JButton("Ingresar");
        crearBtn.setFont(new Font("Arial", Font.PLAIN, 15));
        crearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
                String nombre = nombreField.getText();
                String carnet = carnetField.getText();
                String usuario = usuarioField.getText();
                String contrasena = new String(contrasenaField.getPassword());

                Pasajero a = new Pasajero(nombre, carnet, usuario, contrasena);
                	
                	if(t.addPasajero(a)){
                    JOptionPane.showMessageDialog(null, "Pasajero registrado:\nNombre: " + nombre + "\nCarnet: " + carnet + "\nUsuario: " + usuario, "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    new Principal(t, false, a);
                    dispose(); 
                    login.dispose();
                	}else
                		throw new IllegalArgumentException("El pasajero ya está registrado en la terminal");
                	
                }catch(IllegalArgumentException ex){
                	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
                }
        }
        );

        getContentPane().add(new JLabel()); 
        getContentPane().add(crearBtn);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
