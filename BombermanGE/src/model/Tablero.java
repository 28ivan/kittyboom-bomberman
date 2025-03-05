package model;


import javax.swing.*;

import viewController.BloqueBlando;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Tablero extends Observable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas
	private static final Object Bomba = null;
    private static Tablero miTablero = null;
    private Object[][] matriz = new Object[fila][col];
    
    private Tablero() {
        initialize();
    }
    
    private void initialize() {
    	
    	Random r = new Random();

    	for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
            	boolean x =r.nextBoolean();
            	if(i==0 && j==0) {
            		matriz[0][0] = new BomberMan();
            		setChanged();
                	notifyObservers(new Object[] {"/viewController/sprites_bomberman/Pantalla/whitefront1.png",i,j});
            	}
            	else if (i % 2 != 0 && j % 2 != 0) {
            		matriz[i][j] = new Duro(i, j);
            		setChanged();
                	notifyObservers(new Object[] {"viewController/sprites_bomberman/Pantalla/objetos/hard5.png",i,j});
            	}
            	else if((i!=0 && j!=1) || (i!=1 && j!=0)) {
            		if (x) {
            			matriz[i][j] = new Blando(i, j);
            			setChanged();
            	    	notifyObservers(new Object[] {"viewController/sprites_bomberman/Pantalla/objetos/soft4.png",i,j});
            		}
            	}
            }
    	}

    }
    
    public static Tablero getTablero() {
    	if(miTablero==null) {
    		miTablero = new Tablero();
    	}
    	return miTablero;
    }
    
    public int getFila() { 
    	return this.fila;
    }
    
    public int getCol() { 
    	return this.col;
    }
    
    public void destruirBloques(int pCordX, int pCordY) {
    	if( pCordX>=0 && pCordX <=11 && pCordY>=0 && pCordY <= 17) {
    		if(pCordX-1>=0) {
    			if (esBloqueBlando(pCordX, pCordY)) {
					matriz[pCordX-1][pCordY] = null;
    			}
			}
			if(pCordX+1<fila) {
				if (esBloqueBlando(pCordX, pCordY)) {
					matriz[pCordX+1][pCordY] = null;
				}
			}
			if(pCordY-1>=0) {
				if (esBloqueBlando(pCordX, pCordY)) {
					matriz[pCordX][pCordY-1] = null;
				}
			}
			if(pCordY+1<col) {
				if (esBloqueBlando(pCordX, pCordY)) {
					matriz[pCordX][pCordY+1] = null;
				}
			}
    	}
    }
    
    public boolean esBloqueBlando( int pCordX, int pCordY) {
    	if(matriz[pCordX][pCordY] instanceof BloqueBlando) { 
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean movimientoPosible( int pCordX, int pCordY) {
    	boolean posible = true;
    	if(pCordX<0 || pCordX >17 || pCordY<0 || pCordY>11 ) {
    		posible = false;
    	}else if(matriz[pCordX][pCordY] instanceof Bloque) {
    		posible = false;
    	}
    	return posible;
    }
    
    public void colocarBomba(int pCordX, int pCordY) {
    	matriz[pCordX][pCordY] = new Bomba(pCordX,pCordY);
    }
    
    public void quitarBomba(int pCordX, int pCordY) {
    	matriz[pCordX][pCordY] = null;
    }
    
    public ArrayList<Bomba> getBombas() {
    	ArrayList<Bomba> resul = new ArrayList<>();
    	for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
            	if (i % 2 == 0 && j % 2 == 0 && matriz[i][j].equals(Bomba)) {
            		resul.add((model.Bomba) matriz[i][j]);
            	}
            }
    	}
    	return resul;
    }
}