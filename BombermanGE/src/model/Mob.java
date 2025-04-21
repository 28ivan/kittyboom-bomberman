package model;

public class Mob extends Enemigo {
	
	
	public Mob(int i, int j) {
		super(i, j);
	}

	
	@Override
	public void destruir(CasillaTablero pC, int pI, int pJ) {
		vivo = false;
        if (timer != null) {
            timer.cancel();
        }
        pC.cambiarCasilla(new Explosion(pI, pJ));
	}
}
