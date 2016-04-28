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
 * Servlet implementation class loadPersonalizarAlertas
 */
@WebServlet("/protected_area/loadPersonalizarAlertas")
public class LoadPersonalizarAlertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadPersonalizarAlertas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		System.out.println(GestorAlertasService.getInstance().getAlerta(request.getUserPrincipal().getName()).getUsername());
		request.setAttribute("alerta", GestorAlertasService.getInstance().getAlerta(request.getUserPrincipal().getName()));
		request.getRequestDispatcher("/protected_area/personalizarAlertas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
