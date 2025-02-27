package viewController;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Pantalla extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Pantalla frame = new Pantalla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 public Pantalla() {
	        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 800, 600); 

	        //  panel principal
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);

	   
	        contentPane.setLayout(new GridLayout(11, 17, 0, 0));

	        for (int i = 0; i < 11; i++) { // Filas
	            for (int j = 0; j < 17; j++) { // Columnas
	                JPanel cell = new JPanel(); //los panels
	                cell.setBackground(Color.BLACK); // Fondo negro por defecto
	                cell.setOpaque(true); // pa que se vea

	               
	                if (i % 2 != 0 && j % 2 != 0) {
	                    cell.setBackground(Color.GRAY); // Bloque duro
	                } else if (i == 0 && j == 0) {
	                    cell.setBackground(Color.BLUE); // BomberMan
	                }

	                contentPane.add(cell); 
	            }
	        }
	    } //stageBack1.png
	}