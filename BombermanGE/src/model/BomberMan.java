package model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class BomberMan extends Observable{
	private static BomberMan miBM = new BomberMan();
	private int coordX;
	private int coordY;
	private char dir;
	private int bombasDisp;
	
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
    	boolean disp=false;
    	if (pDir=='u') {
    		this.dir='u';
    		//Comprobar casilla
    		if (disp) {
    			this.coordY--;
    			setChanged();
    	    	notifyObservers(new Object[] {this.coordX,this.coordY});
    		}
    	}
    	else if (pDir=='d') {
    		this.dir='d';
    		//Comprobar casilla
    		if (disp) {
    			this.coordY++;
    			setChanged();
    	    	notifyObservers(new Object[] {this.coordX,this.coordY,dir});
    		}
    	}
    	else if (pDir=='l') {
    		this.dir='l';
    		//Comprobar casilla
    		if (disp) {
    			this.coordX--;
    			setChanged();
    	    	notifyObservers(new Object[] {this.coordX,this.coordY,dir});
    		}
    	}
    	else if (pDir=='r') {
    		this.dir='r';
    		//Comprobar casilla
    		if (disp) {
    			this.coordX++;
    			setChanged();
    	    	notifyObservers(new Object[] {this.coordX,this.coordY,dir});
    		}
    	}
    	setChanged();
    	notifyObservers(new Object[] {this.coordX,this.coordY,dir});
    }
    
    public void colocarBomba() {
    	if (this.bombasDisp==0) return;
    	//new Bomba()
    	this.bombasDisp--;
    	setChanged();
    	notifyObservers(new Object[] {this.coordX,this.coordY,'x'});
    }
	
}
