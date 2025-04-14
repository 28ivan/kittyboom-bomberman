package viewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.*;
import model.Tablero;

public class Pantalla extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
	private Controlador controlador;
	private static int bMSelec;
	private static String bMColor;
	private static int pantallaSelec=1;
	
    public Pantalla(int pBMSelec,int pPantalla) {
    	setBM(pBMSelec);
    	pantallaSelec=1;
    	setFondoPantalla(pPantalla);
    	initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setObservers();
    	getControlador().actionPerformed(null);
    }
    private void setBM(int pBMSelec) {
    	bMSelec=pBMSelec;
    	if (bMSelec==1) {
    		bMColor="black";
    	}else {
    		bMColor="white";
    	}
	}
    
    private void setFondoPantalla(int pPantalla) {
    	pantallaSelec=pPantalla;	
	}
    
    private void initialize() {
    	setBounds(100, 100, 840, 520);
        setResizable(false);
        setLocationRelativeTo(null);
        addKeyListener(getControlador());
        setVisible(true);
        setContentPane(getContentPane());
        for (int i = 0; i < 11; i++) { 
            for (int j = 0; j < 17; j++) { 
            	contentPane.add(new PanelCord(i,j,bMColor));
            }
        }
    }
    
    private void setObservers() {
    	for (int i = 0; i < 11; i++) { 
            for (int j = 0; j < 17; j++) { 
            	PanelCord panel = (PanelCord) contentPane.getComponent(i*17+j);
        		panel.setObserver(i,j);
            }
        }
    }
   
 // FONDO
    public JPanel getContentPane() {
    	if(contentPane == null) {
	        contentPane = new JPanel() {
	        private static final long serialVersionUID = 1L;
			private Image backgroundImage;
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                try {
	                    //  viewController.imagenes
	                    backgroundImage = ImageIO.read(getClass().getClassLoader().getResource("viewController/imagenes/stageBack"+pantallaSelec+".png"));
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                // Dibujar la imagen de fondo 
	                if (backgroundImage != null) {
	                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	                }
	            }
	        };
	        // TABLERO
	        contentPane.setLayout(new GridLayout(11, 17, 0, 0));
    	}
        return contentPane;
    }   
	
	//CONTROLADOR
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}

	private class Controlador implements ActionListener,KeyListener {
		
		public void handleKeyPressed(int pKey) {
			if (pKey==KeyEvent.VK_UP || pKey==KeyEvent.VK_W) {
				Tablero.getTablero().moverBomberman('u');
	        }
			else if (pKey==KeyEvent.VK_DOWN || pKey==KeyEvent.VK_S) {
				Tablero.getTablero().moverBomberman('d');
	        }
			else if (pKey==KeyEvent.VK_LEFT || pKey==KeyEvent.VK_A) {
				Tablero.getTablero().moverBomberman('l');
	        }
			else if (pKey==KeyEvent.VK_RIGHT || pKey==KeyEvent.VK_D) {
				Tablero.getTablero().moverBomberman('r');
	        }
			else if (pKey==KeyEvent.VK_SPACE) {
				Tablero.getTablero().colocarBomba();
	        }
			
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			Tablero.getTablero().initializeTablero();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			getControlador().handleKeyPressed(key);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
