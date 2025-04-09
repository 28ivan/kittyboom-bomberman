package model;

public abstract class BomberMan implements Casilla {
	protected static BomberMan miBM = null;
	protected int i=0;
	protected int j=0;
	protected int bombas=0;
	protected boolean vivo=true;
	protected boolean conBomba=false;
	protected boolean antiguaBomba=false;
	protected char dir = 'd' ;
	protected int num = 1;
	
	protected BomberMan() {
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
    
    public boolean conBomba() {
    	return conBomba;
    }
    
    public char getDir() {
    	return dir;
    }
    
    public void setDir(char pD) {
    	if (pD!=dir) {
    		dir=pD;
    		num=0;
    	}
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
	
	public boolean antiguaBomba() {
		if (antiguaBomba) {
			return true;
		}
		else return false;
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
		vivo=false;
		pC.cambiarCasilla(miBM);
	}
	
    @Override
    public boolean isVivo() {
    	return vivo;
    }

	
	@Override
	public void mover(int pI, int pJ) {
		if (conBomba) {
    		conBomba=false;
    	}
    	BomberManBlanco.getBomberMan().aumentarNum();
    	if (i!=pI || j!=pJ) {
    		CasillaTablero c=Tablero.getTablero().getCasilla(i, j);
    		if (c.esObjeto("Bomba")) {
    			antiguaBomba=true;
    			c.cambiarCasilla(miBM);
    			antiguaBomba=false;
    		}
    		else {
    			c.cambiarCasilla(BloqueFactory.getBloqueFactory().generate("Vacio"));
    		}
    	}
    	i = pI;
    	j = pJ;
    	Tablero.getTablero().getCasilla(i, j).cambiarCasilla(miBM);
	}
}
