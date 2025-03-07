package model;

import java.util.Timer;
import java.util.TimerTask;

public class Bomba {
	private int i;
	private int j;
	private boolean explotada;
	private boolean activa;
	private int tiempoExplosion;
	private Timer timer = null;
	
	public int getI() {
		return this.i;
	}
	public int getJ() {
		return this.j;
	}
	
	public Bomba(int pI, int pJ) {
		this.i = pI;
		this.j = pJ;
		this.explotada = false;
		this.activa = false;
		this.tiempoExplosion = 3000;
	}

	public void activar() {
		if (!activa) {
			activa=true;
			//se ha colocado la bomba
			TimerTask timerTask = new TimerTask(){
				@Override
				public void run() {
					explotar();
				}
			};
			timer = new Timer();
			timer.schedule(timerTask, tiempoExplosion);
			//el timer esta hecho como en gestorSemaforos
		}
	}
	
	private void explotar(){
		if (!explotada)
		explotada = true;
		Explosion explosion = new Explosion(this.i, this.j);		
		explosion.activarExplosion();
	}
	
	public boolean haExplotado() {
		return explotada;
	}




}
