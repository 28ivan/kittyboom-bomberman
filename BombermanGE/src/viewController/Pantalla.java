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


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Tablero) {
			Object res[]=(Object[])arg;
			String tipo = (String) res[0];
			int i = (int) res[1];
			int j = (int) res[2];
			setLabel(i,j, tipo);

		}
    }
	
	  public void setLabel(int pI, int pJ, String pTipo) {
	    	PanelCord panel = (PanelCord) contentPane.getComponent(pI*17+pJ);
	    	JLabel label = panel.getLblNewLabel();
	    	label.setIcon(new ImageIcon(PanelCord.class.getResource(pTipo)));
	    }
	    
	
	//CONTROLADOR
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}

	private class Controlador implements ActionListener {
		
		public void gestionar(int pKey) {
			if (pKey==KeyEvent.VK_UP || pKey==KeyEvent.VK_W) {
				BomberMan.getBomberMan().mover('u');
	        }
			else if (pKey==KeyEvent.VK_DOWN || pKey==KeyEvent.VK_S) {
				BomberMan.getBomberMan().mover('d');
	        }
			else if (pKey==KeyEvent.VK_LEFT || pKey==KeyEvent.VK_A) {
				BomberMan.getBomberMan().mover('l');
	        }
			else if (pKey==KeyEvent.VK_RIGHT || pKey==KeyEvent.VK_D) {
				BomberMan.getBomberMan().mover('r');
	        }
			else if (pKey==KeyEvent.VK_SPACE) {
				BomberMan.getBomberMan().colocarBomba();
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
		getControlador().gestionar(key);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
