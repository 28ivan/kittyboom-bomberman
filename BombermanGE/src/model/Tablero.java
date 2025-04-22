package model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tablero {

	private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas  
    private static Tablero miTablero = null;
    private CasillaTablero[][] matriz = new CasillaTablero[fila][col];
    private StrategyBloques stBloques;
    private StrategyBombas stBombas;
    private int numEnemigos;
    private static int bMSelec;
    private static String bMColor;
    private int pantalla;
    private boolean seleccionado=false;
    
    private Tablero() {
    	for (int i = 0;  i< fila; i++) {
            for (int j = 0; j < col; j++) {
            	matriz[i][j]=new CasillaTablero(i,j);
            }
    	}
    	numEnemigos = 0;
    }
    
    public void setTablero(int pBMSelec ,int pPantalla) {
    	seleccionado=true;
    	bMSelec=pBMSelec;
		pantalla=pPantalla;
    	if (bMSelec==1) {
    		bMColor="Negro";
    		changeStrategyBomba(new stBUltra());
    	}else {
    		bMColor="Blanco";
    		changeStrategyBomba(new stBSuper());
    	}
	}
    
    public void initializeTablero() {
    	if (seleccionado) {
    		if (pantalla==1) {
        		crearClassic();
        	} else if(pantalla==2) {
        		crearSoft();
        	}else if(pantalla==3) {
        		crearEmpty();
        	}
    	}
    }
    
    //pPantalla = 1
	public void crearClassic() {
		changeStrategyBloques(new stBloquesClassic());
		colocarBomberMan();
		colocarBloquesDuros();
		colocarBloquesBlandos();
		colocarEnemigos(8);
	}
	
    //pPantalla = 2
	public void crearSoft() {
		changeStrategyBloques(new stBloquesSoft());
		colocarBomberMan();
		colocarBloquesBlandos();
		colocarEnemigos(8);
	}
	
    //pPantalla = 3
	public void crearEmpty() {
		colocarBomberMan();
		colocarEnemigos(8);
	}
	
	//pPantalla = 4
	public void crearBoss() {
		colocarBomberMan();
		colocarBoss();
		//colocarBloquesDobles()
	}
    
	//CONSTRUCCION DEL TABLERO
    public void colocarBomberMan() {
    	matriz[0][0].cambiarCasilla(BomberManFactory.getBomberManFactory().generate("BomberMan"+bMColor));
    }
    
    public void colocarBloquesDuros() {
    	for (int i = 0;  i< fila; i++) {
            for (int j = 0; j < col; j++) {
            	if(!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)) {
            		if (i%2!=0 && j%2!=0) {
            			matriz[i][j].cambiarCasilla(BloqueFactory.getBloqueFactory().generate("Duro"));
            		} 
            	}
            }
    	}
    }
    
    public void colocarBloquesBlandos() {
    	stBloques.colocarBloquesBlandos();
    }
    
    public void setBloqueBlando(int pI, int pJ) {
    	matriz[pI][pJ].cambiarCasilla(BloqueFactory.getBloqueFactory().generate("Blando"));
    }
    
    public void changeStrategyBloques(StrategyBloques stB){
    	stBloques = stB;
    }
    
    public void changeStrategyBomba(StrategyBombas stB){
    	stBombas = stB;
    }

    public void colocarEnemigos(int num) {
		Random r = new Random();
		while (numEnemigos < num) {
		    int i = r.nextInt(fila);
		    int j = r.nextInt(col);	
		    if (!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)
		        && matriz[i][j].esObjeto("Vacio")) {
		    	matriz[i][j].cambiarCasilla(new Mob(i,j));
		    	numEnemigos++;
		    }
		}
	}
    
    
    public void colocarBoss() {
    	matriz[0][0].cambiarCasilla(Boss.getBoss());
    }
    
    
    //METODOS GET
    public static Tablero getTablero() {
    	if(miTablero==null) {
    		miTablero = new Tablero();
    	}
    	return miTablero;
    }
    
    public BomberMan getBomberman() {
    	if(bMSelec==1) {
    		return BomberManFactory.getBomberManFactory().generate("BomberManNegro");
    	}else {
    		return BomberManFactory.getBomberManFactory().generate("BomberManBlanco");
    	}
    }
    
    public boolean esBomberman(int pI, int pJ) {
    	if (getBomberman().getI()==pI && getBomberman().getJ()==pJ) {
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
    
    
    
    //MOVIMIENTO ENEMIGO
    public void moverEnemigo(int pI, int pJ) {
        if (pI < 0 || pI >= fila || pJ < 0 || pJ >= col) return;
        if (!matriz[pI][pJ].esObjeto("Enemigo")) return;
        CasillaTablero c = matriz[pI][pJ];
        if (!c.isVivo()) return;
        int nuevoI = c.getI();
        int nuevoJ = c.getJ();
        int bombermanI = getBomberman().getI();
        int bombermanJ = getBomberman().getJ();
        if (bombermanI == nuevoI - 1 && bombermanJ == nuevoJ && movimientoEnemigo(nuevoI - 1, nuevoJ)) {
            nuevoI--; 
        } else if (bombermanI == nuevoI + 1 && bombermanJ == nuevoJ && movimientoEnemigo(nuevoI + 1, nuevoJ)) {
            nuevoI++; 
        } else if (bombermanI == nuevoI && bombermanJ == nuevoJ - 1 && movimientoEnemigo(nuevoI, nuevoJ - 1)) {
            nuevoJ--; 
        } else if (bombermanI == nuevoI && bombermanJ == nuevoJ + 1 && movimientoEnemigo(nuevoI, nuevoJ + 1)) {
            nuevoJ++; 
        } else {
            Random random = new Random();
            String[] direcciones = {"arriba", "abajo", "izquierda", "derecha"};
            String direccion = direcciones[random.nextInt(4)];
            if (direccion.equals("arriba") && movimientoEnemigo(nuevoI - 1, nuevoJ)) {
                nuevoI--;
            } else if (direccion.equals("abajo") && movimientoEnemigo(nuevoI + 1, nuevoJ)) {
                nuevoI++;
            } else if (direccion.equals("izquierda") && movimientoEnemigo(nuevoI, nuevoJ - 1)) {
                nuevoJ--;
            } else if (direccion.equals("derecha") && movimientoEnemigo(nuevoI, nuevoJ + 1)) {
                nuevoJ++;
            }
        }       
        if (nuevoI == bombermanI && nuevoJ == bombermanJ) {
            matriz[bombermanI][bombermanJ].destruir();
        } else if (nuevoI != c.getI() || nuevoJ != c.getJ()) { // Movimiento normal
        	c.mover(nuevoI,nuevoJ);
        }
    }
    
    public boolean movimientoEnemigo(int pI, int pJ) {
    	if(pI<0 || pI >=11 || pJ<0 || pJ>=17) return false;
    	CasillaTablero c=getCasilla(pI,pJ);
    	if ((pI == 0 && pJ == 0) || (pI == 0 && pJ == 1) || (pI == 1 && pJ == 0) || 
    		c.esObjeto("Enemigo") || c.esObjeto("Explosion")) {
    		return false;
    	}
    	return c.puedeMoverse();
    }
 
    
    
    //MOVIMIENTO BOMBERMAN
	public void moverBomberman(char pDir) {
	    if (getBomberman().isVivo()) {
	        int pI = getBomberman().getI();
	        int pJ = getBomberman().getJ();
	        int prevI=pI;
	        int prevJ=pJ;
	        BomberMan cB = getBomberman();
	        CasillaTablero c;
	        
	        if (pDir == 'u') {
	        	if (!(pI-1<0)) {
		            if (movimientoPosible(pI - 1, pJ) || matriz[pI - 1][pJ].esObjeto("Enemigo")) {
		                c = matriz[pI - 1][pJ];
		                if (c.esObjeto("Explosion")) {
		                	cB.mover(pI - 1, pJ);
		                    matriz[pI - 1][pJ].destruir();
		                } else if (c.esObjeto("Enemigo")) {
		                	matriz[pI][pJ].destruir();
		                } else {
		                	getBomberman().setDir(pDir);
		                	cB.mover(pI - 1, pJ);
		                }
		            }
	        	}
	        } else if (pDir == 'd') {
	        	if (!(pI+1>10)) {
		            if (movimientoPosible(pI + 1, pJ) || matriz[pI + 1][pJ].esObjeto("Enemigo")) {
		                c = matriz[pI + 1][pJ];
		                if (c.esObjeto("Explosion")) {
		                	cB.mover(pI + 1, pJ);
		                    matriz[pI + 1][pJ].destruir();
		                } else if (c.esObjeto("Enemigo")) {
		                	matriz[pI][pJ].destruir();
		                } else {
		                	getBomberman().setDir(pDir);
		                	cB.mover(pI + 1, pJ);
		                }
		            }
	        	}
	        } else if (pDir == 'r') {
	        	if (!(pJ+1>16)) {
		            if (movimientoPosible(pI, pJ + 1) || matriz[pI][pJ + 1].esObjeto("Enemigo")) {
		                c = matriz[pI][pJ + 1];
		                if (c.esObjeto("Explosion")) {
		                	cB.mover(pI, pJ + 1);
		                    matriz[pI][pJ + 1].destruir();
		                } else if (c.esObjeto("Enemigo")) {
		                	matriz[pI][pJ].destruir();
		                } else {
		                	getBomberman().setDir(pDir);
		                	cB.mover(pI, pJ + 1);
		                }
		            }
	        	}
	        } else if (pDir == 'l') {
	        	if (!(pJ-1<0)) {
		            if (movimientoPosible(pI, pJ - 1) || matriz[pI][pJ - 1].esObjeto("Enemigo")) {
		                c = matriz[pI][pJ - 1];
		                if (c.esObjeto("Explosion")) {
		                	cB.mover(pI, pJ - 1);
		                    matriz[pI][pJ - 1].destruir();
		                } else if (c.esObjeto("Enemigo")) {
		                	matriz[pI][pJ].destruir();
		                } else {
		                	getBomberman().setDir(pDir);
		                	cB.mover(pI, pJ - 1);
		                }
		            }
	        	}
	        }
	        if ((prevI==getBomberman().getI() && prevJ==getBomberman().getJ()) && !getBomberman().conBomba()) {
            	getBomberman().setDir(pDir);
            	cB.mover(pI, pJ);
            }
	        if ((!getBomberman().isVivo())) {
	        	
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
    
    
    
    //BOMBA
    public void colocarBomba() {
    	int pI = getBomberman().getI();
    	int pJ = getBomberman().getJ();
        if (getBomberman().puedeColocarBomba()) {
        	CasillaTablero c = matriz[pI][pJ];
            if (c.esObjeto("Bomba")) {
                return;
            }
            getBomberman().ponerBomba();
        }
    }
    
    
    
    //EXPLOSION
    public void destruirBloques(int pI, int pJ) {
		getBomberman().bombaExplotada();
		if(stBombas!=null) {
			stBombas.destruirBloques(pI,pJ);
		}
        
    }
    
    public void procesarExplosion(int pI, int pJ) {
    	if (pI < 0 || pI >= fila || pJ < 0 || pJ >= col) return;
    	CasillaTablero c = matriz[pI][pJ];
    	if (esBomberman(pI,pJ)) {
    		getBomberman().mover(pI, pJ);
    	}
	    if (c.esObjeto("Enemigo")) {
	    	numEnemigos--;
	    	if (numEnemigos == 0 && pantalla == 4) {
	    		Boss.getBoss().desactivarEscudo();
	    	}
	    	else if (numEnemigos == 0 && pantalla != 4) {
	    		ganar();
	    	}
	    }
	    c.destruir();
    }    

    public void apagar(int pI, int pJ) {
    	if (pI < 0 || pI >= fila || pJ < 0 || pJ >= col) return;
    	CasillaTablero c = matriz[pI][pJ];
    	c.vaciar();
    }

    
    
    //METODOS FIN JUEGO
    private void ganar() {
    	BomberMan bm = getBomberman();
    	bm.ganar();
    	bm.mover(bm.getI(), bm.getJ());
    	Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                restart();
            }
        }, 0, 3000);
    }
    
    private void restart() {
    	for (int i = 0;  i< fila; i++) {
            for (int j = 0; j < col; j++) {
            	matriz[i][j]=new CasillaTablero(i,j);
            }
    	}
    	numEnemigos = 0;
    }
}
