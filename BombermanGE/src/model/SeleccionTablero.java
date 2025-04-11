package model;

import java.util.Observable;

public class SeleccionTablero extends Observable{
	private static SeleccionTablero miSeleccionTablero = null;
	
	
	private SeleccionTablero() {
    }
	
	public static SeleccionTablero getSeleccionTablero() {
    	if(miSeleccionTablero==null) {
    		miSeleccionTablero = new SeleccionTablero();
    	}
    	return miSeleccionTablero;
    }

	public void cerrarPantalla() {
		// TODO Auto-generated method stub
		setChanged();
        notifyObservers(0);
	}

	public void initializePantallaSeleccionTipoTablero() {
		// TODO Auto-generated method stub
		
	}
}
