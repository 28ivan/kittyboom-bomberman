package model;


import javax.swing.*;

import viewController.BloqueBlando;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Tablero extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas
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
            	}
            	else if (i % 2 != 0 && j % 2 != 0) {
            		matriz[i][j] = new Duro(i, j); 
            	}
            	else if((i!=0 && j!=1) || (i!=1 && j!=0)) {
            		if (x) {
            			matriz[i][j] = new Blando(i, j); 
            		}
            	}
            }
    	}
    }
    
    public Tablero getTablero() {
    	if(miTablero==null) {
    		miTablero = new Tablero();
    	}
    	return miTablero;
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
    	if(matriz[pCordX][pCordY] instanceof BloqueBlando) { //falta la referencia de bloque blando
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}