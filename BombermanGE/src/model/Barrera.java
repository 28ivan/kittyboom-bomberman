package model;

public class Barrera extends Duro{
	private boolean destruir;
	
	public Barrera() {
		this.destruir=false;
	}

	
	
	//METODOS CASILLA
	@Override
	public boolean puedeMoverse() {
		return false;
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("Barrera")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setDestruir(boolean pDestruir) {
		this.destruir=pDestruir;
	}
	
	
	@Override
	public void destruir(CasillaTablero pC, int pI, int pJ) {
		if (this.destruir) {
			pC.cambiarCasilla(new Explosion(pI, pJ));
		}
	}
	
	@Override
	public void vaciar(CasillaTablero pC) {
	}
	
	@Override
	public void mover(int pI, int pJ) {
	}
}
