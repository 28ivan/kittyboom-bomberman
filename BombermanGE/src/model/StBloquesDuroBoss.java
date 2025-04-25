package model;

public class StBloquesDuroBoss implements StrategyBloquesDuro {
	private Tablero tablero = Tablero.getTablero();
	private int [][] matrizBloques;
	
	public StBloquesDuroBoss() {
		this.matrizBloques =  
			 new int[][] {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   {0,1,0,0,1,1,1,0,0,0,1,1,1,0,0,1,0},
			   {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
			   {0,0,1,0,1,0,1,1,3,1,1,0,1,0,1,0,0},
			   {0,0,1,0,0,0,1,2,2,2,1,0,0,0,1,0,0},
			   {0,0,1,0,0,0,3,2,2,2,3,0,0,0,1,0,0},
			   {0,0,1,0,0,0,1,2,2,2,1,0,0,0,1,0,0},
			   {0,0,1,0,1,0,1,1,3,1,1,0,1,0,1,0,0},
	  		   {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
			   {0,1,0,0,1,1,1,0,0,0,1,1,1,0,0,1,0},
			   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	}
	
	@Override
	public void colocarBloquesDuros() {
		for (int i = 0;  i< 11; i++) {
            for (int j = 0; j < 17; j++) {
            	if (matrizBloques[i][j] == 1) {
            		tablero.setBloqueDuro(i,j);
            	} else if (matrizBloques[i][j] == 2) {
        			tablero.setBloqueBoss(i, j);
            	} else if (matrizBloques[i][j] == 3) {
        			tablero.setBarrera(i, j);
            	}
            }
    	}
	}
	public void colocarBarrera() {
		for (int i = 0;  i< 11; i++) {
            for (int j = 0; j < 17; j++) {
            	if (matrizBloques[i][j] == 3) {
        			tablero.setBarrera(i, j);
            	}
            }
    	}
	}
	
	public void destruirBarrera(){
		for (int i = 0;  i< 11; i++) {
            for (int j = 0; j < 17; j++) {
            	if (matrizBloques[i][j] == 3) {
            		CasillaTablero c = Tablero.getTablero().getCasilla(i, j);
            		Barrera b = (Barrera) c.getCasilla();
            		b.setDestruir(true);
            		Tablero.getTablero().procesarExplosion(i,j);
            	}
            }
    	}
	}

}
