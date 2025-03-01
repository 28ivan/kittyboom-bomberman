package model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class BomberMan extends Observable{
	private static BomberMan miBM = new BomberMan();
	private int coordX;
	private int coordY;
	private char dir;
	private int bombasDisp;
	private boolean vivo;
	
	public BomberMan() {
		this.coordX = 0;
		this.coordY = 0;
		this.dir='d';
		this.bombasDisp=10;
		
	}
	
	public static BomberMan getBomberMan() {
		return miBM;
	}
	
	public int getCordX() {
        return coordX;
    }

    public int getCordY() {
        return coordY;
    }
    
    public void mover(char pDir) {
    	if (!vivo) return;
    	boolean disp=false;
    	if (pDir=='u') {
    		this.dir='u';
    		//Comprobar casilla
    		if (disp) {
    			this.coordY--;
    		}
    	}
    	else if (pDir=='d') {
    		this.dir='d';
    		//Comprobar casilla
    		if (disp) {
    			this.coordY++;
    		}
    	}
    	else if (pDir=='l') {
    		this.dir='l';
    		//Comprobar casilla
    		if (disp) {
    			this.coordX--;
    		}
    	}
    	else if (pDir=='r') {
    		this.dir='r';
    		//Comprobar casilla
    		if (disp) {
    			this.coordX++;
    		}
    	}
    	setChanged();
    	notifyObservers(new Object[] {dir,this.coordX,this.coordY});
    }
    
    public void colocarBomba() {
    	if (this.bombasDisp==0 || !vivo) return;
    	Bomba bomba = new Bomba(this.coordX, this.coordY, 2);
    	bomba.activar();
    	this.bombasDisp--;
    	setChanged();
    	notifyObservers(new Object[] {'b',this.coordX,this.coordY});
    }
	
    public void morir() {
    	vivo=false;
    	setChanged();
    	notifyObservers(new Object[] {'x',this.coordX,this.coordY});
    }
}
