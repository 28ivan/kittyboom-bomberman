package viewController;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import model.Bomba;

public class BombaVista extends JPanel{
	private static final long serialVersionUID = 3049040957332920611L;
	private Bomba bomba;
	private JLabel lblBomba;
	
	public BombaVista(Bomba pBomba) {
		this.bomba = pBomba;
		inicializar();
		actImagen();
	}	
	
	private void inicializar() {
		this.setSize(getPreferredSize());
        this.setLayout(new GridBagLayout());
        this.add(getLblBomba());
	}
	
    private JLabel getLblBomba() {
        if (lblBomba == null) {
            lblBomba = new JLabel("");
        }
        return lblBomba;
    }
    
    public void actImagen() {
    	String imagen;
    	if(!bomba.haExplotado()) {
    		imagen = "bomb1.png";
    	}
    	else {
    		imagen="kaBomb1.png";
    	}
    	
    	getLblBomba().setIcon(
    			new ImageIcon(this.getClass().getResource(imagen))
    	);
    }
}
