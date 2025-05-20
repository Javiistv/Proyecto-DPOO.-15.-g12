package logic;

import java.util.ArrayList;

public class Terminal {
	
	private ArrayList<Omnibus> parqueo;
	private ArrayList<Conductor> conductores;
	private ArrayList<Pasajero> pasajeros;
	private ArrayList<Viaje> viajes;
	
	public ArrayList<Omnibus> getParqueo() {
		return parqueo;
	}
	public ArrayList<Conductor> getConductores() {
		return conductores;
	}
	public ArrayList<Pasajero> getPasajeros() {
		return pasajeros;
	}
	public ArrayList<Viaje> getViajes() {
		return viajes;
	}
	
	public boolean addOmnibus(Omnibus om) {
		boolean noExist = true;
		for (int i = 0; i < parqueo.size() && noExist ; i++) {
			if(om.getMatricula() == parqueo.get(i).getMatricula())
				noExist = false;
		}
		if(noExist)
			parqueo.add(om);
		
		return noExist;
	}
	public boolean addConductor(Conductor cond) {
		boolean noExist = true;
		for (int i = 0; i < conductores.size() && noExist; i++){
			if(conductores.get(i).getNumID() == cond.getNumID())
				noExist = false;
		}
		if(noExist)
			conductores.add(cond);
		
		return noExist;
	}
	
	public boolean addPasajero(Pasajero pass) {
		boolean noExist = true;
		for (int i = 0; i < pasajeros.size() && noExist; i++){
			if(pasajeros.get(i).getNumID() == pass.getNumID())
				noExist = false;
		}
		if(noExist)
			pasajeros.add(pass);
		
		return noExist;
	}
	
	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
	}
	public Terminal(ArrayList<Omnibus> parqueo,
			ArrayList<Conductor> conductores, ArrayList<Pasajero> pasajeros,
			ArrayList<Viaje> viajes) {
		this.parqueo = parqueo;
		this.conductores = conductores;
		this.pasajeros = pasajeros;
		this.viajes = viajes;
	}

	public ArrayList<Conductor> obtenerMayoresSalarios(){
		ArrayList<Conductor> mayoresSalarios = new ArrayList<>();
		double mayor = 0;
		for(Conductor a: conductores)
			if(a.calcularSalario() > mayor){
				mayoresSalarios.clear();
				mayor = a.calcularSalario();
				mayoresSalarios.add(a);
			}else
				mayoresSalarios.add(a);		
		
		return mayoresSalarios;
	}
	
	
}
