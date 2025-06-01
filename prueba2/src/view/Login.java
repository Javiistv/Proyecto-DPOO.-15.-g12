package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import logic.Pasajero;
import logic.Terminal;


public class Login extends JDialog { //para paquete interfaz

	private final JPanel contentPanel = new JPanel();
	private JTextField nombre;
	private JLabel lblComtrasena;
	private JPasswordField contrasena;
	private JButton btnIngresar;
	private Principal p;
	protected Window frame;
	private Terminal nueva = new Terminal();
	private JButton btnNewButton;
	private boolean admin;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @param p 
	 * @param p 
	 */
	public Login(Principal p, Terminal t) {
		this.p = p;
		this.nueva = t;
		
		setTitle("Login");
		setBounds(100, 100, 373, 381);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Terminal.jpg"));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		{
			JLabel lblNewLabel = new JLabel("Usuario");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 17));
			lblNewLabel.setBounds(124, 47, 75, 16);
			contentPanel.add(lblNewLabel);
		}
		contentPanel.add(getNombre());
		contentPanel.add(getLblComtrasena());
		contentPanel.add(getContrasena());
		contentPanel.add(getBtnIngresar());
		contentPanel.add(getBtnNewButton());
	}
	private JTextField getNombre() {
		if (nombre == null) {
			nombre = new JTextField();
			nombre.setHorizontalAlignment(SwingConstants.CENTER);
			nombre.setFont(new Font("Arial", Font.PLAIN, 15));
			nombre.setBounds(76, 76, 196, 28);
			nombre.setColumns(10);
		}
		return nombre;
	}
	private JLabel getLblComtrasena() {
		if (lblComtrasena == null) {
			lblComtrasena = new JLabel("Contrase\u00F1a");
			lblComtrasena.setHorizontalAlignment(SwingConstants.CENTER);
			lblComtrasena.setFont(new Font("Arial", Font.PLAIN, 17));
			lblComtrasena.setBounds(114, 131, 97, 28);
		}
		return lblComtrasena;
	}
	private JTextField getContrasena() {
		if (contrasena == null) {
			contrasena = new JPasswordField();
			contrasena.setFont(new Font("Arial", Font.PLAIN, 15));
			contrasena.setHorizontalAlignment(SwingConstants.CENTER);
			contrasena.setBounds(76, 172, 196, 34);
			contrasena.setColumns(10);
		}
		return contrasena;
	}
	private JButton getBtnIngresar() {
		if (btnIngresar == null) {
			btnIngresar = new JButton("Ingresar");
			btnIngresar.setFont(new Font("Arial", Font.PLAIN, 15));
			btnIngresar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					boolean admin = false;
					Pasajero a = null;
					String nom = nombre.getText();
					String cont = new String(contrasena.getPassword());
					if(nom.equals("Jalin")&& cont.equals("1234")){
						admin = true;
						new Principal(nueva, admin, null);
						dispose();
					}
					else{
						boolean encontro = false;
						for(int i = 0; i < nueva.getPasajeros().size(); i++){
							if(nom.equals(nueva.getPasajeros().get(i).getUsername()) && cont.equals(nueva.getPasajeros().get(i).getContrasena())){
								encontro = true;
								a = nueva.getPasajeros().get(i);
							}
						}
						if(encontro){
							admin = false;
							new Principal(nueva, admin, a);
							dispose();
						}else 
							JOptionPane.showMessageDialog(null,"Usuario o Contraseña incorrectas ","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnIngresar.setBounds(228, 281, 115, 40);
		}
		return btnIngresar;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Registrarse");
			btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					RegistroPasajeroDialog dialog = new RegistroPasajeroDialog(p, nueva, Login.this);
					dialog.setVisible(true);
				}
			});
			btnNewButton.setBounds(12, 281, 115, 40);
		}
		return btnNewButton;
	}
}
