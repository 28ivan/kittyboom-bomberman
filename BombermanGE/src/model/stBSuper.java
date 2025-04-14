package model;

public class stBSuper implements StrategyBombas{
	private Tablero tablero = Tablero.getTablero();
	@Override
	public void destruirBloques(int pI, int pJ) {
		 tablero.procesarExplosion(pI, pJ);
	     tablero.procesarExplosion(pI - 1, pJ);
	     tablero.procesarExplosion(pI + 1, pJ);
	     tablero.procesarExplosion(pI, pJ - 1);
	     tablero.procesarExplosion(pI, pJ + 1);
	}
}
