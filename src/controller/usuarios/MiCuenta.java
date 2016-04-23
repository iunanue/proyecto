package controller.usuarios;

import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import model.business.GestorUsuariosService;
import model.classes.Usuario;
import model.data.Connect;

/**
 * Servlet implementation class MiCuenta
 */
@WebServlet("/protected_area/miCuenta")
public class MiCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connect c = new Connect();
	
	String username;
	String mail;
	String password;
	String password2;
	String mensaje = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MiCuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getUserPrincipal().getName();
		Usuario usuario = GestorUsuariosService.getInstance().getUsuario(username);
		request.setAttribute("usuario", usuario);

	
		request.getRequestDispatcher("/protected_area/miCuenta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}


}
