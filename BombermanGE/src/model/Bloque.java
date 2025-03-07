package model;

public abstract class Bloque {
	private int i;
	private int j;
	
	public Bloque(int pI, int pJ){
		this.i = pI;
		this.j = pJ; 
	}
	
	public int getI() {
		return i;
	}	
	
	public int getJ() {
		return j;
	}

}
