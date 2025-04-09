package model;

import java.util.Random;

public class stBloquesSoft implements StrategyBloques{
	private Tablero tablero = Tablero.getTablero();
	
	public stBloquesSoft() {}
	
	public void colocarBloquesBlandos() {
		   	Random r = new Random();
	    	for (int i = 0;  i< 11; i++) {	//filas
	            for (int j = 0; j < 17; j++) {	//columnas
	            	boolean random =r.nextBoolean();
	            	if(!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)) {
		            	if (random) {
		            		tablero.matriz[i][j].cambiarCasilla("Blando");
	            		}
	            	}
	            }
	    	}
	 }
}
