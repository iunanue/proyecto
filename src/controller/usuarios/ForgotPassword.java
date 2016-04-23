package controller.usuarios;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import model.business.GestorUsuariosService;
import model.classes.Usuario;
import model.data.Connect;
import model.data.Dao;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Usuario usuario;
	String username;
	String mail;
	String mensaje = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPassword() {
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
		
		if (checkForm(request, response)) 
		{
			usuario = GestorUsuariosService.getInstance().getUsuario(username);
			if(usuario != null)
			{
				if(usuario.getMail().equals(mail))
				{
					System.out.println("CAMBIO CORRECTO");
					GestorUsuariosService.getInstance().forgotPassword(username, mail);	
					request.getRequestDispatcher("index.jsp").forward(request, response);
					
				}
				else
				{
					mensaje = "El mail escrito no corresponde con el del usuario";
					request.getSession().setAttribute("mensaje", mensaje);
					request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
				}
			}
			else
			{
				mensaje = "El usuario no existe en la Base de Datos";
				request.getSession().setAttribute("mensaje", mensaje);
				request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
			}
		} 
		else {
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
		}
	}

	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getParameter("username").equals("")) || (request.getParameter("mail").equals(""))) {
			mensaje = "Rellene todos los campos por favor.";
			return false;
		} else {
			return true;
		}
	}
	
}

// if (checkForm(request, response)) {
//
// dao.getRegister().addUser(request.getParameter("username"),
// request.getParameter("mail"),
// request.getParameter("password"));
// mensaje = null;
// request.getSession().setAttribute("mensaje", mensaje);
// request.getRequestDispatcher("login.jsp").forward(request, response);
// } else {
// request.getSession().setAttribute("mensaje", mensaje);
// request.getRequestDispatcher("register.jsp").forward(request, response);
// }
