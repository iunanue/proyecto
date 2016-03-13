package controller;

import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import classes.Usuario;
import model.Connect;

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
//		System.out.println(request.getUserPrincipal().getName());
		Usuario usuario = c.getIDao().getUsuario(username);
		request.setAttribute("usuario", usuario);
//		System.out.println(usuario.getUsername());
//		System.out.println(usuario.getMail());
//		System.out.println(usuario.getPassword());
	
		request.getRequestDispatcher("/protected_area/miCuenta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
//		
//		username = request.getParameter("username");
//		mail = request.getParameter("mail");
//		password = request.getParameter("password");
//		password2 = request.getParameter("password2");
//		Usuario usuario2 = new Usuario(username,mail,password);
//		if (checkForm(request, response))
//		{
//			c.getDao().updateUser(usuario2);
//			request.getRequestDispatcher("index.jsp").forward(request, response);	
//		}
//		else
//		{
//			request.getSession().setAttribute("mensaje", mensaje);
//			request.getRequestDispatcher("/protected_area/miCuenta.jsp").forward(request, response);
//		}

	}
//	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
//		if ((username.equals("")) || (mail.equals("")) || (password.equals("")) || (password2.equals(""))) {
//			mensaje = "Rellene todos los campos por favor.";
//			System.out.println(mensaje);
//			return false;
//		} else {
//			if (!password.equals(password2)) {
//				mensaje = "Las contraseñas deben coincidir.";
//				System.out.println(mensaje);
//				return false;
//			} else {
//				if (!EmailValidator.getInstance().isValid(mail)) {
//					mensaje = "El email no es válido";
//					System.out.println(mensaje);
//					return false;
//				} else {
//					return true;
//				}
//			}
//
//		}
//
//	}

}
