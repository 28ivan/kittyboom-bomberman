package model.BomberMans;

import model.Bombas.BombaFactory;
import model.Tableros.Tablero;

public class BomberManNegro extends BomberMan{
	private BomberManNegro() {
		super();
		bombasMax=1;
	}
	
	public static BomberMan getBomberMan() {
		if(miBM==null) {
			miBM = new BomberManNegro();
    	}
		return miBM;
	}
	
	public void setBombas(int pNumBombas) {
		bombasMax=pNumBombas;
	}
	
	@Override
	public boolean puedeColocarBomba() {
    	if (this.bombas >= bombasMax || !vivo || conBomba || victoria || Tablero.getTablero().hayBombas()) {
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
