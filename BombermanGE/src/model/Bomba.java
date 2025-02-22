package model;

import java.util.Timer;
import java.util.TimerTask;

public class Bomba {
	private int cordX;
	private int cordY;
	private boolean explotada;
	private int radio;
	private int cont;
	private Timer timer = null;
	
	public Bomba(int pCordX, int pCordY, boolean pExplotada, int pRadio) {
		this.cordX = pCordX;
		this.cordY = pCordY;
		this.explotada = false;
		this.radio = pRadio;
		this.cont = 3;
	}


public void cuentaAtras() {
	//hacerlo con TIMER 
	//TimerTask timerTask = new TimerTask(){}
	if(cont>0) {
		cont = cont-1;
	}
	if(cont==0){
		explotar();
	}
}

private void explotar(){
	explotada = true;
	//habria que destruir los bloques blandos
}
}