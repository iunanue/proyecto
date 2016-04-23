package model.business;

public class GestorCuentasService {

	private static GestorCuentasService gestorCuentasService = null;

	public static GestorCuentasService getInstance() {
		if (gestorCuentasService == null) {
			gestorCuentasService = new GestorCuentasService();
		}
		return gestorCuentasService;
	}
	
}
