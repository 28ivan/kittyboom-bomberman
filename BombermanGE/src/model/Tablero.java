package model;

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
    
    
    
    public void initializeTablero() {
    	//Colocar Bomberman
    	matriz[0][0] = new BomberMan();
		setChanged();
    	notifyObservers(new Object[] {"/viewController/Bomberman/whitefront1.png",0,0});
    	
    	//Colocar Bloques y enemigos
    	Random r = new Random();
    	for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
            	boolean x =r.nextBoolean();
            	if((i== 0 && j== 0)||(i== 0 && j== 1)||(i== 1 && j== 0)) {
            	}else {
            		if (i % 2 != 0 && j % 2 != 0) {
            			matriz[i][j] = new Duro(i, j);
            			setChanged();
            			notifyObservers(new Object[] {"/viewController/Objetos/hard5.png",i,j});
            		}
            		else if((i!=0 && j!=1) || (i!=1 && j!=0)) {
            			if (x) {
            				matriz[i][j] = new Blando(i, j);
            				setChanged();
            				notifyObservers(new Object[] {"/viewController/Objetos/soft4.png",i,j});
            			}
            		}
            	}
            }
    	}
    }
    
    //MOVIMIENTO
    public boolean movimientoPosible( int pCordX, int pCordY) {
    	boolean posible = true;
    	if(pCordX<0 || pCordX >=17 || pCordY<0 || pCordY>=11 ) {
    		posible = false;
    	}else if(matriz[pCordX][pCordY] instanceof Bloque) {
    		posible = false;
    	}
    	return posible;
    }
    
    //BOMBA
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
    
    //EXPLOSION
    public void destruirBloques(int pCordX, int pCordY) {
    	if( pCordX>=0 && pCordX <17 && pCordY>=0 && pCordY < 11) {
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
    	if(matriz[pCordX][pCordY] instanceof Blando) { 
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
