package model;

public class StBloquesDuroClassic implements StrategyBloquesDuro {
	private Tablero tablero = Tablero.getTablero();
	
	public StBloquesDuroClassic() {}
	
	@Override
	public void colocarBloquesDuros() {
		for (int i = 0;  i< 11; i++) {
            for (int j = 0; j < 17; j++) {
            	if(!(i == 0 && j == 0) && !(i == 0 && j == 1) && !(i == 1 && j == 0)) {
            		if (i%2!=0 && j%2!=0) {
            			tablero.setBloqueDuro(i,j);
            		} 
            	}
            }
    	}
		
	}
	public void colocarBarrera() {};
	public void destruirBarrera() {};
}
