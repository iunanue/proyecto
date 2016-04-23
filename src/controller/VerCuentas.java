package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.classes.Cuenta;
import model.data.Connect;

/**
 * Servlet implementation class VerCuentas
 */
@WebServlet("/protected_area/verCuentas")
public class VerCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connect c = new Connect();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List <Cuenta> listaCuentas = c.getIDao().getCuentas();
		System.out.println(listaCuentas.get(0).getSaldo());
		System.out.println(listaCuentas.get(1).getSaldo());
		request.setAttribute("listaCuentas", listaCuentas);
		request.getRequestDispatcher("/protected_area/verCuentas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
