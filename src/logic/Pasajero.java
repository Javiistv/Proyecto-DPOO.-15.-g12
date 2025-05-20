package logic;

public class Pasajero{
	
	private String nombre;
	private String numID;
	
	
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
		this.numID = numID;
	}
	
	public Pasajero(String nombre, String numID) {
		setNombre(nombre);
		setNumID(numID);
	}
	
	
	

}
