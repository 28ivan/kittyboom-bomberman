package model;
import java.util.Observable;

import viewController.Pantalla;

public class Seleccion extends Observable{
	private static Seleccion miPantallaInicio = null;
	private static final int numBM = 2; //numero de bombermans a elegir
	private static int BMSelec;
	private static int pantallaSelec;
    private static boolean yaSeleccionado;
	
	private Seleccion() {
		this.BMSelec= 0;
		this.yaSeleccionado = false;
		this.pantallaSelec=0;
    }
    
    public static Seleccion getSeleccion() {
    	if(miPantallaInicio==null) {
    		miPantallaInicio = new Seleccion();
    	}
    	return miPantallaInicio;
    }
    
    public void initializePantallaInicio() {
    	
    }
    
    public void seleccionarBomberMan(char pDir) {
    	yaSeleccionado=true;
    	if(BMSelec==0) {
    		if (pDir=='l') {
    			BMSelec++;
    		}else if(pDir=='r') {
    			BMSelec=numBM;
    		}
    	}
    	if (pDir=='l') {
    		if (BMSelec<numBM) {
    			BMSelec++;
    			
    		} else if(BMSelec==numBM) {
    			BMSelec=1;
    			
    		}
    	} else if(pDir=='r') {
    		if (BMSelec>1) {
    			BMSelec--;
    			
    		} else if(BMSelec==1) {
    			BMSelec=numBM;
    		}
    	}
    	setChanged();
        notifyObservers(BMSelec);
    }

	public void inicializarTablero() {
		
		if (yaSeleccionado) {
			//Pasar un set bomberman y pantalla seleccionada
			Tablero.getTablero().setTablero(BMSelec,1);;
			
	    	//VISTA//
			if (BMSelec==1) {
				setChanged();
		        notifyObservers(0);
			} else {
				setChanged();
		        notifyObservers(3);
			}
	    	
		}
	}
    
}
