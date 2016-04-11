package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.ClaseGasto;
import classes.ClaseIngreso;
import classes.Cuenta;
import classes.Movimiento;
import model.Connect;

/**
 * Servlet implementation class AnalisisEstandar
 */
@WebServlet("/protected_area/analisisEstandar")
public class AnalisisEstandar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connect c = new Connect();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalisisEstandar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Timestamp fromYear;	
		Timestamp toYear;	
		
		
		Timestamp fromMonth;	
		Timestamp toMonth;	
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		//fromYear toYear
		
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
		
		
		Date dateFromYear = null;
		try {
			dateFromYear = format.parse(year+"-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fromYear = new Timestamp(dateFromYear.getTime());
		
		Date dateToYear = null;
		try {
			dateToYear = format.parse(year+"-12-31");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toYear = new Timestamp(dateToYear.getTime());
		
		
		
		int month = cal.get(Calendar.MONTH) + 1;
		

		Date dateFromMonth = null;
		try {
			if(month>9){
				dateFromMonth = format.parse(year+"-"+month+"-01");
			}
			else
			{
				dateFromMonth = format.parse(year+"-0"+month+"-01");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fromMonth = new Timestamp(dateFromMonth.getTime());
		
		Calendar cal2 = Calendar.getInstance();
		
		cal2.add(Calendar.MONTH, month-1);  
		cal2.set(Calendar.DAY_OF_MONTH, 1);  
		cal2.add(Calendar.DATE, -1);  
		int lastDay = cal2.get(Calendar.DAY_OF_MONTH);
		
		
		
		Date dateToMonth = null;
		try {
			if(month>9){
				dateToMonth = format.parse(year+"-"+month+"-01");
			}
			else
			{
				dateToMonth = format.parse(year+"-0"+month+"-"+lastDay);
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
		
		//Movimientos
		List <Movimiento> listaMovimientosYear = c.getIDao().getGenerarAnalisisEstandar(fromYear, toYear);
		request.setAttribute("listaMovimientosYear", listaMovimientosYear);
		
		//Movimientos Year (Ingresos y Gastos) 
		List <Movimiento> listaIngresosYear = new ArrayList<Movimiento>();
		List <Movimiento> listaGastosYear = new ArrayList<Movimiento>();
		for(int i=0;i<listaMovimientosYear.size();i++){
			if(listaMovimientosYear.get(i).getTipo().equals("Ingreso")){
				listaIngresosYear.add(listaMovimientosYear.get(i));
			}
			else{
				listaGastosYear.add(listaMovimientosYear.get(i));
			}
		}
		request.setAttribute("listaIngresosYear", listaIngresosYear);
		request.setAttribute("listaGastosYear", listaGastosYear);
		
		//Total Ingresos Year
		float totalIngresosYear = 0;
		for(int i=0;i<listaIngresosYear.size();i++){
			totalIngresosYear = totalIngresosYear + listaIngresosYear.get(i).getImporte();
		}
		//Total Gastos Year
		float totalGastosYear = 0;
		for(int i=0;i<listaGastosYear.size();i++){
			totalGastosYear = totalGastosYear + listaGastosYear.get(i).getImporte();
		}
		//Beneficio Year
		float beneficioYear = totalIngresosYear-totalGastosYear;
	
		
		
		
		//Movimientos Month (Ingresos y Gastos) 
		List <Movimiento> listaMovimientosMonth = c.getIDao().getGenerarAnalisisEstandar(fromMonth, toMonth);
		request.setAttribute("listaMovimientosMonth", listaMovimientosMonth);
		
		List <Movimiento> listaIngresosMonth = new ArrayList<Movimiento>();
		List <Movimiento> listaGastosMonth = new ArrayList<Movimiento>();
		for(int i=0;i<listaMovimientosMonth.size();i++){
			if(listaMovimientosMonth.get(i).getTipo().equals("Ingreso")){
				listaIngresosMonth.add(listaMovimientosMonth.get(i));
			}
			else{
				listaGastosMonth.add(listaMovimientosMonth.get(i));
			}
		}
		request.setAttribute("listaIngresosMonth", listaIngresosMonth);
		request.setAttribute("listaGastosMonth", listaGastosMonth);
		
		
		
		//Total Ingresos Month
		float totalIngresosMonth = 0;
		for(int i=0;i<listaIngresosMonth.size();i++){
			totalIngresosMonth = totalIngresosMonth + listaIngresosMonth.get(i).getImporte();
		}
		//Total Gastos Month
		float totalGastosMonth = 0;
		for(int i=0;i<listaGastosMonth.size();i++){
			totalGastosMonth = totalGastosMonth + listaGastosMonth.get(i).getImporte();
		}
		//Beneficio Month
		float beneficioMonth = totalIngresosMonth-totalGastosMonth;
		
		
		//ClaseIngreso y ClaseGasto
		List <ClaseIngreso> listaClaseIngreso = c.getIDao().getClaseIngreso();
		request.setAttribute("listaClaseIngreso", listaClaseIngreso);
		
		List <ClaseGasto> listaClaseGasto = c.getIDao().getClaseGasto();
		request.setAttribute("listaClaseGasto", listaClaseGasto);
		
		//ClaseIngreso Year
		List <Float> listaClaseIngresoYear = new ArrayList<Float>();
		for(int i=0;i<listaIngresosYear.size();i++){
			int puntero=listaIngresosYear.get(i).getId_clase()-1;
			
			listaClaseIngresoYear.add(puntero,listaClaseIngresoYear.get(puntero)+listaIngresosYear.get(i).getImporte());
		}
		request.setAttribute("listaClaseIngresoYear", listaClaseIngresoYear);
		
		//ClaseIngreso Month
		List <Float> listaClaseIngresoMonth = new ArrayList<Float>();
		for(int i=0;i<listaIngresosMonth.size();i++){
			int puntero=listaIngresosMonth.get(i).getId_clase()-1;
			
			listaClaseIngresoMonth.add(puntero,listaClaseIngresoMonth.get(puntero)+listaIngresosMonth.get(i).getImporte());
		}
		request.setAttribute("listaClaseIngresoMonth", listaClaseIngresoMonth);
		
		//ClaseGasto Year
		List <Float> listaClaseGastoYear = new ArrayList<Float>();
		for(int i=0;i<listaGastosYear.size();i++){
			int puntero=listaGastosYear.get(i).getId_clase()-1;
			
			listaClaseGastoYear.add(puntero,listaClaseGastoYear.get(puntero)+listaGastosYear.get(i).getImporte());
		}
		request.setAttribute("listaClaseGastoYear", listaClaseGastoYear);
		
		//ClaseGasto Year
		List <Float> listaClaseGastoMonth = new ArrayList<Float>();
		for(int i=0;i<listaGastosMonth.size();i++){
			int puntero=listaGastosMonth.get(i).getId_clase()-1;
			
			listaClaseGastoMonth.add(puntero,listaClaseGastoMonth.get(puntero)+listaGastosMonth.get(i).getImporte());
		}
		request.setAttribute("listaClaseGastoMonth", listaClaseGastoMonth);
		
		//Cuentas
		List <Cuenta> listaCuentas = c.getIDao().getCuentas();
		request.setAttribute("listaCuentas", listaCuentas);
		
		request.getRequestDispatcher("/protected_area/analisisEstandar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
