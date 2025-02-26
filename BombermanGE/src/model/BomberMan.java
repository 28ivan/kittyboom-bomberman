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
	
	public static BomberMan getGestorSemaforos() {
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
    		if (disp) this.coordY--;
    	}
    	else if (pDir=='d') {
    		this.dir='d';
    		//Comprobar casilla
    		if (disp) this.coordY++;
    	}
    	else if (pDir=='l') {
    		this.dir='l';
    		//Comprobar casilla
    		if (disp) this.coordX--;
    	}
    	else if (pDir=='r') {
    		this.dir='r';
    		//Comprobar casilla
    		if (disp) this.coordX++;
    	}
    	setChanged();
    	notifyObservers(new Object[] {this.coordX,this.coordY,dir});
    }
    
    public void colocarBomba() {
    	if (this.bombasDisp==0) return;
    	boolean disp=false;
    	//Comprobar casilla
    	if (this.dir=='u') {
    		//Comprobar casilla
    		if (disp) { 
    			//new Bomba()
    			this.bombasDisp--;
    		}
    	}
    	else if (this.dir=='d') {
    		//Comprobar casilla
    		if (disp) { 
    			//new Bomba()
    			this.bombasDisp--;
    		}
    	}
    	else if (this.dir=='l') {
    		//Comprobar casilla
    		if (disp) { 
    			//new Bomba()
    			this.bombasDisp--;
    		}
    	}
    	else if (this.dir=='r') {
    		//Comprobar casilla
    		if (disp) { 
    			//new Bomba()
    			this.bombasDisp--;
    		}
    	}
    }
	
}
