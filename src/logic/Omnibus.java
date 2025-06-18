package logic;

import java.time.LocalDate;
import java.util.ArrayList;

public class Omnibus {

	private String matricula;
	private int cantAsientos;
	private ArrayList <Comodidad> comodidades;
	private Disponibilidad disponibilidad;
	private ArrayList<Conductor> choferes;
	private ArrayList<Viaje> viajes;

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public ArrayList<Conductor> getChoferes() {
		return choferes;
	}
	public boolean addChoferes(Conductor chofer) {
		boolean noExist = true;
		for (int i = 0; i < choferes.size() && noExist ; i++) {
			if(chofer.getNumID() == choferes.get(i).getNumID())
				noExist = false;
		}
		if(noExist)
			choferes.add(chofer);

		return noExist;
	}
	public int getCantAsientos() {
		return cantAsientos;
	}
	public void setCantAsientos(int cantAsientos) {
		this.cantAsientos = cantAsientos;
	}

	public ArrayList<Comodidad> getComodidades() {
		return comodidades;
	}
	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public enum Comodidad{
		Climatizado, TV, Banio; 
	}

	public enum Disponibilidad{
		Disponible, Carretera, Reparacion;
	}

	public Omnibus(String matricula, int cantAsientos,
			ArrayList<Comodidad> comodidades, Disponibilidad disponibilidad) {

		setMatricula(matricula);
		setCantAsientos(cantAsientos);
		this.comodidades = new ArrayList<>();
		this.disponibilidad = disponibilidad;
		this.choferes = new ArrayList<>();
		viajes = new ArrayList<>();

	}
	public boolean addViajes(Viaje viaje){
		boolean agregar = true;
		if(!disponibilidad.equals(Disponibilidad.Reparacion)){
			for(int i = 0; i < viajes.size() && agregar; i++)
				if(viajes.get(i).getFechaPartida().equals(viaje.getFechaPartida()))
					agregar = false;

			if(agregar)
				viajes.add(viaje);
		}else
			agregar = false;
		return agregar;	
	}

	@Override
	public String toString() {
		return matricula; 
	}
}
