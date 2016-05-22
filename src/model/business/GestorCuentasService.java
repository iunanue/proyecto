package model.business;

import java.util.List;

import model.classes.Cuenta;
import model.data.Connect;

public class GestorCuentasService {

	private static GestorCuentasService gestorCuentasService = null;

	public static synchronized GestorCuentasService getInstance() {
		if (gestorCuentasService == null) {
			gestorCuentasService = new GestorCuentasService();
		}
		return gestorCuentasService;
	}
	
	public void addCuenta(float saldo,String descripcion){
		Cuenta cuenta = new Cuenta(saldo,descripcion);
		Connect.getIDao().addCuenta(cuenta);
	}
	
	public List<Cuenta> getCuentas(){
		return Connect.getIDao().getCuentas();
	}
	
	public Cuenta getCuenta(int id_cuenta){
		return Connect.getIDao().getCuenta(id_cuenta);
	}
	
	public void updateCuenta(int id_cuenta,float saldo,String descripcion){
		Cuenta cuenta = Connect.getIDao().getCuenta(id_cuenta);
		cuenta.setSaldo(saldo);
		cuenta.setDescripcion(descripcion);
		Connect.getIDao().updateCuenta(cuenta);
	}
	
	public void deleteCuenta(Cuenta entity){
		Connect.getIDao().deleteCuenta(entity);
	}
}
