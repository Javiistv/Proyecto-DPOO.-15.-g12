package logic;

import java.util.ArrayList;

public class Conductor{

	private String name;
	private String numID;
	private Categoria categoria;
	private int cantYears;
	private String numLicencia;
	private ArrayList<Viaje> viajes;
	
	
	public ArrayList<Viaje> getViajes() {
		return viajes;
	}
	
	public boolean addViajes(Viaje viaje) {   //Asumiendo que cada conductor tiene un solo viaje por dia 
		boolean libre = true;
		for(int i = 0; i < viajes.size() && libre; i++)
			if(viaje.getFechaPartida() == viajes.get(i).getFechaPartida())
				libre = false;
		if(libre)
			viajes.add(viaje);
		
		return libre;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if(name.length() == 0)
			throw new IllegalArgumentException("No se introdujo nombre");
		else{
			boolean encontro = false;
			for (int j = 0; j<name.length() && !encontro; j++){
				if(!Character.isLetter(name.charAt(j)) && name.charAt(j)!= ' '){
					encontro = true;
					throw new IllegalArgumentException("Nombre con numero");
				}
			}
			if(!encontro)
				this.name = name;
		}
	}
	
	public String getNumID() {
		return numID;
	}
	public void setNumID(String numID) {
		if(numID == null || numID.length()!= 11)
			throw new IllegalArgumentException("Carnet no valido, deben ser 11 digitos");
		
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public int getCantYears() {
		return cantYears;
	}
	public void setCantYears(int cantYears) {
		this.cantYears = cantYears;
	}
	public String getNumLicencia() {
		return numLicencia;
	}
	public void setNumLicencia(String numLicencia) {
		this.numLicencia = numLicencia;
	}
	
	public enum Categoria{
		A, B, C;
	}
	
	public Conductor(String name, String numID, Categoria categoria, int years, String NumLicencia){
		setName(name);
		setNumID(numID);
		this.categoria = categoria;
		setCantYears(years);
		setNumLicencia(NumLicencia);
	}
	
	public double calcularSalario(){
		double salario = 0;
		switch (categoria) {
		case A:
			salario = 600 + 0.3 * 500 * cantYears;
			break;
		case B:
			salario = 500 + (viajes.size()*50);
			break;
		case C:
			salario = 1000;
			break;
		default:
			break;
		}	
		return salario;
	}
	
}
