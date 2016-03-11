package controller;

import model.Connect;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import classes.Usuario;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
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
	public Register() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		username = request.getParameter("username");
		mail = request.getParameter("mail");
		password = request.getParameter("password");
		password2 = request.getParameter("password2");
		
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("mail"));
		System.out.println(request.getParameter("password"));
		
		
		if (checkForm(request, response))
		{
			if(c.getDao().existsUsuario(username) == false)
			{
				System.out.println("no existe");
				Usuario usuario = new Usuario(username,mail,password);
				c.getDao().addUsuario(usuario);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else
			{
				System.out.println("existe");
				mensaje = "El usuario ya existe en la Base de Datos";
				request.getSession().setAttribute("mensaje", mensaje);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
		else
		{
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		

	}

	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if ((username.equals("")) || (mail.equals("")) || (password.equals("")) || (password2.equals(""))) {
			mensaje = "Rellene todos los campos por favor.";
			System.out.println(mensaje);
			return false;
		} else {
			if (!password.equals(password2)) {
				mensaje = "Las contraseñas deben coincidir.";
				System.out.println(mensaje);
				return false;
			} else {
				if (!EmailValidator.getInstance().isValid(mail)) {
					mensaje = "El email no es válido";
					System.out.println(mensaje);
					return false;
				} else {
					return true;
				}
			}

		}

	}

}
