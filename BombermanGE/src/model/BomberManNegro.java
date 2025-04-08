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
	public void destruir(CasillaTablero pC) {
	}
}
