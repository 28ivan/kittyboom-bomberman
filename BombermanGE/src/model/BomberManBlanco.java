package model;

public class BomberManBlanco extends BomberMan{
	
	public static BomberMan getBomberMan() {
		if(miBM==null) {
			miBM = new BomberManBlanco();
    	}
		return miBM;
	}
}
