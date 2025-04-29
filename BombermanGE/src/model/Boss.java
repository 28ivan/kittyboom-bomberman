package model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Boss {
	
	private static Boss miBoss;
	private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas  
	private int numEnemigos;
	private boolean vivo = true;
	private int vida = 3;
	private boolean escudo = true;
	private boolean invocado = false;
	private Timer timerEscudo = null;
	private Timer timerInvocacion = null;
	private int tiempoEscudo = 10000;
	private int tiempoRomperEscudo = 2000;
	private CasillaTablero[][] matriz = new CasillaTablero[fila][col];
	private RondaState rondaActual;
	
	private Boss() {
		Tablero.getTablero().invocarMobs(1);
		Tablero.getTablero().colocarBarrera();
		rondaActual = new RondaUno();
		
	}

	
	public static Boss getBoss() {
		if(miBoss==null) {
			miBoss = new Boss();
    	}
    	return miBoss;
	}
		
	public void desactivarEscudo() {
		escudo = false; 
		if (timerEscudo == null) {
			timerEscudo = new Timer();
		}
	        TimerTask timerTask = new TimerTask() {
	            @Override
	            public void run() {
	               if (vivo) {
	                   	timerEscudo.cancel(); 
	                   	Tablero.getTablero().destruirBarrera();
	                   	timerEscudo = null; 
	                   	activarEscudo();
	                }
	            }
	        };
	        timerEscudo.schedule(timerTask, tiempoRomperEscudo);
    }
	
	public void activarEscudo() {
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
                    	Tablero.getTablero().invocarMobs(1);
                    }
                }
            };
            timerEscudo.schedule(timerTask, tiempoEscudo);
        }
	}
	
	public void restarVida() {
		if (vida>1) {
			pasarRonda();
			System.out.println("Se ha quitado una vida");
		}else if(vida==1) {
			Tablero.getTablero().getBomberman().ganar();
		}
		vida--;
	}
	
	private void pasarRonda() {
		if (timerEscudo!=null) {
			timerEscudo.cancel();
			Tablero.getTablero().colocarBarrera();
		}
		Tablero.getTablero().invocarMobs(1);
	}

	public void restarEnemigo() {
		if (this.numEnemigos>0) {
			this.numEnemigos--;
		}
	}
}
