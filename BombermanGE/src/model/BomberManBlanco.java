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
}
