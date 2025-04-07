package model;

import java.util.Timer;
import java.util.TimerTask;

public class Enemigo implements Casilla{
    private int i, j;
    private boolean vivo;
    private Timer timer = null;
    private int tiempoMovimiento;
    
    public Enemigo(int i, int j) {
        this.i = i;
        this.j = j;
        this.vivo = true;
        this.tiempoMovimiento = 2000;
    }

    public void morir() {
        this.vivo = false;
        if (timer != null) {
            timer.cancel();
        }
        }

    public boolean isVivo() {
        return vivo;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void empezarTimer() {
        if (timer == null) {
            timer = new Timer();
            Enemigo thisEnemigo = this;  
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (vivo) {
                        Tablero.getTablero().moverEnemigo(thisEnemigo.getI(), thisEnemigo.getJ());
                    } else {
                        timer.cancel(); 
                    }
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, tiempoMovimiento);
        }
    }

    
	@Override
	public boolean puedeMoverse() {
		return false;
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("Enemigo")) {
			return true;
		}else {
			return false;
		}
	}
}