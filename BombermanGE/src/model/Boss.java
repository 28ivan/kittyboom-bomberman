package model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Boss extends Enemigo {
	
	private static Boss miBoss;
	private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas  
	private int numEnemigos;
	private int vida;
	private boolean escudo;
	private boolean invocado;
	private Timer timerEscudo = null;
	private Timer timerInvocacion = null;
	private int tiempoEscudo;
	private int tiempoRomperEscudo;
	private CasillaTablero[][] matriz = new CasillaTablero[fila][col];
	private RondaState rondaActual;
	
	private Boss() {
		super(11,17);
		vida = 3;
		escudo = true;
		invocado = false;
		tiempoEscudo = 10000;
		tiempoRomperEscudo=2000;
		invocarMobs(1);
		rondaActual = new RondaUno();
		
	}

	
	public static Boss getBoss() {
		if(miBoss==null) {
			miBoss = new Boss();
    	}
    	return miBoss;
	}
	
	
	public void invocarMobs(int num) {
	    Random r = new Random();
	    while (numEnemigos < num) {
	        
	        int i = r.nextInt(fila);  
	        int j = r.nextInt(col);   

	        
	        if (!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0) 
	            && Tablero.getTablero().getCasilla(i, j).esObjeto("Vacio") && !Tablero.getTablero().getCasilla(i, j).esObjeto("Barrera")) {
	        	Tablero.getTablero().setInvocacion(i, j);
	            numEnemigos++; 
	            Tablero.getTablero().sumarNumEnemigos();
	        }
	          
	    
	    
    
	    }
	}
	
		
	
	public void desactivarEscudo() {
		escudo = false; 
		if (timerEscudo == null) {
			timerEscudo = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (vivo) {
                    	escudo = true;
                    	timerEscudo.cancel();
                    	timerEscudo = null; 
                    	Tablero.getTablero().destruirBarrera();
                    	activarEscudo();
                    }
                }
            };
            timerEscudo.schedule(timerTask, tiempoRomperEscudo);
        }
	}
	public void activarEscudo() {
		escudo = false; 
		if (timerEscudo == null) {
			timerEscudo = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (vivo) {
                    	escudo = true;
                    	timerEscudo.cancel();
                    	timerEscudo = null; 
                    	Tablero.getTablero().colocarBarrera();
                    }
                }
            };
            timerEscudo.schedule(timerTask, tiempoEscudo);
        }
	}
	public void restarVida() {
		if (vida>1) {
			vida-=vida;
			pasarRonda();
			System.out.println("Se ha quitado una vida");
		}else if(vida==1) {
			vida-=vida;
			Tablero.getTablero().getBomberman().ganar();
		}
	}
	
	private void pasarRonda() {
		// TODO Auto-generated method stub
		//hacer el state?
		if (timerEscudo!=null) {
			timerEscudo.cancel();
			timerEscudo=null;
			Tablero.getTablero().colocarBarrera();
		}
		invocarMobs(1);
	}

	@Override
	public void destruir(CasillaTablero pC, int pI, int pJ) {
		if(!escudo) {
			vida--;
			if (vida == 0) {
				vivo = false;
		        if (timer != null) {
		            timer.cancel();
		        }
		        pC.cambiarCasilla(new Explosion(pI, pJ));
			}
		}
	}


	public void restarEnemigo() {
		if (this.numEnemigos>0) {
			this.numEnemigos--;
		}
	}
}
