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
	private int cordX;
	private int cordY;
	private JLabel lblNewLabel;

	public PanelCord(int pCordX, int pCordY) {
		this.cordX = pCordX;
		this.cordY = pCordY;
		this.setOpaque(false);
		add(getLblNewLabel());
		
	}
	
	
	public int getCordX() {
		return this.cordX;
	}
	
	public int getCordY() {
		return this.cordY;
	}
	
	/*public void setIcon(String pRuta){
		lblPanel.setIcon(new ImageIcon(this.getClass().getResource("/viewController/sprites_bomberman/Pantalla/whitedown1.png")));
	}*/
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(PanelCord.class.getResource("/viewController/sprites_bomberman/Pantalla/whitedown1.png")));
		}
		return lblNewLabel;
	}
}
