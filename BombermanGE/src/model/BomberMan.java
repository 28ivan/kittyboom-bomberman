package model;

public abstract class BomberMan implements Casilla {
	protected static BomberMan miBM ;
	protected int i;
	protected int j;
	protected char dir;
	protected int bombas;
	protected boolean vivo;
	
	protected BomberMan() {
		this.i = 0;
		this.j = 0;
		this.dir='d';
		this.bombas = 0;
		this.vivo = true;
		
	}
	
	public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public boolean getEstado() {
    	return vivo;
    }
    
    public void mover(int pI, int pJ) {
    	i = pI;
    	j = pJ;
    }
    
    public boolean puedeColocarBomba() {
    	if (this.bombas==10 || !vivo) {
    		return false;
    	}
    	else {
    		this.bombas++;
    		return true;
    	}
    }
	
    public void morir() {
    	vivo=false;
    }
    
    public void bombaExplotada() {
    	bombas--;
    }
	@Override
	public boolean puedeMoverse() {
		return false;
	}

	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("BomberMan")) {
			return true;
		}else {
			return false;
		}
	}
}
