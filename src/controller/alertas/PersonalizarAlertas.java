package controller.alertas;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.GestorAlertasService;
import model.business.GestorMovimientosService;

/**
 * Servlet implementation class PersonalizarAlertas
 */
@WebServlet("/protected_area/personalizarAlertas")
public class PersonalizarAlertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String username;
	private float alimentacionMonth;
	private float estudiosMonth;
	private float suministrosMonth;
	private float mantenimientoMonth;
	private float hipotecaAlquilerMonth;
	private float segurosMonth;
	private float ocioMonth;
	private float otrosMonth;
	private float alimentacionYear;
	private float estudiosYear;
	private float suministrosYear;
	private float mantenimientoYear;
	private float hipotecaAlquilerYear;
	private float segurosYear;
	private float ocioYear;
	private float otrosYear;
	
	String mensaje = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalizarAlertas() {
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
		
		if(checkForm(request, response)){
			this.username = request.getUserPrincipal().getName();

			this.alimentacionMonth = Float.parseFloat(request.getParameter("alimentacionMonth"));

			this.estudiosMonth = Float.parseFloat(request.getParameter("estudiosMonth"));

			this.suministrosMonth = Float.parseFloat(request.getParameter("suministrosMonth"));

			this.mantenimientoMonth = Float.parseFloat(request.getParameter("mantenimientoMonth"));

			this.hipotecaAlquilerMonth = Float.parseFloat(request.getParameter("hipotecaAlquilerMonth"));

			this.segurosMonth = Float.parseFloat(request.getParameter("segurosMonth"));

			this.ocioMonth = Float.parseFloat(request.getParameter("ocioMonth"));

			this.otrosMonth = Float.parseFloat(request.getParameter("otrosMonth"));

			this.alimentacionYear = Float.parseFloat(request.getParameter("alimentacionYear"));

			this.estudiosYear = Float.parseFloat(request.getParameter("estudiosYear"));

			this.suministrosYear = Float.parseFloat(request.getParameter("suministrosYear"));

			this.mantenimientoYear = Float.parseFloat(request.getParameter("mantenimientoYear"));

			this.hipotecaAlquilerYear = Float.parseFloat(request.getParameter("hipotecaAlquilerYear"));

			this.segurosYear = Float.parseFloat(request.getParameter("segurosYear"));

			this.ocioYear = Float.parseFloat(request.getParameter("ocioYear"));

			this.otrosYear = Float.parseFloat(request.getParameter("otrosYear"));
			
			GestorAlertasService.getInstance().updateAlerta(username, alimentacionMonth, estudiosMonth, suministrosMonth, mantenimientoMonth, hipotecaAlquilerMonth, segurosMonth, ocioMonth, otrosMonth, alimentacionYear, estudiosYear, suministrosYear, mantenimientoYear, hipotecaAlquilerYear, segurosYear, ocioYear, otrosYear);;
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/personalizarAlertas.jsp").forward(request, response);
		}
		
		
		
	}
	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getParameter("alimentacionMonth").equals(""))||
			(request.getParameter("estudiosMonth").equals(""))||
			(request.getParameter("suministrosMonth").equals(""))||
			(request.getParameter("mantenimientoMonth").equals(""))||
			(request.getParameter("hipotecaAlquilerMonth").equals(""))||
			(request.getParameter("segurosMonth").equals(""))||
			(request.getParameter("ocioMonth").equals(""))||
			(request.getParameter("otrosMonth").equals(""))||
			(request.getParameter("alimentacionYear").equals(""))||
			(request.getParameter("estudiosYear").equals(""))||
			(request.getParameter("suministrosYear").equals(""))||
			(request.getParameter("mantenimientoYear").equals(""))||
			(request.getParameter("hipotecaAlquilerYear").equals(""))||
			(request.getParameter("segurosYear").equals(""))||
			(request.getParameter("ocioYear").equals(""))||
			(request.getParameter("otrosYear").equals(""))) {
			mensaje = "Rellene todos los campos por favor.";
			return false;
		}
		else{
			return true;
		}

	}

}
