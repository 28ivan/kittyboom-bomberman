package viewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


import javax.imageio.ImageIO;
import javax.swing.*;

import model.BomberMan;
import model.Tablero;

public class Pantalla extends JFrame implements Observer,KeyListener{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
	private Controlador controlador;

    public Pantalla() {
    	initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tablero.getTablero().addObserver(this);
        getControlador().actionPerformed(null);
    }
    
    private void initialize() {
        setBounds(100, 100, 666, 404);
        setLocationRelativeTo(null);
        addKeyListener(this);
        setVisible(true);
     
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
        // TABLERO
        contentPane.setLayout(new GridLayout(11, 17, 0, 0));
        setContentPane(contentPane);
        for (int i = 0; i < 11; i++) { 
            for (int j = 0; j < 17; j++) { 
            	contentPane.add(new PanelCord(i,j));
            }
        }

    }

    //UPDATE OBSERVER

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Tablero) {
			Object res[]=(Object[])arg;
			int pI = (int) res[1];
			int pJ = (int) res[2];
			String opcion = (String) res[0];
			switch (opcion) {
            case "u":
            	setLabel("/viewController/Bomberman/whiteup1.png",pI,pJ);
            	break;
            case "d":
            	setLabel("/viewController/Bomberman/whitefront1.png",pI,pJ);
            	break;
            case "l":
            	setLabel("/viewController/Bomberman/whiteleft1.png",pI,pJ);
            	break;
            case "r":
            	setLabel("/viewController/Bomberman/whiteright1.png",pI,pJ);
            	break;
            case "bmBomba":
            	setLabel("/viewController/Bomberman/whitewithbomb1.png",pI,pJ);
            	break;
            case "bomba":
                setLabel("/viewController/Objetos/bomb1.png", pI, pJ);
                break;
            case "duro":
            	setLabel("/viewController/Objetos/hard5.png",pI,pJ);
            	break;
            case "blando":
            	setLabel("/viewController/Objetos/soft4.png",pI,pJ);
            	break;
            case "explosion":
            	setLabel("/viewController/Objetos/kabomb5.png",pI,pJ);
            	break;
            case "muerto":
            	setLabel("/viewController/Bomberman/onfire2.png",pI,pJ);
            	break;
            case "delete":
            	clearLabel(pI,pJ);
            	break;
            default:
			}
		}
    }
	
	public void setLabel(String pTipo, int pI, int pJ) {
		PanelCord panel = (PanelCord) contentPane.getComponent(pI*17+pJ);
		JLabel label = panel.getLabel();
		label.setIcon(new ImageIcon(PanelCord.class.getResource(pTipo)));
	}
	
	public void clearLabel(int pI, int pJ) {
		PanelCord panel = (PanelCord) contentPane.getComponent(pI*17+pJ);
		JLabel label = panel.getLabel();
		label.setIcon(null);
	}
	    
	
	//CONTROLADOR
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}

	private class Controlador implements ActionListener {
		
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
				Tablero.getTablero().colocarBomba(BomberMan.getBomberMan().getI(),BomberMan.getBomberMan().getJ());
	        }
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			Tablero.getTablero().initializeTablero();
		}
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
