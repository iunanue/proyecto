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
 * Servlet implementation class verMovimientos
 */
@WebServlet("/protected_area/verMovimientos")
public class verMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Connect c = new Connect();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verMovimientos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List <Movimiento> listaMovimientos = c.getDao().getMovimientos();
		request.setAttribute("listaMovimientos", listaMovimientos);
		List <ClaseIngreso> listaClaseIngreso = c.getDao().getClaseIngreso();
		request.setAttribute("listaClaseIngreso", listaClaseIngreso);
		List <ClaseGasto> listaClaseGasto = c.getDao().getClaseGasto();
		request.setAttribute("listaClaseGasto", listaClaseGasto);
		List <Cuenta> listaCuentas = c.getDao().getCuentas();
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
