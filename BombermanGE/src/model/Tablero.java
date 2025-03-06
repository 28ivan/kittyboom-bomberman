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
    	for (int i = 0;  i< fila; i++) {	//filas
            for (int j = 0; j < col; j++) {	//columnas
            	boolean random =r.nextBoolean();
            	if((i==0 && j==0)||(i==0 && j==1)||(i==1 && j==0)) {
            	}else {
            		if (i%2!=0 && j%2!=0) {
            			matriz[i][j] = new Duro(i, j);
            			setChanged();
            			notifyObservers(new Object[] {"/viewController/Objetos/hard5.png",i,j});
            		}
            		else if((i!=0 && j!=1) || (i!=1 && j!=0)) {
            			if (random) {
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
    public boolean movimientoPosible( int pI, int pJ) {
    	boolean posible = true;
    	if(pI<0 || pI >=11 || pJ<0 || pJ>=17 ) {
    		posible = false;
    	}else if(matriz[pI][pJ] instanceof Bloque) {
    		posible = false;
    	}
    	return posible;
    }
    
    //BOMBA
    public void colocarBomba(int pI, int pJ) {
    	matriz[pI][pJ] = new Bomba(pI,pJ);
    }
    
    public void quitarBomba(int pI, int pJ) {
    	matriz[pI][pJ] = null;
    }
    
    public ArrayList<Bomba> getBombas() {
    	ArrayList<Bomba> resul = new ArrayList<>();
    	for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
            	if (i % 2 == 0 && j % 2 == 0 && matriz[i][j] instanceof Bomba) {
            		resul.add((model.Bomba) matriz[i][j]);
            	}
            }
    	}
    	return resul;
    }
    
    //EXPLOSION
    public void destruirBloques(int pI, int pJ) {
    	if( pI>=0 && pI <11 && pJ>=0 && pJ < 17) {
    		if(pI-1>=0) {
    			if (esBloqueBlando(pI-1, pJ)) {
					matriz[pI-1][pJ] = null;
    			}
			}
			if(pI+1<fila) {
				if (esBloqueBlando(pI+1, pJ)) {
					matriz[pI+1][pJ] = null;
				}
			}
			if(pJ-1>=0) {
				if (esBloqueBlando(pI, pJ-1)) {
					matriz[pI][pJ-1] = null;
				}
			}
			if(pJ+1<col) {
				if (esBloqueBlando(pI, pJ+1)) {
					matriz[pI][pJ+1] = null;
				}
			}
    	}
    }
	
    
    public boolean esBloqueBlando( int pI, int pJ) {
    	if(matriz[pI][pJ] instanceof Blando) { 
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
