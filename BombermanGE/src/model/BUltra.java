package model;

public class BUltra extends Bomba {

	public BUltra(int pI, int pJ) {
		super(pI, pJ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean puedeMoverse() {
		return false;
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("BUltra")) {
			return true;
		}else {
			return false;
		}
	}

}
