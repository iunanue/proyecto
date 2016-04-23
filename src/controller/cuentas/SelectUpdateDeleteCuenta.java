package controller.cuentas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.GestorCuentasService;
import model.classes.Cuenta;
import model.data.Connect;

/**
 * Servlet implementation class SelectUpdateDeleteCuenta
 */
@WebServlet("/protected_area/selectUpdateDeleteCuenta")
public class SelectUpdateDeleteCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connect c = new Connect();
	int id_cuenta;
	
	Cuenta cuenta;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUpdateDeleteCuenta() {
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
				id_cuenta =  Integer.parseInt(request.getParameter("id_cuenta"));
				cuenta = GestorCuentasService.getInstance().getCuenta(id_cuenta);
				
				if (request.getParameter("update") != null) {
					System.out.println("llega update");
					update(request,response);

				} else if (request.getParameter("delete") != null) {
					System.out.println("llega delete");
					delete(request,response);

				}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("cuenta", cuenta);
		request.getRequestDispatcher("/protected_area/loadUpdateCuenta").forward(request, response);
		
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		GestorCuentasService.getInstance().deleteCuenta(cuenta);	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
