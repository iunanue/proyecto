package controller;

import java.io.IOException;
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
 * Servlet implementation class LoadVerConsultaMovimientos
 */
@WebServlet("/protected_area/verConsultaMovimientos")
public class VerConsultaMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connect c = new Connect();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerConsultaMovimientos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Movimiento> listaMovimientos = (List<Movimiento>) request.getAttribute("listaMovimientos");
		request.setAttribute("listaMovimientos", listaMovimientos);
		
		List <ClaseIngreso> listaClaseIngreso = c.getIDao().getClaseIngreso();
		request.setAttribute("listaClaseIngreso", listaClaseIngreso);
		
		List <ClaseGasto> listaClaseGasto = c.getIDao().getClaseGasto();
		request.setAttribute("listaClaseGasto", listaClaseGasto);
		
		List <Cuenta> listaCuentas = c.getIDao().getCuentas();
		request.setAttribute("listaCuentas", listaCuentas);
		
		request.getRequestDispatcher("/protected_area/verMovimientos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}