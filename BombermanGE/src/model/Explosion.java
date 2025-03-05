package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Explosion extends Observable{
	private int cordX;
	private int cordY;
	
	public Explosion(int pCordX, int pCordY) {
		this.cordX = pCordX;
		this.cordY = pCordY;
		activarExplosion();
	}
	
	public void activarExplosion() {
		int xB= BomberMan.getBomberMan().getCordX();
		int yB= BomberMan.getBomberMan().getCordY();
		int fila= Tablero.getTablero().getFila();
		int col= Tablero.getTablero().getCol();
		Tablero.getTablero().destruirBloques(this.cordX,this.cordY);
		ArrayList<Bomba> listaBombas = Tablero.getTablero().getBombas();
		Iterator<Bomba> itr = listaBombas.iterator();
		//bomberman dentro de rango de explosion
		if( this.cordX>=0 && this.cordX <=11 && this.cordY>=0 && this.cordY <= 17) {
    		if(this.cordX-1>=0) {
    			if (this.cordX-1==xB && this.cordY==yB) {
					BomberMan.getBomberMan().morir();
    			}
			}
			if(this.cordX+1<fila) {
				if (this.cordX+1==xB && this.cordY==yB) {
					BomberMan.getBomberMan().morir();
				}
			}
			if(this.cordY-1>=0) {
				if (this.cordX==xB && this.cordY-1==yB) {
					BomberMan.getBomberMan().morir();
				}
			}
			if(this.cordY+1<col) {
				if (this.cordX==xB && this.cordY-1==yB) {
					BomberMan.getBomberMan().morir();
				}
			}
    	}
		//concatenar bombas
		while(itr.hasNext()) {
			Bomba bomba = itr.next();
			int x= bomba.getCordX();
			int y=bomba.getCordY();
			if( this.cordX>=0 && this.cordX <=11 && this.cordY>=0 && this.cordY <= 17) {
	    		if(this.cordX-1>=0) {
	    			if (this.cordX-1==x && this.cordY==y) {
						bomba.activar();
	    			}
				}
				if(this.cordX+1<fila) {
					if (this.cordX+1==x && this.cordY==y) {
						bomba.activar();
					}
				}
				if(this.cordY-1>=0) {
					if (this.cordX==x && this.cordY-1==y) {
						bomba.activar();
					}
				}
				if(this.cordY+1<col) {
					if (this.cordX==x && this.cordY-1==y) {
						bomba.activar();
					}
				}
	    	}
		}
	}
}
