package viewController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Blando;
import model.BomberMan;
import model.Duro;
import model.Tablero;

public class Pantalla extends JFrame implements Observer{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane=null;

    public Pantalla() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 666, 404);
        setVisible(true);
        
        Tablero.getTablero().addObserver(this);

        // FONDO
        contentPane = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    //  viewController.imagenes
                    backgroundImage = ImageIO.read(getClass().getClassLoader().getResource("viewController/imagenes/stageBack3.png"));
                } catch (IOException e) {
                   
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Dibujar la imagen de fondo 
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

     
        contentPane.setLayout(new GridLayout(11, 17, 0, 0));
        setContentPane(contentPane);

      
        for (int i = 0; i < 11; i++) { // Filas
            for (int j = 0; j < 17; j++) { 
            	contentPane.add(new PanelCord(i,j));
            }
        }
    }

    public void setLabel(int pCordX, int pCordY, String pTipo) {
    	PanelCord panel = (PanelCord) contentPane.getComponent(pCordX*16+pCordY);
    	JLabel label = panel.getLblNewLabel();
    	label.setIcon(new ImageIcon(PanelCord.class.getResource(pTipo)));
    }
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Tablero) {
			Object res[]=(Object[])arg;
			String tipo = (String) res[0];
			int cordX = (int) res[1];
			int cordY = (int) res[2];
			setLabel(cordX,cordY, tipo);

		}
    }
	private class Controlador implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W) {
				BomberMan.getBomberMan().mover('u');
	        }
			else if (e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
				BomberMan.getBomberMan().mover('d');
	        }
			else if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
				BomberMan.getBomberMan().mover('l');
	        }
			else if (e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) {
				BomberMan.getBomberMan().mover('r');
	        }
			else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				BomberMan.getBomberMan().colocarBomba();
	        }
	    }
		
		@Override
	    public void keyTyped(KeyEvent e) {
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	    }
	}
}