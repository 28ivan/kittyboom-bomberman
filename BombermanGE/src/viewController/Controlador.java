package viewController;

import java.util.List;
import java.util.ArrayList;


import model.Bomba;

public class Controlador {
	private List<Bomba> bombas;
	private int maxBombas;
	
	public Controlador(int pMaxBombas) {
		//porque dependiendo del bomberman va a poder poner 10 o 1
		this.bombas = new ArrayList<>();
		this.maxBombas = pMaxBombas;
	}
	
	public boolean ponerBomba(int pCordX, int pCordY, int pRadio) {
		if(bombas.size() >= maxBombas) {
			return false;
		}
		Bomba bomba = new Bomba(pCordX, pCordY, pRadio);
		bomba.activar();
		bombas.add(bomba);
		return true;
	}
}