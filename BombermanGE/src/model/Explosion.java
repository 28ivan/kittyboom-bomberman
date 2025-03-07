package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Explosion extends Observable{
	private int i;
	private int j;
	
	public Explosion(int pI, int pJ) {
		this.i = pI;
		this.j = pJ;
		activarExplosion();
	}
	
	public void activarExplosion() {
		int iB= BomberMan.getBomberMan().getI();
		int jB= BomberMan.getBomberMan().getJ();
		int fila= Tablero.getTablero().getFila();
		int col= Tablero.getTablero().getCol();
		Tablero.getTablero().destruirBloques(this.i,this.j);
		ArrayList<Bomba> listaBombas = Tablero.getTablero().getBombas();
		Iterator<Bomba> itr = listaBombas.iterator();
		//bomberman dentro de rango de explosion
		if( this.i>=0 && this.i <=11 && this.j>=0 && this.j <= 17) {
    		if(this.i-1>=0) {
    			if (this.i-1==iB && this.j==jB) {
					BomberMan.getBomberMan().morir();
    			}
			}
			if(this.i+1<fila) {
				if (this.i+1==iB && this.j==jB) {
					BomberMan.getBomberMan().morir();
				}
			}
			if(this.j-1>=0) {
				if (this.i==iB && this.j-1==jB) {
					BomberMan.getBomberMan().morir();
				}
			}
			if(this.j+1<col) {
				if (this.i==iB && this.j-1==jB) {
					BomberMan.getBomberMan().morir();
				}
			}
    	}
		//concatenar bombas
		while(itr.hasNext()) {
			Bomba bomba = itr.next();
			int iX=bomba.getI();
			int jX=bomba.getJ();
			if( this.i>=0 && this.i <=11 && this.j>=0 && this.j <= 17) {
	    		if(this.i-1>=0) {
	    			if (this.i-1==iX && this.j==jX) {
						bomba.activar();
	    			}
				}
				if(this.i+1<fila) {
					if (this.i+1==iX && this.j==jX) {
						bomba.activar();
					}
				}
				if(this.j-1>=0) {
					if (this.i==iX && this.j-1==jX) {
						bomba.activar();
					}
				}
				if(this.j+1<col) {
					if (this.i==iX && this.j-1==jX) {
						bomba.activar();
					}
				}
	    	}
		}
	}
}
