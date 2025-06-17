package logic;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pasajero{
	
	private String username;
	private String contrasena;
	private String nombre;
	private String numID;
	private ArrayList<Reserva> reservas;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username.length() != 0)
		this.username = username;
		else
			throw new IllegalArgumentException("El campo usuario está vacío");
	}
	
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		if(contrasena.length() > 5)
		this.contrasena = contrasena;
		else 
			throw new IllegalArgumentException("La contraseña debe tener al menos 6 caractéres");
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if(nombre.length() == 0)
			throw new IllegalArgumentException("No se introdujo nombre");
		else{
			boolean encontro = false;
			for (int j = 0; j<nombre.length() && !encontro; j++){
				if(!Character.isLetter(nombre.charAt(j)) && nombre.charAt(j)!= ' '){
					encontro = true;
					throw new IllegalArgumentException("Nombre con numero");
				}
			}
			if(!encontro)
				this.nombre = nombre;
		}
	}
	public String getNumID() {
		return numID;
	}
	public void setNumID(String numID) {
		if(numID.length() == 11){
			String fechaTexto = numID.substring(0, 6);
			try{
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");   
            @SuppressWarnings("unused")
			LocalDate fecha = LocalDate.parse(fechaTexto, formatter);
            this.numID = numID;
			}catch (DateTimeException e){
				throw new IllegalArgumentException("La fecha en el carnet no es válida.");
	    }
		}else
			throw new IllegalArgumentException("El carnet debe tener 11 dígitos");
	}
	
	public Pasajero(String nombre, String numID, String usuario, String contrasena) {
		setNombre(nombre);
		setNumID(numID);
		setUsername(usuario);
		setContrasena(contrasena);
		this.reservas = new ArrayList<> ();
	}
	
	public ArrayList<Reserva>getReservas(){
		return reservas;
	}
	
	
	public boolean addReserva(Reserva nueva){
		boolean noEsta = true;
		for(int i = 0; i < reservas.size() && noEsta; i++)
			if(reservas.get(i).getDestino().equals(nueva.getDestino()) && reservas.get(i).getFechaViaje().equals(nueva.getFechaViaje()))
				noEsta = false;
	
		if(noEsta)
			reservas.add(nueva);
		
		return noEsta;
			
	}
	
	
	

}
