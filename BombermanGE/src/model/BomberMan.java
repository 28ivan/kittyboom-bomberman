package model;

public class BomberMan {
	private static BomberMan miBM ;
	private int i;
	private int j;
	private char dir;
	private int bombas;
	private boolean vivo;
	
	public BomberMan() {
		this.i = 0;
		this.j = 0;
		this.dir='d';
		this.bombas = 0;
		this.vivo = true;
		
	}
	
	public static BomberMan getBomberMan() {
		if(miBM==null) {
			miBM = new BomberMan();
    	}
		return miBM;
	}
	
	public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public boolean getEstado() {
    	return vivo;
    }
    
    public void mover(int pI, int pJ) {
    	i = pI;
    	j = pJ;
    }
    
    public boolean puedeColocarBomba() {
    	if (this.bombas==10 || !vivo) {
    		return false;
    	}
    	else {
    		this.bombas++;
    		return true;
    	}
    }
	
    public void morir() {
    	vivo=false;
    }
    
    public void bombaExplotada() {
    	bombas--;
    }
}
