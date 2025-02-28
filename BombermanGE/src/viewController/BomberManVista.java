package viewController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BomberMan;

@SuppressWarnings("deprecation")
public class BomberManVista extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	private int cordX;
	private int cordY;
	private Controlador controlador = null;
	private JLabel lblBomberManVista;
	
	public BomberManVista(int pCordX, int pCordY) {
		super();
		initialize();
		this.cordX = pCordX;
		this.cordY = pCordY;
		setBomberManVista();
		BomberMan.getBomberMan().addObserver(this);
		controlador = new Controlador();
	    this.addKeyListener(controlador);
	}
		
	private void initialize() {
		this.setSize(getPreferredSize());
		this.add(getBomberManVista());
	}

	private JLabel getBomberManVista() {
		if (lblBomberManVista == null) {
			lblBomberManVista = new JLabel("");
		}
		return lblBomberManVista;
	}
	
	private void setBomberManVista() {
		getBomberManVista().setIcon(new ImageIcon(this.getClass().getResource("whitefront1.png")));
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
	
	@Override
    public void update(Observable o, Object arg) {
		if(o instanceof BomberMan) {
			Object res[]=(Object[])arg;
			char mov = (char) res[2];
			if (mov=='x') {
				getBomberManVista().setIcon(new ImageIcon(this.getClass().getResource("whitewithbomb1.png")));
			}
			else if (mov=='u') {
				getBomberManVista().setIcon(new ImageIcon(this.getClass().getResource("whiteup1.png")));
			}
			else if (mov=='d') {
				getBomberManVista().setIcon(new ImageIcon(this.getClass().getResource("whitedown1.png")));
			}
			else if (mov=='l') {
				getBomberManVista().setIcon(new ImageIcon(this.getClass().getResource("whiteleft1.png")));
			}
			else if (mov=='r') {
				getBomberManVista().setIcon(new ImageIcon(this.getClass().getResource("whiteright1.png")));
			}
		}
    }
}
