package model;

import java.util.Observable;

public class Explosion extends Observable{
	private int cordX;
	private int cordY;
	private int radioExplosion;
	
	public Explosion(int pCordX, int pCordY, int pRadioExplosion) {
		this.cordX = pCordX;
		this.cordY = pCordY;
		this.radioExplosion = pRadioExplosion;
		activarExplosion();
	}
	
	private void activarExplosion() {
		
		for ( int i = 0; i<=radioExplosion ; i++ ) { //destruir bloques segun rango
			Tablero.getTablero().destruirBloques(this.cordX, this.cordY);
			Tablero.getTablero().destruirBloques(this.cordX - i, this.cordY);
			Tablero.getTablero().destruirBloques(this.cordX + i, this.cordY);
			Tablero.getTablero().destruirBloques(this.cordX, this.cordY - i);
			Tablero.getTablero().destruirBloques(this.cordX, this.cordY + i);
		}
		
		//activar otras bombas
		
	//	if (  ) {
	//		BomberMan.getBomberMan().morir();
	//	}
		
	}
}
