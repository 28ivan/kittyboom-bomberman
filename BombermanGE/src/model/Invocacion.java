package model;

public class Invocacion extends Duro {
	private int tiempoInvocacion;

	public Invocacion() {
		this.tiempoInvocacion=1500;//no lo cambies, es lo q dura la animacion
	}
	
	@Override
	public boolean puedeMoverse() {
		return false;
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("Invocacion")) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void destruir(CasillaTablero pC, int pI, int pJ) {
	}

	@Override
	public void vaciar(CasillaTablero pC) {
	}

	@Override
	public void mover(int pI, int pJ) {
	}

}
