package viewController;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Seleccion;
import model.SeleccionTablero;

public class PantallaSeleccionTipoTablero extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	private Controlador controlador;
	private JPanel contentPane;
	
	public PantallaSeleccionTipoTablero() {
		initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SeleccionTablero.getSeleccionTablero().addObserver(this);
        getControlador().actionPerformed(null);
	}
	private void initialize() {
		// TODO Auto-generated method stub
		setBounds(100, 100, 605, 374);
        setResizable(false);
        setLocationRelativeTo(null);
        addKeyListener(getControlador());
        setFocusable(true);
        requestFocus();
        setContentPane(getContentPane());
        setVisible(true);
	}
	
	public JPanel getContentPane() {
    	// Cargar imagen de fondo
        try {
            backgroundImage = ImageIO.read(getClass().getClassLoader().getResource("viewController/imagenes/back.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
		return contentPane;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof SeleccionTablero) { //ESOBJETO CAMBIARLO------------------
			int seleccionPantalla = (int) arg;
			switch (seleccionPantalla) {
				case 0:
					this.setVisible(false);
					Seleccion.getSeleccion().apagarTipoTablero();
		        default:
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
				if (pKey==KeyEvent.VK_ENTER || pKey==KeyEvent.VK_SPACE) {
					SeleccionTablero.getSeleccionTablero().cerrarPantalla();
				}
			}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			getControlador().handleKeyPressed(key);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				SeleccionTablero.getSeleccionTablero().initializePantallaSeleccionTipoTablero();
			}
	    }
	}
