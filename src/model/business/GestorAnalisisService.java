package model.business;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Movimiento;
import model.data.Connect;

public class GestorAnalisisService {

	private static GestorAnalisisService gestorAnalisisService = null;

	// List<String>listaMeses;
	List<String> listaMeses = new ArrayList<String>(Arrays.asList(new String[] { "Enero", "Febrero", "Marzo", "Abril",
			"Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
	List<Integer> listaMesesUltimoDia = new ArrayList<Integer>(
			Arrays.asList(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));
	List<Float> listaMesesIngresos;
	List<Float> listaMesesGastos;
	List<Float> listaMesesBeneficios;
	List<Movimiento> listaMovimientosYear;
	List<Movimiento> listaIngresosYear;
	List<Movimiento> listaGastosYear;
	float totalIngresosYear;
	float totalGastosYear;
	float beneficioYear;
	List<Movimiento> listaMovimientosMonth;
	List<Movimiento> listaIngresosMonth;
	List<Movimiento> listaGastosMonth;
	float totalIngresosMonth;
	float totalGastosMonth;
	float beneficioMonth;
	List<ClaseIngreso> listaClaseIngreso;
	List<ClaseGasto> listaClaseGasto;
	List<Float> listaClaseIngresoYear;
	List<Float> listaClaseIngresoMonth;
	List<Float> listaClaseGastoYear;
	List<Float> listaClaseGastoMonth;
	List<Cuenta> listaCuentas;
	
	
	List<Movimiento> listaMovimientosPersonalizado;
	List<Movimiento> listaIngresosPersonalizado;
	List<Movimiento> listaGastosPersonalizado;
	float totalIngresosPersonalizado;
	float totalGastosPersonalizado;
	float beneficioPersonalizado;
	List<Float> listaClaseIngresoPersonalizado;
	List<Float> listaClaseGastoPersonalizado;
	

	public static GestorAnalisisService getInstance() {
		if (gestorAnalisisService == null) {
			gestorAnalisisService = new GestorAnalisisService();
		}
		return gestorAnalisisService;
	}

	public void analisisEstandar() {

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

		System.out.println(fromYear);
		System.out.println(toYear);
		System.out.println(fromMonth);
		System.out.println(toMonth);

		// Evolucion year

		List<Timestamp> listaMesesFechasFrom = new ArrayList<Timestamp>();
		List<Timestamp> listaMesesFechasTo = new ArrayList<Timestamp>();
		int contador = 0;
		for (int i = 0; i < listaMeses.size(); i++) {
			Date date = null;
			try {
				date = format.parse(year + "-" + (i + 1) + "-01");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp from = new Timestamp(date.getTime());
			listaMesesFechasFrom.add(from);

			Date date2 = null;
			try {
				date2 = format.parse(year + "-" + (i + 1) + "-" + listaMesesUltimoDia.get(i));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Timestamp to = new Timestamp(date2.getTime());
			listaMesesFechasTo.add(to);
		}
		System.out.println("Meses fechas FROM:----------------");
		for (int i = 0; i < listaMesesFechasFrom.size(); i++) {
			System.out.println(listaMesesFechasFrom.get(i));
		}
		System.out.println("Meses fechas TO:----------------");
		for (int i = 0; i < listaMesesFechasTo.size(); i++) {
			System.out.println(listaMesesFechasTo.get(i));
		}
		List<Movimiento> listaMesesMovimientos = new ArrayList<Movimiento>();
		listaMesesIngresos = new ArrayList<Float>();
		listaMesesGastos = new ArrayList<Float>();
		listaMesesBeneficios = new ArrayList<Float>();
		for (int i = 0; i < listaMeses.size(); i++) {
			listaMesesIngresos.add((float) 0);
			listaMesesGastos.add((float) 0);
			listaMesesBeneficios.add((float) 0);
		}

		List<Movimiento> listaMovimientosEvolucionYear;
		for (int i = 0; i < listaMeses.size(); i++) {
			listaMovimientosEvolucionYear = Connect.getIDao().getGenerarAnalisisEstandar(listaMesesFechasFrom.get(i),
					listaMesesFechasTo.get(i));
			float totalIngresos = 0;
			float totalGastos = 0;
			for (int j = 0; j < listaMovimientosEvolucionYear.size(); j++) {
				if (listaMovimientosEvolucionYear.get(j).getTipo().equals("Ingreso")) {
					totalIngresos = totalIngresos + listaMovimientosEvolucionYear.get(j).getImporte();
				} else {
					totalGastos = totalGastos + listaMovimientosEvolucionYear.get(j).getImporte();
				}
			}
			listaMesesIngresos.set(i, totalIngresos);
			listaMesesGastos.set(i, totalGastos);
			listaMesesBeneficios.set(i, totalIngresos - totalGastos);
		}

		System.out.println("listaMesesIngresos :----------------");
		for (int i = 0; i < listaMesesIngresos.size(); i++) {
			System.out.println(listaMesesIngresos.get(i));
		}
		System.out.println("listaMesesGastos :----------------");
		for (int i = 0; i < listaMesesGastos.size(); i++) {
			System.out.println(listaMesesGastos.get(i));
		}
		System.out.println("listaMesesBeneficios :----------------");
		for (int i = 0; i < listaMesesBeneficios.size(); i++) {
			System.out.println(listaMesesBeneficios.get(i));
		}

		// request.setAttribute("listaMeses", listaMeses);
		// request.setAttribute("listaMesesIngresos", listaMesesIngresos);
		// request.setAttribute("listaMesesGastos", listaMesesGastos);
		// request.setAttribute("listaMesesBeneficios", listaMesesBeneficios);

		// Movimientos
		listaMovimientosYear = Connect.getIDao().getGenerarAnalisisEstandar(fromYear, toYear);
		// request.setAttribute("listaMovimientosYear", listaMovimientosYear);

		// Movimientos Year (Ingresos y Gastos)
		listaIngresosYear = new ArrayList<Movimiento>();
		listaGastosYear = new ArrayList<Movimiento>();
		for (int i = 0; i < listaMovimientosYear.size(); i++) {
			if (listaMovimientosYear.get(i).getTipo().equals("Ingreso")) {
				listaIngresosYear.add(listaMovimientosYear.get(i));
			} else {
				listaGastosYear.add(listaMovimientosYear.get(i));
			}
		}
		// request.setAttribute("listaIngresosYear", listaIngresosYear);
		// request.setAttribute("listaGastosYear", listaGastosYear);

		System.out.println("listaIngresosYear:" + listaIngresosYear);
		for (int i = 0; i < listaIngresosYear.size(); i++) {
			System.out.println(listaIngresosYear.get(i).getImporte());
		}
		System.out.println("-------------------------------------");
		// Total Ingresos Year
		totalIngresosYear = 0;
		for (int i = 0; i < listaIngresosYear.size(); i++) {
			totalIngresosYear = totalIngresosYear + listaIngresosYear.get(i).getImporte();
		}
		System.out.println("totalIngresosYear:" + totalIngresosYear);

		// Total Gastos Year
		totalGastosYear = 0;
		for (int i = 0; i < listaGastosYear.size(); i++) {
			totalGastosYear = totalGastosYear + listaGastosYear.get(i).getImporte();
		}
		System.out.println("totalGastosYear: " + totalGastosYear);

		// Beneficio Year
		beneficioYear = totalIngresosYear - totalGastosYear;
		System.out.println("beneficioYear: " + beneficioYear);

		// request.setAttribute("totalIngresosYear", totalIngresosYear);
		// request.setAttribute("totalGastosYear", totalGastosYear);
		// request.setAttribute("beneficioYear", beneficioYear);

		// Movimientos Month (Ingresos y Gastos)
		listaMovimientosMonth = Connect.getIDao().getGenerarAnalisisEstandar(fromMonth, toMonth);
		// request.setAttribute("listaMovimientosMonth", listaMovimientosMonth);

		listaIngresosMonth = new ArrayList<Movimiento>();
		listaGastosMonth = new ArrayList<Movimiento>();
		for (int i = 0; i < listaMovimientosMonth.size(); i++) {
			if (listaMovimientosMonth.get(i).getTipo().equals("Ingreso")) {
				listaIngresosMonth.add(listaMovimientosMonth.get(i));
			} else {
				listaGastosMonth.add(listaMovimientosMonth.get(i));
			}
		}
		// request.setAttribute("listaIngresosMonth", listaIngresosMonth);
		// request.setAttribute("listaGastosMonth", listaGastosMonth);

		// Total Ingresos Month
		totalIngresosMonth = 0;
		for (int i = 0; i < listaIngresosMonth.size(); i++) {
			totalIngresosMonth = totalIngresosMonth + listaIngresosMonth.get(i).getImporte();
		}
		// Total Gastos Month
		totalGastosMonth = 0;
		for (int i = 0; i < listaGastosMonth.size(); i++) {
			totalGastosMonth = totalGastosMonth + listaGastosMonth.get(i).getImporte();
		}
		// Beneficio Month
		beneficioMonth = totalIngresosMonth - totalGastosMonth;

		// request.setAttribute("totalIngresosMonth", totalIngresosMonth);
		// request.setAttribute("totalGastosMonth", totalGastosMonth);
		// request.setAttribute("beneficioMonth", beneficioMonth);

		// ClaseIngreso y ClaseGasto
		listaClaseIngreso = Connect.getIDao().getClaseIngreso();
		// request.setAttribute("listaClaseIngreso", listaClaseIngreso);
		System.out.println("listaClaseIngreso size:" + listaClaseIngreso.size());

		listaClaseGasto = Connect.getIDao().getClaseGasto();
		// request.setAttribute("listaClaseGasto", listaClaseGasto);

		// ClaseIngreso Year
		listaClaseIngresoYear = new ArrayList<Float>();
		for (int i = 0; i < listaClaseIngreso.size(); i++) {
			listaClaseIngresoYear.add((float) 0);
		}
		System.out.println("listaClaseIngresoYear size: " + listaIngresosYear.size());
		// System.out.println("listaClaseIngresoYear:" +listaIngresosYear);
		for (int i = 0; i < listaClaseIngresoYear.size(); i++) {
			System.out.println(listaClaseIngresoYear.get(i));
		}
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
		System.out.println(listaClaseIngresoYear.size());
		for (int i = 0; i < listaIngresosYear.size(); i++) {
			int puntero = (listaIngresosYear.get(i).getId_clase()) - 1;
			System.out.println("puntero: " + puntero);
			float total = 0;
			System.out.println("anterior: " + listaClaseIngresoYear.get(puntero));
			System.out.println("importe: " + listaIngresosYear.get(i).getImporte());
			total = listaClaseIngresoYear.get(puntero) + listaIngresosYear.get(i).getImporte();
			System.out.println("total: " + total);
			listaClaseIngresoYear.set(puntero, total);
			for (int j = 0; j < listaClaseIngresoYear.size(); j++) {
				System.out.println(listaClaseIngresoYear.get(j));
			}
		}
		// request.setAttribute("listaClaseIngresoYear", listaClaseIngresoYear);
		System.out.println("-------------");
		for (int i = 0; i < listaClaseIngresoYear.size(); i++) {
			System.out.println(listaClaseIngresoYear.get(i));
		}
		System.out.println("-------------");
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");

		// ClaseIngreso Month
		listaClaseIngresoMonth = new ArrayList<Float>();
		for (int i = 0; i < listaClaseIngreso.size(); i++) {
			listaClaseIngresoMonth.add((float) 0);
		}
		for (int i = 0; i < listaIngresosMonth.size(); i++) {
			int puntero = (listaIngresosMonth.get(i).getId_clase()) - 1;
			System.out.println("puntero: " + puntero);
			float total = 0;
			System.out.println("anterior: " + listaClaseIngresoMonth.get(puntero));
			System.out.println("importe: " + listaIngresosMonth.get(i).getImporte());
			total = listaClaseIngresoMonth.get(puntero) + listaIngresosMonth.get(i).getImporte();
			System.out.println("total: " + total);
			listaClaseIngresoMonth.set(puntero, total);
			for (int j = 0; j < listaClaseIngresoMonth.size(); j++) {
				System.out.println(listaClaseIngresoMonth.get(j));
			}
		}
		// request.setAttribute("listaClaseIngresoMonth",
		// listaClaseIngresoMonth);

		// ClaseGasto Year
		listaClaseGastoYear = new ArrayList<Float>();
		for (int i = 0; i < listaClaseGasto.size(); i++) {
			listaClaseGastoYear.add((float) 0);
		}
		for (int i = 0; i < listaGastosYear.size(); i++) {
			int puntero = (listaGastosYear.get(i).getId_clase()) - 1;
			System.out.println("puntero: " + puntero);
			float total = 0;
			System.out.println("anterior: " + listaClaseGastoYear.get(puntero));
			System.out.println("importe: " + listaGastosYear.get(i).getImporte());
			total = listaClaseGastoYear.get(puntero) + listaGastosYear.get(i).getImporte();
			System.out.println("total: " + total);
			listaClaseGastoYear.set(puntero, total);
			for (int j = 0; j < listaClaseGastoYear.size(); j++) {
				System.out.println(listaClaseGastoYear.get(j));
			}
		}
		// request.setAttribute("listaClaseGastoYear", listaClaseGastoYear);

		// ClaseGasto Month
		listaClaseGastoMonth = new ArrayList<Float>();
		for (int i = 0; i < listaClaseGasto.size(); i++) {
			listaClaseGastoMonth.add((float) 0);
		}
		for (int i = 0; i < listaGastosMonth.size(); i++) {
			int puntero = (listaGastosMonth.get(i).getId_clase()) - 1;
			System.out.println("puntero: " + puntero);
			float total = 0;
			System.out.println("anterior: " + listaClaseGastoMonth.get(puntero));
			System.out.println("importe: " + listaGastosMonth.get(i).getImporte());
			total = listaClaseGastoMonth.get(puntero) + listaGastosMonth.get(i).getImporte();
			System.out.println("total: " + total);
			listaClaseGastoMonth.set(puntero, total);
			for (int j = 0; j < listaClaseGastoMonth.size(); j++) {
				System.out.println(listaClaseGastoMonth.get(j));
			}
		}
		// request.setAttribute("listaClaseGastoMonth", listaClaseGastoMonth);

		// Cuentas
		listaCuentas = Connect.getIDao().getCuentas();
		// request.setAttribute("listaCuentas", listaCuentas);

		// request.getRequestDispatcher("/protected_area/analisisEstandar.jsp").forward(request,
		// response);
	}

	public void analisisPersonalizado(boolean filtroFecha,boolean filtroTipo,boolean filtroClase,boolean filtroUsuario,boolean filtroCuenta,String tipo,Timestamp fechaInicio,Timestamp fechaFin,int id_clase,String username,int id_cuenta) {
		
		analisisEstandar();
		
		System.out.println(fechaInicio);

		listaMovimientosPersonalizado = Connect.getIDao().getGenerarConsultaMovimientos(filtroFecha, false, false, filtroUsuario, filtroCuenta, null, fechaInicio, fechaFin, 0, username, id_cuenta);

//		request.setAttribute("listaMovimientosPersonalizado", listaMovimientosPersonalizado);

		System.out.println("----------------- Movimientos personalizado --------------");
		for (int i = 0; i < listaMovimientosPersonalizado.size(); i++) {
			System.out.println(listaMovimientosPersonalizado.get(i).getId_movimiento());
		}
		System.out.println("----------------- End Movimientos personalizado --------------");

		
		// hasta aqui

		listaIngresosPersonalizado = new ArrayList<Movimiento>();
		listaGastosPersonalizado = new ArrayList<Movimiento>();
		for (int i = 0; i < listaMovimientosPersonalizado.size(); i++) {
			if (listaMovimientosPersonalizado.get(i).getTipo().equals("Ingreso")) {
				listaIngresosPersonalizado.add(listaMovimientosPersonalizado.get(i));
			} else {
				listaGastosPersonalizado.add(listaMovimientosPersonalizado.get(i));
			}
		}
//		request.setAttribute("listaIngresosPersonalizado", listaIngresosPersonalizado);
//		request.setAttribute("listaGastosPersonalizado", listaGastosPersonalizado);

		System.out.println("----------------- listaIngresosPersonalizado --------------");
		for (int i = 0; i < listaIngresosPersonalizado.size(); i++) {
			System.out.println(listaIngresosPersonalizado.get(i).getId_movimiento());
		}
		System.out.println("----------------- End listaIngresosPersonalizado --------------");
		System.out.println("----------------- listaGastosPersonalizado --------------");
		for (int i = 0; i < listaGastosPersonalizado.size(); i++) {
			System.out.println(listaGastosPersonalizado.get(i).getId_movimiento());
		}
		System.out.println("----------------- End listaGastosPersonalizado --------------");

		// Total Ingresos Personalizado
		totalIngresosPersonalizado = 0;
		for (int i = 0; i < listaIngresosPersonalizado.size(); i++) {
			totalIngresosPersonalizado = totalIngresosPersonalizado + listaIngresosPersonalizado.get(i).getImporte();
		}
		// Total Gastos Personalizado
		totalGastosPersonalizado = 0;
		for (int i = 0; i < listaGastosPersonalizado.size(); i++) {
			totalGastosPersonalizado = totalGastosPersonalizado + listaGastosPersonalizado.get(i).getImporte();
		}
		// Beneficio Personalizado
		beneficioPersonalizado = totalIngresosPersonalizado - totalGastosPersonalizado;

		System.out.println("Ingresos personalizado " + totalIngresosPersonalizado);
		System.out.println("Gastos personalizado " + totalGastosPersonalizado);
		System.out.println("Beneficio personalizado " + beneficioPersonalizado);

//		request.setAttribute("totalIngresosPersonalizado", totalIngresosPersonalizado);
//		request.setAttribute("totalGastosPersonalizado", totalGastosPersonalizado);
//		request.setAttribute("beneficioPersonalizado", beneficioPersonalizado);

		// ClaseIngreso Personalizado
		listaClaseIngresoPersonalizado = new ArrayList<Float>();
		for (int i = 0; i < listaClaseIngreso.size(); i++) {
			listaClaseIngresoPersonalizado.add((float) 0);
		}
		for (int i = 0; i < listaIngresosPersonalizado.size(); i++) {
			int puntero = (listaIngresosPersonalizado.get(i).getId_clase()) - 1;
			System.out.println("puntero: " + puntero);
			float total = 0;
			// System.out.println("anterior:
			// "+listaClaseIngresoPersonalizado.get(puntero));
			// System.out.println("importe:
			// "+listaIngresosPersonalizado.get(i).getImporte());
			total = listaClaseIngresoPersonalizado.get(puntero) + listaIngresosPersonalizado.get(i).getImporte();
			// System.out.println("total: "+total);
			listaClaseIngresoPersonalizado.set(puntero, total);
			// for(int j=0;j<listaClaseIngresoPersonalizado.size();j++){
			// System.out.println(listaClaseIngresoPersonalizado.get(j));
			// }
		}
		for (int j = 0; j < listaClaseIngresoPersonalizado.size(); j++) {
			System.out.println(listaClaseIngresoPersonalizado.get(j));
		}
//		request.setAttribute("listaClaseIngresoPersonalizado", listaClaseIngresoPersonalizado);

		// ClaseGasto Personalizado
		listaClaseGastoPersonalizado = new ArrayList<Float>();
		for (int i = 0; i < listaClaseGasto.size(); i++) {
			listaClaseGastoPersonalizado.add((float) 0);
		}
		for (int i = 0; i < listaGastosPersonalizado.size(); i++) {
			int puntero = (listaGastosPersonalizado.get(i).getId_clase()) - 1;
			System.out.println("puntero: " + puntero);
			float total = 0;
			// System.out.println("anterior:
			// "+listaClaseGastoPersonalizado.get(puntero));
			// System.out.println("importe:
			// "+listaGastosPersonalizado.get(i).getImporte());
			total = listaClaseGastoPersonalizado.get(puntero) + listaGastosPersonalizado.get(i).getImporte();
			// System.out.println("total: "+total);
			listaClaseGastoPersonalizado.set(puntero, total);
			// for(int j=0;j<listaClaseGastoPersonalizado.size();j++){
			// System.out.println(listaClaseGastoPersonalizado.get(j));
			// }
		}
		for (int j = 0; j < listaClaseGastoPersonalizado.size(); j++) {
			System.out.println(listaClaseGastoPersonalizado.get(j));
		}
//		request.setAttribute("listaClaseGastoPersonalizado", listaClaseGastoPersonalizado);
		

	}

	public void clear() {

	}

	public List<String> getListaMeses() {
		return listaMeses;
	}

	public void setListaMeses(List<String> listaMeses) {
		this.listaMeses = listaMeses;
	}

	public List<Float> getListaMesesIngresos() {
		return listaMesesIngresos;
	}

	public void setListaMesesIngresos(List<Float> listaMesesIngresos) {
		this.listaMesesIngresos = listaMesesIngresos;
	}

	public List<Float> getListaMesesGastos() {
		return listaMesesGastos;
	}

	public void setListaMesesGastos(List<Float> listaMesesGastos) {
		this.listaMesesGastos = listaMesesGastos;
	}

	public List<Float> getListaMesesBeneficios() {
		return listaMesesBeneficios;
	}

	public void setListaMesesBeneficios(List<Float> listaMesesBeneficios) {
		this.listaMesesBeneficios = listaMesesBeneficios;
	}

	public List<Movimiento> getListaMovimientosYear() {
		return listaMovimientosYear;
	}

	public void setListaMovimientosYear(List<Movimiento> listaMovimientosYear) {
		this.listaMovimientosYear = listaMovimientosYear;
	}

	public List<Movimiento> getListaIngresosYear() {
		return listaIngresosYear;
	}

	public void setListaIngresosYear(List<Movimiento> listaIngresosYear) {
		this.listaIngresosYear = listaIngresosYear;
	}

	public List<Movimiento> getListaGastosYear() {
		return listaGastosYear;
	}

	public void setListaGastosYear(List<Movimiento> listaGastosYear) {
		this.listaGastosYear = listaGastosYear;
	}

	public float getTotalIngresosYear() {
		return totalIngresosYear;
	}

	public void setTotalIngresosYear(float totalIngresosYear) {
		this.totalIngresosYear = totalIngresosYear;
	}

	public float getTotalGastosYear() {
		return totalGastosYear;
	}

	public void setTotalGastosYear(float totalGastosYear) {
		this.totalGastosYear = totalGastosYear;
	}

	public float getBeneficioYear() {
		return beneficioYear;
	}

	public void setBeneficioYear(float beneficioYear) {
		this.beneficioYear = beneficioYear;
	}

	public List<Movimiento> getListaMovimientosMonth() {
		return listaMovimientosMonth;
	}

	public void setListaMovimientosMonth(List<Movimiento> listaMovimientosMonth) {
		this.listaMovimientosMonth = listaMovimientosMonth;
	}

	public List<Movimiento> getListaIngresosMonth() {
		return listaIngresosMonth;
	}

	public void setListaIngresosMonth(List<Movimiento> listaIngresosMonth) {
		this.listaIngresosMonth = listaIngresosMonth;
	}

	public List<Movimiento> getListaGastosMonth() {
		return listaGastosMonth;
	}

	public void setListaGastosMonth(List<Movimiento> listaGastosMonth) {
		this.listaGastosMonth = listaGastosMonth;
	}

	public float getTotalIngresosMonth() {
		return totalIngresosMonth;
	}

	public void setTotalIngresosMonth(float totalIngresosMonth) {
		this.totalIngresosMonth = totalIngresosMonth;
	}

	public float getTotalGastosMonth() {
		return totalGastosMonth;
	}

	public void setTotalGastosMonth(float totalGastosMonth) {
		this.totalGastosMonth = totalGastosMonth;
	}

	public float getBeneficioMonth() {
		return beneficioMonth;
	}

	public void setBeneficioMonth(float beneficioMonth) {
		this.beneficioMonth = beneficioMonth;
	}

	public List<ClaseIngreso> getListaClaseIngreso() {
		return listaClaseIngreso;
	}

	public void setListaClaseIngreso(List<ClaseIngreso> listaClaseIngreso) {
		this.listaClaseIngreso = listaClaseIngreso;
	}

	public List<ClaseGasto> getListaClaseGasto() {
		return listaClaseGasto;
	}

	public void setListaClaseGasto(List<ClaseGasto> listaClaseGasto) {
		this.listaClaseGasto = listaClaseGasto;
	}

	public List<Float> getListaClaseIngresoYear() {
		return listaClaseIngresoYear;
	}

	public void setListaClaseIngresoYear(List<Float> listaClaseIngresoYear) {
		this.listaClaseIngresoYear = listaClaseIngresoYear;
	}

	public List<Float> getListaClaseIngresoMonth() {
		return listaClaseIngresoMonth;
	}

	public void setListaClaseIngresoMonth(List<Float> listaClaseIngresoMonth) {
		this.listaClaseIngresoMonth = listaClaseIngresoMonth;
	}

	public List<Float> getListaClaseGastoYear() {
		return listaClaseGastoYear;
	}

	public void setListaClaseGastoYear(List<Float> listaClaseGastoYear) {
		this.listaClaseGastoYear = listaClaseGastoYear;
	}

	public List<Float> getListaClaseGastoMonth() {
		return listaClaseGastoMonth;
	}

	public void setListaClaseGastoMonth(List<Float> listaClaseGastoMonth) {
		this.listaClaseGastoMonth = listaClaseGastoMonth;
	}

	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public List<Integer> getListaMesesUltimoDia() {
		return listaMesesUltimoDia;
	}

	public void setListaMesesUltimoDia(List<Integer> listaMesesUltimoDia) {
		this.listaMesesUltimoDia = listaMesesUltimoDia;
	}

	public List<Movimiento> getListaMovimientosPersonalizado() {
		return listaMovimientosPersonalizado;
	}

	public void setListaMovimientosPersonalizado(List<Movimiento> listaMovimientosPersonalizado) {
		this.listaMovimientosPersonalizado = listaMovimientosPersonalizado;
	}

	public List<Movimiento> getListaIngresosPersonalizado() {
		return listaIngresosPersonalizado;
	}

	public void setListaIngresosPersonalizado(List<Movimiento> listaIngresosPersonalizado) {
		this.listaIngresosPersonalizado = listaIngresosPersonalizado;
	}

	public List<Movimiento> getListaGastosPersonalizado() {
		return listaGastosPersonalizado;
	}

	public void setListaGastosPersonalizado(List<Movimiento> listaGastosPersonalizado) {
		this.listaGastosPersonalizado = listaGastosPersonalizado;
	}

	public float getTotalIngresosPersonalizado() {
		return totalIngresosPersonalizado;
	}

	public void setTotalIngresosPersonalizado(float totalIngresosPersonalizado) {
		this.totalIngresosPersonalizado = totalIngresosPersonalizado;
	}

	public float getTotalGastosPersonalizado() {
		return totalGastosPersonalizado;
	}

	public void setTotalGastosPersonalizado(float totalGastosPersonalizado) {
		this.totalGastosPersonalizado = totalGastosPersonalizado;
	}

	public float getBeneficioPersonalizado() {
		return beneficioPersonalizado;
	}

	public void setBeneficioPersonalizado(float beneficioPersonalizado) {
		this.beneficioPersonalizado = beneficioPersonalizado;
	}

	public List<Float> getListaClaseIngresoPersonalizado() {
		return listaClaseIngresoPersonalizado;
	}

	public void setListaClaseIngresoPersonalizado(List<Float> listaClaseIngresoPersonalizado) {
		this.listaClaseIngresoPersonalizado = listaClaseIngresoPersonalizado;
	}

	public List<Float> getListaClaseGastoPersonalizado() {
		return listaClaseGastoPersonalizado;
	}

	public void setListaClaseGastoPersonalizado(List<Float> listaClaseGastoPersonalizado) {
		this.listaClaseGastoPersonalizado = listaClaseGastoPersonalizado;
	}

}
