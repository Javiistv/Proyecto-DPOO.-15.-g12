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
        setSize(404, 306);
        setLocationRelativeTo(parent);
                getContentPane().setLayout(null);
                setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/icono.png"));
        
                // Crear etiquetas y campos de entrada
                JLabel label = new JLabel("Nombre:");
                label.setForeground(new Color(255, 215, 0));
                label.setBounds(0, 1, 188, 43);
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Constantia", Font.BOLD, 17));
                getContentPane().add(label);
        nombreField = new JTextField();
        nombreField.setFont(new Font("Constantia", Font.PLAIN, 14));
        nombreField.setBounds(198, 1, 188, 43);
        getContentPane().add(nombreField);

        JLabel label_1 = new JLabel("Carnet:");
        label_1.setForeground(new Color(255, 215, 0));
        label_1.setBounds(0, 54, 188, 43);
        label_1.setHorizontalAlignment(SwingConstants.RIGHT);
        label_1.setFont(new Font("Constantia", Font.BOLD, 17));
        getContentPane().add(label_1);
        carnetField = new TextFieldNumerico(11);
        carnetField.setFont(new Font("Constantia", Font.PLAIN, 14));
        carnetField.setBounds(198, 54, 188, 43);
        getContentPane().add(carnetField);

        JLabel label_2 = new JLabel("Usuario:");
        label_2.setForeground(new Color(255, 215, 0));
        label_2.setBounds(0, 107, 188, 43);
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        label_2.setFont(new Font("Constantia", Font.BOLD, 17));
        getContentPane().add(label_2);
        usuarioField = new JTextField();
        usuarioField.setFont(new Font("Constantia", Font.PLAIN, 14));
        usuarioField.setBounds(198, 107, 188, 43);
        getContentPane().add(usuarioField);

        JLabel label_3 = new JLabel("Contraseña:");
        label_3.setForeground(new Color(255, 215, 0));
        label_3.setBounds(0, 160, 188, 43);
        label_3.setHorizontalAlignment(SwingConstants.RIGHT);
        label_3.setFont(new Font("Constantia", Font.BOLD, 17));
        getContentPane().add(label_3);
        contrasenaField = new JPasswordField();
        contrasenaField.setFont(new Font("Constantia", Font.PLAIN, 24));
        contrasenaField.setBounds(198, 160, 188, 43);
        getContentPane().add(contrasenaField);

        // Botón de creación
        crearBtn = new JButton("Ingresar");
        crearBtn.setBounds(198, 213, 188, 43);
        crearBtn.setFont(new Font("Constantia", Font.BOLD, 16));
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

        JLabel label_4 = new JLabel();
        label_4.setBounds(0, 213, 188, 43);
        getContentPane().add(label_4); 
        getContentPane().add(crearBtn);
        
        JLabel fondo = new JLabel("");
        fondo.setIcon(new ImageIcon("src/images/fondo3.png"));
        fondo.setForeground(new Color(0, 0, 139));
        fondo.setBounds(0, 1, 386, 257);
        getContentPane().add(fondo);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
