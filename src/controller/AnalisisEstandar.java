package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		
		
//		List <Movimiento> listaMovimientos = c.getIDao().getMovimientos();
//		request.setAttribute("listaMovimientos", listaMovimientos);
//		List <ClaseIngreso> listaClaseIngreso = c.getIDao().getClaseIngreso();
//		request.setAttribute("listaClaseIngreso", listaClaseIngreso);
//		List <ClaseGasto> listaClaseGasto = c.getIDao().getClaseGasto();
//		request.setAttribute("listaClaseGasto", listaClaseGasto);
//		List <Cuenta> listaCuentas = c.getIDao().getCuentas();
//		request.setAttribute("listaCuentas", listaCuentas);
		
//		request.getRequestDispatcher("/protected_area/analisisEstandar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
