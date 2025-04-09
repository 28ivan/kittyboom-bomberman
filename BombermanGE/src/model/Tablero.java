package model;

import java.util.Random;

public class Tablero {

	private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas  
    private static Tablero miTablero = null;
    public CasillaTablero[][] matriz = new CasillaTablero[fila][col];
    private StrategyBloques stBloques;
    
    private Tablero() {
    	for (int i = 0;  i< fila; i++) {
            for (int j = 0; j < col; j++) {
            	matriz[i][j]=new CasillaTablero(i,j);
            }
    	}
    }
    
    public static Tablero getTablero() {
    	if(miTablero==null) {
    		miTablero = new Tablero();
    	}
    	return miTablero;
    }
    
    
    public void initializeTablero() {
    	colocarBomberMan();
    	colocarBloquesDuros();
    	changeStrategy(new stBloquesClassic());
    	colocarBloquesBlandos();
    	colocarEnemigos();
    }
    
    public void colocarBomberMan() {
    	//Colocar Bomberman
    	matriz[0][0].cambiarCasilla("BomberMan");
    }
    
    public void colocarBloquesDuros() {
    	for (int i = 0;  i< fila; i++) {	//filas
            for (int j = 0; j < col; j++) {	//columnas
            	if(!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)) {
            		if (i%2!=0 && j%2!=0) {
            			matriz[i][j].cambiarCasilla("Duro");
            		} 
            	}
            }
    	}
    }
    
    public void colocarBloquesBlandos() {
    	stBloques.colocarBloquesBlandos();
    }
    
	public void colocarBloquesBlandosC() {
    	Random r = new Random();
    	for (int i = 0;  i< fila; i++) {	//filas
            for (int j = 0; j < col; j++) {	//columnas
            	boolean random =r.nextBoolean();
            	if(!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)) {
            		if (i%2!=0 && j%2!=0) {
            		}else if (random) {
            			matriz[i][j].cambiarCasilla("Blando");
            		}
            	}
            }
    	}
	 }
    
    public void changeStrategy(StrategyBloques stB){
    	stBloques = stB;
    }

    public void colocarEnemigos() {
	    int enemigosColocados = 0;
		Random r = new Random();
	
		while (enemigosColocados < 8) {
		    int i = r.nextInt(fila);
		    int j = r.nextInt(col);
	
		    // Asegúrate de que no está en zona prohibida y que la casilla es "Vacio"
		    if (!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)
		        && matriz[i][j].esObjeto("Vacio")) {
		    	matriz[i][j].cambiarCasilla("Enemigo");
		        enemigosColocados++;
		    }
		}
	}
    
    
    
    
    
    
    
    public void moverEnemigo(int pI, int pJ) {
        if (pI < 0 || pI >= fila || pJ < 0 || pJ >= col) return;
        if (!matriz[pI][pJ].esObjeto("Enemigo")) return;

        CasillaTablero c = matriz[pI][pJ];
        if (!c.isVivo()) return;

        int nuevoI = c.getI();
        int nuevoJ = c.getJ();

        int bombermanI = BomberManBlanco.getBomberMan().getI();
        int bombermanJ = BomberManBlanco.getBomberMan().getJ();

        if (bombermanI == nuevoI - 1 && bombermanJ == nuevoJ && movimientoPosible(nuevoI - 1, nuevoJ)) {
            nuevoI--; 
        } else if (bombermanI == nuevoI + 1 && bombermanJ == nuevoJ && movimientoPosible(nuevoI + 1, nuevoJ)) {
            nuevoI++; 
        } else if (bombermanI == nuevoI && bombermanJ == nuevoJ - 1 && movimientoPosible(nuevoI, nuevoJ - 1)) {
            nuevoJ--; 
        } else if (bombermanI == nuevoI && bombermanJ == nuevoJ + 1 && movimientoPosible(nuevoI, nuevoJ + 1)) {
            nuevoJ++; 
        } else {
           
            Random random = new Random();
            String[] direcciones = {"arriba", "abajo", "izquierda", "derecha"};
            String direccion = direcciones[random.nextInt(4)];
            if (direccion.equals("arriba") && movimientoPosible(nuevoI - 1, nuevoJ) && movimientoEnemigo(nuevoI - 1, nuevoJ)) {
                nuevoI--;
            } else if (direccion.equals("abajo") && movimientoPosible(nuevoI + 1, nuevoJ) && movimientoEnemigo(nuevoI + 1, nuevoJ)) {
                nuevoI++;
            } else if (direccion.equals("izquierda") && movimientoPosible(nuevoI, nuevoJ - 1) && movimientoEnemigo(nuevoI, nuevoJ - 1)) {
                nuevoJ--;
            } else if (direccion.equals("derecha") && movimientoPosible(nuevoI, nuevoJ + 1) && movimientoEnemigo(nuevoI, nuevoJ + 1)) {
                nuevoJ++;
            }
        }

       
        if (nuevoI == bombermanI && nuevoJ == bombermanJ) {
            // Matar a Bomberman
            BomberManBlanco.getBomberMan().morir();
            c.cambiarCasilla("Morir");

            // Eliminar al enemigo
            c.destruir();
            matriz[c.getI()][c.getJ()].cambiarCasilla("Vacio");
        } else if (nuevoI != c.getI() || nuevoJ != c.getJ()) { // Movimiento normal
            matriz[c.getI()][c.getJ()].cambiarCasilla("Vacio");
            matriz[nuevoI][nuevoJ].cambiarCasilla("Enemigo");
        }
    }

    
    //MOVIMIENTO
	public void moverBomberman(char pDir) {
	    if (BomberManBlanco.getBomberMan().isVivo()) {
	        int pI = BomberManBlanco.getBomberMan().getI();
	        int pJ = BomberManBlanco.getBomberMan().getJ();
	        int prevI = pI;
	        int prevJ = pJ;
	        CasillaTablero c;
	        
	        if (pDir == 'u') {
	        	if (!(pI-1<0)) {
		            if (movimientoPosible(pI - 1, pJ) || matriz[pI - 1][pJ].esObjeto("Enemigo")) {
		                BomberManBlanco.getBomberMan().mover(pI - 1, pJ);
		                c = matriz[pI - 1][pJ];
		                if (c.esObjeto("Explosion") || c.esObjeto("Enemigo")) {
		                    BomberManBlanco.getBomberMan().morir();
		                    matriz[pI][pJ].cambiarCasilla("Morir");
		                } else {
		                    c = matriz[pI][pJ];
		                    if (!c.esObjeto("Bomba")) {
		                        matriz[pI][pJ].cambiarCasilla("Vacio");
		                    }
		                }
		                pI--;
		            }
	        	}
	        } else if (pDir == 'd') {
	        	if (!(pI+1>10)) {
		            if (movimientoPosible(pI + 1, pJ) || matriz[pI + 1][pJ].esObjeto("Enemigo")) {
			                BomberManBlanco.getBomberMan().mover(pI + 1, pJ);
			                c = matriz[pI + 1][pJ];
			                if (c.esObjeto("Explosion") || c.esObjeto("Enemigo")) {
			                    BomberManBlanco.getBomberMan().morir();
			                    matriz[pI][pJ].cambiarCasilla("Morir");
			                } else {
			                    c = matriz[pI][pJ];
			                    if (!c.esObjeto("Bomba")) {
			                    	matriz[pI][pJ].cambiarCasilla("Vacio");
			                    }
			                }
			                pI++;
		            }
	        	}
	        } else if (pDir == 'r') {
	        	if (!(pJ+1>16)) {
		            if (movimientoPosible(pI, pJ + 1) || matriz[pI][pJ + 1].esObjeto("Enemigo")) {
		                BomberManBlanco.getBomberMan().mover(pI, pJ + 1);
		                c = matriz[pI][pJ + 1];
		                if (c.esObjeto("Explosion") || c.esObjeto("Enemigo")) {
		                    BomberManBlanco.getBomberMan().morir();
		                    matriz[pI][pJ].cambiarCasilla("Morir");
		                } else {
		                    c = matriz[pI][pJ];
		                    if (!c.esObjeto("Bomba")) {
		                    	matriz[pI][pJ].cambiarCasilla("Vacio");
		                    }
		                }
		                pJ++;
		            }
	        	}
	        } else if (pDir == 'l') {
	        	if (!(pJ-1<0)) {
		            if (movimientoPosible(pI, pJ - 1) || matriz[pI][pJ - 1].esObjeto("Enemigo")) {
		                BomberManBlanco.getBomberMan().mover(pI, pJ - 1);
		                c = matriz[pI][pJ - 1];
		                if (c.esObjeto("Explosion") || c.esObjeto("Enemigo")) {
		                    BomberManBlanco.getBomberMan().morir();
		                    matriz[pI][pJ].cambiarCasilla("Morir");
		                } else {
		                    c = matriz[pI][pJ];
		                    if (!c.esObjeto("Bomba")) {
		                    	matriz[pI][pJ].cambiarCasilla("Vacio");
		                    }
		                }
		                pJ--;
		            }
	        	}
	        }

	        // Notificar cambios
	        if (matriz[prevI][prevJ].esObjeto("Bomba")) {
	            if (prevI != pI || prevJ != pJ) {
	            	matriz[prevI][prevJ].cambiarCasilla("Bomba");
	            	matriz[pI][pJ].cambiarCasilla(String.valueOf(pDir));
	            }
	        } else {
	        	matriz[pI][pJ].cambiarCasilla(String.valueOf(pDir));
	        }
	    }
	}
	
    public boolean movimientoPosible(int pI, int pJ) {
    	boolean posible = true;
    	if(pI<0 || pI >=11 || pJ<0 || pJ>=17) {
    		posible = false;
    	}else{
    		CasillaTablero c = matriz[pI][pJ];
    		if (c.esObjeto("BomberMan")) {
    	        return true;
    	    }
    		posible = c.puedeMoverse();
    	}
    	return posible;
    }
    
    public boolean movimientoEnemigo(int pI, int pJ) {
    	boolean posible = true;
    	if ((pI == 0 && pJ == 0) || (pI == 0 && pJ == 1) || (pI == 1 && pJ == 0)) {
    		posible = false;
    	}
    	return posible;
    }

    
    //BOMBA
    public void colocarBomba() {
    	int pI = BomberManBlanco.getBomberMan().getI();
    	int pJ = BomberManBlanco.getBomberMan().getJ();
        if (BomberManBlanco.getBomberMan().puedeColocarBomba()) {
        	CasillaTablero c = matriz[pI][pJ];
            if (c.esObjeto("Bomba")) {
                return;
            }
            matriz[pI][pJ].cambiarCasilla("BSuper");
        }
    }
    
    public void quitarBomba(int pI, int pJ) {
		matriz[pI][pJ].cambiarCasilla("Vacio");
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
    	CasillaTablero c = matriz[pI][pJ];
    	if (c.esObjeto("Duro")) return;
    	else if (c.esObjeto("Bomba")) {
            c.destruir();
        } else if (c.esObjeto("BomberMan")||esBomberMan(pI, pJ)) {
        	BomberManBlanco.getBomberMan().morir();
        	matriz[pI][pJ].cambiarCasilla("Morir");
        } else {
        	if (c.esObjeto("Enemigo")) {
        		matriz[pI][pJ].cambiarCasilla("Morir");
        	}
        	matriz[pI][pJ].cambiarCasilla("Explosion");
        }
        return;
    }    

    public void apagar(int pI, int pJ) {
    	CasillaTablero c = matriz[pI][pJ];
    	if (!c.esObjeto("Explosion")) {
        	return;
        }
		matriz[pI][pJ].cambiarCasilla("Vacio");
    }
    
    
    public boolean esBomberMan(int pI, int pJ) {
    	if (BomberManBlanco.getBomberMan().getI()==pI && BomberManBlanco.getBomberMan().getJ()==pJ) {
    		return true;
    	}
    	return false;
    }
    
    public CasillaTablero getCasilla(int pI, int pJ) {
    	CasillaTablero c = null;
    	if (pI>=0 && pJ>=0 && pI<11 && pJ<17) {
    		c = matriz[pI][pJ];
    	}
    	return c;
    }

}
