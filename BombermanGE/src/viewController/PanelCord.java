package viewController;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCord extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i;
	private int j;
	private JLabel Label;

	public PanelCord(int pI, int pJ) {
		this.i = pJ;
		this.j = pJ;
		this.setOpaque(false);
		add(getLabel());
		this.setSize(getPreferredSize());
		
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	public JLabel getLabel() {
		if (Label == null) {
			Label = new JLabel("");
		}
		return Label;
	}
}
