package model.business;

import model.classes.Alerta;
import model.classes.Movimiento;
import model.data.Connect;

public class GestorAlertasService {

	private static GestorAlertasService gestorAlertasService = null;

	public static GestorAlertasService getInstance() {
		if (gestorAlertasService == null) {
			gestorAlertasService = new GestorAlertasService();
		}
		return gestorAlertasService;
	}

	public void addAlerta(String username, float alimentacionMonth, float estudiosMonth, float suministrosMonth,
			float mantenimientoMonth, float hipotecaAlquilerMonth, float segurosMonth, float ocioMonth,
			float otrosMonth, float alimentacionYear, float estudiosYear, float suministrosYear,
			float mantenimientoYear, float hipotecaAlquilerYear, float segurosYear, float ocioYear, float otrosYear) {
		Alerta alerta = new Alerta(username, alimentacionMonth, estudiosMonth, suministrosMonth, mantenimientoMonth,
				hipotecaAlquilerMonth, segurosMonth, ocioMonth, otrosMonth, alimentacionYear, estudiosYear,
				suministrosYear, mantenimientoYear, hipotecaAlquilerYear, segurosYear, ocioYear, otrosYear);
		Connect.getIDao().addAlerta(alerta);
	}
	
	public Alerta getAlerta(String username){
		return Connect.getIDao().getAlerta(username);
	}
	
	public void updateAlerta(String username, float alimentacionMonth, float estudiosMonth, float suministrosMonth,
			float mantenimientoMonth, float hipotecaAlquilerMonth, float segurosMonth, float ocioMonth,
			float otrosMonth, float alimentacionYear, float estudiosYear, float suministrosYear,
			float mantenimientoYear, float hipotecaAlquilerYear, float segurosYear, float ocioYear, float otrosYear){
		Alerta alertaAntigua = Connect.getIDao().getAlerta(username);
		Alerta alertaActualizada = new Alerta(username, alimentacionMonth, estudiosMonth, suministrosMonth, mantenimientoMonth,
				hipotecaAlquilerMonth, segurosMonth, ocioMonth, otrosMonth, alimentacionYear, estudiosYear,
				suministrosYear, mantenimientoYear, hipotecaAlquilerYear, segurosYear, ocioYear, otrosYear);
		Connect.getIDao().updateAlerta(alertaActualizada);
	}
	
	public void deleteMovimiento(Movimiento entity){
		Connect.getIDao().deleteMovimiento(entity);
	}
}
