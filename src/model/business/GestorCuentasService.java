package model.business;

import java.util.List;

import model.classes.Cuenta;
import model.data.Connect;

public class GestorCuentasService {

	private static GestorCuentasService gestorCuentasService = null;

	public static GestorCuentasService getInstance() {
		if (gestorCuentasService == null) {
			gestorCuentasService = new GestorCuentasService();
		}
		return gestorCuentasService;
	}
	
	public List<Cuenta> getCuentas(){
		return Connect.getIDao().getCuentas();
	}
	
}
