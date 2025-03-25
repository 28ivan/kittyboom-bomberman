package model;

public class Vacio implements Casilla {

	public Vacio() {
	}

	@Override
	public boolean puedeMoverse() {
		return true;
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("Vacio")) {
			return true;
		}else {
			return false;
		}
	}

	
}
