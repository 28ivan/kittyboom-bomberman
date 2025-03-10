package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Explosion extends Observable{
	private int i;
	private int j;
	boolean activa;
	private int tiempoExplosion;
	private Timer timer = null;
	
	public Explosion(int pI, int pJ) {
		this.i = pI;
		this.j = pJ;
		this.activa=false;
		this.tiempoExplosion = 2000;
		activar();
	}
	
	public void activar() {
	    if (!activa) {
	        activa = true;
	        if (timer != null) {
	            timer.cancel();
	        }
	        timer = new Timer();
	        TimerTask timerTask = new TimerTask() {
	            @Override
	            public void run() {
	                Tablero.getTablero().apagar(i, j);
	            }
	        };
	        timer.schedule(timerTask, tiempoExplosion);
	    }
	    return;
	}
	
}
