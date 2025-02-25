package viewController;

import javax.swing.*;
import java.awt.*;

public class PantallaClassic extends JPanel {
    private static final int ROWS = 11; // Filas
    private static final int COLS = 17; // Columnas

    public PantallaClassic() {
        setLayout(new GridLayout(ROWS, COLS)); // Layout de cuadrícula
        setBackground(Color.BLACK); // Fondo 

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JLabel cell = new JLabel(); // Cada celda es un JLabel
                if (i % 2 != 0 && j % 2 != 0) {
                    cell.setText("EDA"); // Bloque duro
                    cell.setForeground(Color.BLACK);
                    cell.setBackground(Color.WHITE);
                } else if (i == 0 && j == 0) {
                    cell.setText("Bomberman"); // BomberMan
                    cell.setForeground(Color.BLACK);
                    cell.setBackground(Color.pink);
                } else {
                    cell.setText(""); // nada 
                    cell.setBackground(Color.MAGENTA);
                }
                cell.setOpaque(true); //esto es pa que se vean los botones
                add(cell); // Añadir la celda al panel
            }
        }
    }
}