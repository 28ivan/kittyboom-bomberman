package model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings({"deprecation"})
public class CasillaTablero extends Observable {
	private Casilla c;
	private int i;
	private int j;
	private int estadoBomba = 1;
	private Timer timerBomba;
	
	public CasillaTablero(int pI, int pJ) {
		c=BloqueFactory.getBloqueFactory().generate("Vacio");
		i=pI;
		j=pJ;
	}
	
	
	
	//METODO ≈ changeState()
	public void cambiarCasilla(Casilla pC) {
		String st=null;
		int n=0;
		if (pC.esObjeto("BomberMan")) {
			BomberMan bm=BomberManBlanco.getBomberMan();
			if (!bm.haGanado()) {
				if(!bm.isVivo()) {
					animacionMuerte();
				}
				else {
					if (!bm.conBomba() && !bm.antiguaBomba()){
						st=String.valueOf(bm.getDir());
						n=bm.getNum();
						c=pC;
					}
					else {
						if (bm.antiguaBomba()) {
							st="Bomba";
							n=estadoBomba;
						}
					}
					setChanged();
					notifyObservers(new Object[] {st,n});
				}
			}
			else {
				animacionVictoria();
			}
		}
		else if (pC.esObjeto("Barrera")) {
			c=pC;
			animacionBarrera();
		}
		else if (pC.esObjeto("Invocacion")) {
			c=pC;
			animacionInvocacion();
		}
		else {
			if (pC.esObjeto("Vacio")) {
				st="Vacio";
			}
			else if (pC.esObjeto("Duro")) {
				st="Duro";
			}
			else if (pC.esObjeto("Blando")) {
				st="Blando";
			}
			else if (pC.esObjeto("Bomba")) {
				if (BomberManBlanco.getBomberMan().conBomba() || BomberManNegro.getBomberMan().conBomba()) {
	                st = "BmBomba";
	            } else {
	                st = "Bomba";
	            }
				n=estadoBomba;
				animacionBomba();
			}
			else if (pC.esObjeto("Explosion")) {
				st="Explosion";
			}
			else if (pC.esObjeto("Enemigo")) {
				st="Enemigo";
			}else if (pC.esObjeto("Boss")) {
				st="Boss";
			}
			c=pC;
			setChanged();
			notifyObservers(new Object[] {st,n});
		}
	}

	//METODOS ENCARGADOS DE LA GESTION DE ESTADO
	public void destruir() {
		c.destruir(this, i, j);
	}
	
	public void vaciar() {
		c.vaciar(this);
	}
	
	public boolean esObjeto(String pTipo) {
		return c.esObjeto(pTipo);
	}
	
	public boolean isVivo() {
		return c.isVivo();
	}
	
	public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public Casilla getCasilla() {
    	return this.c;
    }
    
    public boolean puedeMoverse() {
        return c.puedeMoverse();
    }
    
    public void mover(int pI, int pJ) {
        c.mover(pI,pJ);
    }
    
    
    
    //METODOS ANIMACIONES
    private void animacionMuerte() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int contador = 1;
            @Override
            public void run() {
                if (contador <= 2) {
                    setChanged();
                    notifyObservers(new Object[] {"Morir", contador});
                    contador++;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 175);
    }
    
    private void animacionVictoria() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int[] secuencia={1, 1, 2, 1, 1};
            int indice=0;
            String st;
            @Override
            public void run() {
                if (indice<secuencia.length) {
                	if (indice==0 || indice==4) {
                		st="d";
                	}
                	else {
                		st="Ganar";
                	}
                	setChanged();
                    notifyObservers(new Object[] {st, secuencia[indice]});
                    indice++;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 200);
    }
    
    private void animacionBarrera() {
    	Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int contador = 1;
            @Override
            public void run() {
            	if (contador <= 3) {
                    setChanged();
                    notifyObservers(new Object[] {"Barrera", contador});
                    contador++;
                } else {
                    timer.cancel();             
                }
            }
        }, 0, 1000);
    }
    
    private void animacionBomba() {
        if (timerBomba != null) timerBomba.cancel();
        timerBomba = new Timer();
        timerBomba.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                estadoBomba++;
                if (estadoBomba > 3) {
                    timerBomba.cancel();
                    return;
                }
                String tipo;
                if (BomberManBlanco.getBomberMan().conBomba() || BomberManNegro.getBomberMan().conBomba()) {
                    tipo = "BmBomba";
                } else {
                    tipo = "Bomba";
                }
                setChanged();
                notifyObservers(new Object[] {tipo, estadoBomba});
            }
        }, 1000, 1000);
    }
    
    private void animacionInvocacion() {
		// TODO Auto-generated method stub
		
	}
}
