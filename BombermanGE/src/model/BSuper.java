package model;

public class BSuper extends Bomba {

	public BSuper(int pI, int pJ) {
		super(pI, pJ);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean puedeMoverse() {
		return false;
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("BSuper")) {
			return true;
		}else {
			return false;
		}
	}

}
