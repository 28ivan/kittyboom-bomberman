package model;

public class Blando extends Bloque {
	
	public Blando() {
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("Blando")) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean puedeMoverse() {
		return false;
	}
	
	
	@Override
	public void destruir(CasillaTablero pC) {
		pC.cambiarCasilla(new Vacio());
	}
	
	@Override
	public void mover(int pI, int pJ) {
	}
}
