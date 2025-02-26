package model;

public abstract class Bloque {
	private int cordX;
	private int cordY;
	
	public Bloque(int pCordX, int pCordY){
		this.cordX = pCordX;
		this.cordY = pCordY; 
	}
	
	public int getCordX() {
		return cordX;
	}	
	
	public int getCordY() {
		return cordY;
	}

}
