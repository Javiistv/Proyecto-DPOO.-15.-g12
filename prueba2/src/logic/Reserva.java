package logic;

import java.time.LocalDate;

public class Reserva {
	
	private Pasajero pas;
	private int numReserva;
	private String destino;
	private LocalDate fechaReserva;
	private LocalDate fechaViaje;
	
	public Pasajero getPas() {
		return pas;
	}
	public void setPas(Pasajero pas) {
		this.pas = pas;
	}
	public int getNumReserva() {
		return numReserva;
	}
	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public LocalDate getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public LocalDate getFechaViaje() {
		return fechaViaje;
	}
	public void setFechaViaje(LocalDate fechaViaje) {
		this.fechaViaje = fechaViaje;
	}
	/*public Reserva(Pasajero pas, int numReserva, String destino,
			LocalDate fechaReserva, LocalDate fechaViaje) {
		
		setPas(pas);
		setNumReserva(numReserva);
		setDestino(destino);
		setFechaReserva(fechaReserva);
		setFechaViaje(fechaViaje);
	}*/
	
	public Reserva(Pasajero p, int size, String destino, LocalDate now,
			LocalDate fechaViaje) {
		setPas(p);
		setNumReserva(size);
		setDestino(destino);
		setFechaReserva(now);
		setFechaViaje(fechaViaje);
		// TODO Auto-generated constructor stub
	}

}
