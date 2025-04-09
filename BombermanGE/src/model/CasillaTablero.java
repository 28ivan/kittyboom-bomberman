package model;

import java.util.Observable;

@SuppressWarnings({"deprecation"})
public class CasillaTablero extends Observable {
	private Casilla c;
	private int i;
	private int j;
	
	public CasillaTablero(int pI, int pJ) {
		c=BloqueFactory.getBloqueFactory().generate("Vacio");;
		i=pI;
		j=pJ;
	}
	
	public void cambiarCasilla(Casilla pC) {
		String st=null;
		int n=0;
		if (pC.esObjeto("BomberMan")) {
			BomberMan bm=BomberManBlanco.getBomberMan();
			if (bm.isVivo()) {
				if (bm.antiguaBomba()) {
					st="Bomba";
				}
				else if (bm.conBomba()) {
					st="BmBomba";
				}
				else {
					st=String.valueOf(bm.getDir());
					n=bm.getNum();
					c=pC;
				}
			}
			else {
				st="Morir";
			}
		}
		else {
			if (pC.esObjeto("Vacio")) {
				st="Vacio";
			}
			else if (pC.esObjeto("Duro")) {
				st="Duro";
			}
			else if (pC.esObjeto("Blando")) {
				st="Blando";
			}
			else if (pC.esObjeto("Bomba")) {
				st="BmBomba";
			}
			else if (pC.esObjeto("Explosion")) {
				st="Explosion";
			}
			else if (pC.esObjeto("Enemigo")) {
				st="Enemigo";
			}
			c=pC;
		}
		setChanged();
		notifyObservers(new Object[] {st,n});
	}
	
	public void destruir() {
		c.destruir(this);
	}
	
	public boolean esObjeto(String pTipo) {
		return c.esObjeto(pTipo);
	}
	
	public boolean isVivo() {
		return c.isVivo();
	}
	
	public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public boolean puedeMoverse() {
        return c.puedeMoverse();
    }
    
    public void mover(int pI, int pJ) {
        c.mover(pI,pJ);
    }
}
