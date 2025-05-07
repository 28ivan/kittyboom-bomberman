package model.BomberMans;

import model.Bombas.BombaFactory;
import model.Tableros.Tablero;

public class BomberManBlanco extends BomberMan{
	private int bombasMax=0;
	private BomberManBlanco() {
		super();
		bombasMax=10;
	}
	
	public static BomberMan getBomberMan() {
		if(miBM==null) {
			miBM = new BomberManBlanco();
    	}
		return miBM;
	}
	@Override
	public boolean puedeColocarBomba() {
    	if (this.bombas>=bombasMax || !vivo || conBomba || victoria || Tablero.getTablero().hayBombas()) {
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
	
	public void soyBomberman() {
		System.out.println("blanco");
	}
}
