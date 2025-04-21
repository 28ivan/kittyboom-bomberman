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
	
	private Boss() {
		super(11,17);
		vida = 3;
		escudo = true;
		invocado = false;
		tiempoEscudo = 10000;
		invocarMobs();
	}
	
	public static Boss getBoss() {
		if(miBoss==null) {
			miBoss = new Boss();
    	}
    	return miBoss;
	}
	public void invocarMobs() {
		Tablero.getTablero().colocarEnemigos(3);
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
                    }
                }
            };
            timerEscudo.schedule(timerTask, tiempoEscudo);
        }
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
