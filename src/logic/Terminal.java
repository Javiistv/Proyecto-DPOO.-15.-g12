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
			if(om.getMatricula().equals(parqueo.get(i).getMatricula()))
				noExist = false;
		}
		if(noExist)
			this.parqueo.add(om);
		
		return noExist;
	}
	public boolean addConductor(Conductor cond) {
		boolean noExist = true;
		for (int i = 0; i < conductores.size() && noExist; i++){
			if(cond.getNumID().equals(conductores.get(i).getNumID()))
				noExist = false;
		}
		for (int i = 0; i < pasajeros.size() && noExist; i++){
			if(cond.getNumID().equals(pasajeros.get(i).getNumID()))
				noExist = false;
		}
		if(noExist)
			conductores.add(cond);
		
		return noExist;
	}
	
	public boolean addPasajero(Pasajero pass) {
		boolean noExist = true;
		for (int i = 0; i < pasajeros.size() && noExist; i++){
			if(pasajeros.get(i).getNumID().equals(pass.getNumID()))
				noExist = false;
		}
		for (int i = 0; i < conductores.size() && noExist; i++){
			if(conductores.get(i).getNumID().equals(pass.getNumID()))
				noExist = false;
		}
		if(noExist)
			pasajeros.add(pass);
		return noExist;
	}
	
	public void addViajes(Viaje v) {
		viajes.add(v);
		
	}
	public Terminal() {
		this.parqueo = new ArrayList<>();
		this.conductores =new ArrayList<>();
		this.pasajeros = new ArrayList<>();
		this.viajes = new ArrayList<> ();
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
	
	public Conductor buscarConductorPorId(String id) {
		boolean encontro = false;
		Conductor nuevo = null;
		for (int i = 0; i < conductores.size() && !encontro ; i++){
			if(conductores.get(i).getNumID().equals(id)){
				encontro = true;
			    nuevo = conductores.get(i);
			}
		}
		return nuevo;    
	}
	
	public Viaje buscarViajePorID(int id){
		boolean encontro = false;
		Viaje nuevo = null;
		for(int i = 0; i < viajes.size() && !encontro; i++)
			if(viajes.get(i).getId() == id){
				encontro = true;
				nuevo = viajes.get(i);
			}
		return nuevo;
	}
	
	public Omnibus buscarOmnibusxMatricula(String matricula){
		Omnibus select = null;
		for(int i = 0; i < parqueo.size() && select == null; i++){
			if(parqueo.get(i).getMatricula().equalsIgnoreCase(matricula))
				select = parqueo.get(i);
		}
		return select;
	}
}
