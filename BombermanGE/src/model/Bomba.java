package model;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomba implements Casilla{
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
		activar();
	}

	public void activar() {
		if (!activa) {
			activa=true;
			TimerTask timerTask = new TimerTask(){
				@Override
				public void run() {
					explotar();
				}
			};
			timer = new Timer();
			timer.schedule(timerTask, tiempoExplosion);
		}
	}
	
	public void explotar(){
		if (!explotada)
		explotada = true;
		BomberManBlanco.getBomberMan().bombaExplotada();
		Tablero.getTablero().destruirBloques(i, j);
	}
	
	public boolean haExplotado() {
		return explotada;
	}




}
