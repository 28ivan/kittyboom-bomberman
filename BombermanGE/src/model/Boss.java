package model;

import java.util.Timer;
import java.util.TimerTask;

public class Boss extends Enemigo {
	
	private static Boss miBoss;
	private int vida;
	private boolean escudo;
	private boolean invocado;
	private Timer timerEscudo = null;
	private int tiempoEscudo;
	private int tiempoRomperEscudo;
	
	private Boss() {
		super(11,17);
		vida = 3;
		escudo = true;
		invocado = false;
		tiempoEscudo = 10000;
		tiempoRomperEscudo=2000;
		invocarMobs();
	}
	
	public static Boss getBoss() {
		if(miBoss==null) {
			miBoss = new Boss();
    	}
    	return miBoss;
	}
	public void invocarMobs() {
		Tablero.getTablero().colocarEnemigos(1);
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
