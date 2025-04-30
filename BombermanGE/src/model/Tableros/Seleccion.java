package model.Tableros;
import java.util.Observable;

@SuppressWarnings({"deprecation"})
public class Seleccion extends Observable{
	private static Seleccion miPantallaInicio = null;
	private static final int numBM = 2; //numero de bombermans a elegir
	private static int BMSelec;
	private static int pantallaSelec;
    private static boolean yaSeleccionado;
	
	private Seleccion() {
		BMSelec= 0;
		yaSeleccionado = false;
		pantallaSelec=1;
    }
    
    public static Seleccion getSeleccion() {
    	if(miPantallaInicio==null) {
    		miPantallaInicio = new Seleccion();
    	}
    	return miPantallaInicio;
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
			if(BMSelec==1&&pantallaSelec==4) {
				//aqui no tiene q hacer nada, si hago el if de otra manera no me deja ns pq
			} else {
				Tablero.getTablero().setTablero(BMSelec,pantallaSelec);
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
	
	public void seleccionarTipoTablero() {
		// TODO Auto-generated method stub
		SeleccionTablero.getSeleccionTablero();
		setChanged();
        notifyObservers(4);
	}

	public void apagarTipoTablero() {
		// TODO Auto-generated method stub
		setChanged();
        notifyObservers(5);
	}
	
	public void setPantalla(int pTablero) {
		// TODO Auto-generated method stub
		pantallaSelec=pTablero;
		if (pTablero==1) {
			setChanged();
	        notifyObservers(10);
		} else if (pTablero==2) {
			setChanged();
	        notifyObservers(20);
		}else if (pTablero==3) {
			setChanged();
	        notifyObservers(30);
		} else if(pTablero==4) {
			setChanged();
	        notifyObservers(40);
        }
	}
	
    
}
