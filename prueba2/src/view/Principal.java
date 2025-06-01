package view;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logic.Pasajero;
import logic.Terminal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;

public class Principal extends JFrame {

	private Terminal t;
	
	private JMenuItem eliminarItemBus, addItemChofer, mostrarItemChofer, menuItem_2, eliminarItemChofer;
	private JMenuItem mntmListadoDePasajeros, CrearViajeItem, mntmCerrarSesion, mntmSalir, addItemBus, mostrarItemBus;
	private JMenuItem MostrarViajesItem, AgregarPasajeroItem;

	private JMenu MenuPasajero, MenuViaje, MenuReportes, MenuSesion, MenuChofer, MenuBus;
	private JMenuItem reporteItem;
	private JMenuItem eliminarItem;
	private JMenu MenuReservas;
	private JMenuItem addReservaItem;
	private JMenuItem mostarReservaItem;

	public Principal(final Terminal t, boolean admin, final Pasajero a) {
		this.t = t;

		setTitle("Terminal");
		setSize(618, 525);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Panel de fondo con imagen
		BackgroundPanel panel = new BackgroundPanel("D:/javis/Downloads/Terminal.jpg");
		panel.setLayout(new BorderLayout());
		setContentPane(panel);

		// Creación del menú
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Menú de sesión
		MenuSesion = new JMenu("Sesión");
		MenuSesion.setMargin(new Insets(15, 15, 15, 15));
		MenuSesion.setHorizontalAlignment(SwingConstants.CENTER);
		MenuSesion.setFont(new Font("Arial", Font.BOLD, 18));
		menuBar.add(MenuSesion);
		
		mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Login login = new Login(Principal.this, t);
				login.setVisible(true);
			}
		});
		MenuSesion.add(mntmCerrarSesion);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ConformarSalir dialog = new ConformarSalir(Principal.this);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		MenuSesion.add(mntmSalir);

		//Menu de buses
		if(admin){
		MenuBus = new JMenu("Bus");
		MenuBus.setHorizontalAlignment(SwingConstants.CENTER);
		MenuBus.setFont(new Font("Arial", Font.BOLD, 18));
		menuBar.add(MenuBus);
		MenuBus.setMargin(new Insets(15, 15, 15, 15));
		addItemBus = new JMenuItem("Agregar Bus");
		addItemBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IngresarBusDialog(Principal.this, t).setVisible(true);
			}
		});
		MenuBus.add(addItemBus);
		mostrarItemBus = new JMenuItem("Mostrar Buses");
		mostrarItemBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {  
        			ListadoBusDialog dialog = new ListadoBusDialog(t, Principal.this);
        			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        			dialog.setVisible(true);
        		} catch (Exception e1) {
        			e1.printStackTrace();
        		}
			}
		});
		MenuBus.add(mostrarItemBus);
		}
		
		//Menu de choferes
		if(admin){
		MenuChofer = new JMenu("Chofer");
		MenuChofer.setMargin(new Insets(15, 15, 15, 15));
		MenuChofer.setHorizontalAlignment(SwingConstants.CENTER);
		MenuChofer.setFont(new Font("Arial", Font.BOLD, 18));
		menuBar.add(MenuChofer);
		addItemChofer = new JMenuItem("Agregar Chofer");
		addItemChofer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IngresarChoferDialog dialog = new IngresarChoferDialog(Principal.this, t);
    			dialog.setVisible(true);
			}
		});
		MenuChofer.add(addItemChofer);
		mostrarItemChofer = new JMenuItem("Mostrar Choferes");
		mostrarItemChofer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
    				ListadoChoferDialog dialog = new ListadoChoferDialog(t, Principal.this);
    				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    				dialog.setVisible(true);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
			}
		});
		MenuChofer.add(mostrarItemChofer);
		}

		// Menú de pasajeros
		if(admin){
		MenuPasajero = new JMenu("Pasajeros");
		MenuPasajero.setMargin(new Insets(15, 15, 15, 15));
		MenuPasajero.setHorizontalAlignment(SwingConstants.CENTER);
		MenuPasajero.setFont(new Font("Arial", Font.BOLD, 18));
		menuBar.add(MenuPasajero);
		mntmListadoDePasajeros = new JMenuItem("Listado de pasajeros");
		mntmListadoDePasajeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
                    ListadoPasajeroDialog dialog = new ListadoPasajeroDialog(t, Principal.this);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		MenuPasajero.add(mntmListadoDePasajeros);
		}

		// Menú de viajes
		MenuViaje = new JMenu("Viajes");
		MenuViaje.setMargin(new Insets(15, 15, 15, 15));
		MenuViaje.setHorizontalAlignment(SwingConstants.CENTER);
		MenuViaje.setFont(new Font("Arial", Font.BOLD, 18));
		menuBar.add(MenuViaje);
		if(admin){
		CrearViajeItem = new JMenuItem("Crear viaje");
		CrearViajeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(t.getConductores().size() != 0){
						if(t.getParqueo().size() != 0){
							RegistroViajeDialog dialog = new RegistroViajeDialog(Principal.this, t);
							dialog.setVisible(true);
						}else 
							JOptionPane.showMessageDialog(null, "No se ha registrado ningún omnibus", "Error", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "No se ha registrado ningún conductor", "Error", JOptionPane.ERROR_MESSAGE);
					}
		});
		MenuViaje.add(CrearViajeItem);
		}
		MostrarViajesItem = new JMenuItem("Mostrar viajes");
		MostrarViajesItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
                    ListadoViajesDialog dialog = new ListadoViajesDialog(t, Principal.this);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
			}
			}
		});
		MenuViaje.add(MostrarViajesItem);
		
		//Menu de reservas
		if(!admin){
		MenuReservas = new JMenu("Reservas");
		MenuReservas.setMargin(new Insets(15, 15, 15, 15));
		MenuReservas.setFont(new Font("Arial", Font.BOLD, 17));
		menuBar.add(MenuReservas);
		
		addReservaItem = new JMenuItem("Hacer Reserva");
		addReservaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistroReservaDialog dialog = new RegistroReservaDialog(Principal.this, t, a);
				dialog.setVisible(true);
			}
		});
		MenuReservas.add(addReservaItem);
		
		mostarReservaItem = new JMenuItem("Mostrar Reserva");
		mostarReservaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		MenuReservas.add(mostarReservaItem);
		}
		
		
		// Menú de reportes
		if(admin){
		MenuReportes = new JMenu("Reportes");
		MenuReportes.setMargin(new Insets(15, 15, 15, 15));
		MenuReportes.setHorizontalAlignment(SwingConstants.CENTER);
		MenuReportes.setFont(new Font("Arial", Font.BOLD, 18));
		menuBar.add(MenuReportes);
		reporteItem = new JMenuItem("Mostrar Reporte");
		MenuReportes.add(reporteItem);
		}

	}
}