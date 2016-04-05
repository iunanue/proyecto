package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Cuenta;
import classes.Movimiento;
import model.Connect;

/**
 * Servlet implementation class UpdateCuenta
 */
@WebServlet("/protected_area/updateCuenta")
public class UpdateCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connect c = new Connect();
	
	Cuenta cuenta;
	
	int id_cuenta;
	String descripcion;
	float saldo;
	
	
	String mensaje = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCuenta() {
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
		this.id_cuenta =  Integer.parseInt(request.getParameter("id_cuenta"));
		if (checkForm(request, response))
		{
			this.saldo = Float.parseFloat(request.getParameter("saldo"));
			this.descripcion = request.getParameter("descripcion");
			
			cuenta = c.getIDao().getCuenta(id_cuenta);
			cuenta.setSaldo(saldo);
			cuenta.setDescripcion(descripcion);
		
			c.getIDao().updateCuenta(cuenta);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/loadUpdateCuenta").forward(request, response);
		}
	}
	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getParameter("descripcion").equals(""))||(request.getParameter("saldo").equals(""))) {
			mensaje = "Rellene todos los campos por favor.";
			System.out.println(mensaje);
			return false;
		}
		else{
			return true;
		}

	}

}
