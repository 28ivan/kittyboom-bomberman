package model;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Tablero extends JPanel {
    private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas
    private static Tablero miTablero = null;
    private Object[][] matriz = new Object[fila][col];
    
    private Tablero() {
        initialize();
    }
    
    private void initialize() {
    	
    	Random r =new Random();

    	for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
            	boolean x =r.nextBoolean();
            	if(i==0 && j==0) {
            		matriz[0][0] = new BomberMan();
            	}
            	else if (i % 2 != 0 && j % 2 != 0) {
            		matriz[i][j] = new BloqueDuro();
            	}
            	else if((i!=0 && j!=1) || (i!=1 && j!=0)) {
            		if (x) {
            			matriz[i][j] = new BloqueBlanco();
            		}
            	}
            }
    	}
    }
    
    public Tablero getTablero() {
    	if(miTablero==null) {
    		miTablero = new Tablero();
    	}
    	return miTablero;
    }
}