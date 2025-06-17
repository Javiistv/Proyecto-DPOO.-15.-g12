package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class SobreNosotrosDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public SobreNosotrosDialog() {
		setTitle("Sobre Nosotros");
		setBounds(100, 100, 467, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label_1 = new JLabel("Aplicaci\u00F3n desarrollada por:");
		label_1.setForeground(new Color(255, 215, 0));
		label_1.setFont(new Font("Constantia", Font.BOLD, 20));
		label_1.setBounds(12, 13, 301, 37);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("-Javier A. Soto Villanueva\r\n");
		label_2.setForeground(new Color(255, 215, 0));
		label_2.setFont(new Font("Constantia", Font.PLAIN, 16));
		label_2.setBounds(12, 51, 221, 27);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("-Aylin V\u00E1zquez Alvarez");
		label_3.setForeground(new Color(255, 215, 0));
		label_3.setFont(new Font("Constantia", Font.PLAIN, 16));
		label_3.setBounds(12, 88, 193, 16);
		contentPanel.add(label_3);
		
		JLabel lblIngenieriaInformatiaa = new JLabel("Ingenieria Inform\u00E1tica");
		lblIngenieriaInformatiaa.setForeground(new Color(255, 215, 0));
		lblIngenieriaInformatiaa.setFont(new Font("Constantia", Font.PLAIN, 14));
		lblIngenieriaInformatiaa.setBounds(229, 116, 185, 16);
		contentPanel.add(lblIngenieriaInformatiaa);
		
		JLabel lblerAoGrupo = new JLabel("1er a\u00F1o. Grupo 12");
		lblerAoGrupo.setForeground(new Color(255, 215, 0));
		lblerAoGrupo.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblerAoGrupo.setBounds(239, 135, 127, 16);
		contentPanel.add(lblerAoGrupo);
		
		JLabel lblTutorMarcos = new JLabel("Tutor:  Marcos");
		lblTutorMarcos.setForeground(new Color(255, 215, 0));
		lblTutorMarcos.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblTutorMarcos.setBounds(12, 180, 141, 16);
		contentPanel.add(lblTutorMarcos);
		
		JLabel lblUniversidadTecnologicaDe = new JLabel("Universidad Tecnologica de La Habana \"Jos\u00E9 Antonio Echeverr\u00EDa \"(Cujae)");
		lblUniversidadTecnologicaDe.setForeground(new Color(255, 215, 0));
		lblUniversidadTecnologicaDe.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblUniversidadTecnologicaDe.setBounds(12, 206, 425, 34);
		contentPanel.add(lblUniversidadTecnologicaDe);
		
		JLabel Fondo = new JLabel("");
		Fondo.setIcon(new ImageIcon("src/images/fondo3.png"));
		Fondo.setBounds(0, 0, 449, 253);
		contentPanel.add(Fondo);
	}
}
