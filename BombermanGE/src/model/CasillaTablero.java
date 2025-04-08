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
	
	public void cambiarCasilla(String pSt) {
		int n=1;
		if (pSt=="Vacio") {
			c=BloqueFactory.getBloqueFactory().generate("Vacio");
		}
		else if (pSt=="Duro") {
			c=BloqueFactory.getBloqueFactory().generate("Duro");
		}
		else if (pSt=="Blando") {
			c=BloqueFactory.getBloqueFactory().generate("Blando");
		}
		else if (pSt=="Explosion") {
			c=new Explosion(i,j);
		}
		else if (pSt=="BSuper") {
			c=BombaFactory.getBombaFactory().generate(pSt, i, j);
			pSt="BmBomba";
		}
		else if (pSt=="BUltra") {
			c=BombaFactory.getBombaFactory().generate(pSt, i, j);
			pSt="BmBomba";
		}
		else if (pSt=="BomberMan") {
			c=BomberManBlanco.getBomberMan();
			pSt="d";
		}
		else if (pSt.equals("u") || pSt.equals("d") || pSt.equals("l") || pSt.equals("r")) {
			c=BomberManBlanco.getBomberMan();
			BomberManBlanco.getBomberMan().setDir(pSt.charAt(0));
			BomberManBlanco.getBomberMan().aumentarNum();
			n=BomberManBlanco.getBomberMan().getNum();
		}
		else if (pSt=="Enemigo") {
			c=new Enemigo(i,j);
		}
		setChanged();
		notifyObservers(new Object[] {pSt,n});
	}
	
	public void destruir() {
		this.c.destruir(this);
	}
	
	public boolean esObjeto(String pTipo) {
		return this.c.esObjeto(pTipo);
	}
	
	public boolean isVivo() {
		return this.c.isVivo();
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
}
