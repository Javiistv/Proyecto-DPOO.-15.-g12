package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import logic.Omnibus.Comodidad;

public class Viaje {

	private int id;
	private LocalDate fechaPartida;
	private LocalTime horaPartida;
	private String destino;
	private LocalDate fechaLLegada;
	private double precio;
	private double cantKM;
	private Omnibus omnibus;
	private Conductor conductor;
	private ArrayList<Pasajero> pasajeros;
	
	
	public Omnibus getOmnibus() {
		return omnibus;
	}
	public void setOmnibus(Omnibus omnibus) {
		if(omnibus != null)
		this.omnibus = omnibus;
		else 
			throw new IllegalArgumentException("No se introdujo Omnibus");
	}
	public Conductor getConductor() {
		return conductor;
	}
	public void setConductor(Conductor conductor) {
		if(conductor != null)
		this.conductor = conductor;
		else
			throw new IllegalArgumentException("No se introdujo Conductor");
	}
	public ArrayList<Pasajero> getPasajeros() {
		return pasajeros;
	}
	
	public boolean addPasajeros(Pasajero pass) {
		boolean noExist = true;
		boolean capacidad = true;
		for (int i = 0; i < pasajeros.size() && noExist ; i++) {
			if(pass.getNumID() == pasajeros.get(i).getNumID())
				noExist = false;
		}
		if(omnibus.getCantAsientos() == pasajeros.size()){
			capacidad = false;
		}
		if(noExist && capacidad)
			pasajeros.add(pass);
		
		return noExist;
	}
	
	public LocalDate getFechaPartida() {
		return fechaPartida;
	}
	public void setFechaPartida(LocalDate fechaPartida) {
		if(fechaPartida.isAfter(LocalDate.now()))
		this.fechaPartida = fechaPartida;
		else
			throw new IllegalArgumentException("La fecha de partida no puede ser menor a la actual");
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
		if(fechaLLegada.isAfter(fechaPartida))
		this.fechaLLegada = fechaLLegada;
		else
			throw new IllegalArgumentException("La fecha de llegada debe ser posterior a la de salida");
	}
	
	public double calcularPrecioViaje(double cantKM) {
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
		return precio;
	}
	
	public Viaje(int id, LocalDate fechaPartida, LocalTime horaPartida, String destino,
			LocalDate fechaLLegada, double cantKM, Omnibus omnibus,
			Conductor conductor) {
		
		setFechaPartida(fechaPartida);
		setHoraPartida(horaPartida);
		setDestino(destino);
		setFechaLLegada(fechaLLegada);
		setConductor(conductor);
		setOmnibus(omnibus);
		setConductor(conductor);
		this.pasajeros = new ArrayList<>();
		setCantKM(cantKM);
		calcularPrecioViaje(cantKM);
		
	}
	public double getCantKM() {
		return cantKM;
	}
	public void setCantKM(double cantKM) {
		this.cantKM = cantKM;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
