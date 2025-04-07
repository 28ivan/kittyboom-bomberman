package model;

public class Facade {
	private static Facade miFC;
	
	private Facade() {}
	
	public Facade getFacade() {
		if (miFC == null) {
			miFC = new Facade();
		}
		return miFC;
	}
}
