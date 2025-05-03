package viewController.Pantallas;

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

import model.Tableros.Seleccion;
import model.Tableros.Tablero;

@SuppressWarnings({"deprecation"})
public class Pantalla extends JFrame implements Observer{

    private static final long serialVersionUID = 1L;
    private JLayeredPane contentPane;
    private JPanel gridPanel;
    private JLabel enemigosLabel;
	private Controlador controlador;
	private static int bMSelec;
	private static String bMColor;
	private static int pantallaSelec=1;
	
    public Pantalla(int pBMSelec,int pPantalla) {
    	setFondoPantalla(pPantalla);
    	setBM(pBMSelec);
    	initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setObservers();
    	getControlador().actionPerformed(null);
        setEnemigosLabel(Tablero.getTablero().getNumEnemigos());
    }
    private void setBM(int pBMSelec) {
    	bMSelec=pBMSelec;
    	if (bMSelec==2 || pantallaSelec==1) {
    		bMColor="white";
    	}else {
    		bMColor="black";
    	}
	}
    
    private void setFondoPantalla(int pPantalla) {
    	pantallaSelec=pPantalla;	
	}
    
    private void initialize() {
        setBounds(100, 100, 850, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        addKeyListener(getControlador());
        setVisible(true);

        setContentPane(getContentPane());

        if(pantallaSelec==4) {
        	ponerGifDeFondo("viewController/imagenes/stageBack" + pantallaSelec + ".gif");
        }else {
        	ponerImagenDeFondo("viewController/imagenes/stageBack" + pantallaSelec + ".png");
        }
        
        for (int i = 0; i < 11; i++) { 
            for (int j = 0; j < 17; j++) { 
            	PanelCord panel = new PanelCord(i, j, bMColor);
                panel.setPreferredSize(null);
                gridPanel.add(panel);
            }
        }
    }
    
    private void setObservers() {
    	for (int i = 0; i < 11; i++) { 
            for (int j = 0; j < 17; j++) { 
            	PanelCord panel = (PanelCord) gridPanel.getComponent(i*17+j);
        		panel.setObserver(i,j);
            }
        }
    	Tablero.getTablero().addObserver(this);
    }
   
 // FONDO
    public JLayeredPane getContentPane() {
        if (contentPane == null) {
            contentPane = new JLayeredPane();
            contentPane.setLayout(null); // Posicionamiento manual

            // Crear panel de la cuadrícula
            gridPanel = new JPanel(new GridLayout(11, 17, 0, 0));
            gridPanel.setOpaque(false);
            gridPanel.setBounds(0, 0, 840, 520); 

            contentPane.add(gridPanel, JLayeredPane.DEFAULT_LAYER); // Añadir al fondo
        }
        return contentPane;
    }
    
    private void ponerGifDeFondo(String rutaGif) {
        JLabel gifLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(rutaGif)));
        gifLabel.setBounds(0, 0, 840, 515); // Asegúrate de coincidir con la ventana
        contentPane.add(gifLabel, JLayeredPane.FRAME_CONTENT_LAYER); // Capa de fondo
        contentPane.setComponentZOrder(gifLabel, contentPane.getComponentCount() - 1); // al fondo
    }
    
    private void ponerImagenDeFondo(String rutaImagen) {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource(rutaImagen)));
            Image imagen = icon.getImage().getScaledInstance(840, 520, Image.SCALE_SMOOTH);
            JLabel imagenLabel = new JLabel(new ImageIcon(imagen));
            imagenLabel.setBounds(0, 0, 840, 520);
            contentPane.add(imagenLabel, JLayeredPane.FRAME_CONTENT_LAYER);
            contentPane.setComponentZOrder(imagenLabel, contentPane.getComponentCount() - 1); // al fondo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void limpiarFondo() {
        for (Component comp : contentPane.getComponents()) {
            if (comp instanceof JLabel) {
                contentPane.remove(comp);
            }
        }
        contentPane.repaint();
    }
    
    private void setEnemigosLabel(int numEnemigos) {
    	if (enemigosLabel == null) {
	    	enemigosLabel = new JLabel("Enemigos: " + numEnemigos); 
	    	enemigosLabel.setFont(new Font("Arial", Font.BOLD, 20));
	    	enemigosLabel.setForeground(Color.BLACK); 
	    	enemigosLabel.setBounds(640, 480, 180, 30);
	    	enemigosLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    	contentPane.add(enemigosLabel, JLayeredPane.POPUP_LAYER); 
		}else {
			enemigosLabel.setText("Enemigos: " + numEnemigos);
		}
    }
    
    
    
    //UPDATE
    @Override
	public void update(Observable o, Object arg) {
		if(o instanceof Tablero) {
			Object res[]=(Object[])arg;
			//1: cambiar numEnemigos
			//2: Ganar 
			int opcion = (int) res[0];
			int num = (int) res[1];
			switch (opcion) {
				case 1:
					setEnemigosLabel(num);
	                break;
				case 2:
            		limpiarFondo();
					ponerGifDeFondo("viewController/imagenes/stageBack4.gif");
					break;
				case 3:
					setVisible(false);
					Seleccion.getSeleccion().reiniciarJuego();
			}
		}
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
