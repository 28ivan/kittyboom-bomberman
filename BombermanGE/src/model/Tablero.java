package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
@SuppressWarnings({"deprecation"})

public class Tablero extends Observable {

	private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas
    private static Tablero miTablero = null;
    private Casilla[][] matriz = new Casilla[fila][col];
    
    private Tablero() {
    }
    
    public static Tablero getTablero() {
    	if(miTablero==null) {
    		miTablero = new Tablero();
    	}
    	return miTablero;
    }
    
    
    public void initializeTablero() {
    	//Colocar Bomberman
    	matriz[0][0] = BomberManBlanco.getBomberMan();
		setChanged();
    	notifyObservers(new Object[] {"d",0,0});
    	
    	//Inicializar pantalla con todos los objetos necesarios
    	Random r = new Random();
    	for (int i = 0;  i< fila; i++) {	//filas
            for (int j = 0; j < col; j++) {	//columnas
            	boolean random =r.nextBoolean();
            	if((i==0 && j==1)||(i==1 && j==0)) {
            		matriz[i][j] = BloqueFactory.getBloqueFactory().generate("Vacio");
            	}else {
            		if (i%2!=0 && j%2!=0) {
            			matriz[i][j] = BloqueFactory.getBloqueFactory().generate("Duro");
            			setChanged();
            			notifyObservers(new Object[] {"duro",i,j});
            		}
            		else if((i!=0 && j!=1) || (i!=1 && j!=0)) {
            			if (random) {
            				matriz[i][j] = BloqueFactory.getBloqueFactory().generate("Blando");
            				setChanged();
            				notifyObservers(new Object[] {"blando",i,j});
            			}else {
            				matriz[i][j] = BloqueFactory.getBloqueFactory().generate("Vacio");
            			}
            		}
            	}
            }
    	}
    }
    
    //MOVIMIENTO
    public void moverBomberman(char pDir) {
    	if (BomberManBlanco.getBomberMan().getEstado()) {
    		
    		int pI = BomberManBlanco.getBomberMan().getI();
    		int pJ = BomberManBlanco.getBomberMan().getJ();
    		int prevI = pI;
    		int prevJ = pJ;
    		Casilla c;
    		String accion = String.valueOf(pDir);
    		
    		if (pDir=='u') {
        		if(movimientoPosible(pI-1,pJ)) {
        			BomberManBlanco.getBomberMan().mover(pI-1,pJ);
        			c = matriz[pI-1][pJ];
        			if (c.esObjeto("Explosion")) {
        				BomberManBlanco.getBomberMan().morir();
        				accion="muerto";
        			}
        			else {
        				c = matriz[pI][pJ];
	        			if (!c.esObjeto("Bomba")) {
	        				matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
	        			}
        			} 
	        		matriz[pI-1][pJ] = BomberManBlanco.getBomberMan();
	        		setChanged();
	                notifyObservers(new Object[] {"delete",pI,pJ});
           			pI--;
        		}
        	}
        	else if (pDir=='d') {
        		if(movimientoPosible(pI+1,pJ)) {
        			BomberManBlanco.getBomberMan().mover(pI+1,pJ);
        			c = matriz[pI+1][pJ];
        			if (c.esObjeto("Explosion")) {
        				BomberManBlanco.getBomberMan().morir();
        				accion="muerto";
        			}
        			else {
        				c = matriz[pI][pJ];
	        			if ((!c.esObjeto("Bomba"))) {
	        				matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
	        			}
        			}
	        		matriz[pI+1][pJ] = BomberManBlanco.getBomberMan();
	        		setChanged();
                	notifyObservers(new Object[] {"delete",pI,pJ});
        			pI++;
        		}
        	}
        	else if (pDir=='r') {
        		if(movimientoPosible(pI,pJ+1)) {
        			BomberManBlanco.getBomberMan().mover(pI,pJ+1);
        			c = matriz[pI][pJ+1];
        			if (c.esObjeto("Explosion")) {
        				BomberManBlanco.getBomberMan().morir();
        				accion="muerto";
        			}
        			else {
        				c = matriz[pI][pJ];
	        			if (!c.esObjeto("Bomba")) {
	        				matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
	        			}
        			}	
	        		matriz[pI][pJ+1] = BomberManBlanco.getBomberMan();
        			setChanged();
                	notifyObservers(new Object[] {"delete",pI,pJ});
        			pJ++;
        		}
        	}
        	else if (pDir=='l') {
        		if(movimientoPosible(pI,pJ-1)) {
        			BomberManBlanco.getBomberMan().mover(pI,pJ-1);
        			c = matriz[pI][pJ-1];
        			if (c.esObjeto("Explosion")) {
        				BomberManBlanco.getBomberMan().morir();
        				accion="muerto";
        			}
        			else {
        				c = matriz[pI][pJ];
	        			if (!c.esObjeto("Bomba")) {
	        				matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
	        			}
        			}
	        		matriz[pI][pJ-1] = BomberManBlanco.getBomberMan();
        			setChanged();
                	notifyObservers(new Object[] {"delete",pI,pJ});
        			pJ--;
        		}
        	}
    		c = matriz[prevI][prevJ];
    		if (c.esObjeto("Bomba")) {
    			if (prevI!=pI || prevJ!=pJ) {
    				setChanged();
    				notifyObservers(new Object[]{"bomba", prevI, prevJ});
    				setChanged();
    	        	notifyObservers(new Object[] {accion,pI,pJ});
    			}
    			else {
    				setChanged();
    				notifyObservers(new Object[]{"bmBomba", prevI, prevJ});
    			}
            }
    		else {
    			setChanged();
            	notifyObservers(new Object[] {accion,pI,pJ});
    		}
    	}
    }
    
    public boolean movimientoPosible( int pI, int pJ) {
    	boolean posible = true;
    	if(pI<0 || pI >=11 || pJ<0 || pJ>=17 ) {
    		posible = false;
    	}else{
    		Casilla c = matriz[pI][pJ];
    		posible = c.puedeMoverse();
    	}
    	return posible;
    }
    
    //BOMBA
    public void colocarBomba() {
    	int pI = BomberManBlanco.getBomberMan().getI();
    	int pJ = BomberManBlanco.getBomberMan().getJ();
        if (BomberManBlanco.getBomberMan().puedeColocarBomba()) {
        	Casilla c = matriz[pI][pJ];
            if (c.esObjeto("Bomba")) {
                return;
            }
            matriz[pI][pJ] = new BSuper(pI, pJ);
            setChanged();
            notifyObservers(new Object[] {"bmBomba", pI, pJ });
        }
    }
    
    public void quitarBomba(int pI, int pJ) {
		matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
    	setChanged();
        notifyObservers(new Object[] {"delete", pI, pJ });
    }
    
    public ArrayList<Bomba> getBombas() {
    	Casilla c;
    	ArrayList<Bomba> resul = new ArrayList<>();
    	for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
            	c = matriz[i][j];
            	if (i % 2 == 0 && j % 2 == 0 && c.esObjeto("Bomba")) {
            		resul.add((model.Bomba) matriz[i][j]);
            	}
            }
    	}
    	return resul;
    }
    
    //EXPLOSION
    public void destruirBloques(int pI, int pJ) {
		BomberManBlanco.getBomberMan().bombaExplotada();
        quitarBomba(pI, pJ);
        procesarExplosion(pI, pJ);
        procesarExplosion(pI - 1, pJ);
        procesarExplosion(pI + 1, pJ);
        procesarExplosion(pI, pJ - 1);
        procesarExplosion(pI, pJ + 1);
    }
    
    private void procesarExplosion(int pI, int pJ) {
    	if (pI < 0 || pI >= fila || pJ < 0 || pJ >= col) return;
    	Casilla c = matriz[pI][pJ];
        if (c.esObjeto("Blando")) {
        	setChanged();
            notifyObservers(new Object[] {"delete", pI, pJ});
            generarExplosion(pI, pJ);
            matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
        } else if (c.esObjeto("Bomba")) {
            Bomba bomba = (Bomba) matriz[pI][pJ];
            bomba.explotar();
        } else if (c.esObjeto("BomberMan")||esBomberMan(pI, pJ)){
        	BomberManBlanco.getBomberMan().morir();
            setChanged();
            notifyObservers(new Object[] {"muerto", pI, pJ});
        } else if (c.esObjeto("Vacio")) {
            generarExplosion(pI, pJ);
        }
        return;
    }
    
    
    private void generarExplosion(int pI, int pJ) {
    	Casilla c = matriz[pI][pJ];
    	if (c.esObjeto("Explosion")) {
         	Tablero.getTablero().apagar(pI, pJ);
        }
        matriz[pI][pJ] = new Explosion(pI,pJ);
        setChanged();
        notifyObservers(new Object[] {"explosion", pI, pJ});
    }
    

    public void apagar(int pI, int pJ) {
    	Casilla c = matriz[pI][pJ];
    	if (!c.esObjeto("Explosion")) {
        	return;
        }
		matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
		setChanged();
        notifyObservers(new Object[] {"delete", pI, pJ});
    }
    
    
    public boolean esBomberMan(int pI, int pJ) {
    	if (BomberManBlanco.getBomberMan().getI()==pI && BomberManBlanco.getBomberMan().getJ()==pJ) {
    		return true;
    	}
    	return false;
    }
}
