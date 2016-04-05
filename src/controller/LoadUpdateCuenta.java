package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Cuenta;
import classes.Movimiento;
import model.Connect;

/**
 * Servlet implementation class LoadUpdateCuenta
 */
@WebServlet("/protected_area/loadUpdateCuenta")
public class LoadUpdateCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
Connect c = new Connect();
	
	Cuenta cuenta;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadUpdateCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cuenta = (Cuenta) request.getAttribute("cuenta");
		request.getRequestDispatcher("/protected_area/updateCuenta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
