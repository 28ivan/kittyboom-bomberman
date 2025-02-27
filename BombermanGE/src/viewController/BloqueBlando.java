dpackage viewController;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BloqueBlando extends JPanel{
	private int cordX;
	private int cordY;
	private boolean explotado = false;
	private JLabel lblBloque;
	
	public BloqueBlando(int pCordX, int pCordY, boolean pExplotado) {
		super();
		initialize();
		this.cordX = pCordX;
		this.cordY = pCordY;
		this.explotado = pExplotado;
		setBloque(pExplotado);
	}
		
	private void initialize() {
		this.setSize(getPreferredSize());
		this.setLayout(new GridBagLayout());
		this.add(getBloqueBlando());
	}

	private JLabel getBloqueBlando() {
		if (lblBloque == null) {
			lblBloque = new JLabel("");
		}
		return lblBloque;
	}
	
	private void setBloque(boolean pExplotado) {

		if (pExplotado) {
			getBloqueBlando().setIcon(null);
		}else {
			getBloqueBlando().setIcon(new ImageIcon(this.getClass().getResource("soft4.png")));
		}
		
	}
}
