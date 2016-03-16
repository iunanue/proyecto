package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import classes.Usuario;
import model.Connect;

/**
 * Servlet implementation class updateUsuario
 */
@WebServlet("/protected_area/updateDeleteUsuario")
public class UpdateDeleteUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
Connect c = new Connect();
	
	Usuario usuario;
	String username;
	String mail;
	String password;
	String password2;
	String mensaje = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDeleteUsuario() {
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
		username = request.getParameter("username");
		mail = request.getParameter("mail");
		password = request.getParameter("password");
		password2 = request.getParameter("password2");
		
		usuario = new Usuario(username,mail,password);
		
		System.out.println("llega servlet");
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
					
		if (checkForm(request, response))
		{
			c.getIDao().updateUsuario(usuario);
			request.getRequestDispatcher("index.jsp").forward(request, response);	
		}
		else
		{
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/miCuenta").forward(request, response);
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (checkForm(request, response))
		{
			c.getIDao().deleteUsuario(usuario);
			request.getRequestDispatcher("/Logout").forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/miCuenta").forward(request, response);
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
