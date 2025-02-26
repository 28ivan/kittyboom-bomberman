package model;

import java.util.Timer;
import java.util.TimerTask;

public class Bomba {
	private int cordX;
	private int cordY;
	private boolean explotada;
	private boolean activa;
	private int radio;
	private int tiempoExplosion;
	private Timer timer = null;
	
	public Bomba(int pCordX, int pCordY, int pRadio) {
		this.cordX = pCordX;
		this.cordY = pCordY;
		this.explotada = false;
		this.activa = false;
		this.radio = pRadio;
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
	}
	
	public boolean haExplotado() {
		return explotada;
	}




}