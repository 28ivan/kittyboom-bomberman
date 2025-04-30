package model.BomberMans;

import model.Bombas.BombaFactory;
import model.Tableros.Tablero;

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
	@Override
	public boolean puedeColocarBomba() {
    	if (this.bombas>=10 || !vivo || conBomba) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
	@Override
    public void ponerBomba() {
		if(puedeColocarBomba()) {
    		this.bombas++;
	    	conBomba=true;
	    	Tablero.getTablero().getCasilla(i, j).cambiarCasilla(BombaFactory.getBombaFactory().generate("BSuper", i, j));			
		}

    }
}
