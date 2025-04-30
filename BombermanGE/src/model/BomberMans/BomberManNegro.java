package model.BomberMans;

import model.Bombas.BombaFactory;
import model.Tableros.Tablero;

public class BomberManNegro extends BomberMan{
	private BomberManNegro() {
		super();
	}
	
	public static BomberMan getBomberMan() {
		if(miBM==null) {
			miBM = new BomberManNegro();
    	}
		return miBM;
	}
	@Override
	public boolean puedeColocarBomba() {
    	if (this.bombas>=1 || !vivo || conBomba) {
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
	    	Tablero.getTablero().getCasilla(i, j).cambiarCasilla(BombaFactory.getBombaFactory().generate("BUltra", i, j));
		}
    }
}
