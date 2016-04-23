package model.business;

public class GestorMovimientosService {

	private static GestorMovimientosService gestorMovimientosService = null;

	public static GestorMovimientosService getInstance() {
		if (gestorMovimientosService == null) {
			gestorMovimientosService = new GestorMovimientosService();
		}
		return gestorMovimientosService;
	}
	
	
}
