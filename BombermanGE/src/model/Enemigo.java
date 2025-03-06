package model;

import java.util.Random;
import java.util.Timer;

public class Enemigo {
    private int cordX; 
    private int cordY; 
    private boolean vivo; 
    
    public Enemigo(int pCordX, int pCordY) {
        this.cordX = pCordX;
        this.cordY = pCordY;
        this.vivo = true;
    }
	public void mover(int[][] tablero) {
	    if (!vivo) return; //MUERTO NO SE MUEVE
	
	    Random random = new Random();
	    boolean movimientoValido = false;
	    
	    // este metodo esta ya implementado en Tablero llamalo desde ahi
	    while (!movimientoValido) {
	        int direccion = random.nextInt(4); 
	        switch (direccion) {
	            case 0: // Arriba
	                if (cordY > 0 && tablero[cordX][cordY - 1] == 0) {
	                    cordY--;
	                    movimientoValido = true; 
	                }
	                
	            case 1: // Derecha
	                if (!movimientoValido && cordX < 16 && tablero[cordX + 1][cordY] == 0) {
	                    cordX++;
	                    movimientoValido = true; 
	                }
	               
	            case 2: // Abajo
	                if (!movimientoValido && cordY < 10 && tablero[cordX][cordY + 1] == 0) {
	                    cordY++;
	                    movimientoValido = true; 
	                }
	              
	            case 3: // Izquierda
	                if (!movimientoValido && cordX > 0 && tablero[cordX - 1][cordY] == 0) {
	                    cordX--;
	                    movimientoValido = true; 
	                }
	               
	        }
	
	    }
	}
	
	
	
}