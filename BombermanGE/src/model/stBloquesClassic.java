package model;

import java.util.Random;

public class stBloquesClassic implements StrategyBloques {
	private Tablero tablero = Tablero.getTablero();
	
	public stBloquesClassic() {}
	
	public void colocarBloquesBlandos() {
    	Random r = new Random();
    	for (int i = 0;  i< 11; i++) {	//filas
            for (int j = 0; j < 17; j++) {	//columnas
            	boolean random =r.nextBoolean();
            	if(!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)) {
            		if (i%2!=0 && j%2!=0) {
            		}else if (random) {
            			tablero.matriz[i][j].cambiarCasilla(BloqueFactory.getBloqueFactory().generate("Blando"));
            		}
            	}
            }
    	}
	 }
}
