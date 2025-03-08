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
    	notifyObservers(new Object[] {"d",0,0});
    	
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
            			notifyObservers(new Object[] {"duro",i,j});
            		}
            		else if((i!=0 && j!=1) || (i!=1 && j!=0)) {
            			if (random) {
            				matriz[i][j] = new Blando(i, j);
            				setChanged();
            				notifyObservers(new Object[] {"blando",i,j});
            			}
            		}
            	}
            }
    	}
    }
    
    //MOVIMIENTO
    public void moverBomberman(char pDir) {
    	if (BomberMan.getBomberMan().getEstado()) {
    		
    		int pI = BomberMan.getBomberMan().getI();
    		int pJ = BomberMan.getBomberMan().getJ();
    		int prevI = pI;
    		int prevJ = pJ;
    		
    		if (pDir=='u') {
        		if(movimientoPosible(pI-1,pJ)) {
        			BomberMan.getBomberMan().mover(pI-1,pJ);
        			if (!(matriz[pI][pJ] instanceof Bomba)) {
        				matriz[pI][pJ] = null;
        			}
        			matriz[pI-1][pJ] = BomberMan.getBomberMan();
        			setChanged();
                	notifyObservers(new Object[] {"delete",pI,pJ});
           			pI--;
        		}
        	}
        	else if (pDir=='d') {
        		if(movimientoPosible(pI+1,pJ)) {
        			BomberMan.getBomberMan().mover(pI+1,pJ);
        			if (!(matriz[pI][pJ] instanceof Bomba)) {
        				matriz[pI][pJ] = null;
        			}
        			matriz[pI+1][pJ] = BomberMan.getBomberMan();
        			setChanged();
                	notifyObservers(new Object[] {"delete",pI,pJ});
        			pI++;
        		}
        	}
        	else if (pDir=='r') {
        		if(movimientoPosible(pI,pJ+1)) {
        			BomberMan.getBomberMan().mover(pI,pJ+1);
        			if (!(matriz[pI][pJ] instanceof Bomba)) {
        				matriz[pI][pJ] = null;
        			}
        			matriz[pI][pJ+1] = BomberMan.getBomberMan();
        			setChanged();
                	notifyObservers(new Object[] {"delete",pI,pJ});
        			pJ++;
        		}
        	}
        	else if (pDir=='l') {
        		if(movimientoPosible(pI,pJ-1)) {
        			BomberMan.getBomberMan().mover(pI,pJ-1);
        			if (!(matriz[pI][pJ] instanceof Bomba)) {
        				matriz[pI][pJ] = null;
        			}
        			matriz[pI][pJ-1] = BomberMan.getBomberMan();
        			setChanged();
                	notifyObservers(new Object[] {"delete",pI,pJ});
        			pJ--;
        		}
        	}
    		
    		
    		if (matriz[prevI][prevJ] instanceof Bomba) {
    			if (prevI!=pI || prevJ!=pJ) {
    				setChanged();
    				notifyObservers(new Object[]{"bomba", prevI, prevJ});
    				setChanged();
    	        	notifyObservers(new Object[] {String.valueOf(pDir),pI,pJ});
    			}
    			else {
    				setChanged();
    				notifyObservers(new Object[]{"bmBomba", prevI, prevJ});
    			}
            }
    		else {
    			setChanged();
            	notifyObservers(new Object[] {String.valueOf(pDir),pI,pJ});
    		}
    	}
    	
    }
    
    public boolean movimientoPosible( int pI, int pJ) {
    	boolean posible = true;
    	if(pI<0 || pI >=11 || pJ<0 || pJ>=17 ) {
    		posible = false;
    	}else if(matriz[pI][pJ] instanceof Bloque || matriz[pI][pJ] instanceof Bomba) {
    		posible = false;
    	}
    	return posible;
    }
    
    //BOMBA
    public void colocarBomba(int pI, int pJ) {
        if (BomberMan.getBomberMan().puedeColocarBomba()) {
            if (matriz[pI][pJ] instanceof Bomba) {
                return;
            }
            matriz[pI][pJ] = new Bomba(pI, pJ);
            Bomba bomba = (model.Bomba) matriz[pI][pJ];
            //bomba.activar(); habria q activar la bomba en algun momento
            setChanged();
            notifyObservers(new Object[] { "bmBomba", pI, pJ });
        }
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
//			setChanged();
//            notifyObservers(new Object[] { "delete", pI, pJ });
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
