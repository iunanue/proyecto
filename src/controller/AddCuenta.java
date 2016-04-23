package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.classes.Cuenta;
import model.classes.Movimiento;
import model.data.Connect;

/**
 * Servlet implementation class AddCuenta
 */
@WebServlet("/protected_area/addCuenta")
public class AddCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connect c = new Connect();
	
	float saldo;
	String descripcion;
	
	String mensaje = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		if (checkForm(request, response))
		{
			saldo = Float.parseFloat(request.getParameter("saldo"));
			descripcion = request.getParameter("descripcion");
			
			Cuenta cuenta = new Cuenta(saldo,descripcion);
			c.getIDao().addCuenta(cuenta);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/nuevaCuenta.jsp").forward(request, response);
		}
	}
	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getParameter("saldo").equals(""))||(request.getParameter("descripcion").equals(""))) {
			mensaje = "Rellene todos los campos por favor.";
			System.out.println(mensaje);
			return false;
		}
		else{
			return true;
		}

	}

}
