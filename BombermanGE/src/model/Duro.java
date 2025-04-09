package model;

public class Duro extends Bloque{
	
	public Duro() {
	}

	@Override
	public boolean puedeMoverse() {
		return false;
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("Duro")) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public void destruir(CasillaTablero pC) {
	}
	
	@Override
	public void mover(int pI, int pJ) {
	}
}
