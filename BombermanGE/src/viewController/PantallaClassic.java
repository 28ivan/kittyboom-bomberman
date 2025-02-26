package viewController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PantallaClassic extends JPanel {
    private static final int fila = 11; // Filas
    private static final int col = 17; // Columnas

    public PantallaClassic() {
        setLayout(new GridLayout(fila, col)); // Layout de cuadrícula
        setBackground(Color.BLACK); // Fondo 
        Random p =new Random();
        
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
            	boolean x =p.nextBoolean();
                JLabel cell = new JLabel(); // Cada celda es un JLabel
                if (i % 2 != 0 && j % 2 != 0) {
                    cell.setText("BD"); // Bloque duro
                    cell.setForeground(Color.BLACK);
                    cell.setBackground(Color.WHITE);
                } else if (i == 0 && j == 0) {
                    cell.setText("Bombi"); // BomberMan
                    cell.setForeground(Color.BLACK);
                    cell.setBackground(Color.pink);
                } else { //algunos random, otros libre
                	if (x && ((j != 0 && i != 1) || (i != 0 && j != 1))) {
                		cell.setText("BB"); // nada 
                        cell.setBackground(Color.MAGENTA);
                	} else {
                		cell.setText(""); // nada 
                        cell.setBackground(Color.MAGENTA);
                	}   
                }
                cell.setOpaque(true); //esto es pa que se vean los botones
                add(cell); // Añadir la celda al panel
            }
        }
        
    }
}