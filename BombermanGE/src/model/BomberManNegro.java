package model;

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
    		this.bombas++;
    		return true;
    	}
    }
	@Override
    public void ponerBomba() {
		if(puedeColocarBomba()) {
			conBomba=true;
	    	Tablero.getTablero().getCasilla(i, j).cambiarCasilla(BombaFactory.getBombaFactory().generate("BUltra", i, j));
		}
    }
}
