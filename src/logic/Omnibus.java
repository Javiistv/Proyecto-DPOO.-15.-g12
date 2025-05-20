package logic;

import java.util.ArrayList;

public class Omnibus {
	
	private String matricula;
	private int cantAsientos;
	private ArrayList <Comodidad> comodidades;
	private Disponibilidad disponibilidad;
	private ArrayList<Conductor> choferes;
	
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
		if(cantAsientos > 0)
			this.cantAsientos = cantAsientos;
		else 
			throw new IllegalArgumentException("Cantidad de asientos menor a 0");
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
			ArrayList<Comodidad> comodidades, Disponibilidad disponibilidad,
			ArrayList<Conductor> choferes) {
		
		setMatricula(matricula);
		setCantAsientos(cantAsientos);
		this.comodidades = comodidades;
		this.disponibilidad = disponibilidad;
		this.choferes = choferes;
		
	}

	
}
