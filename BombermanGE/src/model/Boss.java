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
	private int tiempoEscudo;
	private int tiempoRomperEscudo;
	private CasillaTablero[][] matriz = new CasillaTablero[fila][col];
	
	private Boss() {
		super(11,17);
		vida = 3;
		escudo = true;
		invocado = false;
		tiempoEscudo = 10000;
		tiempoRomperEscudo=2000;
		int n = Tablero.getTablero().getNumEnemigos();
		invocarMobs(n);
		
	}

	
	public static Boss getBoss() {
		if(miBoss==null) {
			miBoss = new Boss();
    	}
    	return miBoss;
	}
	
	
	public void invocarMobs(int num) {
	    Random r = new Random();
	    Tablero tablero = Tablero.getTablero(); 

	    while (numEnemigos < num) {
	        int i = r.nextInt(fila);
	        int j = r.nextInt(col);

	        if (!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)
	            && tablero.getCasilla(i, j).esObjeto("Vacio") && !tablero.getCasilla(i, j).esObjeto("Barrera")) {

	            
	            Invocacion inv = new Invocacion();
	            tablero.getCasilla(i, j).cambiarCasilla(inv);

	            numEnemigos++;

	           
	            Timer timer = new Timer();
	            timer.schedule(new TimerTask() {
	                @Override
	                public void run() {
	                    
	                    tablero.getCasilla(i, j).cambiarCasilla(new Mob(i, j));
	                }
	            }, 1500);
	            
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
		if (this.vida>1) {
			this.vida-=this.vida;
			pasarRonda();
			System.out.println("Se ha quitado una vida");
		}else if(this.vida==1) {
			this.vida-=this.vida;
			Tablero.getTablero().getBomberman().ganar();
		}
	}
	
	private void pasarRonda() {
		// TODO Auto-generated method stub
		//hacer el state?
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
}
