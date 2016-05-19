package model.business;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import model.classes.Alerta;
import model.classes.ClaseGasto;
import model.classes.Movimiento;
import model.data.Connect;

public class GestorAlertasService {

	private static GestorAlertasService gestorAlertasService = null;
	
	private List<Float> listaAlertasYear;
	private List<Float> listaAlertasMonth;
	

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
	
	public void deleteAlerta(Alerta entity){
		Connect.getIDao().deleteAlerta(entity);
	}
	
	public void getAlertas(String username){
		
		listaAlertasYear = null;
		listaAlertasMonth = null;
		
		Timestamp fromYear;
		Timestamp toYear;

		Timestamp fromMonth;
		Timestamp toMonth;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// fromYear toYear

		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);

		Date dateFromYear = null;
		try {
			dateFromYear = format.parse(year + "-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fromYear = new Timestamp(dateFromYear.getTime());

		Date dateToYear = null;
		try {
			dateToYear = format.parse(year + "-12-31");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toYear = new Timestamp(dateToYear.getTime());

		// fromMonth toMonth

		int month = cal.get(Calendar.MONTH) + 1;

		Date dateFromMonth = null;
		try {
			if (month > 9) {
				dateFromMonth = format.parse(year + "-" + month + "-01");
			} else {
				dateFromMonth = format.parse(year + "-0" + month + "-01");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fromMonth = new Timestamp(dateFromMonth.getTime());

		Calendar cal2 = Calendar.getInstance();

		cal2.add(Calendar.MONTH, month - 1);
		cal2.set(Calendar.DAY_OF_MONTH, 1);
		cal2.add(Calendar.DATE, -1);
		int lastDay = cal2.get(Calendar.DAY_OF_MONTH);

		Date dateToMonth = null;
		try {
			if (month > 9) {
				dateToMonth = format.parse(year + "-" + month + "-01");
			} else {
				dateToMonth = format.parse(year + "-0" + month + "-" + lastDay);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toMonth = new Timestamp(dateToMonth.getTime());
		
		
		List<ClaseGasto> listaClaseGasto = Connect.getIDao().getClaseGasto();
		listaAlertasYear = new ArrayList<Float>();
		listaAlertasMonth = new ArrayList<Float>();
		List<Movimiento> listaMovimientosYear = Connect.getIDao().getMovimientosUsernameFecha(username, fromYear, toYear);
		System.out.println("size: " + listaMovimientosYear.size());
		List<Movimiento> listaMovimientosMonth = Connect.getIDao().getMovimientosUsernameFecha(username, fromMonth, toMonth);	
		System.out.println("size: " + listaMovimientosMonth.size());
		for(int i=0;i<listaClaseGasto.size();i++){
			listaAlertasYear.add((float)0);
			listaAlertasMonth.add((float)0);
		}
		
		float total = 0;
		for(int i=0;i<listaMovimientosYear.size();i++)
		{
			total = listaAlertasYear.get(listaMovimientosYear.get(i).getId_clase()-1);
			total = total + listaMovimientosYear.get(i).getImporte();
			listaAlertasYear.set(listaMovimientosYear.get(i).getId_clase()-1, total);
		}
		for(int i=0;i<listaMovimientosMonth.size();i++)
		{
			total = listaAlertasMonth.get(listaMovimientosMonth.get(i).getId_clase()-1);
			total = total + listaMovimientosMonth.get(i).getImporte();
			listaAlertasMonth.set(listaMovimientosMonth.get(i).getId_clase()-1, total);
		}
	}
	
	public List<Float> getListaAlertasYear(){
		return listaAlertasYear;
		
	}
	public List<Float> getListaAlertasMonth(){
		return listaAlertasMonth;
	}
	
	
}
