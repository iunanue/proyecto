package controller.alertas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.GestorAlertasService;
import model.business.GestorAnalisisService;

/**
 * Servlet implementation class VerAlertas
 */
@WebServlet("/protected_area/verAlertas")
public class VerAlertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerAlertas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GestorAlertasService.getInstance().getAlertas(request.getUserPrincipal().getName());
		request.setAttribute("alerta", GestorAlertasService.getInstance().getAlerta(request.getUserPrincipal().getName()));
		request.setAttribute("listaAlertasYear", GestorAlertasService.getInstance().getListaAlertasYear());
		request.setAttribute("listaAlertasMonth", GestorAlertasService.getInstance().getListaAlertasMonth());
		
		System.out.println("a ver: " +GestorAlertasService.getInstance().getListaAlertasYear().get(1));
		request.getRequestDispatcher("/protected_area/verAlertas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
