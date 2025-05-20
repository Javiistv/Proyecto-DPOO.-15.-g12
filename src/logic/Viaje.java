package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import logic.Omnibus.Comodidad;

public class Viaje {

	private LocalDate fechaPartida;
	private LocalTime horaPartida;
	private String destino;
	private LocalDate fechaLLegada;
	private double precio;
	private Omnibus omnibus;
	private Conductor conductor;
	private ArrayList<Pasajero> pasajeros;
	
	
	public Omnibus getOmnibus() {
		return omnibus;
	}
	public void setOmnibus(Omnibus omnibus) {
		this.omnibus = omnibus;
	}
	public Conductor getConductor() {
		return conductor;
	}
	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
	public ArrayList<Pasajero> getPasajeros() {
		return pasajeros;
	}
	
	public boolean addPasajeros(Pasajero pass) {
		boolean noExist = true;
		for (int i = 0; i < pasajeros.size() && noExist ; i++) {
			if(pass.getNumID() == pasajeros.get(i).getNumID())
				noExist = false;
		}
		if(noExist)
			pasajeros.add(pass);
		
		return noExist;
	}
	
	public LocalDate getFechaPartida() {
		return fechaPartida;
	}
	public void setFechaPartida(LocalDate fechaPartida) {
		this.fechaPartida = fechaPartida;
	}
	public LocalTime getHoraPartida() {
		return horaPartida;
	}
	public void setHoraPartida(LocalTime horaPartida) {
		this.horaPartida = horaPartida;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public LocalDate getFechaLLegada() {
		return fechaLLegada;
	}
	public void setFechaLLegada(LocalDate fechaLLegada) {
		this.fechaLLegada = fechaLLegada;
	}
	public double getPrecio() {
		return precio;
	}
	
	public void calcularPrecioViaje(double cantKM) {
		double precio = cantKM * 0.8;
		
		if(horaPartida.isAfter(LocalTime.of(18, 0)))
			precio += 20.0;
		if(omnibus.getComodidades().contains(Comodidad.Climatizado))
			precio += 15;
		if(omnibus.getComodidades().contains(Comodidad.TV))
			precio += 5;
		if(omnibus.getComodidades().contains(Comodidad.Banio))
			precio += 3;
		
		this.precio = precio;
	}
	
	public Viaje(LocalDate fechaPartida, LocalTime horaPartida, String destino,
			LocalDate fechaLLegada, double cantKM, Omnibus omnibus,
			Conductor conductor, ArrayList<Pasajero> pasajeros) {
		
		setFechaPartida(fechaPartida);
		setHoraPartida(horaPartida);
		setDestino(destino);
		setFechaLLegada(fechaLLegada);
		setConductor(conductor);
		setOmnibus(omnibus);
		setConductor(conductor);
		this.pasajeros = pasajeros;
		calcularPrecioViaje(cantKM);	
		
	}
	
	
}
