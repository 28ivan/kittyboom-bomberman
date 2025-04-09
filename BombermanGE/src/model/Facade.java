package model;

public class Facade {
	private static Facade miFC;
	private Tablero tablero = Tablero.getTablero();
	private Facade() {}
	
	public Facade getFacade() {
		if (miFC == null) {
			miFC = new Facade();
		}
		return miFC;
	}
	
	public void crearClassic() {
		tablero.changeStrategy(new stBloquesClassic());
		tablero.colocarBomberMan();
		tablero.colocarEnemigos();
		tablero.colocarBloquesBlandos();
		tablero.colocarBloquesDuros();
	}
	
	public void crearSoft() {
		tablero.changeStrategy(new stBloquesSoft());
		tablero.colocarBomberMan();
		tablero.colocarEnemigos();
		tablero.colocarBloquesBlandos();
		tablero.colocarBloquesDuros();
	}
	
	public void crearEmpty() {
		tablero.colocarBomberMan();
		tablero.colocarEnemigos();
	}
}
