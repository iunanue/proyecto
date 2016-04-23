package controller.movimientos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.GestorMovimientosService;
import model.classes.Movimiento;
import model.data.Connect;

/**
 * Servlet implementation class VerUpdateMovimiento
 */
@WebServlet("/protected_area/selectUpdateDeleteMovimiento")
public class SelectUpdateDeleteMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connect c = new Connect();
	int id_movimiento;
	
	Movimiento movimiento;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUpdateDeleteMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		id_movimiento =  Integer.parseInt(request.getParameter("id_movimiento"));
		movimiento = GestorMovimientosService.getInstance().getMovimiento(id_movimiento);
		
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
		request.setAttribute("movimiento", movimiento);
		request.getRequestDispatcher("/protected_area/loadUpdateMovimiento").forward(request, response);
		
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		GestorMovimientosService.getInstance().deleteMovimiento(movimiento);	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
