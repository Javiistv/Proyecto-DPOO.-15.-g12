package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logic.Terminal;

import java.awt.Font;

public class ConformarSalir extends JDialog {  //para paquete interfaz

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ConformarSalir(final Principal parent) {
		setTitle("Confirmar");
		setBounds(100, 100, 390, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeguroQueDesea = new JLabel("Seguro que desea salir de la aplicacion?");
			lblSeguroQueDesea.setFont(new Font("Arial", Font.PLAIN, 15));
			lblSeguroQueDesea.setBounds(43, 35, 268, 50);
			contentPanel.add(lblSeguroQueDesea);
		}
		{
			JButton btnSi = new JButton("SI");
			btnSi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					parent.dispose();
				}
			});
			btnSi.setFont(new Font("Arial", Font.PLAIN, 15));
			btnSi.setBounds(53, 109, 110, 25);
			contentPanel.add(btnSi);
		}
		{
			JButton btnNo = new JButton("NO");
			btnNo.setFont(new Font("Arial", Font.PLAIN, 15));
			btnNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			    	dispose();
				}
			});
			btnNo.setBounds(187, 109, 110, 25);
			contentPanel.add(btnNo);
		}
	}

}
