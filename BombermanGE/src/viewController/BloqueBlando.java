package viewController;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BloqueBlando extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int cordX;
	private int cordY;

	private JLabel lblBloqueBlando;
	
	public BloqueBlando(int pCordX, int pCordY) {
		super();
		initialize();
		this.cordX = pCordX;
		this.cordY = pCordY;
		setBlando();
	}
		
	private void initialize() {
		this.setSize(getPreferredSize());
		this.add(getBloqueBlando());
	}

	private JLabel getBloqueBlando() {
		if (lblBloqueBlando == null) {
			lblBloqueBlando = new JLabel("");
		}
		return lblBloqueBlando;
	}
	
	private void setBlando() {
		getBloqueBlando().setIcon(new ImageIcon(this.getClass().getResource("soft4.png")));
	}
}
