package model;

public class stBUltra implements StrategyBombas {
    private Tablero tablero = Tablero.getTablero();

    public stBUltra() {}

    @Override
    public void destruirBloques(int pI, int pJ) {
        int rM = 20; 
        tablero.procesarExplosion(pI, pJ);

        for (int rango = 1; rango <= rM; rango++) {
            if (!procesarCasilla(pI - rango, pJ)) break;
        }

        for (int rango = 1; rango <= rM; rango++) {
            if (!procesarCasilla(pI + rango, pJ)) break;
        }

        for (int rango = 1; rango <= rM; rango++) {
            if (!procesarCasilla(pI, pJ - rango)) break;
        }

        for (int rango = 1; rango <= rM; rango++) {
            if (!procesarCasilla(pI, pJ + rango)) break;
        }
    }

    //bnloque duro o fuera de tablero
    private boolean procesarCasilla(int i, int j) {
        CasillaTablero c = tablero.getCasilla(i, j);
        if (c == null) return false;
        if (c.esObjeto("Duro")) return false;
        tablero.procesarExplosion(i, j);
        return true;
    }
}