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
        empezarTimer();
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

    public void empezarTimer() {
        if (timer == null) {
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (vivo) {
                        Tablero.getTablero().moverEnemigo(i, j);
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
	
	@Override
	public void destruir(CasillaTablero pC) {
		this.vivo = false;
        if (timer != null) {
            timer.cancel();
        }
        pC.cambiarCasilla(BloqueFactory.getBloqueFactory().generate("Vacio"));
	}
	
	@Override
	public void mover(int pI, int pJ) {
		if (i!=pI || j!=pJ) {
    		CasillaTablero c=Tablero.getTablero().getCasilla(pI, pJ);
    		c.cambiarCasilla(this);
    		Tablero.getTablero().getCasilla(i, j).cambiarCasilla(BloqueFactory.getBloqueFactory().generate("Vacio"));
    	}
    	i = pI;
    	j = pJ;
	}
}
