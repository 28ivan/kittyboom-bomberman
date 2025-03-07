package model;

public class BomberMan {
	private static BomberMan miBM ;
	private int cordX;
	private int cordY;
	private char dir;
	private int bombasDisp;
	private boolean vivo;
	
	public BomberMan() {
		this.cordX = 0;
		this.cordY = 0;
		this.dir='d';
		this.bombasDisp=10;
		this.vivo = true;
		
	}
	
	public static BomberMan getBomberMan() {
		if(miBM==null) {
			miBM = new BomberMan();
    	}
		return miBM;
	}
	
	public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }
    
    public boolean getEstado() {
    	return vivo;
    }
    
    public void mover(int pI, int pJ) {
    	cordX = pI;
    	cordY = pJ;
    }
    
    public void colocarBomba() {
    	if (this.bombasDisp==0 || !vivo) return;
    	Bomba bomba = new Bomba(this.cordX, this.cordY);
    	Tablero.getTablero().colocarBomba(this.cordX,this.cordY);
    	bomba.activar();
    	this.bombasDisp--;
    	//setChanged();
    	//notifyObservers(new Object[] {'b',this.cordX,this.cordY});
    }
	
    public void morir() {
    	vivo=false;
    	//setChanged();
    	//notifyObservers(new Object[] {'x',this.cordX,this.cordY});
    }
}
