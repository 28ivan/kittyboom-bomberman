package model;

public abstract class Bloque implements Casilla {
	
	protected Bloque(){
	}
	

	
	//METODOS CASILLA
	@Override
	public boolean isVivo() {
    	return false;
    }
}
