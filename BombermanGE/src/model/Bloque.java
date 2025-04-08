package model;

public abstract class Bloque implements Casilla {
	
	protected Bloque(){
			
	}
	
	@Override
	public boolean isVivo() {
    	return false;
    }

}
