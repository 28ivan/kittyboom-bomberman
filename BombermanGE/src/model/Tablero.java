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
    	matriz[0][0] = BomberManFactory.getBomberManFactory().generate("BomberManBlanco");
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
            		else{
            			if (random) {
            				matriz[i][j] = BloqueFactory.getBloqueFactory().generate("Blando");
            				setChanged();
            				notifyObservers(new Object[] {"blando",i,j});
            			}
            			else {
            				matriz[i][j] = BloqueFactory.getBloqueFactory().generate("Vacio");
            			}
            		} 
            	}
            }
    	}
    
	    int enemigosColocados = 0;
		Random r3 = new Random();
	
		while (enemigosColocados < 10) {
		    int i = r3.nextInt(fila);
		    int j = r3.nextInt(col);
	
		    // Asegúrate de que no está en zona prohibida y que la casilla es "Vacio"
		    if (!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)
		        && matriz[i][j].esObjeto("Vacio")) {
	
		        Enemigo enemigo = new Enemigo(i, j);
    			matriz[i][j] = enemigo;
		        enemigo.empezarTimer();
		        enemigosColocados++;
	
		        setChanged();
		        notifyObservers(new Object[] {"enemigo", i, j});
		    }
		}
	}
    
    public void moverEnemigo(int pI, int pJ) {
        if (pI < 0 || pI >= fila || pJ < 0 || pJ >= col) return;
        if (!matriz[pI][pJ].esObjeto("Enemigo")) return;

        Enemigo enemigo = (Enemigo) matriz[pI][pJ];
        if (!enemigo.isVivo()) return;

        int nuevoI = enemigo.getI();
        int nuevoJ = enemigo.getJ();

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
            setChanged();
            notifyObservers(new Object[]{"muerto", bombermanI, bombermanJ});

            // Eliminar al enemigo
            enemigo.morir();
            matriz[enemigo.getI()][enemigo.getJ()] = BloqueFactory.getBloqueFactory().generate("Vacio");
            setChanged();
            notifyObservers(new Object[]{"delete", enemigo.getI(), enemigo.getJ()});
        } else if (nuevoI != enemigo.getI() || nuevoJ != enemigo.getJ()) { // Movimiento normal
            matriz[nuevoI][nuevoJ] = matriz[enemigo.getI()][enemigo.getJ()];
            matriz[enemigo.getI()][enemigo.getJ()] = BloqueFactory.getBloqueFactory().generate("Vacio");
            setChanged();
            notifyObservers(new Object[]{"delete", enemigo.getI(), enemigo.getJ()});
            enemigo.setI(nuevoI);
            enemigo.setJ(nuevoJ);
            setChanged();
            notifyObservers(new Object[]{"enemigo", nuevoI, nuevoJ});
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

	        if (pDir == 'u') {
	            if (movimientoPosible(pI - 1, pJ) || matriz[pI - 1][pJ].esObjeto("Enemigo")) {
	                BomberManBlanco.getBomberMan().mover(pI - 1, pJ);
	                c = matriz[pI - 1][pJ];
	                if (c.esObjeto("Explosion") || c.esObjeto("Enemigo")) {
	                    BomberManBlanco.getBomberMan().morir();
	                    accion = "muerto";
	                } else {
	                    c = matriz[pI][pJ];
	                    if (!c.esObjeto("Bomba")) {
	                        matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
	                    }
	                }
	                matriz[pI - 1][pJ] = BomberManBlanco.getBomberMan();
	                setChanged();
	                notifyObservers(new Object[] {"delete", pI, pJ});
	                pI--;
	            }
	        } else if (pDir == 'd') {
	            if (movimientoPosible(pI + 1, pJ) || matriz[pI + 1][pJ].esObjeto("Enemigo")) {
	                BomberManBlanco.getBomberMan().mover(pI + 1, pJ);
	                c = matriz[pI + 1][pJ];
	                if (c.esObjeto("Explosion") || c.esObjeto("Enemigo")) {
	                    BomberManBlanco.getBomberMan().morir();
	                    accion = "muerto";
	                } else {
	                    c = matriz[pI][pJ];
	                    if (!c.esObjeto("Bomba")) {
	                        matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
	                    }
	                }
	                matriz[pI + 1][pJ] = BomberManBlanco.getBomberMan();
	                setChanged();
	                notifyObservers(new Object[] {"delete", pI, pJ});
	                pI++;
	            }
	        } else if (pDir == 'r') {
	            if (movimientoPosible(pI, pJ + 1) || matriz[pI][pJ + 1].esObjeto("Enemigo")) {
	                BomberManBlanco.getBomberMan().mover(pI, pJ + 1);
	                c = matriz[pI][pJ + 1];
	                if (c.esObjeto("Explosion") || c.esObjeto("Enemigo")) {
	                    BomberManBlanco.getBomberMan().morir();
	                    accion = "muerto";
	                } else {
	                    c = matriz[pI][pJ];
	                    if (!c.esObjeto("Bomba")) {
	                        matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
	                    }
	                }
	                matriz[pI][pJ + 1] = BomberManBlanco.getBomberMan();
	                setChanged();
	                notifyObservers(new Object[] {"delete", pI, pJ});
	                pJ++;
	            }
	        } else if (pDir == 'l') {
	            if (movimientoPosible(pI, pJ - 1) || matriz[pI][pJ - 1].esObjeto("Enemigo")) {
	                BomberManBlanco.getBomberMan().mover(pI, pJ - 1);
	                c = matriz[pI][pJ - 1];
	                if (c.esObjeto("Explosion") || c.esObjeto("Enemigo")) {
	                    BomberManBlanco.getBomberMan().morir();
	                    accion = "muerto";
	                } else {
	                    c = matriz[pI][pJ];
	                    if (!c.esObjeto("Bomba")) {
	                        matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generate("Vacio");
	                    }
	                }
	                matriz[pI][pJ - 1] = BomberManBlanco.getBomberMan();
	                setChanged();
	                notifyObservers(new Object[] {"delete", pI, pJ});
	                pJ--;
	            }
	        }

	        // Notificar cambios
	        c = matriz[prevI][prevJ];
	        if (c.esObjeto("Bomba")) {
	            if (prevI != pI || prevJ != pJ) {
	                setChanged();
	                notifyObservers(new Object[] {"bomba", prevI, prevJ});
	                setChanged();
	                notifyObservers(new Object[] {accion, pI, pJ});
	            } else {
	                setChanged();
	                notifyObservers(new Object[] {"bmBomba", prevI, prevJ});
	            }
	        } else {
	            setChanged();
	            notifyObservers(new Object[] {accion, pI, pJ});
	        }
	    }
	}
    public boolean movimientoPosible(int pI, int pJ) {
    	boolean posible = true;
    	if(pI<0 || pI >=11 || pJ<0 || pJ>=17) {
    		posible = false;
    	}else{
    		Casilla c = matriz[pI][pJ];
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
        	Casilla c = matriz[pI][pJ];
            if (c.esObjeto("Bomba")) {
                return;
            }
            matriz[pI][pJ] = BombaFactory.getBombaFactory().generate("BSuper",pI,pJ);
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
    	if (c.esObjeto("Duro")) return;
    	else if (c.esObjeto("Bomba")) {
            Bomba bomba = (Bomba) matriz[pI][pJ];
            bomba.explotar();
        } else if (c.esObjeto("BomberMan")||esBomberMan(pI, pJ)) {
        	BomberManBlanco.getBomberMan().morir();
            setChanged();
            notifyObservers(new Object[] {"muerto", pI, pJ});
        } else {
        	if (c.esObjeto("Enemigo")) {
        		Enemigo enemigo = (Enemigo) c;
        		enemigo.morir();
        	}
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