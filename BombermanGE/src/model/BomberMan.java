package model;

public abstract class BomberMan implements Casilla {
	protected static BomberMan miBM = null;
	protected int i;
	protected int j;
	protected int bombas;
	protected boolean vivo;
	protected char dir = 'd' ;
	protected int num = 1;
	
	protected BomberMan() {
		this.i = 0;
		this.j = 0;
		this.bombas = 0;
		this.vivo = true;
		
	}
	
	public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public int getNum() {
        return num;
    }
    
    public void aumentarNum() {
    	num++;
    	if (num==6) num=1;
    }
    
    public void setDir(char pD) {
    	if (pD!=dir) {
    		dir=pD;
    		num=0;
    	}
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
	
	public boolean esBomberMan(int pI, int pJ) {
		if (this.i==pI&&this.j==pJ) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean puedeMoverse() {
		return true;
	}
	
	@Override
	public boolean esObjeto(String pTipo) {
		if(pTipo.equals("BomberMan")) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void destruir(CasillaTablero pC) {
	}
	
    @Override
    public boolean isVivo() {
    	return vivo;
    }
}
