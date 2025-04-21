package model;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemigo implements Casilla{
    protected int i, j;
    protected boolean vivo;
    protected Timer timer = null;
    protected int tiempoMovimiento;
    
    public Enemigo(int i, int j) {
        i = i;
        j = j;
        vivo = true;
        tiempoMovimiento = 2000;
        empezarTimer();
    }

    
    
    //METODOS GET O SIMILAR
    public boolean isVivo() {
        return vivo;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    
    
    //LOGICA MOVIMIENTO ENEMIGO
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

    
    
	//METODOS CASILLA
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
	public void vaciar(CasillaTablero pC) {
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



	@Override
	public void destruir(CasillaTablero pC, int pI, int pJ) {
		//Lo implementan los hijos
	}
}
