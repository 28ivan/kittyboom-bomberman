package model;

public class BomberManBlanco extends BomberMan{
	private BomberManBlanco() {
		super();
	}
	
	public static BomberMan getBomberMan() {
		if(miBM==null) {
			miBM = new BomberManBlanco();
    	}
		return miBM;
	}
	
    
    public void ponerBomba() {
    	conBomba=true;
    	Tablero.getTablero().getCasilla(i, j).cambiarCasilla(BombaFactory.getBombaFactory().generate("BSuper", i, j));
    }
}
