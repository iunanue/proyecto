package controller.analisis;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.GestorAnalisisService;
import model.business.GestorCuentasService;
import model.business.GestorMovimientosService;
import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Movimiento;
import model.data.Connect;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GestorAnalisisService.getInstance().analisisEstandar();
		request.setAttribute("listaMeses", GestorAnalisisService.getInstance().getListaMeses());
		request.setAttribute("listaMesesIngresos", GestorAnalisisService.getInstance().getListaMesesIngresos());
		request.setAttribute("listaMesesGastos", GestorAnalisisService.getInstance().getListaMesesGastos());
		request.setAttribute("listaMesesBeneficios", GestorAnalisisService.getInstance().getListaMesesBeneficios());
		request.setAttribute("listaMovimientosYear", GestorAnalisisService.getInstance().getListaMovimientosYear());
		request.setAttribute("listaIngresosYear", GestorAnalisisService.getInstance().getListaIngresosYear());
		request.setAttribute("listaGastosYear", GestorAnalisisService.getInstance().getListaGastosYear());
		request.setAttribute("totalIngresosYear", GestorAnalisisService.getInstance().getTotalIngresosYear());
		request.setAttribute("totalGastosYear", GestorAnalisisService.getInstance().getTotalGastosYear());
		request.setAttribute("beneficioYear", GestorAnalisisService.getInstance().getBeneficioYear());
		request.setAttribute("listaMovimientosMonth", GestorAnalisisService.getInstance().getListaMovimientosMonth());
		request.setAttribute("listaIngresosMonth", GestorAnalisisService.getInstance().getListaIngresosMonth());
		request.setAttribute("listaGastosMonth", GestorAnalisisService.getInstance().getListaGastosMonth());
		request.setAttribute("totalIngresosMonth", GestorAnalisisService.getInstance().getTotalIngresosMonth());
		request.setAttribute("totalGastosMonth", GestorAnalisisService.getInstance().getTotalGastosMonth());
		request.setAttribute("beneficioMonth", GestorAnalisisService.getInstance().getBeneficioMonth());
		request.setAttribute("listaClaseIngreso", GestorMovimientosService.getInstance().getClaseIngreso());
		request.setAttribute("listaClaseGasto", GestorMovimientosService.getInstance().getClaseGasto());
		request.setAttribute("listaClaseIngresoYear", GestorAnalisisService.getInstance().getListaClaseIngresoYear());
		request.setAttribute("listaClaseIngresoMonth", GestorAnalisisService.getInstance().getListaClaseIngresoMonth());
		request.setAttribute("listaClaseGastoYear", GestorAnalisisService.getInstance().getListaClaseGastoYear());
		request.setAttribute("listaClaseGastoMonth", GestorAnalisisService.getInstance().getListaClaseGastoMonth());
		request.setAttribute("listaCuentas", GestorCuentasService.getInstance().getCuentas());
		
		request.getRequestDispatcher("/protected_area/analisisEstandar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
