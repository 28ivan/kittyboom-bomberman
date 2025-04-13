package model;

public class stBUltra implements StrategyBombas{
	public stBUltra() {}
	private Tablero tablero = Tablero.getTablero();
	@Override
	public void destruirBloques(int pI, int pJ) {
		 tablero.procesarExplosion(pI, pJ);
		for (int rango = 1; rango <= 20; rango++) {
            tablero.procesarExplosion(pI- rango, pJ);
            tablero.procesarExplosion(pI + rango, pJ);
            tablero.procesarExplosion(pI, pJ - rango);
            tablero.procesarExplosion(pI, pJ + rango);
        }
	}
	
}
