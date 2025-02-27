package viewController;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BloqueDuro extends JPanel {

	private static final long serialVersionUID = 1L;
	private int cordX;
	private int cordY;
	private JLabel lblBloqueDuro;
	
	public BloqueDuro(int pCordX, int pCordY) {
		super();
		initialize();
		this.cordX = pCordX;
		this.cordY = pCordY;
		setDuro();
	}		
	
	private void initialize() {
		this.setSize(getPreferredSize());
		this.setLayout(new GridBagLayout());
		this.add(getBloqueDuro());
	}
	
	private JLabel getBloqueDuro() {
		if (lblBloqueDuro == null) {
			lblBloqueDuro = new JLabel("");
		}
		return lblBloqueDuro;
	}
	
	public void setDuro() {
		getBloqueDuro().setIcon(new ImageIcon(this.getClass().getResource("hard5.png")));
	}

}
